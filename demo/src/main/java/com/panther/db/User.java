package com.panther.db;

import java.util.Collection; 
import java.util.List;
import javax.persistence.Column; 
import javax.persistence.Entity; 
import javax.persistence.Id; 
import javax.persistence.Table; 
import org.springframework.security.core.GrantedAuthority; 
import org.springframework.security.core.userdetails.UserDetails; 

@Entity 
@Table(name = "users") 
public class User implements UserDetails { 

   /** 
   * 
   */ 
   private static final long serialVersionUID = 1L;

   @Id 
   private String username; 
   private String password;

   public User() { 
   } 
   public User(String username, String password, boolean accountNonLocked) { 
      this.username = username; 
      this.password = password; 
   } 
   @Override 
   public Collection< extends GrantedAuthority> getAuthorities() { 
      return List.of(() -> "read"); 
   }
   @Override
   public String getPassword() {    
      return password; 
   } 
   public void setPassword(String password) { 
      this.password = password; 
   } 
   @Override 
   public String getUsername() { 
      return username; 
   } 
   public void setUsername(String username) { 
      this.username = username; 
   } 
   @Override 
   public boolean isAccountNonExpired() { 
      return true; 
   } 
   @Override public boolean isCredentialsNonExpired() { 
      return true; 
   } 
   @Override public boolean isEnabled() { 
   return true; 
   } 
}