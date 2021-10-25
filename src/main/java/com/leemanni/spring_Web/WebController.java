package com.leemanni.spring_Web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.leemanni.service.ContentViewService;
import com.leemanni.service.DeleteService;
import com.leemanni.service.IncrementService;
import com.leemanni.service.InsertService;
import com.leemanni.service.SelectService;
import com.leemanni.service.UpdateService;
import com.leemanni.service.WebService;
import com.leemanni.vo.UserVO;
import com.mysql.jdbc.UpdatableResultSet;

@Controller
public class WebController {

	@RequestMapping("/view")
	public String view() {
		System.out.println("WebController => view() => view.jsp 연결");
		return "view";
	}
	
	@RequestMapping("/login")
	public String login(Model model) {
		return "login";
	}
	
	@RequestMapping("/loginOK")
	public String loginOK() {
		return "redirect:index";
	}
	
	@RequestMapping("/index")
	public ModelAndView index() {
		System.out.println("WebController => index() => index.jsp 연결");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index/index");
		modelAndView.addObject("name", "이원희");
		return modelAndView;
	}
	
	// # 신버전 커맨드 클래스 사용
	@RequestMapping("/check")
	public String checkView(@ModelAttribute("vo") UserVO userVO) {
		return "index/checkView";
	}
//	====================================================================================================================
	
	@RequestMapping("/insert")
	public String insert() {
		return"insert";
	}
	
	@RequestMapping("/insertOK")
	public String insertOK(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		AbstractApplicationContext ctx = new  GenericXmlApplicationContext("classpath:application_ctx.xml");
		WebService service =  ctx.getBean("insert", InsertService.class);
		service.execute(model);
		ctx.close();
		return "redirect:list";
	}
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		AbstractApplicationContext ctx = new  GenericXmlApplicationContext("classpath:application_ctx.xml");
		WebService service =  ctx.getBean("select", SelectService.class);
		service.execute(model);
		ctx.close();
		return "list";
	}
	
//	선택된 글 조회수 1 증가
	@RequestMapping("/increment")
	public String increment(HttpServletRequest request, Model model) {
		System.out.println("DTO => increment");
		model.addAttribute("request", request);
		AbstractApplicationContext ctx = new  GenericXmlApplicationContext("classpath:application_ctx.xml");
		WebService service =  ctx.getBean("increment", IncrementService.class);
		service.execute(model);
		ctx.close();
		int idx =  Integer.parseInt(request.getParameter("idx"));
		int currentPage =  Integer.parseInt(request.getParameter("currentPage"));
		model.addAttribute("idx", idx);
		model.addAttribute("currentPage", currentPage);
		return "redirect:contentView";
	}
	
	
//	선택된 글 보여주기
	@RequestMapping("/contentView")
	public String contnetView(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		AbstractApplicationContext ctx = new  GenericXmlApplicationContext("classpath:application_ctx.xml");
		WebService service = ctx.getBean("contentView", ContentViewService.class);
		service.execute(model);
		ctx.close();
		return "contentView";
	}
	
	@RequestMapping("/update")
	public String update(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		AbstractApplicationContext ctx = new  GenericXmlApplicationContext("classpath:application_ctx.xml");
		WebService service = ctx.getBean("update", UpdateService.class);
		service.execute(model);
		int idx =  Integer.parseInt(request.getParameter("idx"));
		int currentPage =  Integer.parseInt(request.getParameter("currentPage"));
		
		model.addAttribute("idx", idx);
		model.addAttribute("currentPage", currentPage);
		return "redirect:list";
	}
	
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		AbstractApplicationContext ctx = new  GenericXmlApplicationContext("classpath:application_ctx.xml");
		WebService service = ctx.getBean("delete", DeleteService.class);
		service.execute(model);
		
		
		
		int currentPage =  Integer.parseInt(request.getParameter("currentPage"));
		model.addAttribute("currentPage", currentPage);
		return "redirect:list";
	}
	
	
}






















