package com.netpalese.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.netpalese.domain.entity.User;
import com.netpalese.service.UserService;
import com.netpalese.validator.UserValidator;

@Controller
@RequestMapping("/signup.do")
public class SignupController {

	@Autowired
	private UserService userService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(new UserValidator());
	}

	@RequestMapping(method = RequestMethod.GET)
	public String signup(final Model model) {
		model.addAttribute("signup", new User());
		return "signup";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String signup(@Valid @ModelAttribute("signup") User user, BindingResult br, final Model model) {
		if(br.hasErrors()) {
			return "signup";
		}
		userService.createUser(user);
		return "redirect:login.do";
	}
}
