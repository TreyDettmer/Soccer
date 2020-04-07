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


        try
        {
            Scanner scanner = new Scanner(file);



            ArrayList<String> readValues = new ArrayList<>();
            while (scanner.hasNext())
            {
                readValues.add(scanner.nextLine());
            }
            scanner.close();
            String firstName = "";
            String lastName = "";
            int uniform = 0;
            String teamName = "";
            int goals = 0;
            int assists = 0;
            int shots = 0;
            int fouls = 0;
            int saves = 0;
            int yellowCards = 0;
            int redCards = 0;

            for (int i = 0; i < readValues.size();i++)
            {
                switch (i%11)
                {
                    case 0:
                        firstName = readValues.get(i);
                        break;
                    case 1:
                        lastName = readValues.get(i);
                        break;
                    case 2:
                        uniform = Integer.parseInt(readValues.get(i));
                        break;
                    case 3:
                        teamName = readValues.get(i);
                        break;
                    case 4:
                        goals = Integer.parseInt(readValues.get(i));
                        break;
                    case 5:
                        assists = Integer.parseInt(readValues.get(i));
                        break;
                    case 6:
                        shots = Integer.parseInt(readValues.get(i));
                        break;
                    case 7:
                        fouls = Integer.parseInt(readValues.get(i));
                        break;
                    case 8:
                        saves = Integer.parseInt(readValues.get(i));
                        break;
                    case 9:
                        yellowCards = Integer.parseInt(readValues.get(i));
                        break;
                    case 10:
                        redCards = Integer.parseInt(readValues.get(i));
                        SoccerPlayer player = new SoccerPlayer(firstName,lastName,uniform,teamName);
                        int playerVarIncrementer = 0;
                        while (playerVarIncrementer < goals){player.bumpGoals();playerVarIncrementer++;}
                        playerVarIncrementer = 0;
                        while (playerVarIncrementer < assists){player.bumpAssists();playerVarIncrementer++;}
                        playerVarIncrementer = 0;
                        while (playerVarIncrementer < shots){player.bumpShots();playerVarIncrementer++;}
                        playerVarIncrementer = 0;
                        while (playerVarIncrementer < fouls){player.bumpFouls();playerVarIncrementer++;}
                        playerVarIncrementer = 0;
                        while (playerVarIncrementer < saves){player.bumpSaves();playerVarIncrementer++;}
                        playerVarIncrementer = 0;
                        while (playerVarIncrementer < yellowCards){player.bumpYellowCards();playerVarIncrementer++;}
                        playerVarIncrementer = 0;
                        while (playerVarIncrementer < redCards){player.bumpRedCards();playerVarIncrementer++;}
                        String combinedName = combineName(firstName,lastName);
                        playerHashMap.put(combinedName,player);
                        Log.i("string", "Added Player");



                        break;


                }




            }
            /*
            for (;;)
            {
                Log.i("string", "before");
                if (!scanner.hasNextLine()) break;
                Log.i("string", "after");
                firstName = scanner.nextLine();
                if (!scanner.hasNextLine()) break;
                lastName = scanner.nextLine();
                if (!scanner.hasNextLine()) break;
                uniform = Integer.parseInt(scanner.nextLine());
                if (!scanner.hasNextLine()) break;
                teamName = scanner.nextLine();
                if (!scanner.hasNextLine()) break;
                goals = Integer.parseInt(scanner.nextLine());
                if (!scanner.hasNextLine()) break;
                assists = Integer.parseInt(scanner.nextLine());
                if (!scanner.hasNextLine()) break;
                shots = Integer.parseInt(scanner.nextLine());
                if (!scanner.hasNextLine()) break;
                fouls = Integer.parseInt(scanner.nextLine());
                if (!scanner.hasNextLine()) break;
                saves = Integer.parseInt(scanner.nextLine());
                if (!scanner.hasNextLine()) break;
                yellowCards = Integer.parseInt(scanner.nextLine());
                if (!scanner.hasNextLine()) break;
                redCards = Integer.parseInt(scanner.nextLine());
                SoccerPlayer player = new SoccerPlayer(firstName,lastName,uniform,teamName);





            }
            */

            Log.i("string", "returning true");
            return true;
        }
        catch (FileNotFoundException ex)
        {
            Log.i("string", "File not found");
            return false;
        }


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
            pw.close();
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
        HashSet<String> teams = new HashSet<>();
        for (Map.Entry element : playerHashMap.entrySet()) {
            SoccerPlayer player = (SoccerPlayer) element.getValue();
            String team = player.getTeamName();
            if (!teams.contains(team))
            {
                teams.add(team);
            }
        }
        return teams;
    }

}
