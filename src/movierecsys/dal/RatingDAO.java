/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierecsys.dal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import movierecsys.be.Movie;
import movierecsys.be.Rating;
import movierecsys.be.User;

/**
 *
 * @author pgn
 */
public class RatingDAO
{
    private static final String RATING_SOURCE = "data/ratings.txt";
    
    /**
     * Persists the given rating.
     * @param rating the rating to persist.
     */
    public void createRating(Rating rating, User user) throws IOException
    {
        Path path = new File(RATING_SOURCE).toPath();
        int id = -1;
        try (BufferedWriter bw = Files.newBufferedWriter(path, StandardOpenOption.SYNC, StandardOpenOption.APPEND, StandardOpenOption.WRITE))
        {
            id = getRatings(user);
            bw.newLine();
            bw.write(id + "," + user + "," + rating);
        }
    }
    
    /**
     * Updates the rating to reflect the given object.
     * @param rating The updated rating to persist.
     */
    public void updateRating(Rating rating)
    {
        //TODO Update rating
    }
    
    /**
     * Removes the given rating.
     * @param rating 
     */
    public void deleteRating(Rating rating)
    {
        //TODO Delete rating
    }
    
    /**
     * Gets all ratings from all users.
     * @return List of all ratings.
     */
    public List<Rating> getAllRatings() throws IOException
    {
        List<Rating> allRatings = new ArrayList<>();
        String source = "data/ratings.txt";
        File file = new File(source);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) //Using a try with resources!
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                if (!line.isEmpty())
                {
                    try
                    {
                        Rating rat = stringArrayToRating(line);
                        allRatings.add(rat);
                    } catch (Exception ex)
                    {
                        //Do nothing. Optimally we would log the error.
                    }
                }
            }
        }
        return allRatings;
    }
    
    /**
     * Get all ratings from a specific user.
     * @param user The user 
     * @return The list of ratings.
     */
    public int getRatings(User user) throws IOException
    {
        List<Rating> allRatings = getAllRatings();
        int highId = allRatings.get(allRatings.size() - 1).getRating();
        return highId + 1;
    }
    
    private Rating stringArrayToRating(String line)
    {
        String[] arrRating = line.split(",");

        int id = Integer.parseInt(arrRating[0]);
        int user = Integer.parseInt(arrRating[1]);
        String movie = arrRating[2];

        Rating rat = new Rating(movie, user, id);
        return rat;
    }
}
