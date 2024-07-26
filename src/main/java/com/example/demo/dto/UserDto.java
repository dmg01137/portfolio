package com.example.demo.dto;

import lombok.Data;

@Data
public class UserDto 
{
	private int user_number;
	private String user_id;
	private String password;
	private String user_privilege;
	private String user_name;
}
