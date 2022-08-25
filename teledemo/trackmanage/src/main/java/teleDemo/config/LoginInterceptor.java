package teleDemo.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import teleDemo.entities.userlogin;
import teleDemo.mapper.UserLoginMapper;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 登录拦截器
 *
 * @author pan_junbiao
 **/
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Resource
    UserLoginMapper userLoginMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //如果需要把Cookie发到服务端，需要指定Access-Control-Allow-Credentials字段为true;
        response.setHeader("Access-Control-Allow-Credentials", "true");
        //允许跨域的域名，*号为允许所有,存在被 DDoS攻击的可能。
        response.setHeader("Access-Control-Allow-Origin","http://127.0.0.1:5501");
        //表明服务器支持的头信息字段
        response.setHeader("Access-Control-Allow-Headers","content-type");

/*服务端在response的header中配置"Access-Control-Allow-Origin", "http://xxx:${port}";
服务端在response的header中配置"Access-Control-Allow-Credentials", "true"
* */
        String uri = request.getRequestURI();

        //判断当前请求地址是否登录地址或首页地址
        if (uri.contains("login") || uri.contains("index")) {
            //登录请求，直接放行
            return true;
        } else {
            //判断用户是否登录
            if (request.getCookies() != null) {
                //说明已经登录，放行
                //调用解密方法
                String token="";
                for (Cookie cookie:request.getCookies()){
                    if(cookie.getName().equals("token")){
                        token=cookie.getValue();
                    }
                }
                if(token.equals("")){
                    //没有登录，重定向到登录界面
                    response.sendRedirect(request.getContextPath() + "/login");
                }
                userlogin user = CreatJwt.getUser(token);
                List<userlogin> list = userLoginMapper.getUserByName(user.getUserName());
                if(list!=null&&list.size()!=0){
                    return true;
                }
                response.sendRedirect(request.getContextPath() + "/login");
            } else {
                //没有登录，重定向到登录界面
                response.sendRedirect(request.getContextPath() + "/login");
            }
        }

        //默认拦截
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}