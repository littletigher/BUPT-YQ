package teleDemo.config;


import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class MyMvcConfig extends WebMvcAutoConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .maxAge(3600)
                .allowCredentials(true);
    }

    /**
     * 控制器配置
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/toIndexPage").setViewName("/index");
        registry.addViewController("/").setViewName("/index");
    }



    /**
     * 拦截器配置
     */

//    @Bean
//    public HandlerInterceptor getMyInterceptor(){
//        return new LoginInterceptor();
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        //注册Interceptor拦截器
//        InterceptorRegistration registration = registry.addInterceptor(getMyInterceptor());
//        registration.addPathPatterns("/**"); //所有路径都被拦截
//        registration.excludePathPatterns( //添加不拦截路径
//                "/index", //登录页面
//                "/login",       //登录请求
//                "/**/*.html",   //html静态资源
//                "/**/*.js",     //js静态资源
//                "/**/*.css"     //css静态资源
//        );
//    }
}
