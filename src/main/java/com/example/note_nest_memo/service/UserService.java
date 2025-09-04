package com.example.note_nest_memo.service;


import java.util.List;

import com.example.note_nest_memo.dtoPlayloads.userDto;

public interface UserService {
	
	public abstract userDto createUser(userDto userDto);
	
	public abstract void deleteUser(Integer userId);
	
	public abstract userDto updateUser(Integer userId , userDto userDto );
	
	public abstract userDto getUser(Integer userId);
	
	public abstract List<userDto> getAllUsers();
	
	public abstract userDto userLogin(String email, String password) throws Exception;
}
