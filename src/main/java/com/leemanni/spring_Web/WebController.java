package com.leemanni.spring_Web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.leemanni.service.InsertService;
import com.leemanni.service.WebService;
import com.leemanni.vo.UserVO;

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
		return "insert";
	}
	
	
	
	
	
	
	
	
	
	
	// # 신버전 커맨드 클래스 사용
	@RequestMapping("/check")
	public String checkView(@ModelAttribute("vo") UserVO userVO) {
		return "index/checkView";
	}
	
	
	
}






















