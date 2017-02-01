package Database;

import Models.Database.DatabaseReferee;

import java.util.List;

public interface RefereeDAO_ORM {

    void saveReferee(DatabaseReferee referee);

    boolean checkRefereeExistence(String name, String surname);

    void addNewReferee(DatabaseReferee referee);

    void updateReferee(DatabaseReferee referee);

    DatabaseReferee getRefereeByNameAndSurname(String name, String Surname);

    List<DatabaseReferee> getAllReferees();
}
