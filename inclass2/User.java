package com.gemini.inclass2;

import java.io.Serializable;

public class User implements Serializable {
  private String name;
  private String email;
  private int id;
  private String department;

  public User(String name, String email, int id, String department) {
    this.name = name;
    this.email = email;
    this.id = id;
    this.department = department;
  }

  public User(){

  }

}
