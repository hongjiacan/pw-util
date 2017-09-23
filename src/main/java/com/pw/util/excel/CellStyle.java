package com.pw.util.excel;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Created by PoemWhite on 2017/9/14.
 */
public class CellStyle {

    private Align align;
    private Vertical vertical;

    private ExcelFont font;

    private FillPattern fillPattern;

    private Short fillBackgroundColor;
    private Short fillForegroundColor;


    private Boolean hidden;
    private Boolean locked;
    private Boolean wrapText;

    private Short rotation;
    private Short indention;

    private Border borderTop;
    private Border borderRight;
    private Border borderBottom;
    private Border borderLeft;

    private Short borderTopColor;
    private Short borderRightColor;
    private Short borderBottomColor;
    private Short borderLeftColor;



    public Align getAlign() {
        return align;
    }
    public void setAlign(Align align) {
        this.align = align;
    }
    public Vertical getVertical() {
        return vertical;
    }
    public void setVertical(Vertical vertical) {
        this.vertical = vertical;
    }
    public ExcelFont getFont() {
        return font;
    }
    public void setFont(ExcelFont font) {
        this.font = font;
    }
    public FillPattern getFillPattern() {
        return fillPattern;
    }
    public void setFillPattern(FillPattern fillPattern) {
        this.fillPattern = fillPattern;
    }
    public Short getFillBackgroundColor() {
        return fillBackgroundColor;
    }
    public void setFillBackgroundColor(Short fillBackgroundColor) {
        this.fillBackgroundColor = fillBackgroundColor;
    }
    public Short getFillForegroundColor() {
        return fillForegroundColor;
    }
    public void setFillForegroundColor(Short fillForegroundColor) {
        this.fillForegroundColor = fillForegroundColor;
    }
    public Boolean getHidden() {
        return hidden;
    }
    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }
    public Boolean getLocked() {
        return locked;
    }
    public void setLocked(Boolean locked) {
        this.locked = locked;
    }
    public Boolean getWrapText() {
        return wrapText;
    }
    public void setWrapText(Boolean wrapText) {
        this.wrapText = wrapText;
    }
    public Short getRotation() {
        return rotation;
    }
    public void setRotation(Short rotation) {
        this.rotation = rotation;
    }
    public Short getIndention() {
        return indention;
    }
    public void setIndention(Short indention) {
        this.indention = indention;
    }
    public Border getBorderTop() {
        return borderTop;
    }
    public void setBorderTop(Border borderTop) {
        this.borderTop = borderTop;
    }
    public Border getBorderRight() {
        return borderRight;
    }
    public void setBorderRight(Border borderRight) {
        this.borderRight = borderRight;
    }
    public Border getBorderBottom() {
        return borderBottom;
    }
    public void setBorderBottom(Border borderBottom) {
        this.borderBottom = borderBottom;
    }
    public Border getBorderLeft() {
        return borderLeft;
    }
    public void setBorderLeft(Border borderLeft) {
        this.borderLeft = borderLeft;
    }
    public Short getBorderTopColor() {
        return borderTopColor;
    }
    public void setBorderTopColor(Short borderTopColor) {
        this.borderTopColor = borderTopColor;
    }
    public Short getBorderRightColor() {
        return borderRightColor;
    }
    public void setBorderRightColor(Short borderRightColor) {
        this.borderRightColor = borderRightColor;
    }
    public Short getBorderBottomColor() {
        return borderBottomColor;
    }
    public void setBorderBottomColor(Short borderBottomColor) {
        this.borderBottomColor = borderBottomColor;
    }
    public Short getBorderLeftColor() {
        return borderLeftColor;
    }
    public void setBorderLeftColor(Short borderLeftColor) {
        this.borderLeftColor = borderLeftColor;
    }

    public void setBorder(Border borderTop,Border borderRight,Border borderBottom,Border borderLeft){
        this.setBorderTop(borderTop);
        this.setBorderRight(borderRight);
        this.setBorderBottom(borderBottom);
        this.setBorderLeft(borderLeft);
    }

    public void setBorder(Border border){
        this.setBorder(border, border, border, border);
    }




    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);

    }
    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);

    }

    public CellStyle mixin(CellStyle mixinObj){
        if(mixinObj==null){
            return this;
        }


        Align align = mixinObj.getAlign();
        if(align!=null){
            this.setAlign(align);
        }
        Vertical vertical = mixinObj.getVertical();
        if(vertical!=null){
            this.setVertical(vertical);
        }

        ExcelFont font = mixinObj.getFont();
        if(font !=null){
            if(this.getFont()==null){
                this.setFont(font);
            }
            else{
                this.getFont().mixin(font);
            }
        }

        FillPattern fillPattern = mixinObj.getFillPattern();
        if(fillPattern!=null){
            this.setFillPattern(fillPattern);
        }

        Short fillBackgroundColor = mixinObj.getFillBackgroundColor();
        if(fillBackgroundColor!=null){
            this.setFillBackgroundColor(fillBackgroundColor);
        }
        Short fillForegroundColor = mixinObj.getFillForegroundColor();
        if(fillForegroundColor!=null){
            this.setFillForegroundColor(fillForegroundColor);
        }


        Boolean hidden = mixinObj.getHidden();
        if(hidden!=null){
            this.setHidden(hidden);
        }
        Boolean locked = mixinObj.getLocked();
        if(locked!=null){
            this.setLocked(locked);
        }
        Boolean wrapText = mixinObj.getWrapText();
        if(wrapText!=null){
            this.setWrapText(wrapText);
        }

        Short rotation = mixinObj.getRotation();
        if(rotation!=null){
            this.setRotation(rotation);
        }
        Short indention = mixinObj.getIndention();
        if(indention !=null){
            this.setIndention(indention);
        }

        Border borderTop = mixinObj.getBorderTop();
        if(borderTop != null){
            this.setBorderTop(borderTop);
        }
        Border borderRight = mixinObj.getBorderRight();
        if(borderRight != null){
            this.setBorderRight(borderRight);
        }
        Border borderBottom = mixinObj.getBorderBottom();
        if(borderBottom != null){
            this.setBorderBottom(borderBottom);
        }
        Border borderLeft = mixinObj.getBorderLeft();
        if(borderLeft != null){
            this.setBorderLeft(borderLeft);
        }

        Short borderTopColor = mixinObj.getBorderTopColor();
        if(borderTopColor != null){
            this.setBorderTopColor(borderTopColor);
        }
        Short borderRightColor = mixinObj.getBorderRightColor();
        if(borderRightColor != null){
            this.setBorderRightColor(borderRightColor);
        }
        Short borderBottomColor = mixinObj.getBorderBottomColor();
        if(borderBottomColor != null){
            this.setBorderBottomColor(borderBottomColor);
        }
        Short borderLeftColor = mixinObj.getBorderLeftColor();
        if(borderLeftColor != null){
            this.setBorderLeftColor(borderLeftColor);
        }


        return this;
    }


    public enum Align{
        ALIGN_GENERAL((short)0),
        ALIGN_LEFT((short)1),
        ALIGN_CENTER((short)2),
        ALIGN_RIGHT((short)3),
        ALIGN_FILL((short)4),
        ALIGN_JUSTIFY((short)5),
        ALIGN_CENTER_SELECTION((short)6);

        private short value;

        Align(short value){
            this.value = value;

        }
        public short getValue(){
            return this.value;
        }

    }

    public enum Vertical{
        VERTICAL_TOP((short)0),
        VERTICAL_CENTER((short)1),
        VERTICAL_BOTTOM((short)2),
        VERTICAL_JUSTIFY((short)3);

        private short value;

        Vertical(short value){
            this.value = value;

        }
        public short getValue(){
            return this.value;
        }

    }

    public enum Border{
        BORDER_NONE((short)0),
        BORDER_THIN((short)1),
        BORDER_MEDIUM((short)2),
        BORDER_DASHED((short)3),
        BORDER_HAIR((short)4),
        BORDER_THICK((short)5),
        BORDER_DOUBLE((short)6),
        BORDER_DOTTED((short)7),
        BORDER_MEDIUM_DASHED((short)8),
        BORDER_DASH_DOT((short)9),
        BORDER_MEDIUM_DASH_DOT((short)10),
        BORDER_DASH_DOT_DOT((short)11),
        BORDER_MEDIUM_DASH_DOT_DOT((short)12),
        BORDER_SLANTED_DASH_DOT((short)13);

        private short value;

        Border(short value){
            this.value = value;

        }
        public short getValue(){
            return this.value;
        }

    }

    public enum FillPattern{
        NO_FILL((short)0),
        SOLID_FOREGROUND((short)1),
        FINE_DOTS((short)2),
        ALT_BARS((short)3),
        SPARSE_DOTS((short)4),
        THICK_HORZ_BANDS((short)5),
        THICK_VERT_BANDS((short)6),
        THICK_BACKWARD_DIAG((short)7),
        THICK_FORWARD_DIAG((short)8),
        BIG_SPOTS((short)9),
        BRICKS((short)10),
        THIN_HORZ_BANDS((short)11),
        THIN_VERT_BANDS((short)12),
        THIN_BACKWARD_DIAG((short)13),
        THIN_FORWARD_DIAG((short)14),
        SQUARES((short)15),
        DIAMONDS((short)16),
        LESS_DOTS((short)17),
        LEAST_DOTS((short)18);

        private short value;

        FillPattern(short value){
            this.value = value;

        }
        public short getValue(){
            return this.value;
        }

    }

    public static final short ALIGN_GENERAL = 0;
    public static final short ALIGN_LEFT = 1;
    public static final short ALIGN_CENTER = 2;
    public static final short ALIGN_RIGHT = 3;
    public static final short ALIGN_FILL = 4;
    public static final short ALIGN_JUSTIFY = 5;
    public static final short ALIGN_CENTER_SELECTION = 6;
    public static final short VERTICAL_TOP = 0;
    public static final short VERTICAL_CENTER = 1;
    public static final short VERTICAL_BOTTOM = 2;
    public static final short VERTICAL_JUSTIFY = 3;
    public static final short BORDER_NONE = 0;
    public static final short BORDER_THIN = 1;
    public static final short BORDER_MEDIUM = 2;
    public static final short BORDER_DASHED = 3;
    public static final short BORDER_HAIR = 4;
    public static final short BORDER_THICK = 5;
    public static final short BORDER_DOUBLE = 6;
    public static final short BORDER_DOTTED = 7;
    public static final short BORDER_MEDIUM_DASHED = 8;
    public static final short BORDER_DASH_DOT = 9;
    public static final short BORDER_MEDIUM_DASH_DOT = 10;
    public static final short BORDER_DASH_DOT_DOT = 11;
    public static final short BORDER_MEDIUM_DASH_DOT_DOT = 12;
    public static final short BORDER_SLANTED_DASH_DOT = 13;
    public static final short NO_FILL = 0;
    public static final short SOLID_FOREGROUND = 1;
    public static final short FINE_DOTS = 2;
    public static final short ALT_BARS = 3;
    public static final short SPARSE_DOTS = 4;
    public static final short THICK_HORZ_BANDS = 5;
    public static final short THICK_VERT_BANDS = 6;
    public static final short THICK_BACKWARD_DIAG = 7;
    public static final short THICK_FORWARD_DIAG = 8;
    public static final short BIG_SPOTS = 9;
    public static final short BRICKS = 10;
    public static final short THIN_HORZ_BANDS = 11;
    public static final short THIN_VERT_BANDS = 12;
    public static final short THIN_BACKWARD_DIAG = 13;
    public static final short THIN_FORWARD_DIAG = 14;
    public static final short SQUARES = 15;
    public static final short DIAMONDS = 16;
    public static final short LESS_DOTS = 17;
    public static final short LEAST_DOTS = 18;
}
