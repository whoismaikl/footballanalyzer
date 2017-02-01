package Comparators;

import Models.Database.DatabasePlayer;

import java.util.Comparator;

public class PlayerComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Integer playerOneGoals = ((DatabasePlayer)o1).getGoals();
        Integer playerTwoGoals = ((DatabasePlayer)o2).getGoals();
        Integer playerOneAssists = ((DatabasePlayer)o1).getAssists();
        Integer playerTwoAssists = ((DatabasePlayer)o2).getAssists();

        return playerTwoGoals.equals(playerOneGoals) ? playerTwoAssists.compareTo(playerOneAssists) : playerTwoGoals.compareTo(playerOneGoals);
    }
}
