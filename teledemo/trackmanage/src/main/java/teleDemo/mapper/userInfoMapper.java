package teleDemo.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import teleDemo.entities.tbInfo;
import teleDemo.entities.tbuser;

import java.util.List;

@Mapper
public interface userInfoMapper {
    @Select("SELECT * FROM tb_user;")
    @Results(id = "tbUserMap", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "phone_number", property = "phoneNumber", jdbcType = JdbcType.LONGVARCHAR),
            @Result(column = "status", property = "status", jdbcType = JdbcType.VARCHAR),
    })
    List<tbuser> getAlltbUser();

    @Select("select * from tb_user limit #{pageNum}, #{limit};")
    @ResultMap(value = "tbUserMap")
    List<tbuser> gettbUserByPage(@Param("pageNum") int pageNum, @Param("limit") int limit);

    @Update("UPDATE tb_user SET status = #{status} WHERE id = #{id};")
    @ResultMap(value = "tbUserMap")
    int changeUser(@Param("status") String status, @Param("id") int id);

    @Select("select * from tb_user where id = #{id};")
    @ResultMap(value = "tbUserMap")
    tbuser gettbUserById(@Param("id") int id);

    @Delete("DELETE FROM teledata.tb_user WHERE id = #{id};")
    int deleteUserById(@Param("id") int id);
}

