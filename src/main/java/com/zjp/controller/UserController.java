package com.zjp.controller;

import com.alibaba.druid.util.StringUtils;
import com.zjp.bean.User;
import com.zjp.enums.ResultCodeEnum;
import com.zjp.service.UserService;
import com.zjp.utils.PageResultSet;
import com.zjp.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户控制器
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("login")
    public Result login(String username,String password){
        String login = userService.login(username, password);
        // 登陆成功
        if(StringUtils.equals(login,"success")){
            return Result.success();
        }
        return Result.failure(ResultCodeEnum.UNAUTHORIZED,"用户名或者密码错误");
    }

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result getUserById(@PathVariable int id){
        User user = userService.getUserById(id);
        return Result.success(user);
    }

    /**
     * 获取所有的用户
     * @return
     */
    @GetMapping("/all")
    public Result getAllUser(Integer pageNo){
        if(pageNo == null){
            pageNo = 1;
        }
        PageResultSet<User> resultSet = userService.getAllUser(pageNo);
        return Result.success(resultSet);
    }

    /**
     * 添加用户
     * @param user
     * @return
     */
    @PostMapping("/add")
    public Result addUser(@Validated @RequestBody User user){
        userService.addUser(user);
        return Result.success();
    }

    /**
     * 根据主键更新
     * @param user
     * @return
     */
    @PostMapping("/update")
    public Result UpdateUser(@RequestBody User user){
        userService.updateUser(user);
        return Result.success();
    }

    /**
     * 根据name模糊查询
     * @param name
     * @return
     */
    @GetMapping("/find/like/{name}")
    public Result fingUserike(@PathVariable String name){
        List<User> users = userService.queryByExample1(name);
        return Result.success(users);
    }

    /**
     * 实现 or 查询
     * @param name
     * @param name2
     * @return
     */
    @GetMapping("/find/like2")
    public Result findUserLike2(String name,String name2){
        List<User> users = userService.queryByExample2(name,name2);
        return Result.success(users);
    }

    /**
     * 查询后的数据排序
     * @param age
     * @return
     */
    @GetMapping("/find/order")
    public Result findUserOrder(@RequestParam int age){
        User user = new User();
        user.setAge(age);
        List<User> users = userService.queryByExample3(user);
        return Result.success(users);
    }

    /**
     * 实现动态模糊查询
     * @param username
     * @param name
     * @return
     */
    @GetMapping("find/like3")
    public Result findUser(String username,String name){
        List<User> users = userService.dynamicSql(username, name);
        return Result.success(users);
    }

    /**
     * 使用weekend查询
     * @param username
     * @return
     */
    @GetMapping("find/like4")
    public Result findUserByWeekend(String username){
        List<User> users = userService.queryByWeekend(username);
        return Result.success(users);
    }

    @GetMapping("test")
    public Result test(){
        Map<String,Object> map = new HashMap<>();
        map.put("normal",11);
        map.put("late",11);
        map.put("early",11);
        map.put("missing",11);
        return Result.success(map);
    }
}
