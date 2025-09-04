package com.example.note_nest_memo.service.serviceImp;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.example.note_nest_memo.dtoPlayloads.userDto;
import com.example.note_nest_memo.model.User;
import com.example.note_nest_memo.repository.UserRepository;
import com.example.note_nest_memo.service.UserService;

@Service
public class UserServiceImp implements UserService{

	@Autowired
	private UserRepository userRepository;

	@Override
	public userDto createUser(userDto userDto) {
		User user = userDtoTransformedToUser(userDto); // plug into conversion first
		user.setCreated_at(new Timestamp(System.currentTimeMillis()));
		 userRepository.insertIntoUser(user);
		return UserTransformedToUserDto(user);
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = userRepository.findUserById(userId);
		 userRepository.deleteUserByUserObj(user);
		 userRepository.deleteUser(userId);
	}

	@Override
	public userDto updateUser(Integer userId, userDto userDto) {
		User user = userRepository.findUserById(userId);

		if( user == null ) {
			  throw new ResourceAccessException("Not found");
		  }
//	    user.setName(userDto.getName());
//	    user.setEmail(userDto.getEmail());
//	    user.setPassword(userDto.getPassword());
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		userRepository.updateUser(user);


		 return UserTransformedToUserDto(user);

	}

	@Override
	public userDto getUser(Integer userId) {
		User user = userRepository.findUserById(userId);
		return UserTransformedToUserDto(user);
	}

	@Override
	public List<userDto> getAllUsers() {
		List<User> users = userRepository.getAllUsers();
		return users.stream().map(this::UserTransformedToUserDto).collect(Collectors.toList());
	}

	@Override
	public userDto userLogin(String email, String password) throws Exception{
		User user = userRepository.findByEmailAndPassword(email, password);
		if( user != null && user.getEmail().equals(email) && user.getPassword().equals(password)) {
			userDto userDto  = new userDto();
			userDto.setId(user.getId());
			userDto.setName(user.getName());
			userDto.setEmail(user.getEmail());
			userDto.setPassword(user.getPassword());
			return userDto;
		}
		else {
			  throw new Exception("Invalid email or password");
		}
	}

	public User userDtoTransformedToUser(userDto userDto ) {
		User user= new User();
		user.setId(userDto.getId());
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		return user;
	}

	public userDto UserTransformedToUserDto(User user ) {
		userDto userDto= new userDto();
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setEmail(user.getEmail());
		userDto.setPassword(user.getPassword());
		return userDto;
	}
}
