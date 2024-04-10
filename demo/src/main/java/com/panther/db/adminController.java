package com.panther.db;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.ModelAttribute; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.panther.details.itemDetails;
import com.panther.details.memberDetails;

import org.springframework.web.bind.annotation.SessionAttributes; 

import java.sql.SQLException;

@Controller()
@RequestMapping("/admin")
public class adminController {
   JDBC SQL = new JDBC();
   
   @GetMapping("")
   public String baseGet(Model model) {
      return "adminBase";
   }
   
   @GetMapping("/add")
   public String get(Model model) {
      model.addAttribute("item", new itemDetails());
      return "adding";
   }
   
   @PostMapping("/add")
   public String post(@ModelAttribute("item") itemDetails item, Model model) {
      JDBC sql = new JDBC();
      if(sql.addItem(item))
         model.addAttribute("message", "item added successfully");
      else
         model.addAttribute("message", "Did not add item");
      return "status";
   }

   @GetMapping("/member")
   public String memberGet(Model model) throws SQLException {
       ResultSet rs = SQL.search("`member`");
       if (rs == null)
           model.addAttribute("memberList", new ArrayList<>());
       else {
       List<memberDetails> values = new ArrayList<>();
       while (rs.next()) {
         memberDetails item = new memberDetails();
         item.setDiscord(rs.getString("discord")
                      .toString()
                      .trim());
         item.setName(rs.getString("memberName")
                        .toString()
                        .trim());
         item.setMemberUntil(rs.getString("memberUntil")
                        .toString()
                        .trim());
         item.setClub(rs.getString("club")
                         .toString()
                         .trim());
         values.add(item);
        }
       rs.close();
       model.addAttribute("memberList", values);
       }
       return "members";
   }
}