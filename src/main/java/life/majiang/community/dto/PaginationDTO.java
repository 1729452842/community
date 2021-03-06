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
    private Integer totalPage;
    private Integer page;

    private List<Integer> pages = new ArrayList<>();


    public void setPagination(Integer totalCount, Integer page, Integer size) {

        totalPage = (totalCount % size == 0) ? (totalCount / size) : (totalCount / size + 1);
        if(totalPage == 0){
            totalPage = 1;
        }

        if(page < 1){
            page = 1;
        }
        if(page > totalPage) {
            page = totalPage;
        }
        this.page = page;

        pages.add(page);
        for (int i = 1; i <= 3; i++) {

            if(page - i >= 1){
                pages.add(0,page - i);
                //System.out.println("头："+page+pages);
            }

            if(page + i <= totalPage){
                pages.add(page + i);
               // System.out.println("尾部："+page+pages);
            }
        }

        showPrevious = (page == 1) ? false : true;

        showNext = (page == totalPage) ? false : true;

        showFirstPage = (pages.contains(1)) ? false : true;

        showEndPage = (pages.contains(totalPage)) ? false : true;
    }
}
