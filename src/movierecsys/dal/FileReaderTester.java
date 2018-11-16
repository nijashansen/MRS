/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierecsys.dal;

import java.io.IOException;
import movierecsys.be.Movie;

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
        MovieDAO movieDao = new MovieDAO();
<<<<<<< HEAD
<<<<<<< HEAD
        Movie movie = new Movie(22, 0, "");
        movieDao.deleteMovie(movie);
        if ()
        {
            System.out.println("cannot find");
        }
=======
//         
//        Movie movie = new Movie(17774, 2015, "Django unchained");
//        movieDao.deleteMovie(movie);
        
>>>>>>> a1b1fd024a8f26d28ec2d9cfc71f7ae562502e5e
=======
        
>>>>>>> parent of c24de87... useless change
        
//        List<Movie> allMovies = movieDao.getAllMovies();
//        for (Movie allMovy : allMovies)
//        {
//            System.out.println(allMovy.getTitle());
//        }
        
          

//        Movie id = movieDao.getMovie(17774);
//        System.out.println(id.getTitle());
        
//        Movie movie = movieDao.createMovie(2015, "Django unchained"); //Only run this once, or you will get multiple entries!
//        System.out.println(movie);
    }
}
