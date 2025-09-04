package com.example.note_nest_memo.dtoPlayloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class userDto {
	
    private Integer id;
    
    @NotEmpty(message = " Don't be muffled up! Name must not be empty!")
    @Size(min = 6, max = 30, message = "Name must be between 6 and 30 characters!")
    private String name;
    
    @Email(message = "Email must be on correct format!")
    private String email;
    
    @NotBlank(message = "password must not be empty!")
    private String password;

	public Integer getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}

