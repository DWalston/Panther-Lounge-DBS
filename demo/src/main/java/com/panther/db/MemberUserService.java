package com.panther.db;

 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
 
public class CustomUserDetailsService implements UserDetailsService {
 
    @Autowired
    JDBC SQL = new JDBC();
     
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = SQL.search("member", ["id", username]);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new MemberUserDetails(user);
    }
 
}