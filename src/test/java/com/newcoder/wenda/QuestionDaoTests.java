package com.newcoder.wenda;

import com.newcoder.wenda.dao.QuestionDAO;
import com.newcoder.wenda.dao.UserDAO;
import com.newcoder.wenda.model.Question;
import com.newcoder.wenda.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
//@Sql("/init-schema.sql")
public class QuestionDaoTests {
    @Autowired
    QuestionDAO questionDAO;

    @Test
    public void questionDaoTests() {

        List<Question> questions=questionDAO.selectLatestQuestions(0,0,10);
        System.out.println(questions.size());
    }

}
