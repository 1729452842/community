package life.majiang.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO {

    private List<QuestionDTO> questions;
    private boolean showPrevious;//上一页
    private boolean showFirstPage;//显示第一页
    private boolean showNext;//下一页
    private boolean showEndPage;//显示最后一页

    private Integer page;

    private List<Integer> pages = new ArrayList<Integer>();


    public void setPagination(Integer totalCount, Integer page, Integer size) {
        Integer totalPage = (totalCount % size == 0) ? (totalCount / size) : (totalCount / size + 1);

        pages.add(page);
        for (int i = 1; i <= 3; i++) {
            if(page - 1 > 0){
                pages.add(page - 1,0);
            }
            if(page + 1 <= totalCount){
                pages.add(page + 1);
            }
            
        }

        showPrevious = (page == 1) ? false : true;

        showNext = (page == totalPage) ? false : true;

        showFirstPage = (pages.contains(1)) ? false : true;

        showEndPage = (pages.contains(totalPage)) ? false : true;
    }
}
