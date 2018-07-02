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

import java.util.Date;
import java.util.List;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
//@Sql("/init-schema.sql")
public class InitDataBaseTests {
	@Autowired
	UserDAO userDAO;
	@Autowired
	QuestionDAO questionDAO;

	@Test
	public void initDatabase() {
		Random random = new Random();
		for(int i=1; i<11;++i){
//			User user =new User();
//			user.setName(String.format("USER%d",i));
//			user.setPassword("");
//			user.setSalt("");
//			user.setHeadUrl(String.format("http://images.newcoder.com/head/%dt.png",random.nextInt(1000)));
//			userDAO.addUser(user);

//			Question question = new Question();
//			question.setTitle(String.format("title{%d}",i));
//			question.setContent("balalal");
//			question.setCreateDate(new Date());
//			question.setUserId(i);
//			question.setCommentCount(11-i);
//			questionDAO.addQuestion(question);
		}

	}

}
