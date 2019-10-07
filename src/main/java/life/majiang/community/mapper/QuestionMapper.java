package life.majiang.community.mapper;

import life.majiang.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {

    @Insert("insert into question(title,description,gtm_create,gtm_modified,creator,tag) " +
            "values (#{title},#{description},#{gtmCreate},#{gtmModified},#{creator},#{tag})")
    public void create(Question question);

//    @Select("select id,title,description,tag,gtm_create as gtmCreate,gtm_modified as gtmModified," +
//            "creator,view_count as viewCount,comment_count as commentCount,like_count as likeCount from question")
    @Select("select * from question limit #{offset},#{size}")
    List<Question> list(@Param(value = "offset") Integer offset, @Param(value = "size")Integer size);

    @Select("select count(1) from question")
    Integer count();

    @Select("select * from question where creator = #{userId} limit #{offset},#{size}")
    List<Question> listByUserId(@Param(value = "userId")int userId, @Param(value = "offset")Integer offset, @Param(value = "size")Integer size);

    @Select("select count(1) from question where creator = #{userId}")
    Integer countByUserId(@Param(value = "userId")Integer userId);

    @Select("select * from question where id = #{id}")
    Question getById(@Param("id")Integer id);
}
