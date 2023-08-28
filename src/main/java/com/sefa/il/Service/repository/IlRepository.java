package com.sefa.il.Service.repository;

import com.sefa.il.Service.model.Il;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IlRepository extends MongoRepository<Il,String> {
}
