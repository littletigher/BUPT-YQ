package teleDemo.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import teleDemo.entities.userlogin;
import java.util.List;

@Mapper
public interface UserLoginMapper {
    @Select("select * from userlogin where userName = #{username} and password = #{password}")
    @Results(id="tbInfoMap",value={
            @Result(column = "userID",property = "userID",jdbcType = JdbcType.INTEGER,id = true),
            @Result(column = "userName",property = "userName",jdbcType = JdbcType.VARCHAR),
            @Result(column = "password",property = "password",jdbcType = JdbcType.VARCHAR),
            @Result(column = "role",property = "role",jdbcType = JdbcType.INTEGER),
    })
    List<userlogin> getUserLogin(@Param("username") String username, @Param("password") String password);

    @Select("select * from userlogin where userName = #{username}")
    @ResultMap(value = "tbInfoMap")
    List<userlogin> getUserByName(@Param("username") String username);
}