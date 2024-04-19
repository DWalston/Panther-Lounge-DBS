package com.panther.db;


import org.json.JSONObject;
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.ModelAttribute; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import com.panther.details.itemDetails;
import com.fasterxml.jackson.databind.JsonSerializable.Base;
import com.panther.details.checkoutForm;
import com.panther.details.memberDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.web.bind.annotation.SessionAttributes; 

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.ResultSet;

@Controller
public class BaseController {

    JDBC SQL;
    public BaseController() throws SQLException{
        SQL = new JDBC();
    }

    @GetMapping("/")
    public String home(Model model)
    {
        model.addAttribute("searchQuery", new itemDetails());
        return "index";
    }
    @GetMapping("/index")
    public String homeTwo()
    {
        return "redirect:/";
    }
    
    @PostMapping("/")
    public String home(@ModelAttribute("search") itemDetails search) {
        String itemName = search.getName();
        return "/catalog?name=" + itemName;
    }
    
    @GetMapping("/login")
    public String login()
    {
	return "redirect:/";
    }
	
	@GetMapping("/logout")
	public String logout()
	{
		return "redirect:/";
	}
    
    @GetMapping("/checkout")
    public String checkout(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentName = authentication.getName();
       model.addAttribute("searchQuery", new itemDetails());
       checkoutForm request = new checkoutForm();
       LocalDate today = LocalDate.now();
       DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d MMM uuuu");
       request.setMemberID(currentName);
       request.setCodate(today.format(dateFormat));
       request.setReturnDate(today.plusWeeks(2).format(dateFormat));
       model.addAttribute("request", request);
       return "checkout";
    }
    
    @PostMapping("/checkout")
    public String submit(@ModelAttribute("request") checkoutForm request, Model model) throws SQLException {
      String message;
      if(SQL.addCheckout(request))
         message = "Checked out item!";
      else
         message = "Could not check out, please try again or contact an officer";
      return "redirect:/checkin?message=" + message;
    }

    @GetMapping("/checkin")
    public String checkin(Model model) throws SQLException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentName = authentication.getName();
        ResultSet rs = SQL.findCOItems(currentName);

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
         item.setCodate(rs.getString("coDate")
                          .toString()
                          .trim());
         item.setReturnDate(rs.getString("returnDate")
                              .toString()
                              .trim());
         item.setItemName(SQL.getItemName(item.getItemID()));
         values.add(item);
        }
       rs.close();
       model.addAttribute("displayItems", values);}
       return "checkin";
    }

    @GetMapping("/checkin/confirm")
    public String returnForm(@RequestParam() String id, Model model) throws SQLException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentName = authentication.getName();
        checkoutForm test = new checkoutForm();
        LocalDate today = LocalDate.now();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d MMM uuuu");
        test.setMemberID(currentName);
        test.setItemID(id);
        test.setItemName(SQL.getItemName(id));
        test.setReturnDate(today.format(dateFormat));
        model.addAttribute("request", test);
        return "checkinForm";
    }

    @PostMapping("/checkin/confirm")
    public String itemReturn(@ModelAttribute("request") checkoutForm request, Model model) {
        String message;
        if(SQL.returnItem(request.getMemberID(), request.getItemID()))
           message = "Item returned successfully!";
        else
           message = "Could not be returned, please try again or contact an officer";
      return "redirect:/checkin?message=" + message;
    }
}
