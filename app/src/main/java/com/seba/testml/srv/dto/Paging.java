package com.seba.testml.srv.dto;

import com.google.gson.annotations.SerializedName;

public class Paging {

    @SerializedName("total")
    private int total;

    @SerializedName("offset")
    private int offset;

    @SerializedName("limit")
    private int limit;

    @SerializedName("primary_results")
    private int primary_results;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getPrimary_results() {
        return primary_results;
    }

    public void setPrimary_results(int primary_results) {
        this.primary_results = primary_results;
    }
}
