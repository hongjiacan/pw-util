package com.pw.util.excel;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Created by PoemWhite on 2017/9/14.
 */
public class ExcelFont {

    private String fontName;
    private Short fontHeight;
    private Boolean italic;
    private Boolean strikeout;
    private Short color;
    private Short typeOffset;
    private Byte underline;
    private Byte charSet;
    private Short boldweight;



    public String getFontName() {
        return fontName;
    }

    public void setFontName(String fontName) {
        this.fontName = fontName;
    }

    public Short getFontHeight() {
        return fontHeight;
    }

    public void setFontHeight(Short fontHeight) {
        this.fontHeight = fontHeight;
    }

    public Boolean getItalic() {
        return italic;
    }

    public void setItalic(Boolean italic) {
        this.italic = italic;
    }

    public Boolean getStrikeout() {
        return strikeout;
    }

    public void setStrikeout(Boolean strikeout) {
        this.strikeout = strikeout;
    }

    public Short getColor() {
        return color;
    }

    public void setColor(Short color) {
        this.color = color;
    }

    public Short getTypeOffset() {
        return typeOffset;
    }

    public void setTypeOffset(Short typeOffset) {
        this.typeOffset = typeOffset;
    }

    public Byte getUnderline() {
        return underline;
    }

    public void setUnderline(Byte underline) {
        this.underline = underline;
    }

    public void setCharSet(Byte charSet) {
        this.charSet = charSet;
    }

    public void setBoldweight(Short boldweight) {
        this.boldweight = boldweight;
    }

    public Integer getCharSet()
    {

        if(this.charSet == null){
            return null;
        }

        if(this.charSet >= 0)
            return (int)charSet;
        else
            return charSet + 256;
    }

    public void setCharSet(int charset)
    {
        byte cs = (byte)charset;
        if(charset > 127)
            cs = (byte)(charset - 256);
        this.setCharSet(cs);
    }



    public Short getBoldweight() {
        return boldweight;
    }

    public void setFontHeightInPoints(short height){
        this.setFontHeight((short)(height * 20));
    }

    public short getFontHeightInPoints(){
        return (short)(this.getFontHeight() / 20);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);

    }
    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);

    }

    public ExcelFont mixin(ExcelFont mixinObj){
        if(mixinObj==null){
            return this;
        }

        String fontName = mixinObj.getFontName();
        if(fontName!=null){
            this.setFontName(fontName);
        }
        Short fontHeight = mixinObj.getFontHeight();
        if(fontHeight!=null){
            this.setFontHeight(fontHeight);
        }
        Boolean italic = mixinObj.getItalic();
        if(italic!=null){
            this.setItalic(italic);
        }
        Boolean strikeout = mixinObj.getStrikeout();
        if(strikeout!=null){
            this.setStrikeout(strikeout);
        }
        Short color = mixinObj.getColor();
        if(color!=null){
            this.setColor(color);
        }
        Short typeOffset = mixinObj.getTypeOffset();
        if(typeOffset!=null){
            this.setTypeOffset(typeOffset);
        }
        Byte underline = mixinObj.getUnderline();
        if(underline!=null){
            this.setUnderline(underline);
        }
        Integer charSet = mixinObj.getCharSet();
        if(charSet!=null){
            this.setCharSet(charSet);
        }
        Short boldweight = mixinObj.getBoldweight();
        if(boldweight!=null){
            this.setBoldweight(boldweight);
        }

        return this;
    }

    public static final short BOLDWEIGHT_NORMAL = 400;
    public static final short BOLDWEIGHT_BOLD = 700;
    public static final short COLOR_NORMAL = 32767;
    public static final short COLOR_RED = 10;
    public static final short SS_NONE = 0;
    public static final short SS_SUPER = 1;
    public static final short SS_SUB = 2;
    public static final byte U_NONE = 0;
    public static final byte U_SINGLE = 1;
    public static final byte U_DOUBLE = 2;
    public static final byte U_SINGLE_ACCOUNTING = 33;
    public static final byte U_DOUBLE_ACCOUNTING = 34;
    public static final byte ANSI_CHARSET = 0;
    public static final byte DEFAULT_CHARSET = 1;
    public static final byte SYMBOL_CHARSET = 2;
}
