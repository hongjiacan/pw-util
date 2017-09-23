package com.pw.util.excel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by PoemWhite on 2017/9/14.
 */
public class PwExcelUtil {

    public static Log logger = LogFactory.getLog(PwExcelUtil.class);

    private ExcelDownloadManager manager = ExcelDownloadManager.getInstance();
    private String excelFile;
    private String excelFileName;
    private Class<?> clazz;
    private List<String[]> headerColumnsList;
    private List<String[]> headerNamesList;
    private int[] columnTypes;
    private List<int[]> colspansList;
    private SheetStyle sheetStyle;//表格样式
    private int totalRecord;
    private WriteExcel webWriteExcel;

    public PwExcelUtil(String excelFile, String excelFileName, Class<?> clazz, int totalRecord) {
        this(excelFile, excelFileName, clazz, null, null, null, totalRecord);
    }

    public PwExcelUtil(String excelFile,
                       String excelFileName,
                       Class<?> clazz,
                       String[] headerColumns,
                       String[] headerNames,
                       int[] columnTypes,
                       int totalRecord) {
        this(excelFile, excelFileName, clazz, array2List(headerColumns), array2List(headerNames), columnTypes, null, null, totalRecord);
    }

    public PwExcelUtil(String excelFile,
                       String excelFileName,
                       Class<?> clazz,
                       List<String[]> headerColumnsList,
                       List<String[]> headerNamesList,
                       int[] columnTypes,
                       List<int[]> colspansList,
                       SheetStyle sheetStyle,
                       int totalRecord) {
        this.excelFile = excelFile;
        this.excelFileName = excelFileName;
        this.clazz = clazz;
        this.headerColumnsList = headerColumnsList;
        this.headerNamesList = headerNamesList;
        this.columnTypes = columnTypes;
        this.colspansList = colspansList;
        this.sheetStyle = sheetStyle;
        this.totalRecord = totalRecord;

        webWriteExcel= new WriteExcel(excelFile);

        Map processInfo = new HashMap<String, Integer>();
        processInfo.put("totalRecord", totalRecord);
        processInfo.put("dealRecord", 0);
        manager.put(excelFileName, processInfo);
    }

    private static List<String[]> array2List(String[] array) {
        List<String[]> list = new ArrayList<String[]>();
        if(array != null && array.length > 0){
            list.add(array);
        }
        return list;
    }

    public void writeHeader() {
        try {
            List<WriteExcelContentInfo> infos = getHeader();
            webWriteExcel.writeContent(0, infos, sheetStyle);
        } catch (IOException e) {
            logger.error(e);
        } catch (Exception e) {
            logger.error(e);
        }
    }

    public void writeData(int startRow, List<?> list) {
        try {
            List<WriteExcelContentInfo> infos = new ArrayList<WriteExcelContentInfo>();
            if (list == null) throw new NullPointerException();
            Map<String, Integer> processInfo = manager.get(excelFileName);
            int dealRecord = processInfo.get("dealRecord");
            for (Object obj:list) {
                WriteExcelContentInfo row = getEachRowData(obj);
                infos.add(row);
                dealRecord++;
                processInfo.put("dealRecord", dealRecord);
                manager.put(excelFileName, processInfo);
            }
            webWriteExcel.writeContent(startRow, infos);
        } catch (IOException e) {
            logger.error(e);
        } catch (Exception e) {
            logger.error(e);
        }
    }

    public List<WriteExcelContentInfo> getHeader() {
        List<WriteExcelContentInfo> header = new ArrayList<WriteExcelContentInfo>();
        if (checkHeader()) {
            int headerLen = headerNamesList.size();
            int colspansListLen = colspansList==null ? 0 : colspansList.size();
            for(int m = 0;m < headerLen;m++){
                String[] headerNames = headerNamesList.get(m);
                WriteExcelContentInfo info = new WriteExcelContentInfo();
                info.setContents(headerNames);
                if((m < headerLen - 1) && colspansListLen > 0){//最后一行表头不允许合并单元格
                    int[] colspans = colspansList.get(m);
                    info.setColspans(colspans);
                }

                int[] headerTypes = new int[headerNames.length];
                for (int i = 0; i < headerNames.length; i++) {
                    headerTypes[i] = HSSFCell.CELL_TYPE_STRING;
                }
                info.setHSSFCellTypes(headerTypes);

                header.add(info);
            }
        }
        else if (isEmpty(headerColumnsList)&& isEmpty(headerNamesList)) {
            List<String> contents = new ArrayList<String>();
            List<Integer> HSSFCellTypes = new ArrayList<Integer>();

            getAnnotationHeaderInfo(clazz, contents, HSSFCellTypes);
            int length = contents.size();
            String[] cs = new String[length];
            int[] ti = new int[length];
            for (int i=0 ;i<length; i++) {
                cs[i] = contents.get(i);
                ti[i] = HSSFCellTypes.get(i);
            }

            WriteExcelContentInfo info = new WriteExcelContentInfo();
            info.setContents(cs);
            info.setHSSFCellTypes(ti);

            header.add(info);
        } else {
            throw new RuntimeException("请检查表头信息参数");
        }
        return header;
    }

