package Transformers;

import Models.Database.DatabaseGame;
import Models.Domain.Game;
import Models.Domain.Goal;
import Models.Domain.Team;
import Services.TimeUtils;

public class GameToDatabaseModel {
    public static final int GAME_LENGTH = 3600;

    public DatabaseGame transformGameToDatabaseModel(Game game, Long teamOneId, Long teamTwoId, Long refereeOneId, Long refereeTwoId, Long mainRefereeId) {

        DatabaseGame databaseGame = new DatabaseGame();

        databaseGame.setDate(game.getGameDate());
        databaseGame.setWatchers(game.getWatcherCount());
        databaseGame.setLocation(game.getLocation());

        int lastGoalTime = getLastGoalTime(game);
        if (lastGoalTime > GAME_LENGTH) {
            databaseGame.setLength(lastGoalTime);
        } else {
            databaseGame.setLength(GAME_LENGTH);
        }

        databaseGame.setTeamOneId(teamOneId);

        databaseGame.setScore(getScore(game));
        databaseGame.setTeamTwoId(teamTwoId);
        databaseGame.setRefereeOneId(refereeOneId);
        databaseGame.setRefereeTwoId(refereeTwoId);
        databaseGame.setMainRefereeId(mainRefereeId);

        return databaseGame;
    }

    private String getScore(Game game) {

        Team teamOne = game.getTeams().get(0);
        Team teamTwo = game.getTeams().get(1);

        int teamOneScore = 0;
        int teamTwoScore = 0;
        try {

            for (Goal goal : teamOne.getGoals()) {
                teamOneScore++;
            }
        } catch (NullPointerException e) {
            teamOneScore = 0;
        }
        try {
            for (Goal goal : teamTwo.getGoals()) {
                teamTwoScore++;
            }
        } catch (NullPointerException e) {
            teamTwoScore = 0;
        }
        return teamOneScore + ":" + teamTwoScore;

    }

    private int getLastGoalTime(Game game) {
        Team teamOne = game.getTeams().get(0);
        Team teamTwo = game.getTeams().get(1);
        int lastGoalTime = 0;
        String goalTime;
        int goalTimeInSeconds;
        TimeUtils timeUtils = new TimeUtils();
        try {
            for (Goal goal : teamOne.getGoals()) {
                goalTime = goal.getGoalTime();
                goalTimeInSeconds = timeUtils.returnTimeInSeconds(goalTime);
                if (lastGoalTime < goalTimeInSeconds) {
                    lastGoalTime = goalTimeInSeconds;
                }
            }

        } catch (NullPointerException e) {
            lastGoalTime = 0;
        }

        try {
            for (Goal goal : teamTwo.getGoals()) {
                goalTime = goal.getGoalTime();
                goalTimeInSeconds = timeUtils.returnTimeInSeconds(goalTime);
                if (lastGoalTime < goalTimeInSeconds) {
                    lastGoalTime = goalTimeInSeconds;
                }
            }
        } catch (NullPointerException e) {
            lastGoalTime = 0;
        }
        return lastGoalTime;
    }

}
