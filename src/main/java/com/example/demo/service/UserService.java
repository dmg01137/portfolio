package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dao.detection.UserDao;
import com.example.demo.dto.UserDto;

@Service
public class UserService 
{
	// DAO 변수 생성
	private final UserDao userDao;
	
	// 서비스 생성자
	public UserService(UserDao userDao)
	{
		this.userDao = userDao;
	}
	
	// 회원 추가 함수
	public int addUser(UserDto userDto)
	{
		return userDao.addUser(userDto);

	}
	
	// 회원 리스트 출력 함수
	public List<UserDto> listUser()
	{
		return userDao.listUser();
	}
	
	public String getUserById(String user_id)
	{
		String getId = userDao.getUserById(user_id);
		return getId;
	}
	
	public String getUserByPw(String user_password)
	{
		String getPw = userDao.getUserByPw(user_password);
		return getPw;
	}
	
	
	public UserDto findByUserNumber(int user_number) 
    {
        return userDao.findByUserNumber(user_number);
    }
	
	public int updateUser(UserDto userDto)
	{
		return userDao.updateUser(userDto);
	}
	
	public int getLastUserNumber() 
    {
        return userDao.getLastUserNumber();
    }
	
	public int deleteUser(UserDto userDto)
	{
		return userDao.deleteUser(userDto);
	}
	
	
}
