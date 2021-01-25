package com.poplar.bean;

import lombok.Data;

import java.util.List;

/**
 * Create BY poplar ON 2021/1/24
 */
@Data
public class Dept {

    private Integer deptno;

    private String dname;

    private String loc;

    private List<Emp> emps;
}
