package com.pw.util.query;

/**
 * Created by PoemWhite on 2017/4/25.
 */
public class Select {

    private String selectConnectionName;

    private String countConnectionName;

    private String sql;

    private Object[] parameters;

    public Select(String sql, Object[] parameters) {
        super();
        this.sql = sql;
        this.parameters = parameters;
    }

    public String getSelectConnectionName() {
        return selectConnectionName;
    }

    public void setSelectConnectionName(String selectConnectionName) {
        this.selectConnectionName = selectConnectionName;
    }

    public String getCountConnectionName() {
        return countConnectionName;
    }

    public void setCountConnectionName(String countConnectionName) {
        this.countConnectionName = countConnectionName;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public Object[] getParameters() {
        if (parameters == null) {
            parameters = new Object[]{};
        }
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }
}
