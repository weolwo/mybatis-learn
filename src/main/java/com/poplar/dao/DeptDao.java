package com.poplar.dao;

import com.poplar.bean.Dept;
import com.poplar.bean.Emp;
import org.apache.ibatis.annotations.Mapper;

/**
 * Create BY poplar ON 2021/1/24
 */
@Mapper
public interface DeptDao {


    Dept selectDeptByDeptno(Integer deptno);

    Dept selectDeptByDeptnoBySingle(Integer deptno);

    Emp selectEmpByDeptno(Integer deptno);
}
