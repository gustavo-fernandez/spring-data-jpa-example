package com.example.springdatajpaexample.web;

import com.example.springdatajpaexample.model.BookDto;
import com.example.springdatajpaexample.service.BookService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BookController {

  private final BookService bookService;

  @GetMapping
  public List<BookDto> getAllBooks() {
    return bookService.findAll();
  }

}