    public void getAnnotationHeaderInfo(Class<?> clazz, List<String> contents, List<Integer> HSSFCellTypes) {
        Class<?> supClazz = clazz.getSuperclass();
        if (supClazz != null) {
            if ("java.lang.Object" == supClazz.getName()) {
                getInnerHeaderInfo(clazz, contents, HSSFCellTypes);
            } else {
                getInnerHeaderInfo(clazz, contents, HSSFCellTypes);
                getAnnotationHeaderInfo(supClazz, contents, HSSFCellTypes);
            }

        }
    }

    public void getInnerHeaderInfo(Class<?> clazz, List<String> contents, List<Integer> HSSFCellTypes) {
        Field[] fields = clazz.getDeclaredFields();
        if (fields != null) {
            for (int i=0; i < fields.length; i++) {
                Field field = fields[i];

                if (field.getType().getName().startsWith("java.")
                        || field.getType().isPrimitive()) {
                    ExcelColumn annotation =  field.getAnnotation(ExcelColumn.class);

                    String columnName = field.getName();
                    if (annotation != null) {
                        columnName = annotation.name();
                    } else {
                        continue;
                    }
                    contents.add(columnName);
                    HSSFCellTypes.add(HSSFCell.CELL_TYPE_STRING);
                    System.out.println(columnName);
                } else {
                    getInnerHeaderInfo(field.getType(), contents, HSSFCellTypes);
                }
            }
        }
    }

    public WriteExcelContentInfo getEachRowData(Object obj) {
        WriteExcelContentInfo rowData = new WriteExcelContentInfo();
        List<String> contents = new ArrayList<String>();
        List<Integer> HSSFCellTypes = new ArrayList<Integer>();
        if (checkHeader()) {
            int index = headerColumnsList.size()-1;
            String[] headerColumns = headerColumnsList.get(index);
            String[] headerNames = headerNamesList.get(index);
            for (String col : headerColumns) {
                getCellData(clazz, obj, col, contents);
            }
            int length = contents.size();
            String[] cs = new String[length];
            for (int i=0 ;i<length; i++) {
                cs[i] = contents.get(i);
            }

            if (columnTypes == null || columnTypes.length == 0) {
                columnTypes = new int[headerNames.length];
                for (int i = 0; i < headerNames.length; i++) {
                    columnTypes[i] = HSSFCell.CELL_TYPE_STRING;
                }
            }
            rowData.setHSSFCellTypes(columnTypes);
            rowData.setContents(cs);
        } else if (isEmpty(headerColumnsList)&& isEmpty(headerNamesList)) {
            getAnnotationRowData(clazz, obj, contents, HSSFCellTypes);
            int length = contents.size();
            String[] cs = new String[length];
            int[] ti = new int[length];
            for (int i=0 ;i<length; i++) {
                cs[i] = contents.get(i);
                ti[i] = HSSFCellTypes.get(i);
            }
            rowData.setHSSFCellTypes(ti);
            rowData.setContents(cs);
        } else {
            throw new RuntimeException("请检查表头信息参数");
        }

        return rowData;
    }

    private boolean isEmpty(List<String[]> list){
        if(list == null || list.size() == 0){
            return true;
        }
        for(String[] arr : list){
            if(ObjectUtils.isEmpty(arr)){
                return true;
            }
        }
        return false;
    }

    private boolean isEqualsSize(List<String[]> list1, List<String[]> list2){
        if(list1.size() != list2.size()){
            return false;
        }
        for(int i = 0;i < list1.size();i++){
            String[] a1 = list1.get(i);
            String[] a2 = list2.get(i);
            if(a1.length != a2.length){
                return false;
            }
        }
        return true;
    }

