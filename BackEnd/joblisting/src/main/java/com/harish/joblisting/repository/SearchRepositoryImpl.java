package com.harish.joblisting.repository;

import java.util.ArrayList;
import java.util.*;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

import com.harish.joblisting.model.Post;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Component
public class SearchRepositoryImpl implements SearchRepository
{
	@Autowired
	MongoClient client;
	
	@Autowired
	MongoConverter converter;
	
    public List<Post> findByText(String text)
    {
    	final List<Post> posts= new ArrayList<>();
    	
    	MongoDatabase database = client.getDatabase("joblistingDB");
    	MongoCollection<Document> collection = database.getCollection("jobPost");

    	AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search", 
    	    new Document("index", "default").append("text", 
    	    new Document("query", text).append("path", Arrays.asList("techs", "desc", "profie")))), 
    	    new Document("$sort", 
    	    new Document("exp", 1L)), 
    	    new Document("$limit", 5L)));
    	
    	result.forEach(doc -> posts.add(converter.read(Post.class, doc)));
    	
    	return posts;
    }
}
