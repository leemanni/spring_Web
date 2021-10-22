package com.leemanni.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.ui.Model;

import com.leemanni.dao.WebDAO;
import com.leemanni.vo.MvcboardVO;

public class InsertService implements WebService{

	@Override
	public void execute(MvcboardVO mvcboardVO) {}

	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request= (HttpServletRequest) map.get("request");
		String name = request.getParameter("name");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		AbstractApplicationContext ctx = new  GenericXmlApplicationContext("classpath:application_ctx.xml");
		MvcboardVO mvcboardVO = ctx.getBean("mvcboardVO", MvcboardVO.class);
		mvcboardVO.setName(name);
		mvcboardVO.setContent(content);
		mvcboardVO.setSubject(subject);
		WebDAO dao =ctx.getBean("dao", WebDAO.class);
		dao.insert(mvcboardVO);
		
		ctx.close();
		
	}

}


















