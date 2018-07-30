package com.example.space.api.domain.primary;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author liyu
 * @date 18-7-26
 */
@Entity
@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 1124967796278684883L;

    @Id
    @GeneratedValue
    private Long id;

    /**
     * 为测试事务，设置长度不超过5， 默认长度255， 设置后需要重新运行使在数据库中生效
     */
    @Column(nullable = false, length = 5)
    private String name;

    @Column(nullable = false)
    private Integer age;

    public Long getId() {
        return id;
    }

    public  User(){}

    public User(String name, Integer age){
        this.name = name;
        this.age = age;
    }

    public User(Long id, String username, Integer age) {
        this.id = id;
        this.name = username;
        this.age = age;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
