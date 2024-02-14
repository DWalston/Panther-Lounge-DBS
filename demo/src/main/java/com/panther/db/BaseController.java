package com.panther.db;


import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.ModelAttribute; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.servlet.mvc.support.RedirectAttributes; 
import org.springframework.web.bind.annotation.SessionAttributes; 

@Controller
public class BaseController {

    @RequestMapping("/")
    public String home()
    {
        return "index";
    }
    
    @GetMapping("/login")
    public String login()
    {
	return "index";
    }
	@GetMapping("/logout")
	public String logout()
	{
		return "index";
	}
    
    @RequestMapping("/checkout")
    public String checkout() {
       return "checkout";
    }
    
    @GetMapping("/catalog")
    public String cat(Model model) {
       return "catalog";
    }
}
