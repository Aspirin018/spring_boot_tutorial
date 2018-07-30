package com.example.space.api.service;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author liyu
 * @date 18-7-26
 */
public interface UserService {

    /**
     * 新增
     * @param name
     * @param age
     */
    @Transactional(rollbackFor = Exception.class)
    void create(String name, Integer age);

    /**
     * 根据name删除
     * @param name
     */
    @Transactional(rollbackFor = Exception.class)
    void deleteByName(String name);

    /**
     * 查询所有
     * @return
     */
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    Integer getAllUsers();

    /**
     * 删除所有
     */
    @Transactional(rollbackFor = Exception.class)
    void deleteAllUsers();
}
