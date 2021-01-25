package com.poplar.dao;

import com.poplar.bean.User;

/**
 * Create BY poplar ON 2021/1/24
 */
public interface UserDao {

    Integer saveUser(User user);

    User selectEmpByUserId(Integer id);
}
