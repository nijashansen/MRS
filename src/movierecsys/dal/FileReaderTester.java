/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierecsys.dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import movierecsys.be.Movie;
import java.sql.Statement;
import movierecsys.be.Rating;
import movierecsys.be.User;
/**
 *
 * @author pgn
 */
public class FileReaderTester
{

    /**
     * Example method. This is the code I used to create the users.txt files.
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException
    {
//        mitigateMovies();
//        mitigateUsers();
        mitigateRatings();
    }
    
    public static void mitigateUsers() throws IOException
    {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setServerName("10.176.111.31");
        ds.setDatabaseName("mrsnwh");
        ds.setUser("CS2018A_25");
        ds.setPassword("CS2018A_25");
        
        List<User> users = new UserDAO().getAllUsers();
        
        try(Connection con = ds.getConnection())
        {
            Statement statement = con.createStatement();
            int counter = 0;
            for (User user : users)
            {
                String sql = "INSERT INTO [User] (id, name) VALUES("
                        + user.getId() + ",'"
                        + user.getName() + "');";
                statement.addBatch(sql);
                counter++;
                if(counter % 1000 == 0)
                {
                    statement.executeBatch();
                    System.out.println("added 1000 users.");
                }
            }
                statement.executeBatch();
                
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }
    
    public static void mitigateRatings() throws IOException
    {
        List<Rating> allRatings = new RatingDAO().getAllRatings();
        System.out.println("#Ratings " + allRatings.size());
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setServerName("10.176.111.31");
        ds.setDatabaseName("mrsnwh");
        ds.setUser("CS2018A_25");
        ds.setPassword("CS2018A_25");
        try(Connection con = ds.getConnection())
        {
            Statement st = con.createStatement();
            int counter = 0;
            for (Rating rating : allRatings)
            {
                String sql = "INSERT INTO Rating (movieId, userId, rating) VALUES ("
                        + rating.getMovie() + ","
                        + rating.getUser() + ","
                        + rating.getRating() + ");";
                st.addBatch(sql);
                counter++;
                if(counter % 1000 == 0)
                {
                    st.executeBatch();
                    System.out.println("Added 1000 ratings.");
                }
            }
            st.executeBatch();
        }
        catch (SQLException ex)
        {
        }
    }
    
    public static void mitigateMovies() throws IOException
    {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setServerName("10.176.111.31");
        ds.setDatabaseName("mrsnwh");
        ds.setUser("CS2018A_25");
        ds.setPassword("CS2018A_25");
        
        MovieDAO mvDAO = new MovieDAO();
        List<Movie> movies = mvDAO.getAllMovies();
        
        try(Connection con = ds.getConnection())
        {
            Statement statement = con.createStatement();
            for (Movie movie : movies)
            {
                String sql = "INSERT INTO Movie (id, year, title) VALUES("
                        +movie.getId()+","
                        +movie.getYear()+",'"
                        +movie.getTitle().replace("'", "")+"');";
                System.out.println(sql);
                int i = statement.executeUpdate(sql);
                // INSERT INTO Movie (id,year,title) VALUES(1, 2018, Venom)
                System.out.println("Affected row = " + i);
            }
        }
        catch(SQLException ex)
            {
               ex.printStackTrace();
            }
        
    }
}
