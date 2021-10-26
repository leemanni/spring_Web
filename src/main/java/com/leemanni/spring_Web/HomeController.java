package com.leemanni.spring_Web;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

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

import com.leemanni.service.ContentViewService;
import com.leemanni.service.DeleteService;
import com.leemanni.service.IncrementService;
import com.leemanni.service.InsertService;
import com.leemanni.service.SelectService;
import com.leemanni.service.UpdateService;
import com.leemanni.service.WebService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	private JdbcTemplate template;
	
	
	public JdbcTemplate getTemplate() {
		return template;
	}
	@Autowired	
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
		Constant.template = this.template;
	}
	
	
//	===========================================================================================================
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "redirect:list";
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
