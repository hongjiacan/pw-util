package com.pw.util.excel;

/**
 * Created by PoemWhite on 2017/9/14.
 */
public class WriteExcelContentInfo {

    public String[] contents;

    private int[] HSSFCellTypes;

    private boolean[] isWrap;

    private int colspans[];

    /**
     * 获取当前行的所有列内容
     * @return
     */
    public String[] getContents() {
        return contents;
    }

    /**
     * 设置当前行所有列的内容
     * @param contents
     */
    public void setContents(String[] contents) {
        this.contents = contents;
    }

    /**
     * 获取当前行所有列的格式
     * 		HSSFCell
     * @return
     */
    public int[] getHSSFCellTypes() {
        return HSSFCellTypes;
    }

    /**
     * 设置当前行所有列的格式
     * 		HSSFCell
     * @param cellTypes
     */
    public void setHSSFCellTypes(int[] cellTypes) {
        HSSFCellTypes = cellTypes;
    }

    /**
     * 获取是否换行
     * @return
     */
    public boolean[] getIsWrap() {
        return isWrap;
    }

    /**
     * 设置是否换行
     * @param isWrap
     */
    public void setIsWrap(boolean[] isWrap) {
        this.isWrap = isWrap;
    }

    /**
     * 获取当前行的所有内容宽度
     */
    public int[] getColspans() {
        return colspans;
    }

    /**
     * 设置前行的所有内容宽度
     */
    public void setColspans(int[] colspans) {
        this.colspans = colspans;
    }
}
