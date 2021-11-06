package com.example.springdatajpaexample.service;

import com.example.springdatajpaexample.model.BookDto;
import java.util.List;

public interface BookService {

  List<BookDto> findAll();

}
