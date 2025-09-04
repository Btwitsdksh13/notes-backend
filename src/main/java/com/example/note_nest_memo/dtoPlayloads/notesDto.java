package com.example.note_nest_memo.dtoPlayloads;

import java.sql.Timestamp;

import com.example.note_nest_memo.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class notesDto {
	
	private Integer id;
	
	@NotBlank(message = "Note title must no be dummy or desolute")
	@Size( min = 10 , max = 50 , message = "Your note title must be in range of 10 and 50 characters")
	private String title;
	
	@NotBlank(message = "Note content must not be Negligible")
	@Size( min = 10 , max = 500 , message = "Your content text field must be adequately well-suited on the range of 10 and 500 characters")
	private String content;
	
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp created_at;
  
   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp updated_at;

	private User user;
	
	public Integer getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public Timestamp getUpdated_at() {
		return updated_at;
	}

	public User getUser() {
		return user;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
