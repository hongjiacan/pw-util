package com.pw.util.query;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PoemWhite on 2017/4/25.
 */
public class QueryResult<T> {

    final private List<T> pojos;

    final private int total;

    public QueryResult(List<T> pojos, int total) {
        if (pojos == null) {
            pojos = new ArrayList<T>();
        }
        if (total < pojos.size()) {
            throw new IllegalArgumentException("pojos size doesn't equal total size");
        }
        this.pojos = pojos;
        this.total = total;
    }

    public List<T> getPojos() {
        return pojos;
    }

    public int getTotal() {
        return total;
    }

    public T getPojo() {
        return (pojos.size() > 0 ? pojos.get(0) : null);
    }
}