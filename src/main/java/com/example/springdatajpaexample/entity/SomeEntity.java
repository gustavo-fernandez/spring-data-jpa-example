package com.example.springdatajpaexample.entity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Entity
@Slf4j
@Access(AccessType.PROPERTY)
@NamedQuery(name = "SomeEntity.getAll", query = "select c from SomeEntity c where c.id = 1")
public class SomeEntity {

  @Setter
  private Integer id;
  private String name;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Integer getId() {
    return this.id;
  }

  @Column(name = "my_name")
  public String getName() {
    log.info("SomeEntity.getName()");
    return name;
  }

  public void setName(String name) {
    log.info("SomeEntity.setName(" + name + ")");
    this.name = name;
  }

  @Override
  public String toString() {
    return "SomeEntity[id:" + id + ", name:" + name + "]";
  }

}
