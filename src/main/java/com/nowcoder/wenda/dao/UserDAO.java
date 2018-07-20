package com.nowcoder.wenda.dao;

import com.nowcoder.wenda.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserDAO {

    String TABLE_NAME = " user ";
    String INSERT_FIELDS = " name, password, salt, head_url ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Insert({" insert into ", TABLE_NAME, "(", INSERT_FIELDS, ")"
            , " values(#{name},#{password},#{salt},#{headUrl})"})
    int addUser(User user);

    @Update({" update from ", TABLE_NAME, " set password =#{pwd}  ", " where id=#{id}"})
    int updatePassword(@Param("id") int id,
                       @Param("pwd") String pwd);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where id=#{id}"})
    User selectUserById(int id);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where name=#{name}"})
    List<User> selectUserByName(String name);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where name=#{name} and password=#{password}"})
    List<User> userJudge(String name, String password);
}
