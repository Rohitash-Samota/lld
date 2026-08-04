package com.example.lld.sqlBuilder;

import java.util.List;

final public class SqlQuery {
    private final String query;
    private final List<Object> parameter;

    public SqlQuery(String q, List<Object> p) {
        this.query = q;
        this.parameter = List.copyOf(p);
    }

    public String getQuery() {
        return query;
    }

    public List<Object> getParameter() {
        return parameter;
    }

    @Override
    public String toString() {
        return "SqlQuery [query=" + query + ", parameter=" + parameter + ", getClass()=" + getClass() + ", hashCode()="
                + hashCode() + ", toString()=" + super.toString() + "]";
    }
}
