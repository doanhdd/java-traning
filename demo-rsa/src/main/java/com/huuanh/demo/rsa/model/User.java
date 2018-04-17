package com.huuanh.demo.rsa.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name = "users")
public class User implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "user_id", columnDefinition = "INT(11)", nullable = false)
  private int userId;

  @Column(name = "email", columnDefinition = "VARCHAR(255)", nullable = false, unique = true)
  private String email;

  @Column(name = "password", columnDefinition = "VARCHAR(255)", nullable = false)
  private String password;

  @Column(name = "role", columnDefinition = "TINYINT(1)", nullable = false)
  private Integer role;

  @Column(name = "ptk", columnDefinition = "NVARCHAR(2048)", nullable = false)
  private String publicKey;

  @Column(name = "created_at", columnDefinition = "DATETIME", nullable = false, updatable = false)
  @Temporal(TemporalType.TIMESTAMP)
  @CreatedDate
  private Date createdAt;

  @Column(name = "modified_at", columnDefinition = "DATETIME")
  @Temporal(TemporalType.TIMESTAMP)
  @LastModifiedDate
  private Date modifiedAt;

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public Date getModifiedAt() {
    return modifiedAt;
  }

  public void setModifiedAt(Date modifiedAt) {
    this.modifiedAt = modifiedAt;
  }

  public Integer getRole() {
    return role;
  }

  public void setRole(Integer role) {
    this.role = role;
  }

  public String getPublicKey() {
    return publicKey;
  }

  public void setPublicKey(String publicKey) {
    this.publicKey = publicKey;
  }
}
