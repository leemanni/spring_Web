package com.leemanni.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.ui.Model;

import com.leemanni.dao.WebDAO;
import com.leemanni.vo.MvcboardVO;

public class DeleteService implements WebService {

	@Override
	public void execute(MvcboardVO mvcboardVO) {
		
	}

	@Override
	public void execute(Model model) {
		System.out.println("DeleteService => execute()");
		Map<String, Object> map = model.asMap();
		HttpServletRequest request =(HttpServletRequest) map.get("request");
		GenericXmlApplicationContext context = new GenericXmlApplicationContext("classpath:application_ctx.xml");
		WebDAO dao =context.getBean("dao", WebDAO.class);
		dao.delete(Integer.parseInt(request.getParameter("idx")));
	}

}
