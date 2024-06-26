package com.panther.db;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.ModelAttribute; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.panther.details.itemDetails;
import com.panther.details.memberDetails;
import com.panther.details.checkoutForm;

import org.springframework.web.bind.annotation.SessionAttributes; 

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller()
@RequestMapping("/admin")
public class adminController {
   JDBC SQL = new JDBC();
   private final InMemoryUserDetailsManager inMemoryUserDetailsManager;
	@Autowired
    public adminController(InMemoryUserDetailsManager inMemoryUserDetailsManager) throws SQLException {
       this.inMemoryUserDetailsManager = inMemoryUserDetailsManager;
    }
   
   @GetMapping("")
   public String baseGet(Model model) {
      return "adminBase";
   }
   
   @GetMapping("/edit")
   public String get(@RequestParam(defaultValue = "") String message, Model model) {
      model.addAttribute("message", message);
      model.addAttribute("addForm", new itemDetails());
      model.addAttribute("changeForm", new itemDetails());
      model.addAttribute("removeForm", new itemDetails());
      return "adding";
   }
   
   @PostMapping("/edit/add")
   public String postAdd(@ModelAttribute("add") itemDetails item, Model model) {
      String message;
      if(SQL.addItem(item))
         message = "Item added Successfully";
      else
         message = "Error: Item not added";
      return "redirect:/admin/edit?message=" + message;
   }

   @PostMapping("/edit/change")
   public String postEdit(@ModelAttribute("change") itemDetails item, Model model) {
      String message;
      if(SQL.editItem(item))
         message = "Item edited Successfully";
      else
         message = "Error: Item not edited";
      return "redirect:/admin/edit?message=" + message;
   }

   @PostMapping("/edit/remove")
   public String postDelete(@ModelAttribute("remove") itemDetails item, Model model) {
      String message;
      if(SQL.removeItem(item))
         message = "Item deleted Successfully";
      else
         message = "Error: Item not deleted";
      return "redirect:/admin/edit?message=" + message;
   }

   @GetMapping("/member")
   public String memberGet(@RequestParam(defaultValue = "") String message, Model model) throws SQLException {
      model.addAttribute("message", message);
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
   
	@GetMapping("/register")
	public String register(Model model) {
		memberDetails newUser = new memberDetails();
		LocalDate today = LocalDate.now();
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("uuuu-MM-dd");
		newUser.setPhone("8139512605");
		newUser.setClub("FITSSFF");
        newUser.setJoined(today.format(dateFormat));
        newUser.setMemberUntil(today.plusWeeks(52).format(dateFormat));
		newUser.setFlag("0");
        model.addAttribute("member", newUser);
		return "register";
	}
	
	@PostMapping("/register")
    public String submit(@ModelAttribute("request") memberDetails member, Model model) {
      String message;
	  if(SQL.addMember(member)) {
         message = "item added successfully";
		 UserDetails q = User.withDefaultPasswordEncoder()
				.username(member.getId())
				.password(member.getPassword())
				.roles("USER")
				.build();
         inMemoryUserDetailsManager.createUser(q);
	  }
      else
         message = "Error: did not add item";
      return "redirect:/admin/member?message=" + message;
	}

   @GetMapping("/items")
   public String COlist(Model model) throws SQLException {
      ResultSet rs = SQL.search("`checkout`");
       if (rs == null)
           model.addAttribute("displayItems", new ArrayList<>());
       else {
       List<checkoutForm> values = new ArrayList<>();
       while (rs.next()) {
         checkoutForm item = new checkoutForm();
         String itemId = rs.getString("itemId")
                           .toString()
                           .trim();
         item.setItemID(itemId);
         item.setMemberID(rs.getString("memberId")
                            .toString()
                            .trim());
         item.setCodate(rs.getString("coDate")
                          .toString()
                          .trim());
         item.setReturnDate(rs.getString("returnDate")
                              .toString()
                              .trim());
         item.setItemName(SQL.getItemName(item.getItemID()));
         String ret = rs.getString("returned").toString().trim();
         if (ret.equals("1"))
            item.setReturned("Yes");
         else
            item.setReturned("No");
         values.add(item);
        }
       rs.close();
       model.addAttribute("displayItems", values);
       }
       return "items";
   }
}