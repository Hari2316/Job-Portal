package com.harish.joblisting.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.harish.joblisting.model.Post;

public interface PostRepository extends MongoRepository<Post,String>
{
   
}
