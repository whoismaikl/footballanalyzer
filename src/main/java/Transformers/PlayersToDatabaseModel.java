package Transformers;

import Database.hibernateORM.TeamDAOImpl;
import Models.Database.DatabasePlayer;
import Models.Database.DatabaseTeam;
import Models.Domain.*;
import Services.TimeUtils;

import java.util.ArrayList;
import java.util.List;

public class PlayersToDatabaseModel {

    public List<DatabasePlayer> transformPlayerToDatabaseModel(Team teamOne, Team teamTwo) {

        ArrayList<DatabasePlayer> databasePlayersList = new ArrayList<DatabasePlayer>();
        int i = 0;

        // get id from t1
        TeamDAOImpl teamDaoImpl = new TeamDAOImpl();

        DatabaseTeam databaseTeam = teamDaoImpl.getTeamByName(teamOne.getTeamName());
        Long teamId = databaseTeam.getId();

        for (Player player : teamOne.getPlayers()) {

            DatabasePlayer databasePlayer = new DatabasePlayer();
            databasePlayer.setTeamId(teamId);
            databasePlayer.setName(player.getName());
            databasePlayer.setSurname(player.getSurname());
            databasePlayer.setRole(player.getRole());
            databasePlayer.setNumber(player.getPlayerNumber());

            databasePlayer.setGoals(getPlayerGoals(teamOne, teamTwo, player.getPlayerNumber(), player.getRole()));
            databasePlayer.setAssists(getPlayerAssists(teamOne, player.getPlayerNumber()));
            databasePlayer.setTimesAsMainPlayer(checkIsMainPlayer(teamOne, player.getPlayerNumber()));
            databasePlayer.setGamesPlayed(playerWasInGame(teamOne, player.getPlayerNumber()));

            databasePlayer.setYellowCards(getYellowCards(teamOne, player.getPlayerNumber()));
            if (databasePlayer.getYellowCards() == 2) {
                databasePlayer.setRedCards(1);
                databasePlayer.setYellowCards(0);
            }

            databasePlayer.setTimePlayed(getPlayedTime(teamOne, player.getPlayerNumber()));

            databasePlayersList.add(i, databasePlayer);
            i++;
        }

        return databasePlayersList;
    }

    private int getPlayedTime(Team team, int playerNumber) {
        //Time played
        //1 - get Game length
        //2 - check if player replaced
        //if Yes then gameLenght - replacement time

        int goalTimeInSeconds;
        int gameTime = 3600;
        int replacementTimeInSeconds = 0;
        int replacementTimeBefore = 0;
        int replacementTimeAfter = 0;
        String goalTime = "";
        try {
            TimeUtils timeUtils = new TimeUtils();

            for (Goal goal : team.getGoals()) {
                goalTime = goal.getGoalTime();

                goalTimeInSeconds = timeUtils.returnTimeInSeconds(goalTime);
                if (goalTimeInSeconds > gameTime) // goals in xml must be in correct order
                    gameTime = goalTimeInSeconds;
            }

            for (Replacement replacement : team.getReplacements()) {
                //Out of game
                if (replacement.getPlayer1() == playerNumber) {
                    String replacementTime = replacement.getReplacementTime();
                    replacementTimeBefore = timeUtils.returnTimeInSeconds(replacementTime);// time in game before switch
                }
                //Return to game
                if (replacement.getPlayer2() == playerNumber) {
                    String replacementTime = replacement.getReplacementTime();

                    replacementTimeAfter = timeUtils.returnTimeInSeconds(replacementTime);
                    replacementTimeInSeconds = replacementTimeBefore + (gameTime - replacementTimeAfter); // he returned in game time increase
                }
            }

            // if no replacements player gets gameTime
            if (replacementTimeInSeconds != 0)
                return replacementTimeInSeconds;

        } catch (Exception e) {
            gameTime = 3600;
        }
        return gameTime;
    }

    private int getYellowCards(Team team, int playerNumber) {

        int yellowCards = 0;
        for (Penalty player : team.getPenalties()) {
            if (player.getPlayerNumber() == playerNumber) {
                yellowCards++;
            }
        }
        return yellowCards;
    }

    private int checkIsMainPlayer(Team team, int playerNumber) {
        int isMainPlayer = 0;
        for (BasePlayer player : team.getBasePlayers()) {
            if (player.getPlayerNumber() == playerNumber)
                isMainPlayer = 1;
        }
        return isMainPlayer;
    }

    private int playerWasInGame(Team team, int playerNumber) {
        int isMainPlayer = 0;
        try {
            for (Replacement replacement : team.getReplacements()) {
                if (replacement.getPlayer2() == playerNumber)
                    isMainPlayer = 1;
            }
        } catch (Exception e) {
        }
        try {
            if (isMainPlayer != 1) {
                isMainPlayer = checkIsMainPlayer(team, playerNumber);
            }
        } catch (Exception e) {
            isMainPlayer = 0;
        }
        return isMainPlayer;
    }

    private int getPlayerAssists(Team team, int playerNumber) {
        int assistCount = 0;
        try {
            for (Goal goal : team.getGoals()) {
                for (Assist assist : goal.getAssist()) {
                    if (playerNumber == assist.getPlayerNumber())
                        assistCount++;
                }
            }
        } catch (NullPointerException e) {
            assistCount = 0;
        }
        return assistCount;
    }

    private int getPlayerGoals(Team teamOne, Team teamTwo, int playerNumber, char playerRole) {
        int goals = 0;
        try {
            if (playerRole == 'V') {
                for (Goal goal : teamTwo.getGoals()) {
                    goals++;
                }
            } else {
                for (Goal goal : teamOne.getGoals()) {
                    if (goal.getGoalPlayer() == playerNumber) {
                        goals++;
                    }
                }
            }
        } catch (NullPointerException e) {
            goals = 0;
        }
        return goals;
    }
}
