package com.banking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.banking.models.Account;
import com.banking.models.User;
import com.banking.repositories.AccountRepository;
import com.banking.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("mybank/login")
public class LoginController {

	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@RequestMapping("")
	public String getLoginView(HttpSession ses)
	{
		if(ses.getAttribute("name")!=null)
			return "mybank/home";
		return "user/user-login";
	}
	@RequestMapping("registration")
	public String getRegistrationView()
	{
		return "user/registration";
	}
	@RequestMapping("create-account")
	public String createUserAccount(User user,Model model)
	{
		int accountno=userService.createUser(user);
		model.addAttribute("name",user.getName());
		model.addAttribute("an",accountno);
		return "user/registration-success";
	}
	@RequestMapping("authenticate")
	public String authenticateUser(String userid,String password,Model model,HttpSession ses)
	{
		User user=userService.getUser(userid);
		if(user==null)
		{
			model.addAttribute("uid",userid);
			model.addAttribute("msg","User id does not exist");
			return "user/user-login";
		}
		String dpassword=user.getPassword();
		if(!password.equals(dpassword))
		{
			model.addAttribute("uid",userid);
			model.addAttribute("msg","Password is wrong");
			return "user/user-login";
		}
		ses.setAttribute("name",user.getName());
		Account account=accountRepository.findByUserid(userid);
		ses.setAttribute("accountno",account.getAccountNo());
		return "home/home-page";
	}
	@RequestMapping("logout")  
	public String logoutUser(HttpSession ses,Model model)
	{
		if(ses.getAttribute("name")==null)
			return "mybank/login";
		model.addAttribute("name",(String)ses.getAttribute("name"));
		ses.invalidate();
		return "user/logout";
	}
}
