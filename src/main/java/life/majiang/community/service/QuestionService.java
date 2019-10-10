package life.majiang.community.service;

import life.majiang.community.dto.PaginationDTO;
import life.majiang.community.dto.QuestionDTO;
import life.majiang.community.mapper.QuestionMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.Question;
import life.majiang.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    public PaginationDTO list(Integer page, Integer size) {

        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = questionMapper.count();
        paginationDTO.setPagination(totalCount,page,size);
        if(page < 1){
            page = 1;
        }
        if(page > paginationDTO.getTotalPage()) {
            page = paginationDTO.getTotalPage();
        }

        Integer offset = size * (page - 1);

        List<Question> questions = questionMapper.list(offset,size);
        List<QuestionDTO> questionDTOS = new ArrayList<>();

        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOS);
        return paginationDTO;
    }

    public PaginationDTO list(int userId, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = questionMapper.countByUserId(userId);
        paginationDTO.setPagination(totalCount,page,size);
        if(page < 1){
            page = 1;
        }
        if(page > paginationDTO.getTotalPage()) {
            page = paginationDTO.getTotalPage();
        }

        Integer offset = size * (page - 1);

        List<Question> questions = questionMapper.listByUserId(userId,offset,size);

        List<QuestionDTO> questionDTOS = new ArrayList<>();

        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOS);

        return paginationDTO;
    }

    public QuestionDTO getById(Integer id) {

        Question question = questionMapper.getById(id);
        QuestionDTO questionDTO = new QuestionDTO();
        User user = userMapper.findById(question.getCreator());
        questionDTO.setUser(user);
        BeanUtils.copyProperties(question,questionDTO);
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if(question.getId() == null){
            question.setGtmCreate(System.currentTimeMillis());
            question.setGtmModified(question.getGtmCreate());
            questionMapper.create(question);
        }else{
            question.setGtmModified(question.getGtmCreate());
            questionMapper.update(question);
        }
    }
}
