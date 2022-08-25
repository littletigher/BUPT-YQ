package teleDemo.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;
import teleDemo.config.CreatJwt;
import teleDemo.entities.*;
import teleDemo.mapper.UserLoginMapper;
import teleDemo.mapper.UserRegistMapper;
import teleDemo.mapper.userInfoMapper;
import teleDemo.service.TrackService;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class testController {
    @Resource
    teleDemo.mapper.comInfoMapper comInfoMapper;

    @GetMapping("/advice")
    public String test() {
        int i = 1 / 0;
        return "test success";
    }

    @Resource
    UserLoginMapper userLoginMapper;

    @PostMapping("/login")
    public PostVo<String> login(@Param("username")String username, @Param("password")String password, HttpServletResponse response) {
        List<userlogin> list=userLoginMapper.getUserLogin(username,password);
        if(!list.isEmpty()){
            Cookie cookie=new Cookie("token", CreatJwt.getoken(list.get(0)));
            cookie.setHttpOnly(true);
            response.addCookie(cookie);
            return new PostVo<String>(0,"登录成功！",null);
        }else{
            return new PostVo<String>(-1,"登录失败！",null);
        }
    }

    @Resource
    UserRegistMapper userRegistMapper;

    @PostMapping("/regist")
    public UserRegist regist(@Param("username")String username, @Param("password")String password){
        UserRegist result=new UserRegist();
        result.setResult(-1);

        userlogin existUser= userRegistMapper.findUserByName(username);
        if(existUser!=null){
            result.setMsg("用户名已存在");
        }else{
            userRegistMapper.regist(username,password);
            result.setUserName(username);
            result.setPassword(password);
            result.setMsg("注册成功");
            result.setResult(0);
        }
        return result;
    }
}