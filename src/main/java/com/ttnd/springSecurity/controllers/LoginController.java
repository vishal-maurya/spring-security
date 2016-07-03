package com.ttnd.springSecurity.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ttnd.springSecurity.services.UserService;

@Controller
@RequestMapping("/")
public class LoginController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = { "/" })
	public ModelAndView welcomePage() {
		ModelAndView welcomePage = new ModelAndView();
		welcomePage.setViewName("login");
		return welcomePage;
	}

	@RequestMapping(value = "/login", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout,
			@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "password", required = false) String password) {

		// System.out.println(userService.getUser(username,
		// password).getUserRoles().toString());
		/*
		 * for(UserRoles rs:userService.getUser(username,
		 * password).getUserRoles()){ System.out.println(rs.getType()); }
		 */
		Authentication auth = new UsernamePasswordAuthenticationToken(username, password);
		SecurityContextHolder.getContext().setAuthentication(auth);
		System.out.println(auth.isAuthenticated());
		System.out.println(error);

		ModelAndView model = new ModelAndView();
		model.setViewName("homepage");
		if (error != null && !error.isEmpty()) {
			model.addObject("error", "Invalid username and password!");
			model.setViewName("login");
		}

		if (logout != null) {
			model.addObject("message", "You've been logged out successfully.");
			model.setViewName("login");
		}

		return model;
	}

	@RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
       Authentication auth = SecurityContextHolder.getContext().getAuthentication();
       if (auth != null){    
          new SecurityContextLogoutHandler().logout(request, response, auth);
       }
       return "login";
    }
	
	@Secured("ROLE_ADMIN")
	@RequestMapping("/admin")
	public ModelAndView adminPage() {
		ModelAndView adminPage = new ModelAndView();
		adminPage.setViewName("admin");
		return adminPage;
	}
}
