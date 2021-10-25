package com.leemanni.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.ui.Model;

import com.leemanni.dao.WebDAO;
import com.leemanni.vo.MvcboardVO;

public class ContentViewService implements WebService {

	@Override
	public void execute(MvcboardVO mvcboardVO) {
		
	}

	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		AbstractApplicationContext ctx = new  GenericXmlApplicationContext("classpath:application_ctx.xml");
		WebDAO dao = ctx.getBean("dao", WebDAO.class);
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		MvcboardVO mvcboardVO = ctx.getBean("mvcboardVO", MvcboardVO.class);
		mvcboardVO = dao.selectByIdx(idx);
		
		model.addAttribute("mvcboardVO", mvcboardVO);
		model.addAttribute("currentPage", request.getParameter("currentPage"));
		model.addAttribute("enter", "\r\n");
		ctx.close();
	}

}
