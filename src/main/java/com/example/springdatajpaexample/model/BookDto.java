package com.example.springdatajpaexample.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDto {

  private String isbn;
  private String name;
  private String description;
  private LocalDateTime creationTime;

}
