package com.leemanni.spring_Web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
	
	// form 에서 넘어온 데이터를 받아서 다른 페이지로 넘겨주기
	/*
	 * # 구 버전
	@RequestMapping("/check")
	public ModelAndView check(HttpServletRequest request) {
		System.out.println("WebController => check() => check.jsp 연결");
		String hobby = request.getParameter("hobby");
		String weight = request.getParameter("weight");
		String height = request.getParameter("height");
		int age =0;
		try {
			age = Integer.parseInt(request.getParameter("age"));
		} catch (NumberFormatException e) {
		}
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index/checkView");
		modelAndView.addObject("hobby", hobby);
		modelAndView.addObject("weight", weight);
		modelAndView.addObject("height", height);
		modelAndView.addObject("age", age);
		
		modelAndView.addObject("name", "이원희");
		return modelAndView;
	}
	*/
	
	
	// # 신버전 커맨드 클래스 사용
	@RequestMapping("/check")
	public String checkView(@ModelAttribute("vo") UserVO userVO) {
		return "index/checkView";
	}
	
	
	
}






















