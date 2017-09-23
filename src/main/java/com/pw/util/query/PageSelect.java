package com.pw.util.query;

import com.pw.util.support.constant.PwConstant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PoemWhite on 2017/4/25.
 */
public class PageSelect extends Select{

    final private Select wrappedSelect;

    final private Page page;

    public PageSelect(Select select, Page page) {
        super(select.getSql(), select.getParameters());

        String sql = getSql();
        sql = String.format("%1$s %2$s", getSql(), "limit ? , ? ");

        setSql(sql);

        List<Object> parameters = new ArrayList<Object>();
        for (Object parameter : getParameters()) {
            parameters.add(parameter);
        }

        int start = page.getStart();
        int limit = page.getLimit();

        if(start<0){
            start = PwConstant.PAGINATE_START_DEFAULT;
        }
        parameters.add(start);

        if(limit<0){
            limit = PwConstant.PAGINATE_LIMIT_DEFAULT;
        }
        parameters.add(limit);

        setParameters(parameters.toArray());

        wrappedSelect = select;
        this.page = page;
    }

    public Select getWrappedSelect() {
        return wrappedSelect;
    }

    public Page getPage() {
        return page;
    }
}