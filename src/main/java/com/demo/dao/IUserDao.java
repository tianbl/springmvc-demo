package com.demo.dao;

import com.demo.entity.User;

/**
 * Created by baolei on 2016/3/10.
 */
public interface IUserDao {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}
