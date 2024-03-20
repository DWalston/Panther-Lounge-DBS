package com.panther.db;

import java.time.LocalDate;
import java.sql.*;
import javax.sql.DataSource;
import com.panther.details.memberDetails;
import com.panther.details.itemDetails;
import com.panther.details.checkoutForm;

public class JDBC {
	
	public boolean addMember (memberDetails member) {
        String command = "INSERT INTO member" +
                       " VALUES (?,?,?,?,?,?,?,?,?,?)";
        
        String[] values = new String[10];
       values[0] = member.getId();
       values[1] = member.getName();
       values[2] = member.getEmail();
       values[3] = member.getDiscord();
       values[4] = member.getPhone();
       values[5] = member.getClub();
       values[6] = member.getJoined();
       values[7] = member.getMemberUntil();
       values[8] = member.getFlag();
	   values[9] = member.getPassword();
       
       if (executeUpdate(command, values))
          return true;
       else
          return false;
   }
	
    public boolean addItem (String tableName, String[] values) {
        String command = "INSERT INTO " + tableName +
                       " VALUES (" + (new String(new char[values.length]).replace("\0", ", ?").substring(2) + ")");
        
        if (executeUpdate(command, values))
           return true;
        else
           return false;
    }
    public boolean addItem (itemDetails item) {
       String command = "INSERT INTO items" +
                      " VALUES (?,?,?,?,?,?,?,?,?)";
                      
       String[] values = new String[9];
       values[0] = item.getId();
       values[1] = item.getName();
       values[2] = item.getType();
       values[3] = item.getShelf();
       values[4] = item.getClub();
       values[5] = item.getSeries();
       values[6] = item.getStatus();
       values[7] = item.getRating();
       values[8] = item.getDesc();
       
       if (executeUpdate(command, values))
          return true;
       else
          return false;
   }

   //public boolean addSpreadsheet ()
   // do whatever process to get the scanner working here

   /* 
    * 
    */

   public boolean addCheckout (checkoutForm request) {
     String command = "INSERT INTO checkOut VALUES "
                      + "(?,?,?,?,?)";

     String[] values = new String[5];
     values[0] = request.getMemberID();
     values[1] = request.getItemID();
     values[2] = LocalDate.now().toString();
     values[3] = "0";
     values[4] = null;

     if (executeUpdate(command, values))
          return true;
     else
          return false;
   }

   public boolean returnItem(String memID, String itemID) {
      String command = "UPDATE checkOut SET returned=?,returnDate=?"
                       + " WHERE memberId=? AND itemId=?";

      String[] values = new String[4];
      values[0] = "1";
      values[1] = LocalDate.now().toString();
      values[2] = memID;
      values[3] = itemID;

      if (executeUpdate(command, values))
        return true;
      else return false;
   }
    
    //public String constructParams(String[][] params)
    
    public boolean removeItem (String tableName, String[][] constraints) {
        String command = "DELETE FROM " + tableName + " WHERE ";
        for (String[] param : constraints) {
            command = command + param[0] + "=? AND ";
        }
        
        try {
            DataSource dataSource = configDataSource.source();
            Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(command.substring(0, (command.length() - 5)));
            for (int i = 1; i <= constraints.length; i++) {
                ps.setString(i, constraints[i - 1][1]);
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
        return true;
   }
    
    public boolean editItem (String tableName, String[][] constraints, String[][] changes) {
      String command = "UPDATE " + tableName + " SET ";
       String[] params = new String[constraints.length + changes.length];
       int index = 0;
       for (String [] change : changes) {
          command = command + change[0] + "=?, ";
          params[index++] = change[1];
       }
       command = command.substring(0, command.length() - 2) + " WHERE ";
       for (String[] constraint : constraints) {
          command = command + constraint[0] + "=? AND ";
          params[index++] = constraint[1];
       }
       
       if (executeUpdate(command.substring(0, command.length() - 5), params))
          return true;
       else
          return false;
    }
    
    public ResultSet search (String tableName) {
       String command = "SELECT * FROM " + tableName;
       System.out.println(command);
       
       ResultSet rs = executeQuery(command, new String[0]);
       return rs;
    }
     //dear god in heaven these next two are broken rn
    public ResultSet search (String tableName, int start) {
        String command = "SELECT * FROM " + tableName + " LIMIT ?,?";
        System.out.println(command);

        ResultSet rs = executeQuery(command, new String[0]);
        return rs;
     }
	 public ResultSet itemNameSearch(String itemName) {
        String command = "SELECT * FROM items WHERE itemName LIKE ?";
        
        ResultSet rs = executeQuery(command, new String[0]);
        return rs;
    }

    public ResultSet search (String tableName, String[][] params) {
       String command = "SELECT * FROM " + tableName + " WHERE ";
    
       for (String[] param : params) {
          command = command + param[0] + "=? AND ";
       }
       command.substring(0, (command.length() - 5));
       
       ResultSet rs = null;
       try {
          DataSource dataSource = configDataSource.source();
          Connection conn = dataSource.getConnection();
          PreparedStatement ps = conn.prepareStatement(command);
          for (int i = 1; i <= params.length; i++) {
              ps.setString(i, params[i - 1][1]);
          }
          rs = ps.executeQuery();
      } catch (SQLException e) {
          System.out.println(e);
      }
       return rs;
    }
    
    public boolean executeUpdate(String command, String[] wildcards) {
       try {
          DataSource dataSource = configDataSource.source();
          Connection conn = dataSource.getConnection();
          PreparedStatement ps = conn.prepareStatement(command);
          for (int i = 1; i <= wildcards.length; i++) {
              ps.setString(i, wildcards[i - 1]);
          }
          ps.executeUpdate();
      } catch (SQLException e) {
          System.out.println(e);
          return false;
      }
      return true;
    }
    
    public ResultSet executeQuery (String command, String[] wildcards) {
       ResultSet rs = null;
       try {
          DataSource dataSource = configDataSource.source();
          Connection conn = dataSource.getConnection();
          PreparedStatement ps = conn.prepareStatement(command);
          for (int i = 1; i <= wildcards.length; i++) {
              ps.setString(i, wildcards[i - 1]);
          }
          rs = ps.executeQuery();
      } catch (SQLException e) {
          System.out.println(e);
      }
      return rs;
    }

    /*public ResultSet executeQuery (preparedStatement ps) {
        ResultSet rs = null;
        try {
           DataSource dataSource = configDataSource.source();
           Connection conn = dataSource.getConnection();
           rs = ps.executeQuery();
       } catch (SQLException e) {
           System.out.println(e);
       }
       return rs;
     }*/

}
