package com.leemanni.spring_Web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebController {

	@RequestMapping("/view")
	public String view() {
		System.out.println("WebController => view() => view.jsp 연결");
		return "view";
	}
	
	@RequestMapping("/login")
	public String login(Model model) {
		System.out.println("WebController => login() => login.jsp 연결");
		model.addAttribute("name", "이원희");
		return "login";
	}
	
	
	@RequestMapping("/index")
	public ModelAndView index() {
		System.out.println("WebController => index() => index.jsp 연결");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index/index");
		modelAndView.addObject("name", "이원희");
		return modelAndView;
	}
	
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
	
}
