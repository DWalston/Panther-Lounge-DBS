package com.panther.db;


import org.json.JSONObject;
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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/catalog")
public class CatalogController {
    JDBC SQL;
    String tableName = "items";

    public CatalogController() throws SQLException{
        SQL = new JDBC();
    }

    @GetMapping("")
    public String cat(Model model) throws SQLException {
       model.addAttribute("searchQuery", new itemDetails());
       ResultSet rs = SQL.search(tableName);
       if (rs == null)
           model.addAttribute("displayItems", new ArrayList<>());
       else {
       List<itemDetails> values = new ArrayList<>();
       while (rs.next()) {
         itemDetails item = new itemDetails();
         item.setId(rs.getString("id")
                      .toString()
                      .trim());
         item.setName(rs.getString("itemName")
                        .toString()
                        .trim());
         item.setType(rs.getString("itemType")
                        .toString()
                        .trim());
         item.setSeries(rs.getString("series")
                         .toString()
                         .trim());
         item.setStatus(rs.getString("itemStatus")
                          .toString()
                          .trim());
         item.setRating(rs.getString("rating")
                          .toString()
                          .trim());
         item.setDescription(rs.getString("description")
                        .toString()
                        .trim());
         item.setClub(rs.getString("club")
                        .toString()
                        .trim());
         item.setShelf(rs.getString("shelf")
                            .toString()
                            .trim());
         values.add(item);
        }
       rs.close();
       model.addAttribute("displayItems", values);
       }
       return "catalog";
    }

    /*@PostMapping("")
    public String gotoCheckout(Model model) {
        return "redirect:/checkout";
    }*/

    @PostMapping("/searchProcess")
    public String seachByName(@ModelAttribute("searchQuery") itemDetails query, Model model) {
        String name = query.getName();
        return "redirect:/catalog/search?name=" + name;
    }

    @GetMapping("/search")
    public String search(@RequestParam() String name, Model model) throws SQLException {
        model.addAttribute("searchQuery", new itemDetails());
        ResultSet rs = SQL.itemNameSearch(name);

        if (rs == null)
           model.addAttribute("displayItems", new ArrayList<>());
       else {
       List<itemDetails> values = new ArrayList<>();
       while (rs.next()) {
         itemDetails item = new itemDetails();
         item.setId(rs.getString("id")
                      .toString()
                      .trim());
         item.setName(rs.getString("itemName")
                        .toString()
                        .trim());
         item.setType(rs.getString("itemType")
                        .toString()
                        .trim());
         item.setSeries(rs.getString("series")
                         .toString()
                         .trim());
         item.setStatus(rs.getString("itemStatus")
                          .toString()
                          .trim());
         item.setRating(rs.getString("rating")
                          .toString()
                          .trim());
         item.setDescription(rs.getString("description")
                        .toString()
                        .trim());
         item.setClub(rs.getString("club")
                        .toString()
                        .trim());
         item.setShelf(rs.getString("shelf")
                            .toString()
                            .trim());
         values.add(item);
        }
       rs.close();
       model.addAttribute("displayItems", values);}
       return "catalog";
    }

    @GetMapping("/advanced")
    public String advanced(Model model) {
        model.addAttribute("search", new itemDetails());
        return "advanced";
    }
}
