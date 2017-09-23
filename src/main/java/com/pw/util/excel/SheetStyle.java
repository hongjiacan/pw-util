package com.pw.util.excel;

/**
 * Created by PoemWhite on 2017/9/14.
 */
public class SheetStyle {

    private Integer[] columnWidths;//列宽
    private short[] rowHeights;//行高
    private CellStyle[] rowCellStyles;//行单元格样式(不包含行高)

    public Integer[] getColumnWidths() {
        return columnWidths;
    }
    public void setColumnWidths(Integer[] columnWidths) {
        this.columnWidths = columnWidths;
    }
    public void setRowCellStyles(CellStyle[] rowCellStyles) {
        this.rowCellStyles = rowCellStyles;
    }
    public short[] getRowHeights() {
        return rowHeights;
    }
    public void setRowHeights(short[] rowHeights) {
        this.rowHeights = rowHeights;
    }
    public CellStyle[] getRowCellStyles() {
        return rowCellStyles;
    }
}
