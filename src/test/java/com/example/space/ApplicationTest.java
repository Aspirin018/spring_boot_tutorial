package com.example.space;

import com.example.space.web.HelloController;
import com.example.space.web.UserController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import  org.springframework.test.web.servlet.result.MockMvcResultMatchers;


/**
 * @author liyu
 * @date 18-7-26
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MockServletContext.class)
@WebAppConfiguration
public class ApplicationTest {

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        //运行测试前需要改动测试的controller
        mockMvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
    }

    @Test
    public void getHello() throws Exception {
        mockMvc.perform(get("/hello").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Hello World")));
    }

    @Test
    public void testUserController() throws Exception {
        RequestBuilder requestBuilder = null;
 /*
       //1. get
        requestBuilder = get("/users/");
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));

        //2.post
        requestBuilder = post("/users/")
                .param("id", "1")
                .param("name", "testname")
                .param("age", "20");
        mockMvc.perform(requestBuilder)
                .andExpect(content().string(equalTo("success")));
*/

        //3.get users
        requestBuilder = get("/users/");
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[{\"id\":1,\"name\":\"testname\",\"age\":20}]")));
/*
        //4.put
        requestBuilder = put("/users/1")
                .param("name", "changedname")
                .param("age", "30");
        mockMvc.perform(requestBuilder)
                .andExpect(content().string(equalTo("success")));

        //5.get id=1
        requestBuilder = get("/users/1");
        mockMvc.perform(requestBuilder)
                .andExpect(content().string(equalTo({\"id\":1,\"name\":\"测试终极大师\",\"age\":30}")));


        //6. delete id=1
        requestBuilder = delete("/users/1");
        mockMvc.perform(requestBuilder)
                .andExpect(content().string(equalTo("success")));
*/
    }
}
