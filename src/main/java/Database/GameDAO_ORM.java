package Database;

import Models.Database.DatabaseGame;
import Models.Domain.Game;

public interface GameDAO_ORM {

    void saveGame(DatabaseGame game);

    boolean checkGameExistence(DatabaseGame game);

    void addNewGame(DatabaseGame game);

    boolean checkGameExistence(Game game);
}
