package com.harish.joblisting.repository;

import java.util.List;

import com.harish.joblisting.model.Post;

public interface SearchRepository 
{
    //declaring our own method
	List<Post> findByText(String text);
}
