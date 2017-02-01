package Transformers;

import Models.Database.DatabaseTeam;
import Models.Domain.Goal;
import Models.Domain.Team;
import Services.TimeUtils;

public class TeamToDatabaseModel {

    public DatabaseTeam transformTeamToDatabaseModel(Team teamOne, Team teamTwo) {
        DatabaseTeam databaseTeam = new DatabaseTeam();

        try {
            databaseTeam.setName(teamOne.getTeamName());

            boolean isAdditionalTime;
            int teamOneGoals;
            int teamTwoGoals;

            isAdditionalTime = isAdditionalTime(teamOne);
            if (!isAdditionalTime)
                isAdditionalTime = isAdditionalTime(teamTwo);

            teamOneGoals = getTeamGoals(teamOne);
            teamTwoGoals = getTeamGoals(teamTwo);

            databaseTeam.setScoredGoals(teamOneGoals);
            databaseTeam.setLostGoals(teamTwoGoals);
            setTeamScore(databaseTeam, teamOneGoals, teamTwoGoals, isAdditionalTime);
            return databaseTeam;

        } catch (NullPointerException e) {

        }
        return databaseTeam;
    }

    private void setTeamScore(DatabaseTeam databaseTeam, int teamOneGoals, int teamTwoGoals, boolean isAdditionalTime) {

        boolean win = false;
        if (teamOneGoals > teamTwoGoals)
            win = true;

        if (win && !isAdditionalTime) {
            databaseTeam.setScore(5);
            databaseTeam.setWinInMainTime(1);
        }

        if (win && isAdditionalTime) {
            databaseTeam.setScore(3);
            databaseTeam.setWinInAdditionalTime(1);
        }

        if (!win && isAdditionalTime) {
            databaseTeam.setScore(2);
            databaseTeam.setLoseInAdditionalTime(1);
        }

        if (!win && !isAdditionalTime) {
            databaseTeam.setScore(1);
            databaseTeam.setLoseInMainTime(1);
        }

    }

    private boolean isAdditionalTime(Team team) {
        String goalTime = "";
        TimeUtils timeUtils = new TimeUtils();
        boolean isAdditionalTime = false;
        try {
            for (Goal goal : team.getGoals()) {
                goalTime = goal.getGoalTime();
                if (timeUtils.isAdditionalTime(goalTime)) {
                    isAdditionalTime = true;
                    break;
                }
            }
        } catch (NullPointerException e) {
            isAdditionalTime = false;
        }
        return isAdditionalTime;
    }

    private int getTeamGoals(Team team) {

        int goals = 0;
        try {
            for (Goal goal : team.getGoals()) {
                goals++;
            }
        } catch (Exception e) {
            goals = 0;
        }
        return goals;
    }

}
