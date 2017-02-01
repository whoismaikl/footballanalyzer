package Database;

import Models.Database.DatabaseTeam;

import java.util.List;

public interface TeamDAO_ORM {

    void saveTeam(DatabaseTeam team);

    boolean checkTeamExistence(String teamName);

    void addNewTeam(DatabaseTeam team);

    void updateTeam(DatabaseTeam team);

    DatabaseTeam getTeamByName(String name);

    List<DatabaseTeam> getAllTeams();
}
