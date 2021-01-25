package com.poplar.bean;

import lombok.Data;

import java.util.Date;

/**
 * Create BY poplar ON 2021/1/24
 */
@Data
public class Emp {

    private Integer empno;

    private String ename;

    private String job;

    private String mgr;

    private Date hiredate;

    private Double sal;

    private Double comm;

    private Integer deptno;

    private Dept dept;
}
