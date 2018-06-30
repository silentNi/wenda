package com.newcoder.wenda.dao;

import com.newcoder.wenda.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDAO {
   String TABLE_NAME=" user ";
   String INSERT_FIELDS=" name, password, salt, head_url ";
   String SELECT_FIELDS=" id, "+INSERT_FIELDS;

    @Insert({" insert into ",TABLE_NAME,"(",INSERT_FIELDS,")"
            , " values(#{name},#{password},#{salt},#{headUrl})"})
    int addUser(User user);
}
