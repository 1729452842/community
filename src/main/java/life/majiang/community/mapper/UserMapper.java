package life.majiang.community.mapper;

import life.majiang.community.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

   @Insert("insert into user(name,account_id,token,gmt_create,gmt_modified,avatar_url) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insert(User user);

   @Select("select * from user where account_id = #{accountId}")
  // @Select("select id,name,account_id as 'accountId',token,gmt_create as 'gmtCreate',gmt_modified as 'gmtModified', avatar_url as avatarUrl from user where id = #{id}")
    User findByAccountId(@Param("accountId")String  accountId);

   //@Select("select id,name,account_id as 'accountId',token,gmt_create as 'gmtCreate',gmt_modified as 'gmtModified' from user where token = #{token}")
   @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);

    @Select("select * from user where id = #{id}")
    User findById(@Param("id")Integer id);

    @Update("update user set name = #{name}, token = #{token},gmt_modified = #{gmtModified}, avatar_url = #{avatarUrl} where id = #{id}")
    void update(User user);
}

