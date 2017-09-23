package com.pw.util.excel;

import com.pw.util.exception.PwRuntimeException;
import com.pw.util.file.PwFileHelper;
import com.pw.util.string.PwStringUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PoemWhite on 2017/9/14.
 */
public class WriteExcel {

    public static Log logger = LogFactory.getLog(WriteExcel.class);

    private String excelFile="";


    /**
     * 不使用模板直接生成excel文件
     * @param excelFile
     */
    public WriteExcel(String excelFile){
        this.excelFile=excelFile;
        //不判断是否存在,每次都新建文件,防止同文件名旧数据残留
//        if(!PwFileHelper.isExists(excelFile)){
            excelFile= PwStringUtil.getFormatPath(excelFile);
            String dirPath=PwStringUtil.getPreString(excelFile, "/");
            if(!PwFileHelper.isDir(dirPath)){
                PwFileHelper.createDir(dirPath);
            }
            try{
                HSSFWorkbook workbook = new HSSFWorkbook();
                FileOutputStream fileOutputStream = new FileOutputStream(excelFile);
                workbook.write(fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
            }catch(Exception e){
                throw new RuntimeException(e);
            }
//        }
    }

    /**
     * 往excel中写入内容
     * @param startRow	写入excel的起始行
     * @param contentInfos	需要写入excel的内容
     * @throws Exception
     */
    public void writeContent(int startRow,List<WriteExcelContentInfo> contentInfos ) throws Exception{
        if(contentInfos==null || contentInfos.size()<=0){
            return ;
        }

        InputStream excelInputStream=PwFileHelper.fileToInputStream(excelFile);

        HSSFWorkbook workbook = new HSSFWorkbook(excelInputStream);

        HSSFSheet childSheet = null;

        try{
            childSheet=workbook.getSheetAt(0);
        }catch(Exception e){
            logger.error(e);
        }

        if(childSheet==null){
            childSheet=workbook.createSheet();
        }

        int length=contentInfos.size();
        HSSFCellStyle cellStyle= workbook.createCellStyle();
        for(int i=0;i<length;i++){
            int rowIndex = i+startRow;
            HSSFRow row=childSheet.createRow(rowIndex);
            WriteExcelContentInfo contentInfo=contentInfos.get(i);
            String[] contents=contentInfo.contents;
            int[] colspans = contentInfo.getColspans();
            int[] contentTypes=contentInfo.getHSSFCellTypes();
            if(contents!=null && contents.length>0){
                int contentlength=contents.length;
                int colIndex = 0;
                for(int j=0;j<contentlength;j++){
                    cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
                    cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
                    cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
                    cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
                    if(contentInfo.getIsWrap()!=null){
                        cellStyle.setWrapText(contentInfo.getIsWrap()[j]);
                    }

                    HSSFCell cell=row.createCell(colIndex);
//					cell.setEncoding(HSSFCell.ENCODING_UTF_16);
                    cell.setCellType(contentTypes[j]);
                    cell.setCellStyle(cellStyle);
                    if(contentTypes[j]== HSSFCell.CELL_TYPE_STRING){
                        contents[j]=""+contents[j];
                    }
                    cell.setCellValue(contents[j]);

                    //合并单元格
                    if(colspans != null && colspans.length == contentlength){
                        if(colspans[j] > 1){
                            CellRangeAddress cra = new CellRangeAddress(rowIndex, rowIndex, colIndex, colIndex+colspans[j]-1);
                            childSheet.addMergedRegion(cra);
                            //设置合并单元格样式
                            for (int m = cra.getFirstColumn(); m <=  cra.getLastColumn(); m ++){
                                CellUtil.getCell(row, m).setCellStyle(cellStyle);
                            }
                        }
                        colIndex += colspans[j]-1;
                    }
                    colIndex++;
                }
            }
        }

        excelInputStream.close();

        FileOutputStream fileOutputStream = new FileOutputStream(excelFile);
        workbook.write(fileOutputStream);
        fileOutputStream.flush();
        fileOutputStream.close();

        contentInfos.clear();

        contentInfos=null;
    }

    /**
     * 往excel中写入内容
     * @param startRow	写入excel的起始行
     * @param contentInfos	需要写入excel的内容
     * @throws Exception
     */
    public void writeContent(String sheetName,int startRow,List<WriteExcelContentInfo> contentInfos ) throws PwRuntimeException{
        if(contentInfos==null || contentInfos.size()<=0){
            return ;
        }

        sheetName=PwStringUtil.getString(sheetName);

        if(sheetName.equals("")){
            throw new PwRuntimeException("SheetName 不允许为空!!!");
        }

        InputStream excelInputStream=null;
        HSSFWorkbook workbook = null;
        try{
            excelInputStream= PwFileHelper.fileToInputStream(excelFile);
            workbook=new HSSFWorkbook(excelInputStream);

            HSSFSheet childSheet = workbook.getSheet(sheetName);

            if(childSheet==null){
                childSheet = workbook.createSheet(sheetName);
            }

            int length=contentInfos.size();
            HSSFCellStyle cellStyle= workbook.createCellStyle();
            for(int i=0;i<length;i++){

                HSSFRow row=childSheet.createRow(i+startRow);
                WriteExcelContentInfo contentInfo=contentInfos.get(i);
                String[] contents=contentInfo.contents;
                int[] contentTypes=contentInfo.getHSSFCellTypes();
                if(contents!=null && contents.length>0){
                    int contentlength=contents.length;
                    for(int j=0;j<contentlength;j++){

                        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
                        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
                        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
                        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
                        if(contentInfo.getIsWrap()!=null){
                            cellStyle.setWrapText(contentInfo.getIsWrap()[j]);
                        }
                        HSSFCell cell=row.createCell(j);
//						cell.setEncoding(HSSFCell.ENCODING_UTF_16);
                        cell.setCellType(contentTypes[j]);
                        cell.setCellStyle(cellStyle);
                        if(contentTypes[j]==HSSFCell.CELL_TYPE_STRING){
                            contents[j]=" "+contents[j];
                        }
                        cell.setCellValue(contents[j]);
                    }
                }
            }
        }catch(Exception e){
            throw new PwRuntimeException(e);
        }finally{
            try {
                if(excelInputStream!=null){
                    excelInputStream.close();
                }
            } catch (IOException e) {
                logger.error(e);
            }
        }

        FileOutputStream fileOutputStream = null;

        try{
            fileOutputStream=new FileOutputStream(excelFile);
            workbook.write(fileOutputStream);
            fileOutputStream.flush();
        }catch(Exception e){
            throw new PwRuntimeException(e);
        }finally{
            try {
                if(fileOutputStream!=null){
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                logger.error(e);
            }
        }
    }

    /**
     * 往excel中写入内容
     * @param startRow	写入excel的起始行
     * @param contentInfos	需要写入excel的内容
     * @param sheetStyle  行列样式
     * @throws Exception
     */
    public void writeContent(int startRow, List<WriteExcelContentInfo> contentInfos, SheetStyle sheetStyle) throws Exception{
        if(contentInfos==null || contentInfos.size()<=0){
            return ;
        }

        InputStream excelInputStream=PwFileHelper.fileToInputStream(excelFile);

        HSSFWorkbook workbook = new HSSFWorkbook(excelInputStream);

        HSSFSheet childSheet = null;

        try{
            childSheet=workbook.getSheetAt(0);
        }catch(Exception e){
            logger.info(e);//先取,不存在就create
        }

        if(childSheet==null){
            childSheet=workbook.createSheet();
        }

        List<HSSFCellStyle> rowCellStyleList = new ArrayList<HSSFCellStyle>();
        if(sheetStyle != null){
            Integer[] columnWidths = sheetStyle.getColumnWidths();
            //设置列宽
            if(columnWidths != null && columnWidths.length > 0){
                int i = 0;
                for(int width : columnWidths){
                    if(width > 0){
                        childSheet.setColumnWidth(i++, width);
                    }
                }
            }

            //设置行高
//			short[] rowHeights = sheetStyle.getRowHeights();
//			if(rowHeights != null && rowHeights.length > 0){
//				int i = 0;
//				for(short height : rowHeights){
//					if(height > 0){
//						childSheet.getRow(i++).setHeight(height);
//					}
//				}
//			}

            //设置行样式
            CellStyle[] rowCellStyles = sheetStyle.getRowCellStyles();
            if(rowCellStyles != null && rowCellStyles.length > 0){
//				int i = 0;
                for(CellStyle rcs : rowCellStyles){
                    HSSFCellStyle rowCellStyle = workbook.createCellStyle();
                    //设置自动换行
                    if(rcs.getWrapText() != null){
                        rowCellStyle.setWrapText(rcs.getWrapText());
                    }
                    //设置居中
                    if(rcs.getAlign() != null){
                        rowCellStyle.setAlignment(rcs.getAlign().getValue());
                    }
                    if(rcs.getVertical() != null){
                        rowCellStyle.setVerticalAlignment(rcs.getVertical().getValue());
                    }

                    //设置字体
                    if(rcs.getFont() != null){
                        ExcelFont ft = rcs.getFont();
                        HSSFFont font = workbook.createFont();
                        if(ft.getFontName() != null && !"".equals(ft.getFontName())){
                            font.setFontName(ft.getFontName());
                        }
                        if(ft.getFontHeight() != null){
                            font.setFontHeight(ft.getFontHeight());
                        }
                        if(ft.getFontHeightInPoints() > 0){
                            font.setFontHeightInPoints(ft.getFontHeightInPoints());
                        }
                        if(ft.getItalic() != null){
                            font.setItalic(ft.getItalic());
                        }
                        if(ft.getStrikeout() != null){
                            font.setStrikeout(ft.getStrikeout());
                        }
                        if(ft.getColor() != null){
                            font.setColor(ft.getColor());
                        }
                        if(ft.getTypeOffset() != null){
                            font.setTypeOffset(ft.getTypeOffset());
                        }
                        if(ft.getUnderline() != null){
                            font.setUnderline(ft.getUnderline());
                        }
                        if(ft.getCharSet() != null){
                            font.setCharSet(ft.getCharSet());
                        }
                        if(ft.getBoldweight() != null){
                            font.setBoldweight(ft.getBoldweight());
                        }

                        rowCellStyle.setFont(font);

                        rowCellStyleList.add(rowCellStyle);
//						childSheet.getRow(i++).setRowStyle(rowCellStyle);//设置后无效果
                    }
                }
            }
        }

        int length=contentInfos.size();
        for(int i=0;i<length;i++){
            int rowIndex = i+startRow;
            HSSFRow row=childSheet.createRow(rowIndex);
            WriteExcelContentInfo contentInfo=contentInfos.get(i);
            String[] contents=contentInfo.contents;
            int[] colspans = contentInfo.getColspans();
            int[] contentTypes=contentInfo.getHSSFCellTypes();

            if(sheetStyle != null){
                //设置行高
                short[] rowHeights = sheetStyle.getRowHeights();
                if(rowHeights != null && rowHeights.length > i && rowHeights[i] > 0){
                    row.setHeight(rowHeights[i]);
                }
            }

            HSSFCellStyle cellStyle = null;
            if(rowCellStyleList.size() > i){
                cellStyle = rowCellStyleList.get(i);
            }
            if(cellStyle == null){
                cellStyle = workbook.createCellStyle();
            }

            if(contents!=null && contents.length>0){
                int contentlength=contents.length;
                int colIndex = 0;
                for(int j=0;j<contentlength;j++){
                    cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
                    cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
                    cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
                    cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
                    if(contentInfo.getIsWrap()!=null){
                        cellStyle.setWrapText(contentInfo.getIsWrap()[j]);
                    }

                    HSSFCell cell=row.createCell(colIndex);
//					cell.setEncoding(HSSFCell.ENCODING_UTF_16);
                    cell.setCellType(contentTypes[j]);
                    cell.setCellStyle(cellStyle);
                    if(contentTypes[j]==HSSFCell.CELL_TYPE_STRING){
                        contents[j]=""+contents[j];
                    }
                    cell.setCellValue(contents[j]);

                    //合并单元格
                    if(colspans != null && colspans.length == contentlength){
                        if(colspans[j] > 1){
                            CellRangeAddress cra = new CellRangeAddress(rowIndex, rowIndex, colIndex, colIndex+colspans[j]-1);
                            childSheet.addMergedRegion(cra);
                            //设置合并单元格样式
                            for (int m = cra.getFirstColumn(); m <=  cra.getLastColumn(); m ++){
                                CellUtil.getCell(row, m).setCellStyle(cellStyle);
                            }
                        }
                        colIndex += colspans[j]-1;
                    }
                    colIndex++;
                }
            }
        }

        excelInputStream.close();

        FileOutputStream fileOutputStream = new FileOutputStream(excelFile);
        workbook.write(fileOutputStream);
        fileOutputStream.flush();
        fileOutputStream.close();

        contentInfos.clear();

        contentInfos=null;

    }
    public static void main(String[] args){

    }
}
