package com.pw.util.query;

/**
 * Created by PoemWhite on 2017/4/25.
 */
public class RecordCountSelect extends Select{

    public static final String RECORD_COUNT = "lfr_record_count";

    final private Select wrappedSelect;

    public RecordCountSelect(Select select) {
        super(select.getSql(), select.getParameters());
        setSql(String.format("select count(1) %1$s from (%2$s) t", RECORD_COUNT, getSql()));
        wrappedSelect = select;
    }

    public Select getWrappedSelect() {
        return wrappedSelect;
    }
}