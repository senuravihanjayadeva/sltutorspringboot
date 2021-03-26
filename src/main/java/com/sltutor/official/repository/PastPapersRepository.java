package com.sltutor.official.repository;

import com.sltutor.official.model.Pastpaper;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PastPapersRepository extends MongoRepository<Pastpaper,String> {



}
