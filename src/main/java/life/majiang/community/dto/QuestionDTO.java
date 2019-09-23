package life.majiang.community.dto;

import life.majiang.community.model.User;
import lombok.Data;

@Data
public class QuestionDTO {

    private Integer id;

    private String title;

    private String description;

    private String tag;

    private Long gtmCreate;

    private Long gtmModified;

    private Integer creator;

    private Integer viewCount;

    private Integer commentCount;

    private Integer likeCount;

    private User user;
}
