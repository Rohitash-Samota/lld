package com.example.lld.sqlBuilder;

import com.example.lld.sqlBuilder.enums.JoinType;

public class JoinDTO {
    private JoinType joinType;
    private String table;
    private String colA;
    private String colB;

    public JoinDTO(JoinType joinType, String table, String colA, String colB) {
        this.joinType = joinType;
        this.table = table;
        this.colA = colA;
        this.colB = colB;
    }

    public JoinType getJoinType() {
        return joinType;
    }

    public void setJoinType(JoinType joinType) {
        this.joinType = joinType;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getColA() {
        return colA;
    }

    public void setColA(String colA) {
        this.colA = colA;
    }

    public String getColB() {
        return colB;
    }

    public void setColB(String colB) {
        this.colB = colB;
    }

}
