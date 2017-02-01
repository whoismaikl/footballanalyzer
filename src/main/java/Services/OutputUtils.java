package Services;

import Comparators.PlayerComparator;
import Comparators.RefereeComparator;
import Comparators.TeamComparator;
import Database.hibernateORM.PlayerDAOImpl;
import Database.hibernateORM.RefereeDAOImpl;
import Database.hibernateORM.TeamDAOImpl;
import Models.Database.DatabasePlayer;
import Models.Database.DatabaseReferee;
import Models.Database.DatabaseTeam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class OutputUtils {

    //Print Tournament table
    public void printTournamentTable() {
        TeamDAOImpl teamDaoImpl = new TeamDAOImpl();
        List<DatabaseTeam> databaseTeams = teamDaoImpl.getAllTeams();

        Object[] teamsToObjectArray = databaseTeams.toArray();
        Arrays.sort(teamsToObjectArray, new TeamComparator());

        for (int i = 0; i < teamsToObjectArray.length; i++) {
            System.out.println(((DatabaseTeam) teamsToObjectArray[i]).toString());
        }
    }

    //Print Ten Best Players
    public void printTenBestPlayers() {

        PlayerDAOImpl playerDaoImpl = new PlayerDAOImpl();
        List<DatabasePlayer> databasePlayers = playerDaoImpl.getAllPlayers();
        Object[] playerObjectArray = databasePlayers.toArray();
        Arrays.sort(playerObjectArray, new PlayerComparator());

        for (int i = 0; i < 10; i++) {
            System.out.println(((DatabasePlayer) playerObjectArray[i]).toString());
        }

    }

    //Print Torunament Rudest player
    public void printRudestPlayer() {
        PlayerDAOImpl playerDaoImpl = new PlayerDAOImpl();
        List<DatabasePlayer> databasePlayers = playerDaoImpl.getAllPlayers();

        DatabasePlayer rudestPlayer = new DatabasePlayer();

        int maxPenalties = 0;
        int currentTotalPenalties = 0;

        for (int i = 0; i < databasePlayers.size(); i++) {

            currentTotalPenalties = ((databasePlayers.get(i).getRedCards() * 2) + databasePlayers.get(i).getYellowCards());
            if (currentTotalPenalties > maxPenalties) {
                rudestPlayer = databasePlayers.get(i);
                maxPenalties = currentTotalPenalties;
            }
        }

        System.out.println("Rudest Player In Tournament is: " + rudestPlayer.toString());
    }

    //Print full statistic about selected Team
    public void printTeamStatistic() throws IOException {
        //Print Teams
        printTeamsWithId();
        System.out.println("Type team Name: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String userChoice = reader.readLine();

        TeamDAOImpl teamDaoImpl = new TeamDAOImpl();
        DatabaseTeam databaseTeam = teamDaoImpl.getTeamByName(userChoice);
        Long teamId = databaseTeam.getId();

        PlayerDAOImpl playerDAO = new PlayerDAOImpl();
        List<DatabasePlayer> teamPlayers = playerDAO.getAllPlayersByTeamId(teamId);

        for (int i = 0; i < teamPlayers.size(); i++) {
            System.out.println(teamPlayers.get(i).toString());
        }

    }

    private void printTeamsWithId() {
        TeamDAOImpl teamDaoImpl = new TeamDAOImpl();
        List<DatabaseTeam> databaseTeams = teamDaoImpl.getAllTeams();
        for (int i = 0; i < databaseTeams.size(); i++) {
            System.out.println("Team Id: " + databaseTeams.get(i).getId() + " Team Name: " + databaseTeams.get(i).getName());
        }
    }

    //Print Goalkeepers statistic
    public void printAllGoalkeepers() {

        PlayerDAOImpl playerDaoImpl = new PlayerDAOImpl();
        List<DatabasePlayer> databasePlayers = playerDaoImpl.getAllGoalkeepers();
        Object[] playerObjectArray = databasePlayers.toArray();
        Arrays.sort(playerObjectArray, new PlayerComparator());

        for (int i = 0; i < playerObjectArray.length; i++) {
            System.out.println(((DatabasePlayer) playerObjectArray[i]).toString());
        }
    }

    //Print five best goalkeepers
    public void printBestFiveGoalkeepers() {

        PlayerDAOImpl playerDaoImpl = new PlayerDAOImpl();
        List<DatabasePlayer> databasePlayers = playerDaoImpl.getAllGoalkeepers();
        Object[] playerObjectArray = databasePlayers.toArray();
        Arrays.sort(playerObjectArray, new PlayerComparator());

        try {
            double averageGoals = 2.0;
            int gameCount = 0;
            int goalPerGame = 0;

            for (int i = playerObjectArray.length - 1; i > playerObjectArray.length - 6; i--) {

                goalPerGame = ((DatabasePlayer) playerObjectArray[i]).getGoals();
                gameCount = ((DatabasePlayer) playerObjectArray[i]).getGamesPlayed();

                if (gameCount != 0) {
                    averageGoals = (goalPerGame / gameCount) * 10;
                    System.out.println(((DatabasePlayer) playerObjectArray[i]).toString() + "Average Goal In Game= " + averageGoals / 10);
                }

            }
        } catch (Exception e) {

        }
    }

    //Print referees Statistic
    public void printReferees() {
        RefereeDAOImpl refereeDAO = new RefereeDAOImpl();
        List<DatabaseReferee> databaseReferees = refereeDAO.getAllReferees();
        Object[] refereesObjectArray = databaseReferees.toArray();
        Arrays.sort(refereesObjectArray, new RefereeComparator());

        for (int i = 0; i < refereesObjectArray.length; i++) {
            System.out.println(((DatabaseReferee) refereesObjectArray[i]).toString());
        }
    }

    //Print total player Count in torney
    public void printPlayerCount() {

        PlayerDAOImpl playerDaoImpl = new PlayerDAOImpl();
        List<DatabasePlayer> databasePlayers = playerDaoImpl.getAllPlayers();

        int j = 0;
        for (int i = 0; i < databasePlayers.size(); i++) {
            j++;
        }
        List<DatabasePlayer> databaseGoalkeepers = playerDaoImpl.getAllGoalkeepers();
        for (int i = 0; i < databaseGoalkeepers.size(); i++) {
            j++;
        }

        System.out.println("Players Participated In Torney Count = " + j);
    }

    //Print total teams in tourney
    public void printTeamCount() {

        TeamDAOImpl teamDaoImpl = new TeamDAOImpl();
        List<DatabaseTeam> databaseTeams = teamDaoImpl.getAllTeams();
        int i;
        for (i = 0; i < databaseTeams.size(); i++) {
            i++;
        }
        System.out.println("Teams Participated In Torney Count = " + i);
    }
}
