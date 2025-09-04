package com.example.note_nest_memo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.note_nest_memo.dtoPlayloads.notesDto;
import com.example.note_nest_memo.service.serviceImp.NoteServiceImp;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@CrossOrigin
@RestController
@RequestMapping("/api/notes")
public class NotesController {
	
	@Autowired
	private NoteServiceImp noteServiceImp;
	
	private int count = 0;
	
	@RequestMapping( value = "/create/{userId}" , method = RequestMethod.POST ,  produces = MediaType.APPLICATION_JSON_VALUE , consumes =  MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createNotes( @Valid @RequestBody notesDto notesDto ,  @PathVariable @Email(message = "email must be valid") @NotBlank(message = "email must not be null") String userId , BindingResult bindingResult){
		
        if (!Pattern.matches("^[A-Za-z0-9+_.-]+@(.+)$", userId)) {
            FieldError error = new FieldError("userId", "userId", "Email must be valid");
            bindingResult.addError(error);
        }
        
		if( bindingResult.hasErrors()) {
			
			StringBuilder errorSb = new StringBuilder();
			
			for( FieldError error : bindingResult.getFieldErrors()) {
				errorSb.append(error.getField())
					   .append(" : ")
					   .append(error.getDefaultMessage())
					   .append(" ; ");
			}
			
			 Map<String, Object> response = new HashMap<>();
			 response.put("success", false);
			 response.put("count" , -1 );
			 response.put("errors", errorSb.toString().trim());
			 
			 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
		
		notesDto note = noteServiceImp.createNotes(notesDto, userId);
		 Map<String, Object> response = new HashMap<>();
	       response.put("message", "You Crafted off Your Notes Without Any Issues!");
	       response.put("Your Note Descriptions:", note);
	       response.put("count", ++count);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@RequestMapping( value = "/update/{notesId}" , method = RequestMethod.PUT , produces = MediaType.APPLICATION_JSON_VALUE ,consumes =  MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateNotes( @Valid @RequestBody notesDto notesDto , @PathVariable Integer notesId ,BindingResult bindingResult){
		if(bindingResult.hasErrors()) {
			Map<String, Object> response = new HashMap<>();
			response.put("success", false);
			response.put("count", -1);
			response.put("errors", bindingResult.getFieldErrors()
					.stream()
					.collect(Collectors.toMap(FieldError::getField , FieldError::getDefaultMessage)));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
		
		notesDto updatedNote = noteServiceImp.updateNote(notesDto, notesId);
	    Map<String, Object> response = new HashMap<>();
        response.put("message", "Note Flawlessly Updated!");
        response.put("updatedNote", updatedNote);
        response.put("success", true);
        return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@RequestMapping( value = "/delete/{notesId}" , method = RequestMethod.DELETE , produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<Map<String, Object>> deleteNotes( @PathVariable Integer notesId ){
	   noteServiceImp.deleteNote(notesId);
	
	   Map<String, Object> response = new HashMap<>();
	   response.put("message", "note was obsequitously deleted!");
	   response.put("success", true);
	   response.put("status", HttpStatus.OK.value());
	   // just for tranquility , we can also handle this graciously by creating Api(request) or whatever new Obj
	   return new ResponseEntity<>(response , HttpStatus.OK);
	}
	
	@RequestMapping( value = "/selectUser/{userId}" , method = RequestMethod.GET)
    public ResponseEntity<List<notesDto>> getNotesByUser(@PathVariable String userId){
        List<notesDto> Note=noteServiceImp.getNoteByUser(userId);
        return new ResponseEntity<List<notesDto>>(Note,HttpStatus.OK);
    }
	
	@RequestMapping( value = "/select/{notesId}" , method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<notesDto> getNoteById(@PathVariable Integer notesId){
        notesDto note = noteServiceImp.getNote(notesId);
        return new ResponseEntity<notesDto>(note , HttpStatus.ACCEPTED);
    }
	
    @RequestMapping(value = "/" , method = RequestMethod.GET ,produces = MediaType.APPLICATION_JSON_VALUE )
    @CrossOrigin
    public ResponseEntity<List<notesDto>> getNotes(){
        List<notesDto> Note = noteServiceImp.getAllNote();
        return new ResponseEntity<List<notesDto>>(Note,HttpStatus.OK);
    }
    
}

