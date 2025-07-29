package com.bookNet.bookNet.common;

public enum SearchCondition {

    EQUAL("="),

    LESS_THAN ("<"),

    GREATER_THAN(">"),

    LESS_THAN_OR_EQUAL("<="),

    GREATER_THAN_OR_EQUAL(">=");


    private final String searchCondition;

    public String getSearchCondition() {
        return searchCondition;
    }

    SearchCondition(String symbol) {
        this.searchCondition = symbol;
    }

}
