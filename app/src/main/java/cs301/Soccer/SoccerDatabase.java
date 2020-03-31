package cs301.Soccer;

import android.util.Log;
import android.util.SparseArray;

import cs301.Soccer.soccerPlayer.SoccerPlayer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Soccer player database -- presently, all dummied up
 *
 * @author *** put your name here ***
 * @version *** put date of completion here ***
 *
 */
public class SoccerDatabase implements SoccerDB {

    private HashMap<String,SoccerPlayer> playerHashMap = new HashMap<>();

    private String combineName(String first, String last)
    {
        String returnedString = first + " ## " + last;
        return returnedString;
    }


    /**
     * add a player
     *
     * @see SoccerDB#addPlayer(String, String, int, String)
     */
    @Override
    public boolean addPlayer(String firstName, String lastName,
                             int uniformNumber, String teamName) {
        String combinedName = combineName(firstName,lastName);
        if (!playerHashMap.containsKey(combinedName))
        {
            SoccerPlayer player = new SoccerPlayer(firstName,lastName,uniformNumber,teamName);
            playerHashMap.put(combinedName,player);
            return true;
        }
        return false;
    }

    /**
     * remove a player
     *
     * @see SoccerDB#removePlayer(String, String)
     */
    @Override
    public boolean removePlayer(String firstName, String lastName) {
        String combinedName = combineName(firstName,lastName);
        if (playerHashMap.containsKey(combinedName))
        {
            playerHashMap.remove(combinedName);
            return true;
        }
        return false;
    }

    /**
     * look up a player
     *
     * @see SoccerDB#getPlayer(String, String)
     */
    @Override
    public SoccerPlayer getPlayer(String firstName, String lastName) {
        String combinedName = combineName(firstName,lastName);
        if (playerHashMap.containsKey(combinedName))
        {
            return playerHashMap.get(combinedName);
        }
        return null;
    }

    /**
     * increment a player's goals
     *
     * @see SoccerDB#bumpGoals(String, String)
     */
    @Override
    public boolean bumpGoals(String firstName, String lastName) {
        String combinedName = combineName(firstName,lastName);
        if (playerHashMap.containsKey(combinedName))
        {
            playerHashMap.get(combinedName).bumpGoals();
            return true;
        }
        return false;
    }

    /**
     * increment a player's assists
     *
     * @see SoccerDB#bumpAssists(String, String)
     */
    @Override
    public boolean bumpAssists(String firstName, String lastName) {
        String combinedName = combineName(firstName,lastName);
        if (playerHashMap.containsKey(combinedName))
        {
            playerHashMap.get(combinedName).bumpAssists();
            return true;
        }
        return false;
    }

    /**
     * increment a player's shots
     *
     * @see SoccerDB#bumpShots(String, String)
     */
    @Override
    public boolean bumpShots(String firstName, String lastName) {
        String combinedName = combineName(firstName,lastName);
        if (playerHashMap.containsKey(combinedName))
        {
            playerHashMap.get(combinedName).bumpShots();
            return true;
        }
        return false;
    }

    /**
     * increment a player's saves
     *
     * @see SoccerDB#bumpSaves(String, String)
     */
    @Override
    public boolean bumpSaves(String firstName, String lastName) {
        String combinedName = combineName(firstName,lastName);
        if (playerHashMap.containsKey(combinedName))
        {
            playerHashMap.get(combinedName).bumpSaves();
            return true;
        }
        return false;
    }

    /**
     * increment a player's fouls
     *
     * @see SoccerDB#bumpFouls(String, String)
     */
    @Override
    public boolean bumpFouls(String firstName, String lastName) {
        String combinedName = combineName(firstName,lastName);
        if (playerHashMap.containsKey(combinedName))
        {
            playerHashMap.get(combinedName).bumpFouls();
            return true;
        }
        return false;
    }

    /**
     * increment a player's yellow cards
     *
     * @see SoccerDB#bumpYellowCards(String, String)
     */
    @Override
    public boolean bumpYellowCards(String firstName, String lastName) {
        String combinedName = combineName(firstName,lastName);
        if (playerHashMap.containsKey(combinedName))
        {
            playerHashMap.get(combinedName).bumpYellowCards();
            return true;
        }
        return false;
    }

    /**
     * increment a player's red cards
     *
     * @see SoccerDB#bumpRedCards(String, String)
     */
    @Override
    public boolean bumpRedCards(String firstName, String lastName) {
        String combinedName = combineName(firstName,lastName);
        if (playerHashMap.containsKey(combinedName))
        {
            playerHashMap.get(combinedName).bumpRedCards();
            return true;
        }
        return false;
    }

    /**
     * tells the number of players on a given team
     *
     * @see SoccerDB#numPlayers(String)
     */
    @Override
    // report number of players on a given team (or all players, if null)
    public int numPlayers(String teamName) {
        int count = 0;
        if (teamName == null)
        {
            return playerHashMap.size();
        }
        for (Map.Entry element : playerHashMap.entrySet())
        {
            SoccerPlayer player = (SoccerPlayer)element.getValue();
            if (player.getTeamName().equalsIgnoreCase(teamName))
            {
                count += 1;
            }
        }
        return count;
    }

    /**
     * gives the nth player on a the given team
     *
     * @see SoccerDB#playerNum(int, String)
     */
    // get the nTH player
    @Override
    public SoccerPlayer playerNum(int idx, String teamName) {
        int count = 0;
        if (teamName == null)
        {
            for (Map.Entry element : playerHashMap.entrySet())
            {
                if (count == idx)
                {
                    SoccerPlayer player = (SoccerPlayer)element.getValue();
                    return player;
                }
                count++;
            }
        }
        else
        {
            for (Map.Entry element : playerHashMap.entrySet())
            {
                SoccerPlayer player = (SoccerPlayer)element.getValue();
                if (player.getTeamName().equalsIgnoreCase(teamName))
                {
                    if (count == idx)
                    {
                        return player;
                    }
                    count++;
                }
            }
        }
        return null;
    }

    /**
     * reads database data from a file
     *
     * @see SoccerDB#readData(java.io.File)
     */
    // read data from file
    @Override
    public boolean readData(File file) {
        return file.exists();
    }

    /**
     * write database data to a file
     *
     * @see SoccerDB#writeData(java.io.File)
     */
    // write data to file
    @Override
    public boolean writeData(File file) {
        try {
            PrintWriter pw = new PrintWriter(file);
            for (Map.Entry element : playerHashMap.entrySet()) {
                SoccerPlayer player = (SoccerPlayer) element.getValue();
                pw.println(logString(player.getFirstName()));
                pw.println(logString(player.getLastName()));
                pw.println(logString(player.getUniform() + ""));
                pw.println(logString(player.getTeamName()));
                pw.println(logString(player.getGoals() + ""));
                pw.println(logString(player.getAssists() + ""));
                pw.println(logString(player.getShots() + ""));
                pw.println(logString(player.getFouls() + ""));
                pw.println(logString(player.getSaves() + ""));
                pw.println(logString(player.getYellowCards() + ""));
                pw.println(logString(player.getRedCards() + ""));
            }
            return true;

        }
        catch (FileNotFoundException ex)
        {
            return false;
        }

    }

    /**
     * helper method that logcat-logs a string, and then returns the string.
     * @param s the string to log
     * @return the string s, unchanged
     */
    private String logString(String s) {
        Log.i("write string", s);
        return s;
    }

    /**
     * returns the list of team names in the database
     *
     * @see cs301.Soccer.SoccerDB#getTeams()
     */
    // return list of teams
    @Override
    public HashSet<String> getTeams() {
        return new HashSet<String>();
    }

}
