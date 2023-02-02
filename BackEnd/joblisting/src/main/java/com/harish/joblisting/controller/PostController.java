package com.harish.joblisting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.harish.joblisting.model.Post;
import com.harish.joblisting.repository.PostRepository;
import com.harish.joblisting.repository.SearchRepository;

@RestController
@CrossOrigin(origins="http://localhost:3000")
public class PostController 
{
	
	@Autowired
	PostRepository repo;
	
	@Autowired
	SearchRepository srepo;
	
	@GetMapping("/allPosts")
	@CrossOrigin
	public List<Post> getAllPosts()
	{
		return repo.findAll();
	}
	
	
	
	@GetMapping("/posts/{text}")
	@CrossOrigin
	public List<Post> search(@PathVariable String text)
	{
		return srepo.findByText(text);
	}
	
	
	
	
	@PostMapping("/post")
	@CrossOrigin
	public Post addPost(@RequestBody  Post post)
	{
		return repo.save(post);
	}
	
	
	
	
}
