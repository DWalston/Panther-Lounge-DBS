package com.panther.db;


import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.ModelAttribute; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.panther.details.itemDetails;
import com.panther.details.checkoutForm;

import org.springframework.web.bind.annotation.SessionAttributes; 

@Controller
public class BaseController {

    @RequestMapping("/")
    public String home()
    {
        return "index";
    }

    @GetMapping("/checkout")
    public String checkout(Model model) {
        checkoutForm test = new checkoutForm();
        test.setItemID("35");
        test.setMemberID("4");
        model.addAttribute("request", test);
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

    @GetMapping("/catalog")
    public String cat(Model model) {
       model.addAttribute("item", new itemDetails());
       return "catalog";
    }

    @PostMapping("/catalog")
    public String gotoCheckout(Model model) {
        return "redirect:/checkout";
    }
}
