package com.leemanni.service;

import org.springframework.ui.Model;

import com.leemanni.vo.MvcboardVO;

public interface WebService {
	void execute(MvcboardVO mvcboardVO);
	void execute(Model model);
}
