package com.demo.service;

import com.demo.entity.User;

/**
 * Created by baolei on 2016/3/10.
 */
public interface IUserService {
    public User selectByPrimaryKey(Long id);
    public User getByUsername(String username);
}
