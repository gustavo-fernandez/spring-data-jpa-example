package com.example.springdatajpaexample;

import com.example.springdatajpaexample.entity.BookEntity;
import com.example.springdatajpaexample.entity.SomeEntity;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.support.TransactionTemplate;

@Slf4j
@SpringBootApplication
public class SpringDataJpaExampleApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringDataJpaExampleApplication.class, args);
  }

  @Bean
  CommandLineRunner commandLineRunner(EntityManager entityManager, TransactionTemplate transactionTemplate) {
    return x -> {
      // TX 1
      BookEntity createdBook = transactionTemplate.execute(status -> {
        BookEntity bookEntity = BookEntity.builder()
          //.description("Descripcion de prueba")
          .name("El libro del mas alla")
          .noEsUnCampo("Este campo no se va a persistir")
          .isbn("1234567891234")
          .creationTime(LocalDateTime.now())
          .build();
        entityManager.persist(bookEntity);
        return bookEntity;
      });

      // TX 2
      try {
        transactionTemplate.execute(status -> {
          BookEntity bookEntity = BookEntity.builder()
            .description("Desc 2")
            .name("Librito")
            .isbn("2344445891234")
            .creationTime(LocalDateTime.now())
            .build();
          entityManager.persist(bookEntity);
          BookEntity entity2 = BookEntity.builder()
            .description("Desc 2")
            .name("Librito")
            .isbn("2344445891234")
            .creationTime(LocalDateTime.now())
            .build();
          entityManager.persist(entity2);
          return null;
        });
      } catch (Exception e) {
        log.error("Error al insertar 2 registros", e);
      }

      List<BookEntity> entities = transactionTemplate.execute(status -> {
        List<BookEntity> books = entityManager.createQuery("select c from BookEntity c", BookEntity.class)
          .getResultList();
        return books;
      });
      System.out.println(entities);

      transactionTemplate.execute(status -> {
        BookEntity bookEntity1 = entityManager.find(BookEntity.class, 1L);
        bookEntity1.setCreationTime(LocalDateTime.now().plusDays(2));
        bookEntity1.setName("El libro del mas alla, pero mas aca");
        entityManager.persist(bookEntity1);
        return null;
      });

      transactionTemplate.execute(status -> {
        SomeEntity someEntity = new SomeEntity();
        someEntity.setName("Hola");
        entityManager.persist(someEntity);
        return null;
      });

      transactionTemplate.execute(status -> {
        var someEntity = entityManager.createNamedQuery("SomeEntity.getAll", SomeEntity.class)
          .getSingleResult();
        System.out.println(someEntity);
        return null;
      });
    };
  }

}