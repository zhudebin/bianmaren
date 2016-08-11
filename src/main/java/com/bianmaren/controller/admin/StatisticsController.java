package com.bianmaren.controller.admin;

import com.bianmaren.controller.BaseController;
import com.bianmaren.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller("statisticsController")
@RequestMapping("/admin/statistics")
public class StatisticsController extends BaseController{

	@Resource(name = "memberServiceImpl")
	private MemberService memberService;

	
}
