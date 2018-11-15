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
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
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
    public void updateRating(Rating rating) throws IOException
    {
        File tmp = new File("data/tmp_movies.txt");
        List<Rating> allRating = getAllRatings();
        allRating.removeIf((Rating t) -> t.getRating()== rating.getRating());
        allRating.add(rating);
        Collections.sort(allRating, (Rating o1, Rating o2) -> Integer.compare(o1.getRating(), o2.getRating()));
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(tmp)))
        {
            for (Rating rat : allRating)
            {
                bw.write(rat.getMovie()+","+rat.getUser()+","+rat.getRating());
                bw.newLine();
            }
        }
        Files.copy(tmp.toPath(), new File(RATING_SOURCE).toPath(), StandardCopyOption.REPLACE_EXISTING);
        Files.delete(tmp.toPath());
    }
    
    /**
     * Removes the given rating.
     * @param rating 
     */
    public void deleteRating(Rating rating) throws IOException
    {
        String tempFile = "temp.txt";
        File oldFile = new File (RATING_SOURCE);
        File newFile = new File (tempFile);
        List<Rating> newRatList = new ArrayList();
        List<Rating> oldRatList = getAllRatings();
        try
        {
            for (int i = 0; i < oldRatList.size(); i++)
            {
                if(oldRatList.get(i).getRating()!= rating.getRating())
                {
                    newRatList.add(oldRatList.get(i));
                }
            }
            FileWriter fw = new FileWriter(tempFile);
            BufferedWriter bw = new BufferedWriter(fw);
            for (Rating rate : newRatList)
            {
                bw.write(rate.getMovie()+ "," + rate.getUser() + "," + rate.getRating());
                bw.newLine();
            }
            bw.flush();
            bw.close();
            oldFile.delete();
            File dump = new File(RATING_SOURCE);
            newFile.renameTo(dump);
        }
        catch(IOException e)
                {
                    System.out.println("Something went wrong");
                }
        
        
        
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
        List<Rating> allRates = new ArrayList<>();
        String source = "data/Ratings.txt";
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
                        Rating rate = stringArrayToRating(line);
                        if (rate.getRating() == getRatings(user))
                        {
                           return getRatings(user);
                        }
                        
                    } catch (Exception ex)
                    {
                        //Do nothing. Optimally we would log the error.
                    }
                }
            }
        }
        return getRatings(user);
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
