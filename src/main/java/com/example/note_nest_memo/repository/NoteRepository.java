package com.example.note_nest_memo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;


import com.example.note_nest_memo.model.Notes;
import com.example.note_nest_memo.model.User;

@Mapper
public interface NoteRepository {
	// we did that cuz we are willing harmoniously connect with user login to bust up to every notes 
	public abstract List<Notes> findByUser(User user);
	
	public abstract Notes findNoteById(Integer id);
	
	public abstract Integer insertIntoNotes(Notes notes);
	
	public abstract List<Notes> findAllNotes();
	
	public abstract Integer updateNotes(Notes notes);
	
	public abstract void deleteNotes(Integer id);
	
	public abstract void deleteNodeByObj(Notes notes);

}