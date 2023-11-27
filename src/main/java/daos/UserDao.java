package daos;

import business.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class UserDao extends Dao implements UserDaoInterface
{
    public UserDao(String dbName){
        super(dbName);
    }
    
    @Override
    public List<User> findAllUsers()
    {
    	Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<User> users = new ArrayList<User>();
        
        try 
        {
            //Get connection object using the methods in the super class (Dao.java)...
            con = this.getConnection();

            String query = "SELECT * FROM USER";
            ps = con.prepareStatement(query);
            
            //Using a PreparedStatement to execute SQL...
            rs = ps.executeQuery();
            while (rs.next()) 
            {
                int userId = rs.getInt("ID");
                String username = rs.getString("USERNAME");
                String password = rs.getString("PASSWORD");
                String lastname = rs.getString("LAST_NAME");
                String firstname = rs.getString("FIRST_NAME");
                User u = new User(userId, firstname, lastname, username, password);
                users.add(u);
            }
        } 
        catch (SQLException e) 
        {
            System.out.println("An error occurred in the findAllUsers() method: " + e.getMessage());
        } 
        finally 
        {
            try 
            {
                if (rs != null) 
                {
                    rs.close();
                }
                if (ps != null) 
                {
                    ps.close();
                }
                if (con != null) 
                {
                    freeConnection(con);
                }
            } 
            catch (SQLException e) 
            {
                System.out.println("An error occurred when shutting down the findAllUsers() method: " + e.getMessage());
            }
        }
        return users;     // may be empty
    }

    @Override
    public User findUserByUsernamePassword(String uname, String pword)
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User u = null;
        try {
            con = this.getConnection();
            
            String query = "SELECT * FROM USER WHERE USERNAME = ? AND PASSWORD = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, uname);
            ps.setString(2, pword);
            
            rs = ps.executeQuery();
            if (rs.next()) 
            {
            	int userId = rs.getInt("ID");
                String username = rs.getString("USERNAME");
                String password = rs.getString("PASSWORD");
                String lastname = rs.getString("LAST_NAME");
                String firstname = rs.getString("FIRST_NAME");
                u = new User(userId, firstname, lastname, username, password);
            }
        } 
        catch (SQLException e) 
        {
            System.out.println("An error occurred in the findUserByUsernamePassword() method: " + e.getMessage());
        } 
        finally 
        {
            try 
            {
                if (rs != null) 
                {
                    rs.close();
                }
                if (ps != null) 
                {
                    ps.close();
                }
                if (con != null) 
                {
                    freeConnection(con);
                }
            } 
            catch (SQLException e) 
            {
                System.out.println("An error occurred when shutting down the findUserByUsernamePassword() method: " + e.getMessage());
            }
        }
        return u;     // u may be null 
    }  

    @Override
    public User findUserById(int id) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User u = null;
        try {
            con = this.getConnection();
            
            String query = "SELECT * FROM USER WHERE ID = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            
            rs = ps.executeQuery();
            if (rs.next()) 
            {
            	int userId = rs.getInt("ID");
                String username = rs.getString("USERNAME");
                String password = rs.getString("PASSWORD");
                String lastname = rs.getString("LAST_NAME");
                String firstname = rs.getString("FIRST_NAME");
                u = new User(userId, firstname, lastname, username, password);
            }
        } 
        catch (SQLException e) 
        {
            System.out.println("An error occurred in the findUserById() method: " + e.getMessage());
        } 
        finally 
        {
            try 
            {
                if (rs != null) 
                {
                    rs.close();
                }
                if (ps != null) 
                {
                    ps.close();
                }
                if (con != null) 
                {
                    freeConnection(con);
                }
            } 
            catch (SQLException e) 
            {
                System.out.println("An error occurred when shutting down the findUserById() method: " + e.getMessage());
            }
        }
        return u;     // u may be null 
    }

    @Override
    public int addUser(String uname, String pword, String fName, String lName) {
        Connection con = null;
        PreparedStatement ps = null; 
        // This will be used to hold the generated ID (i.e. the value auto-generated
        // by MySQL when inserting this entry into the database
        ResultSet generatedKeys = null;
        // Set the newId value to a default of -1
        // If the value returned by the method is -1, we know that the update failed
        // as the id value was never updated
        int newId = -1;
        try {
            con = this.getConnection();

            String query = "INSERT INTO user(first_name, last_name, username, password) VALUES (?, ?, ?, ?)";
            
            // Need to get the id back, so have to tell the database to return the id it generates
            // That is why we include the Statement.RETURN_GENERATED_KEYS parameter
            ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            
            ps.setString(1, fName);
            ps.setString(2, lName);
            ps.setString(3, uname);
            ps.setString(4, pword);
            
            // Because this is CHANGING the database, use the executeUpdate method
            ps.executeUpdate();
            
            // Find out what the id generated for this entry was
            generatedKeys = ps.getGeneratedKeys();
            // If there was a result, i.e. if the entry was inserted successfully
            if(generatedKeys.next())
            {
                // Get the id value that was generated by MySQL when the entry was inserted
                newId = generatedKeys.getInt(1);
            }
        } 
        catch (SQLException e) 
        {
            System.err.println("\tA problem occurred during the addUser method:");
            System.err.println("\t"+e.getMessage());
            newId = -1;
        } 
        finally 
        {
            try 
            {
                if(generatedKeys != null){
                    generatedKeys.close();
                }
                if (ps != null) 
                {
                    ps.close();
                }
                if (con != null) 
                {
                    freeConnection(con);
                }
            } 
            catch (SQLException e) 
            {
                System.err.println("A problem occurred when closing down the addUser method:\n" + e.getMessage());
            }
        }
        return newId;
    }
    
    public static void main(String [] args){
        UserDao userDao = new UserDao("user_database");
        int id = userDao.addUser("Michelle", "password", "Michelle", "Graham");
        System.out.println("The new id is: " + id);
    }
}