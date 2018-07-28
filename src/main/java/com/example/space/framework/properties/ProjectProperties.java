package com.example.space.framework.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author liyu
 * @date 18-7-28
 */
@Component
public class ProjectProperties {

    @Value("${com.example.space.name}")
    private String name;

    @Value("${com.example.space.title}")
    private String title;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
