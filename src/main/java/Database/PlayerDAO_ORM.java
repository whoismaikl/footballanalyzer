package Database;

import Models.Database.DatabasePlayer;

import java.util.List;

public interface PlayerDAO_ORM {

    void savePlayer(DatabasePlayer player);

    boolean checkPlayerExistence(DatabasePlayer player);

    void addNewPlayer(DatabasePlayer player);

    void updatePlayer(DatabasePlayer player);

    DatabasePlayer getPlayerByTeamAndNumber(Long teamId, int playerNumber);

    List<DatabasePlayer> getAllPlayers();

    List<DatabasePlayer> getAllGoalkeepers();

    List<DatabasePlayer> getRudestPlayers();

    List<DatabasePlayer> getAllPlayersByTeamId(Long Id);
}
