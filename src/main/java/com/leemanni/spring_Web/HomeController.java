package com.leemanni.spring_Web;

import java.util.HashMap;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.leemanni.dao.DAO;
import com.leemanni.service.ContentViewService;
import com.leemanni.service.DeleteService;
import com.leemanni.service.IncrementService;
import com.leemanni.service.InsertService;
import com.leemanni.service.SelectService;
import com.leemanni.service.UpdateService;
import com.leemanni.service.WebService;
import com.leemanni.vo.MvcboardList;
import com.leemanni.vo.MvcboardVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	@Autowired
	private SqlSession sqlSession;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "redirect:list";
	}

	@RequestMapping("/insert")
	public String insert() {
		return "insert";
	}

	@RequestMapping("/insertOK")
	public String insertOK(HttpServletRequest request, Model model, MvcboardVO mvcboardVO) {

		DAO dao = sqlSession.getMapper(DAO.class);

		String name = request.getParameter("name");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		mvcboardVO.setName(name);
		mvcboardVO.setContent(content);
		mvcboardVO.setSubject(subject);

		dao.insert(mvcboardVO);

		return "redirect:list";
	}

	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model) {

		DAO dao = sqlSession.getMapper(DAO.class);
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:application_ctx.xml");

		int pageSize = 10;
		int currentPage = 1;
		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} catch (Exception e) {
		}

		int totalCount = dao.selectCount();

		MvcboardList mvcboardList = ctx.getBean("mvcboardList", MvcboardList.class);
		mvcboardList.initMvcboardList(pageSize, totalCount, currentPage);
		HashMap<String, Integer> hmap = new HashMap<>();
		hmap.put("endNo", mvcboardList.getEndNo());
		hmap.put("startNo", mvcboardList.getStartNo());
		mvcboardList.setList(dao.selectList(hmap));

		ctx.close();
		model.addAttribute(mvcboardList);
		return "list";
	}

//	선택된 글 조회수 1 증가
	@RequestMapping("/increment")
	public String increment(HttpServletRequest request, Model model) {
		DAO dao = sqlSession.getMapper(DAO.class);
		int idx = Integer.parseInt(request.getParameter("idx"));
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		dao.increment(idx);

		model.addAttribute("idx", idx);
		model.addAttribute("currentPage", currentPage);
		return "redirect:contentView";
	}

//	선택된 글 보여주기
	@RequestMapping("/contentView")
	public String contnetView(HttpServletRequest request, Model model) {
		DAO dao = sqlSession.getMapper(DAO.class);
		int idx = Integer.parseInt(request.getParameter("idx"));
		MvcboardVO mvcboardVO = dao.selectByIdx(idx);
		model.addAttribute("mvcboardVO", mvcboardVO);
		model.addAttribute("currentPage", Integer.parseInt(request.getParameter("currentPage")));
		model.addAttribute("enter", "\r\n");

		return "contentView";
	}

	@RequestMapping("/update")
	public String update(HttpServletRequest request, Model model, MvcboardVO mvcboardVO) {
		DAO dao = sqlSession.getMapper(DAO.class);

		int idx = Integer.parseInt(request.getParameter("idx"));
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));

		dao.update(mvcboardVO);

		model.addAttribute("idx", idx);
		model.addAttribute("currentPage", currentPage);
		return "redirect:list";
	}

	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model) {
		DAO dao = sqlSession.getMapper(DAO.class);
		int idx = Integer.parseInt(request.getParameter("idx"));
		dao.delete(idx);

		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		model.addAttribute("currentPage", currentPage);
		return "redirect:list";
	}

}
