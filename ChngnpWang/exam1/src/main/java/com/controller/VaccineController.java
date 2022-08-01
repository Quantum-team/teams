package com.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pojo.vaccine;
import com.service.VaccineService;

@Controller
public class VaccineController {
	@Resource
	private VaccineService vaccineService;
	
	@RequestMapping("index")
	public String selectAll(Model model){
		List vaccineList = vaccineService.selectAll();
		System.out.println(vaccineList);
		model.addAttribute("vaccineList",vaccineList);
		return "a1";
	}
	
}
