package com.shok.planets.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 通用删除请求
 *
 * @author yupi
 */
@Data
public class DeleteRequest implements Serializable {

    private long id;
}