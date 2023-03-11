package com.techreturners.bookmanager.repository;

import com.techreturners.bookmanager.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookManagerRepository extends CrudRepository<Book, Long> {

}
