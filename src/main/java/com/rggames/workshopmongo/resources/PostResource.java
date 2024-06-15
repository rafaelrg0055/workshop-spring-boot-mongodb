package com.rggames.workshopmongo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rggames.workshopmongo.domain.Post;
import com.rggames.workshopmongo.resources.util.URL;
import com.rggames.workshopmongo.services.PostService;


@RestController
@RequestMapping(value="/posts")
public class PostResource {

	@Autowired
	private PostService service;   //O controlador Rest UserResource acessa o Service
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
 	public ResponseEntity<Post> findById(@PathVariable String id) {
		Post obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	
	@RequestMapping(value="/titlesearch", method=RequestMethod.GET)
 	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value="text", defaultValue="") String text) {   //@RequestParam é para o "?" da URL
		text = URL.decodeParam(text);   //decodifica o post
		List<Post> list = service.findByTitle(text);
		return ResponseEntity.ok().body(list);
	}
	
	
}
