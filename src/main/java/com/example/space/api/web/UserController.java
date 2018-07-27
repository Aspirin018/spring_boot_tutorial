package com.example.space.api.web;

import com.example.space.api.domain.primary.User;
import com.example.space.api.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author liyu
 * @date 18-7-26
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    static Map<Long, User> users = Collections.synchronizedMap(new HashMap<Long, User>());

    @ApiOperation(value = "获取用户列表", notes = "")
    @GetMapping("/")
    public Integer getUserList(){
//        List<User> r = new ArrayList<User>(users.values());
        return userService.getAllUsers();
    }

    @ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    @PostMapping("/")
    public String postUser(@ModelAttribute User user){
        users.put(user.getId(), user);
        return "success";
    }

    @ApiOperation(value = "更新用户详细信息", notes = "根据id获取用户 ")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @GetMapping("{id}")
    public User getUser(@PathVariable Long id){
        return users.get(id);
    }

    @ApiOperation(value = "更新用户", notes = "根据id更新用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "user", value = "用户实体user", required = true, dataType = "User")
    })
    @PutMapping("{id}")
    public String putUser(@PathVariable Long id, @ModelAttribute User user){
        User u = users.get(id);
        u.setName(user.getName());
        u.setAge(user.getAge());
        users.put(id, u);
        return "success";
    }

    @ApiOperation(value = "删除用户", notes = "根据id删除用户")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @DeleteMapping
    public String deleteUser(@PathVariable Long id) {
        users.remove(id);
        return "success";
    }


}
