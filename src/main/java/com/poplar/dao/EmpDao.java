package com.poplar.dao;

import com.poplar.bean.Emp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Create BY poplar ON 2021/1/24
 */
@Mapper
public interface EmpDao {

    Integer save(Emp emp);

    Integer delete(Integer empno);

    Integer update(Emp emp);

    Emp selectEmpByEmpno(Integer empno);

    Emp selectEmpAndDeptByEmpno(Integer empno);

    List<Emp> selectEmpByCondition(Emp emp);

    List<Emp> selectEmpByConditionByIn(@Param("empnos") List<Integer> empnos);

    List<Emp> selectAll();

    List<Emp> selectEmpByEmpnoAndSal(@Param("deptno") Integer deptno, @Param("sal") Double sal);

    List<Emp> selectEmpByEmpnoAndSalMap(Map<String, Object> map);
}