    /**
     * 检查表头配置
     * @return
     */
    private boolean checkHeader() {
        if (!isEmpty(headerColumnsList) && !isEmpty(headerNamesList)
                && isEqualsSize(headerColumnsList, headerNamesList)) {

            // 校验合并单元格配置
            if (colspansList == null || colspansList.size() <= 0) {
                return true;
            }

            if (colspansList.size() == headerColumnsList.size()
                    || colspansList.size() == headerColumnsList.size() - 1) {
                int colspansListLen = colspansList.size();
                int headerColumnsListLen = headerColumnsList.size();
                boolean hasLast = true;// 是否设置最后一行表头合并单元格标识
                if (colspansListLen == headerColumnsListLen - 1) {
                    hasLast = false;
                }
                int maxWidth = 0;
                int lastColLen = headerColumnsList
                        .get(headerColumnsListLen - 1).length;
                for (int i = 0; i < colspansListLen; i++) {
                    int[] cL = colspansList.get(i);
                    if (cL.length != headerColumnsList.get(i).length) {
                        return false;
                    }
                    int tmpWidth = 0;
                    for (int c : cL) {
                        if (c <= 0) {
                            c = 1;// 容错
                        }
                        tmpWidth += c;
                    }
                    if (maxWidth < tmpWidth) {
                        maxWidth = tmpWidth;
                    }

                    if (i == colspansListLen - 1) {// 最后一行表头
                        if (hasLast) {
                            if (tmpWidth != cL.length) {
                                return false;// 最后一行表头宽度=最后一行表头列数
                            } else {
                                return true;
                            }
                        } else {
                            if (maxWidth > lastColLen) {
                                return false;// 当前行的列数<=当前行单元格个数<=最后一行的列数
                            } else {
                                return true;
                            }
                        }
                    } else {
                        if (tmpWidth < cL.length
                                || (hasLast && tmpWidth > lastColLen)) {
                            return false;// 当前行表头列数<=当前行表头宽度<=最后一行表头的列数
                        }
                    }
                }
            } else {
                return false;
            }
        }
        return false;
    }

    public void getCellData(Class<?> clazz, Object obj, String methodLink, List<String> contents) {

        if(obj instanceof Map){
//			String a = methodLink;
//			String b = ((Map<String,String>)obj).get(methodLink);
            contents.add(((Map<String,String>)obj).get(methodLink)==null?"":((Map<String,String>)obj).get(methodLink));
            return;
        }


        int index = methodLink.indexOf(".");
        String methodName = methodLink;
        try {
            if (index != -1) {
                methodName = methodLink.substring(0, index);

                Method method = clazz.getMethod(getGetMethodName(methodName), null);
                if (method.invoke(obj, null) != null) {
                    getCellData(method.invoke(obj, null).getClass(), method.invoke(obj, null), methodLink.substring(index + 1, methodLink.length()), contents);
                }
            } else {
                Method method = clazz.getMethod(getGetMethodName(methodName), null);
                Object value = method.invoke(obj, null);
                String content = (value == null?"":value.toString());
                contents.add(content);
            }
        } catch (SecurityException e) {
            logger.error(e);
        } catch (IllegalArgumentException e) {
            logger.error(e);
        } catch (NoSuchMethodException e) {
            logger.error(e);
        } catch (IllegalAccessException e) {
            logger.error(e);
        } catch (InvocationTargetException e) {
            logger.error(e);
        }
    }

    public void getAnnotationRowData(Class<?> clazz, Object obj, List<String> contents, List<Integer> HSSFCellTypes) {
        Field[] fields = clazz.getDeclaredFields();
        if (fields != null) {
            for (int i=0; i < fields.length; i++) {
                Field field = fields[i];

                try {
                    if (field.getType().getName().startsWith("java.")
                            || field.getType().isPrimitive()) {
                        ExcelColumn annotation = field.getAnnotation(ExcelColumn.class);
                        Method method = clazz.getMethod(getGetMethodName(field.getName()), null);
                        Object value = method.invoke(obj, null);
                        int type = HSSFCell.CELL_TYPE_STRING;
                        if (annotation != null) {
                            type = annotation.type();
                        } else {
                            continue;
                        }
                        String content = (value == null?"":value.toString());
                        System.out.println(content);
                        contents.add(content);
                        HSSFCellTypes.add(type);
                    } else {
                        Method method = clazz.getMethod(getGetMethodName(field.getName()), null);
                        if (method.invoke(obj, null) != null) {
                            getAnnotationRowData(method.invoke(obj, null).getClass(), method.invoke(obj, null), contents, HSSFCellTypes);
                        }
                    }
                } catch (Exception e) {
                    continue;
                }
            }
        }
    }

    private String upperFirstLetter(String word) {
        return word.substring(0, 1).toUpperCase() + word.substring(1, word.length());
    }

    private String getGetMethodName(String fieldName) {
        return "get" + upperFirstLetter(fieldName);
    }

    public String getExcelFile() {
        return excelFile;
    }

    public void setExcelFile(String excelFile) {
        this.excelFile = excelFile;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public List<String[]> getHeaderColumnsList() {
        return headerColumnsList;
    }

    public void setHeaderColumnsList(List<String[]> headerColumnsList) {
        this.headerColumnsList = headerColumnsList;
    }

    public List<String[]> getHeaderNamesList() {
        return headerNamesList;
    }

    public void setHeaderNamesList(List<String[]> headerNamesList) {
        this.headerNamesList = headerNamesList;
    }

    public List<int[]> getcolspansList() {
        return colspansList;
    }

    public void setColspansList(List<int[]> colspansList) {
        this.colspansList = colspansList;
    }

    public String getExcelFileName() {
        return excelFileName;
    }

    public void setExcelFileName(String excelFileName) {
        this.excelFileName = excelFileName;
    }
}
