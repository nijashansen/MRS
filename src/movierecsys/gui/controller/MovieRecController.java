/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierecsys.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import movierecsys.be.Movie;
import movierecsys.dal.MovieDAO;

/**
 *
 * @author pgn
 */
public class MovieRecController implements Initializable
{

    /**
     * The TextField containing the URL of the targeted website.
     */
    @FXML
    private TextField txtMovieSearcjh;

    /**
     * The TextField containing the query word.
     */
    @FXML
    private ListView<Movie> lstMovies;


    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        MovieDAO mvDAO = new MovieDAO();
        //observableArrayList<Movie> list = new FXCollections.observableArrayList<>();
        List<Movie> listmv = new ArrayList<>();
        try
        {
            listmv = mvDAO.getAllMovies();
            lstMovies.getItems().addAll(listmv);
        } catch (IOException ex)
        {
            Logger.getLogger(MovieRecController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}