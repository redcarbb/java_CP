package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.entity.UserEntity;
import com.service.UserService;
import com.vo.User;
import com.vo.UserVo;

import jakarta.servlet.http.HttpServletRequest;

// @Component
@Controller
public class UserController {
	
	@Autowired
	private UserService userService;

	@RequestMapping("/login")
	public String login(@RequestParam(name = "u1", required = false) String username, @RequestParam("p1") String password) {
		// "/WEB-INF/views/login.jsp" 
		
		System.out.println(username);
		System.out.println(password);
		if (userService.checkLogin(username, password)) {
			return "loginSuccess";
		} else {
			return "loginFail";
		}
		
	}
	
	//@RequestMapping(value = "/register", method = RequestMethod.POST)
	@PostMapping("/register")
	public String register(@RequestParam("username") String username, @RequestParam("password") String password) {
		
		userService.addUser(username, password);
		return "registerSuccess";
	}
	
	@GetMapping("/toUserList")
	public String userList(Model model) {
		List<UserEntity> userList = userService.findAllUser();
		model.addAttribute("userList", userList);
		return "userList";
	}
	
	@GetMapping("/toUserList3")
	public ModelAndView userList3() {
		List<UserEntity> userList = userService.findAllUser();
//		model.addAttribute("userList", userList);

		ModelAndView mv = new ModelAndView();
		mv.addObject("userList", userList);
		mv.setViewName("userList");
		
		return mv;
	}
	
	public String userList2(HttpServletRequest req) {
		//req.setAttribute("userList", userList);
		return "";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable("id") Long id) {
		System.out.println("@PathVariable id: " + id);
		userService.deleteUserById(id);
		// userList
		// redirect:/toUserList
		return "redirect:/toUserList";
	}
	
	@GetMapping("/toUpdate/{id}")
	public String toUpdatePage(@PathVariable("id") Long id, Model model) {
		
		// 透過ID 找到 USER
		UserVo vo = userService.findUserById(id);
		model.addAttribute("user", vo);
		return "updateUser";
	}
	
	@PostMapping("/update")
	public String updateUser(UserVo user) {
		System.out.println("UserVo: " + user);
		// 更新資料
		userService.upateUser(user);
		
		return "redirect:/toUserList";
		
	}
	
	@GetMapping("/getSession")
	public String getSession() {
		User user = userService.getSessionUser();
		System.out.println("Session :" + user.getUsername());
		return "redirect:/toUserList";
	}
}
