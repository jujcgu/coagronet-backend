package com.coagronet.pages;

import java.util.List;
import java.util.Map;

public class PaginatedResponse<T> {
    private Map<String, Object> pageInfo;
    private List<T> data;

    // Getters y setters
    public Map<String, Object> getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(Map<String, Object> pageInfo) {
        this.pageInfo = pageInfo;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
