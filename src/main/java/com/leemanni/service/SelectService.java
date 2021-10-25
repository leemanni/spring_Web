package com.leemanni.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.ui.Model;

import com.leemanni.dao.WebDAO;
import com.leemanni.vo.MvcboardList;
import com.leemanni.vo.MvcboardVO;

public class SelectService implements WebService{

	@Override
	public void execute(MvcboardVO mvcboardVO) {
		
	}

	@Override
	public void execute(Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request =(HttpServletRequest) map.get("request");
		
		
		AbstractApplicationContext ctx = new  GenericXmlApplicationContext("classpath:application_ctx.xml");
		WebDAO dao = ctx.getBean("dao", WebDAO.class);
		
		int pageSize = 10;
		int currentPage = 1;
		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} catch (Exception e) {
		}
//		System.out.println(currentPage);
		int totalCount = dao.selectCount();
//		System.out.println(totalCount);
		MvcboardList mvcboardList = ctx.getBean("mvcboardList", MvcboardList.class);
		mvcboardList.initMvcboardList(pageSize, totalCount, currentPage);
		
		HashMap<String, Integer> hmap = new HashMap<>();
		hmap.put("endNo", mvcboardList.getEndNo());
		hmap.put("startNo", mvcboardList.getStartNo());
		mvcboardList.setList(dao.selectList(hmap));
//		System.out.println(mvcboardList.getList());
		model.addAttribute(mvcboardList);
		ctx.close();
	}

}
