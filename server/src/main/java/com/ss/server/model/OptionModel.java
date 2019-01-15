package com.ss.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 通用的选项对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OptionModel implements Serializable{

    private Serializable id;
    private String val;
    private String val2;
    public OptionModel(Serializable id, String val) {
        this.id = id;
        this.val = val;
    }

}
