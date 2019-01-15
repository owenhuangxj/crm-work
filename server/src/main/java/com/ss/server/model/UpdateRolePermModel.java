package com.ss.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRolePermModel implements Serializable{
    private String roleId;
    private Integer permType;
    private List<String> permVals = new ArrayList<>();
}
