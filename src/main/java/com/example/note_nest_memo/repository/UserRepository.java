package com.example.note_nest_memo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.note_nest_memo.model.User;

@Mapper
public interface UserRepository {
	
   public abstract User findByEmailAndPassword(String email, String password);
   
   public abstract User findByEmail(String email);
   
   public abstract Integer insertIntoUser(User user);

   public abstract User findUserById(Integer id);
   
   public abstract Integer updateUser(User user);
   
   public abstract void deleteUser(Integer id); 
   
   public abstract void deleteUserByUserObj(User user); 
   
   public abstract List<User> getAllUsers();
   
}
