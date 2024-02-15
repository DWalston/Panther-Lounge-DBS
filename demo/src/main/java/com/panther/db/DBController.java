package com.panther.db;

import java.sql.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
 
@RestController
@CrossOrigin
public class DBController {
   JDBC SQL = new JDBC();
   @GetMapping("/findAll")
   @CrossOrigin
   public String findAll(@RequestParam String table) throws SQLException {
      String tableName = table;
      ResultSet rs = SQL.search(tableName);
      JSONArray values = new JSONArray();
      if (rs == null)
        return values.toString();
      while (rs.next()) {
         JSONObject jsonobj = new JSONObject();
         jsonobj.put("id",
                     rs.getString("id")
                         .toString()
                         .trim());
         jsonobj.put("name",
                     rs.getString("itemName")
                         .toString()
                         .trim());
         jsonobj.put("type",
               rs.getString("itemType")
                   .toString()
                   .trim());
         jsonobj.put("series",
               rs.getString("series")
                   .toString()
                   .trim());
         jsonobj.put("status",
               rs.getString("itemStatus")
                   .toString()
                   .trim());
         jsonobj.put("rating",
               rs.getString("rating")
                   .toString()
                   .trim());
         jsonobj.put("desc",
               rs.getString("descript")
                   .toString()
                   .trim());
         values.put(jsonobj);
     }
     rs.close();
     System.out.println("values " + values.length());
     return values.toString();
   }

   @GetMapping("/find")
   @CrossOrigin
   public String find(@RequestParam String table, @RequestParam String name) throws SQLException {
      String tableName = table;
      String[][] searchParams = {{"itemName",name}};
      ResultSet rs = SQL.search(tableName, searchParams);
      JSONArray values = new JSONArray();
      if (rs == null)
        return values.toString();
      while (rs.next()) {
         JSONObject jsonobj = new JSONObject();
         jsonobj.put("id",
                     rs.getString("id")
                         .toString()
                         .trim());
         jsonobj.put("name",
                     rs.getString("itemName")
                         .toString()
                         .trim());
         jsonobj.put("type",
               rs.getString("itemType")
                   .toString()
                   .trim());
         jsonobj.put("series",
               rs.getString("series")
                   .toString()
                   .trim());
         jsonobj.put("status",
               rs.getString("itemStatus")
                   .toString()
                   .trim());
         jsonobj.put("rating",
               rs.getString("rating")
                   .toString()
                   .trim());
         jsonobj.put("desc",
               rs.getString("descript")
                   .toString()
                   .trim());
         values.put(jsonobj);
     }
     rs.close();
     System.out.println("values " + values.length());
     return values.toString();
   }
}