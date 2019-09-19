package life.majiang.community.mapper;

import life.majiang.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

   @Insert("insert into user(name,account_id,token,gmt_create,gmt_modified) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified})")
    void insert(User user);

   @Select("select id,name,account_id as 'accountId',token,gmt_create as 'gmtCreate',gmt_modified as 'gmtModified' from user where account_id = #{id}")
    User findByAccountId(String id);

   @Select("select id,name,account_id as 'accountId',token,gmt_create as 'gmtCreate',gmt_modified as 'gmtModified' from user where token = #{token}")
    User findByToken(@Param("token") String token);
}

