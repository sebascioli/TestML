package com.seba.testml.srv.dto;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class QueryModel {

    @SerializedName("site_id")
    private String site_id;

    @SerializedName("query")
    private String query;

    @SerializedName("paging")
    private Paging paging;

    @SerializedName("results")
    private ArrayList<Product> products = new ArrayList<>();

    @SerializedName("secondary_results")
    private ArrayList<Object> secondary_results = new ArrayList<>();

    @SerializedName("related_results")
    private ArrayList<Object> related_results = new ArrayList<>();

    @SerializedName("sort")
    private Sort sort;

    @SerializedName("available_sorts")
    private ArrayList<Object> available_sorts = new ArrayList<>();

    @SerializedName("filters")
    private ArrayList<Object> filters = new ArrayList<>();

    @SerializedName("available_filters")
    private ArrayList<Object> available_filters = new ArrayList<>();

    public String getSite_id() {
        return site_id;
    }

    public void setSite_id(String site_id) {
        this.site_id = site_id;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public ArrayList<Object> getSecondary_results() {
        return secondary_results;
    }

    public void setSecondary_results(ArrayList<Object> secondary_results) {
        this.secondary_results = secondary_results;
    }

    public ArrayList<Object> getRelated_results() {
        return related_results;
    }

    public void setRelated_results(ArrayList<Object> related_results) {
        this.related_results = related_results;
    }

    public Sort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    public ArrayList<Object> getAvailable_sorts() {
        return available_sorts;
    }

    public void setAvailable_sorts(ArrayList<Object> available_sorts) {
        this.available_sorts = available_sorts;
    }

    public ArrayList<Object> getFilters() {
        return filters;
    }

    public void setFilters(ArrayList<Object> filters) {
        this.filters = filters;
    }

    public ArrayList<Object> getAvailable_filters() {
        return available_filters;
    }

    public void setAvailable_filters(ArrayList<Object> available_filters) {
        this.available_filters = available_filters;
    }
}