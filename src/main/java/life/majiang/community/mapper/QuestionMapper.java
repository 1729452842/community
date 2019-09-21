package life.majiang.community.mapper;

import life.majiang.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper {

    @Insert("insert into question(title,description,gtm_create,gtm_modified,creator,tag) " +
            "values (#{title},#{description},#{gtmCreate},#{gtmModified},#{creator},#{tag})")
    public void create(Question question);
}
