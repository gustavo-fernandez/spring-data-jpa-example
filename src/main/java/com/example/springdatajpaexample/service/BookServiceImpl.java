package com.example.springdatajpaexample.service;

import com.example.springdatajpaexample.entity.BookEntity;
import com.example.springdatajpaexample.model.BookDto;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

  private final EntityManager entityManager;

  @Override
  public List<BookDto> findAll() {
    return entityManager.createQuery("select p from BookEntity p", BookEntity.class)
      .getResultList()
      .stream()
      .map(this::mapToDto)
      .collect(Collectors.toList());
  }

  private BookDto mapToDto(BookEntity bookEntity) {
    BookDto bookDto = new BookDto();
    bookDto.setIsbn(bookEntity.getIsbn());
    bookDto.setDescription(bookEntity.getDescription());
    bookDto.setName(bookEntity.getName());
    bookDto.setCreationTime(bookEntity.getCreationTime());
    return bookDto;
  }

}
