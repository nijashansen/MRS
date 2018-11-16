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
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import movierecsys.be.User;

/**
 *
 * @author pgn
 */
public class UserDAO
{
    private final String USER_SOURCE = "users.txt";
    /**
     * Gets a list of all known users.
     * @return List of users.
     */
    public List<User> getAllUsers() throws IOException
    {
        List<User> allUsers = new ArrayList<>();
        File file = new File(USER_SOURCE);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) //Using a try with resources!
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                if (!line.isEmpty())
                {
                    try
                    {
                        User use = stringArrayToUser(line);
                        allUsers.add(use);
                    } catch (Exception ex)
                    {
                        //Do nothing. Optimally we would log the error.
                    }
                }
            }
        }
        return allUsers;
    }
    
    private User stringArrayToUser(String line)
    {
        String[] arrUser = line.split(",");

        int id = Integer.parseInt(arrUser[0]);
        String name = arrUser[1];
        

        User user = new User(id, name);
        return user;
    }
    /**
     * Gets a single User by its ID.
     * @param id The ID of the user.
     * @return The User with the ID.
     */
    public User getUser(int id) throws IOException
    {
        List<User> allUsers = new ArrayList<>();
        File file = new File(USER_SOURCE);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) //Using a try with resources!
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                if (!line.isEmpty())
                {
                    try
                    {
                        User use = stringArrayToUser(line);
                        if (use.getId() == id)
                        {
                           return use;
                        }
                        
                    } catch (Exception ex)
                    {
                        //Do nothing. Optimally we would log the error.
                    }
                }
            }
        }
        return null;
    }
    
    
    /**
     * Updates a user so the persistence storage reflects the given User object.
     * @param user The updated user.
     */
    public void updateUser(User user) throws IOException
    {
        File tmp = new File(USER_SOURCE);
        List<User> getAllUsers = getAllUsers();
        getAllUsers.removeIf((User t) -> t.getId() == user.getId());
        getAllUsers.add(user);
        Collections.sort(getAllUsers, (User o1, User o2) -> Integer.compare(o1.getId(), o2.getId()));
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(tmp)))
        {
            for (User use : getAllUsers)
            {
                bw.write(use.getId()+","+use.getName());
                bw.newLine();
            }
        }
        Files.copy(tmp.toPath(), new File(USER_SOURCE).toPath(), StandardCopyOption.REPLACE_EXISTING);
        Files.delete(tmp.toPath());
    }
    
}
