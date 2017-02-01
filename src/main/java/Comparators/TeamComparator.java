package Comparators;

import Models.Database.DatabaseTeam;

import java.util.Comparator;

public class TeamComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Integer teamOneGoals = ((DatabaseTeam)o1).getScore();
        Integer teamTwoGoals = ((DatabaseTeam)o2).getScore();

        return teamTwoGoals.compareTo(teamOneGoals);
    }
}
