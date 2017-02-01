package Comparators;

import Models.Database.DatabaseReferee;

import java.util.Comparator;

public class RefereeComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Integer refereeOnePenalties = ((DatabaseReferee)o1).getAssignedPenalties();
        Integer refereeTwoPenalties = ((DatabaseReferee)o2).getAssignedPenalties();

        return refereeTwoPenalties.compareTo(refereeOnePenalties);
    }
}
