package com.example.space.api.service;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author liyu
 * @date 18-7-26
 */
public interface UserService {

    /**
     * 新增
     * -------------指定配置的事务管理器---------
     * 多数据源时，在声明事务时，需要通过value属性指定配置的事务管理器名
     * -------------指定事务格里级别------------
     * 用isolation属性指定事务隔离级别
     * 默认Default,对于大多数数据库等同于read_commited;
     * read_uncommited:表示一个事务只能读取另一个事务修改但还提交的数据，不能防止脏读和不可重复读，因此很少用
     * read_commited:表示一个事务只能读取另一个事务已经提交的数据，可以防止脏读，推荐使用
     * repeatable_read:表示一个事务在整个过程中可以多次重复执行某个查询，并且每次返回的记录相同，
     *    即使在多次查询之间有新增的数据满足该查询，这些新增的记录也会被忽略，该级别可以防止脏读和不可重复读
     * serializable:所有事务依次逐个执行，事务之间完全不可能产生干扰，可以防止脏读，不可重复读，幻读，但是严重影响程序性能，通常不会用到
     * --------------指定事务的传播行为---------------
     * 所谓事务的传播行为是指，如果在开始当前事务之前，一个事务上下文已经存在，此时有若干选项可以指定一个事务性方法的执行行为
     * REQUIRED：如果当前存在事务，则加入该事务；如果当前没有事务，则创建一个新的事务。
     * SUPPORTS：如果当前存在事务，则加入该事务；如果当前没有事务，则以非事务的方式继续运行。
     * MANDATORY：如果当前存在事务，则加入该事务；如果当前没有事务，则抛出异常。
     * REQUIRES_NEW：创建一个新的事务，如果当前存在事务，则把当前事务挂起。
     * NOT_SUPPORTED：以非事务方式运行，如果当前存在事务，则把当前事务挂起。
     * NEVER：以非事务方式运行，如果当前存在事务，则抛出异常。
     * NESTED：如果当前存在事务，则创建一个事务作为当前事务的嵌套事务来运行；如果当前没有事务，则该取值等价于REQUIRED
     * @param name
     * @param age
     */
    @Transactional(rollbackFor = Exception.class, value = "transactionManagerPrimary", isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
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
