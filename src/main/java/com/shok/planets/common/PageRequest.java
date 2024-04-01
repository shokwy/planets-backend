package com.shok.planets.common;

import lombok.Data;

import java.io.Serializable;


@Data
public class PageRequest implements Serializable{

    /**
     * 页面大小
     */
    protected int pageSize;

    /**
     * 当前是第几页
     */
    protected int pageNum;

}