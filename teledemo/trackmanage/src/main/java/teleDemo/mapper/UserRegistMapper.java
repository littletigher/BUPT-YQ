package teleDemo.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;
import teleDemo.entities.UserRegist;
import teleDemo.entities.userlogin;

@Mapper //标记mapper文件位置，否则在Application.class启动类上配置mapper包扫描
@Repository
public interface UserRegistMapper {

    @Select(value = "select u.userName,u.password from userlogin u where u.userName=#{username}")
    @Results
            ({@Result(property = "userName", column = "userName"),
                    @Result(property = "password", column = "password")})
    public userlogin findUserByName(@Param("username") String username);


    @Insert("insert into userlogin (userName,password) values(#{username},#{password})")
    //加入该注解可以保存对象后，查看对象插入id
    //@Options(useGeneratedKeys = true, keyProperty = "userID")
//    @Results
//            ({@Result(column = "userID",property = "userID",jdbcType = JdbcType.INTEGER,id = true),
//                    @Result(column = "userName",property = "userName",jdbcType = JdbcType.VARCHAR),
//                    @Result(column = "password",property = "password",jdbcType = JdbcType.VARCHAR),
//                    @Result(column = "role",property = "role",jdbcType = JdbcType.INTEGER),
//            })
    public void regist(@Param("username") String username, @Param("password") String password);

}

