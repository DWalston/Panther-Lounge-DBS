package com.panther.details;

import lombok.Data;
import java.time.LocalDate;

@Data
public class memberDetails {
   String id;
   String name;
   String email;
   String discord;
   String phone;
   String club;
   String joined;
   String memberUntil;
   String flag;
   String password;
}