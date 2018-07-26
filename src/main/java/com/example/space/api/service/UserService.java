package com.example.space.api.service;

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
    void create(String name, Integer age);

    /**
     * 根据name删除
     * @param name
     */
    void deleteByName(String name);

    /**
     * 查询所有
     * @return
     */
    Integer getAllUsers();

    /**
     * 删除所有
     */
    void deleteAllUsers();
}
