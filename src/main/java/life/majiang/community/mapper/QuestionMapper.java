package life.majiang.community.mapper;

import life.majiang.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {

    @Insert("insert into question(title,description,gtm_create,gtm_modified,creator,tag) " +
            "values (#{title},#{description},#{gtmCreate},#{gtmModified},#{creator},#{tag})")
    public void create(Question question);

//    @Select("select id,title,description,tag,gtm_create as gtmCreate,gtm_modified as gtmModified," +
//            "creator,view_count as viewCount,comment_count as commentCount,like_count as likeCount from question")
    @Select("select * from question")
    List<Question> list();
}
