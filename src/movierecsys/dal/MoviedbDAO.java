/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierecsys.dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import movierecsys.be.Movie;
import org.junit.runners.model.Statement;

/**
 *
 * @author Nijas Hansen
 */
public class MoviedbDAO implements IMovieRepository
{

    @Override
    public Movie createMovie(int releaseYear, String title) throws IOException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteMovie(Movie movie) throws IOException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Movie> getAllMovies() throws IOException
    {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setServerName("10.176.111.31");
        ds.setDatabaseName("mrsnwh");
        ds.setUser("CS2018A_25");
        ds.setPassword("CS2018A_25");
        
        List<Movie> movies = new ArrayList<Movie>();
        
        try(Connection con = ds.getConnection())
        {
            java.sql.Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM SELECT * FROM Movie;");
            while(rs.next())
            {
                int id = rs.getInt("id");
                int year = rs.getInt("year");
                String title = rs.getString("title");
                Movie movie = new Movie(id, year, title);
                movies.add(movie);
            }
        }
        catch(SQLException ex)
            {
               ex.printStackTrace();
            }
        return movies;  
    }

    @Override
    public Movie getMovie(int id) throws IOException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
