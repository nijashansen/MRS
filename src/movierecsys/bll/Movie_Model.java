/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierecsys.bll;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import movierecsys.be.Movie;
import movierecsys.dal.MovieDAO;


/**
 *
 * @author Nijas Hansen
 */
public class Movie_Model
{
    List<Movie> list = new ArrayList<>();
    MovieDAO movieDao = new MovieDAO();
    List<Movie> obList = FXCollections.observableArrayList();
}
