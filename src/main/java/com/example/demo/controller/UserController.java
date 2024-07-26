package com.example.demo.controller;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dto.UserDto;
import com.example.demo.dto.detection.Users;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController 
{
	private final UserService userService;
	
	public UserController(UserService userService)
	{
		this.userService = userService;
	}

	
	@GetMapping("/addUser")
	public String addUser(Model model) 
	{		
		int lastUserNumber = userService.getLastUserNumber() + 1;
		model.addAttribute("user_number", lastUserNumber);
		 
	    return "addUser";
	}

	// html 회원 가입 정보 입력후 제출
	@PostMapping("/addUser")
	public String postAddUser(UserDto userDto, Model model)
	{
        BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
        userDto.setPassword(bcryptPasswordEncoder.encode(userDto.getPassword()));
   
		if(userService.addUser(userDto) > 0)
		{
			return "/signin";
		}
		
		return "/signin";
	}
	
	// html 회원 리스트 출력
	@GetMapping("/listUser")
	public String listUser(Model model, HttpSession session, RedirectAttributes redirectAttributes)
	{
		
		List<UserDto> listUser = userService.listUser();
		model.addAttribute("listUser", listUser);
		return "/listUser";
		  
	}
	
	
	/*
	 * @GetMapping("/userLogin") public String userLogin(HttpSession session) {
	 * String user = (String)session.getAttribute("user");
	 * 
	 * if(user == null) { return "/userLogin"; } return "/dashboard"; }
	 * 
	 * @PostMapping("/userLogin") public String userLogin(@RequestParam("user_id")
	 * String user_id , @RequestParam("user_password") String user_password, Model
	 * model, HttpSession session) { String userId =
	 * userService.getUserById(user_id); String userPassword =
	 * userService.getUserByPw(user_id);
	 * 
	 * BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
	 * 
	 * String message; if(userId == null) { message = "존재하지 않는 아이디입니다.";
	 * model.addAttribute("message", message); return "/userLogin"; }
	 * 
	 * if(!bcryptPasswordEncoder.matches(user_password, userPassword)) { message =
	 * "비밀번호가 틀렸습니다."; model.addAttribute("message", message); return "/userLogin";
	 * }
	 * 
	 * session.getMaxInactiveInterval(); session.setAttribute("userId", userId);
	 * return "/dashBoard2";
	 * 
	 * }
	 */
	  
	  
	  
	  @GetMapping("/logout") 
	  public String userLogout(HttpSession session) 
	  {
		  session.invalidate(); 
		  return "/userLogin"; 
	  }
	 
	
	@GetMapping("/updateUser")
	public String updateUser(@RequestParam("user_number") int userNumber, Model model, HttpSession session, RedirectAttributes redirectAttributes) 
	{	
		UserDto user = userService.findByUserNumber(userNumber);

		// 모델에 데이터 추가
		model.addAttribute("user_number", user.getUser_number());
		model.addAttribute("user_id", user.getUser_id());
		model.addAttribute("user_privilege", user.getUser_privilege());
		model.addAttribute("user_name", user.getUser_name());

		return "/updateUser";
	}

	@PostMapping("/updateUser")
	public String updateUserList(UserDto userDto) 
	{
		BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
        userDto.setPassword(bcryptPasswordEncoder.encode(userDto.getPassword()));
		
		if (userService.updateUser(userDto) > 0) {
			return "redirect:/listUser";
		}
		return "redirect:/listUser";
	}
	
	
	
	
	
	
	
	
	
	
	@GetMapping("/deleteUser")
	public String getDeleteUser()
	{
		return "redirect:/listUser";	
	}
	
	@GetMapping("/delete/{dept_no}")
	public String deleteUser(UserDto userDto)
	{
		if(userService.deleteUser(userDto) > 0)
		{
			return "redirect:/listUser";
		}
		return "redirect:/listUser";
	}
	
	
	@PostMapping("/deleteUser")
	@ResponseBody
	public UserDto deleteFunction(@RequestBody UserDto user_number)
	{
		System.out.println(user_number.getUser_number());
		
		if(userService.deleteUser(user_number) > 0)
		{
			return user_number;
		}
		return user_number;			
	}
	
	
	
	
	
	

}
