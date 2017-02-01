package Transformers;

import Models.Database.DatabaseReferee;
import Models.Domain.Penalty;
import Models.Domain.Referee;
import Models.Domain.Team;

public class RefereeToDatabaseModel {

    public DatabaseReferee transformRefereeToDatabaseModel(Referee referee, Team teamOne, Team teamTwo) {

        DatabaseReferee databaseReferee = new DatabaseReferee();

        databaseReferee.setName(referee.getName());
        databaseReferee.setSurname(referee.getSurname());
        //Because no data who of referies set penalty, I assume that they all make this decision, so each penalty is counted for all three referees.
        databaseReferee.setAssignedPenalties(getAssignedPenalties(teamOne, teamTwo));

        return databaseReferee;
    }

    private int getAssignedPenalties(Team teamOne, Team teamTwo) {
        int penalties = 0;
        try {
            for (Penalty player : teamOne.getPenalties()) {
                penalties++;
            }
            for (Penalty player : teamTwo.getPenalties()) {
                penalties++;
            }
        } catch (Exception e) {
            penalties = 0;
        }
        return penalties;
    }
}
