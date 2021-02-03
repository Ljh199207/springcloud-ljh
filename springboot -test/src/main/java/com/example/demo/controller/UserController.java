package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("user/{userName}")
	@ResponseBody
	public User getUserByName(@PathVariable(value = "userName") String userName) {
		return this.userService.findByName(userName);
	}

	@PostMapping("user/{id}")
	@ResponseBody
	public User getUserByName(@PathVariable(value = "id") Integer id) {
		return this.userService.getById(id);
	}


	@PostMapping("user/save")
	@ResponseBody
	public void saveUser(@RequestBody User user) {
		this.userService.saveUser(user);
	}

	@GetMapping("user")
	public String ListUser(Model model) {
		model.addAttribute("accountList", userService.getList());
		return "user";
	}
}
