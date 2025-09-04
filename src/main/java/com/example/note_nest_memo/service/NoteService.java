package com.example.note_nest_memo.service;

import java.util.List;

import com.example.note_nest_memo.dtoPlayloads.notesDto;

public interface NoteService {
	
	public abstract notesDto createNotes(notesDto notesDto, String userId);
	

 	notesDto updateNote(notesDto notesDto,Integer notesId);
	
	void deleteNote(Integer notesId);
	
	notesDto getNote(Integer notesId);
	
	List<notesDto> getAllNote();

    List<notesDto> getNoteByUser(String userId);
    
}
