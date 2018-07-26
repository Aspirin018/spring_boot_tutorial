package com.example.space.web;

import com.example.space.domain.User;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author liyu
 * @date 18-7-26
 */
@RestController
@RequestMapping("/users")
public class UserController {

    static Map<Long, User> users = Collections.synchronizedMap(new HashMap<Long, User>());

    @GetMapping("/")
    public List<User> getUserList(){
        List<User> r = new ArrayList<User>(users.values());
        return r;
    }

    @PostMapping("/")
    public String postUser(@ModelAttribute User user){
        users.put(user.getId(), user);
        return "success";
    }

    @GetMapping("{id}")
    public User getUser(@PathVariable Long id){
        return users.get(id);
    }

    @PutMapping("{id}")
    public String putUser(@PathVariable Long id, @ModelAttribute User user){
        User u = users.get(id);
        u.setName(user.getName());
        u.setAge(user.getAge());
        users.put(id, u);
        return "success";
    }

    @DeleteMapping
    public String deleteUser(@PathVariable Long id) {
        users.remove(id);
        return "success";
    }


}
