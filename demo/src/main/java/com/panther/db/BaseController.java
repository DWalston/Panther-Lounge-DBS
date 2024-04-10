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
import com.panther.details.checkoutForm;
import com.panther.details.memberDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.bind.annotation.SessionAttributes; 

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
public class BaseController {
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
       checkoutForm request = new checkoutForm();
       LocalDate today = LocalDate.now();
       DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d MMM uuuu");
       request.setMemberID("903943025");
       request.setItemID("21836");
       request.setCodate(today.format(dateFormat));
       request.setReturnDate(today.plusWeeks(2).format(dateFormat));
       model.addAttribute("request", request);
       return "checkout";
    }
    
    @PostMapping("/checkout")
    public String submit(@ModelAttribute("request") checkoutForm request, Model model) {
      JDBC sql = new JDBC();
      if(sql.addCheckout(request))
         model.addAttribute("message", "item added successfully");
      else
         model.addAttribute("message", "Did not add item");
      return "status";
    }

    @GetMapping("/checkin")
    public String checkin(Model model) {
       checkoutForm test = new checkoutForm();
       LocalDate today = LocalDate.now();
       DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d MMM uuuu");
       test.setMemberID("903943025");
       test.setItemID("21836");
       test.setReturnDate(today.format(dateFormat));
       model.addAttribute("request", test);
       return "checkin";
    }

    @PostMapping("/checkin")
    public String itemReturn(@ModelAttribute("request") checkoutForm request, Model model) {
      JDBC sql = new JDBC();
      if(sql.returnItem(request.getMemberID(), request.getItemID()))
         model.addAttribute("message", "item added successfully");
      else
         model.addAttribute("message", "Did not add item");
      return "status";
    }
}
