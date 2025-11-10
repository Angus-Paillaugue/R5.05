package fr.paillaugue.school.r505.R505.controller;

import fr.paillaugue.school.r505.R505.modele.Roles;
import fr.paillaugue.school.r505.R505.modele.UserData;

public class User {
  private Integer id;
  private String name;
  private String passwordHash;
  private String email;
  private Roles role;

  public User(UserData userData) {
    if (userData == null) {
        this.id = null;
        this.name = null;
        this.passwordHash = null;
        this.email = null;
        this.role = null;
    } else {
        this.id = userData.getId();
        this.name = userData.getName();
        this.passwordHash = userData.getPasswordHash();
        this.email = userData.getEmail();
        this.role = userData.getRole();
    }
}

  public User(String name, String email, String passwordHash, Roles role) {
    this.name = name;
    this.email = email;
    this.passwordHash = passwordHash;
    this.role = role;
  }

  public User() {
  }

  public UserData toData() {
    UserData u = new UserData();
    u.setId(id);
    u.setName(name);
    u.setPasswordHash(passwordHash);
    u.setEmail(email);
    u.setRole(role);
    return u;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPasswordHash() {
    return passwordHash;
  }

  public void setPasswordHash(String passwordHash) {
    this.passwordHash = passwordHash;
  }

  public Roles getRole() {
    return role;
  }

  public void setRole(Roles role) {
    this.role = role;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
