package com.demo.models;

import lombok.*;

@Data
//@Getter replaced by @Data
//@Setter replaced by @Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class User {
  private String username;
  private String password;
  private String email;
  private String firstName;
  private String lastName;
  private String role;
  private boolean active;

  public static User standardUser() {
    return User.builder()
        .username("standard_user")
        .password("password")
        .role("standard")
        .active(true)
        .build();
  }

  public static User lockedOutUser() {
    return User.builder()
        .username("locked_out_user")
        .password("secret_sauce")
        .role("locked")
        .active(false)
        .build();
  }

  public static User problemUser() {
    return User.builder()
        .username("problem_user")
        .password("secret_sauce")
        .role("problem")
        .active(true)
        .build();
  }
}
