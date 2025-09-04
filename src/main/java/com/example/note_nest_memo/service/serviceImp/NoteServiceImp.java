package com.example.note_nest_memo.service.serviceImp;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.example.note_nest_memo.dtoPlayloads.notesDto;
import com.example.note_nest_memo.model.Notes;
import com.example.note_nest_memo.model.User;
import com.example.note_nest_memo.repository.NoteRepository;
import com.example.note_nest_memo.repository.UserRepository;
import com.example.note_nest_memo.service.NoteService;

@Service
public class NoteServiceImp implements NoteService{

	@Autowired
	private NoteRepository noteRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public notesDto createNotes(notesDto notesDto, String userId) {
		// in order to dexterously create note , use incipicently have to match up email 
	  User user = userRepository.findByEmail(userId);
//	  Notes notes = new Notes();
//	  notes.setTitle(notesDto.getTitle());
//	  notes.setContent(notesDto.getContent());
//	  notes.setCreated_at(notesDto.getCreated_at());
//	  notes.setUpdated_at(notesDto.getUpdated_at());
//	  notes.setUser(user);
	  
	 Notes notes =  noteDtoTransformedToNotes(notesDto);
     notesDto.setCreated_at(new Timestamp(System.currentTimeMillis()));
     notesDto.setUpdated_at(new Timestamp(System.currentTimeMillis()));
	 notes.setUser(user);
	 
	  noteRepository.insertIntoNotes(notes);
	
		return notesTransformedToNotesDto(notes);
	}
	
	@Override
	public notesDto updateNote(notesDto notesDto, Integer notesId) {
		
		Notes notes = noteRepository.findNoteById(notesId);
	//		
	    if (notes == null) {
            throw new RuntimeException("Note not found");
        }
	    
		notes.setTitle(notesDto.getTitle());
		notes.setContent(notesDto.getContent());
		notes.setUpdated_at(notesDto.getUpdated_at());
        notes.setUpdated_at(new Timestamp(System.currentTimeMillis()));
     
//		notes.setContent(notesDto.getContent());
		 noteRepository.updateNotes(notes);
		
		 return notesTransformedToNotesDto(notes);
	}

	@Override
	public void deleteNote(Integer notesId) {
		Notes notes=noteRepository.findNoteById(notesId);
		if( notes != null ) {
			noteRepository.deleteNodeByObj(notes);
			 noteRepository.deleteNotes(notesId);
		}
		else {
			 throw new ResourceAccessException("Not found");
		}
	}

	@Override
	public notesDto getNote(Integer notesId) {
		Notes notes = noteRepository.findNoteById(notesId);
		if( notes != null ) {
			return notesTransformedToNotesDto(notes);	
		}
		else {
			 throw new ResourceAccessException("Not found Any Note!");
		}
	}

	@Override
	public List<notesDto> getAllNote() {
		List<Notes> notes = noteRepository.findAllNotes();
		return notes.stream().map(this::notesTransformedToNotesDto).collect(Collectors.toList());
	}

	@Override
	public List<notesDto> getNoteByUser(String userId) {
		User user = userRepository.findByEmail(userId);
		List<Notes> notes = noteRepository.findByUser(user);
		
		return notes.stream().map(note -> notesTransformedToNotesDto(note)).collect(Collectors.toList());
	}
	
	public Notes noteDtoTransformedToNotes(notesDto notesDto) {
		  
		  Notes notes = new Notes();
		  notes.setId(notesDto.getId());
		  notes.setTitle(notesDto.getTitle());
		  notes.setContent(notesDto.getContent());
		  notes.setCreated_at(notesDto.getCreated_at());
		  notes.setUpdated_at(notesDto.getUpdated_at());
		  notes.setUser(notesDto.getUser());
		  return notes;
	}
	
	public notesDto notesTransformedToNotesDto(Notes notes ) { 
		notesDto notesDto = new notesDto();
		notesDto.setId(notes.getId());
		notesDto.setTitle(notes.getTitle());
		notesDto.setContent(notes.getContent());
		notesDto.setCreated_at(notes.getCreated_at());
		notesDto.setUpdated_at(notes.getUpdated_at());
		notesDto.setUser(notes.getUser());
		  return notesDto;
	}

}
