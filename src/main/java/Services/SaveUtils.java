package Services;

import Database.hibernateORM.GameDAOImpl;
import Database.hibernateORM.PlayerDAOImpl;
import Database.hibernateORM.RefereeDAOImpl;
import Database.hibernateORM.TeamDAOImpl;
import Models.Database.DatabaseGame;
import Models.Database.DatabasePlayer;
import Models.Database.DatabaseReferee;
import Models.Database.DatabaseTeam;
import Models.Domain.Game;
import Models.Domain.Referee;
import Models.Domain.Team;
import Transformers.GameToDatabaseModel;
import Transformers.PlayersToDatabaseModel;
import Transformers.RefereeToDatabaseModel;
import Transformers.TeamToDatabaseModel;

import java.util.List;

public class SaveUtils {

    public void saveTeams(Game game){
        Team teamOne = game.getTeams().get(0);
        Team teamTwo = game.getTeams().get(1);

        TeamToDatabaseModel teamTransformer = new TeamToDatabaseModel();
        DatabaseTeam databaseTeamOne = teamTransformer.transformTeamToDatabaseModel(teamOne, teamTwo);
        DatabaseTeam databaseTeamTwo = teamTransformer.transformTeamToDatabaseModel(teamTwo, teamOne);
        TeamDAOImpl teamDaoImpl = new TeamDAOImpl();
        teamDaoImpl.saveTeam(databaseTeamOne);
        teamDaoImpl.saveTeam(databaseTeamTwo);
    }

    public void savePlayers(Team teamOne, Team teamTwo){
        PlayersToDatabaseModel playerTransformer = new PlayersToDatabaseModel();

        PlayerDAOImpl playerDaoImpl = new PlayerDAOImpl();
        List<DatabasePlayer> databasePlayers = playerTransformer.transformPlayerToDatabaseModel(teamOne, teamTwo);
        for (DatabasePlayer player : databasePlayers){
            playerDaoImpl.savePlayer(player);
        }

        databasePlayers = playerTransformer.transformPlayerToDatabaseModel(teamTwo, teamOne);
        for (DatabasePlayer player : databasePlayers){
            playerDaoImpl.savePlayer(player);
        }
    }

    public void saveReferees(Game game, Team teamOne, Team teamTwo){
        RefereeDAOImpl refereeDaoImpl = new RefereeDAOImpl();
        DatabaseReferee databaseReferee = new DatabaseReferee();
        //1 - transform to Db Model
        RefereeToDatabaseModel refereeTransformer = new RefereeToDatabaseModel();
        for (Referee referee : game.getReferees()) {
            databaseReferee = refereeTransformer.transformRefereeToDatabaseModel(referee, teamOne, teamTwo);
            refereeDaoImpl.saveReferee(databaseReferee);
        }

        databaseReferee = refereeTransformer.transformRefereeToDatabaseModel(game.getMainReferee(), teamOne, teamTwo);
        refereeDaoImpl.saveReferee(databaseReferee);
    }

    public void saveGame(Game game){
        //Game game, Long teamOneId, Long teamTwoId, Long refereeOneId, Long refereeTwoId, Long mainRefereeId
        //Get all ids
        TeamDAOImpl teamDaoImpl = new TeamDAOImpl();
        Team teamOne = game.getTeams().get(0);
        Team teamTwo = game.getTeams().get(1);
        DatabaseTeam databaseTeam = teamDaoImpl.getTeamByName(teamOne.getTeamName());
        DatabaseReferee databaseReferee = new DatabaseReferee();
        RefereeDAOImpl refereeDaoImpl = new RefereeDAOImpl();

        databaseTeam = teamDaoImpl.getTeamByName(teamOne.getTeamName());
        Long teamOneId = databaseTeam.getId();
        databaseTeam = teamDaoImpl.getTeamByName(teamTwo.getTeamName());
        Long teamTwoId = databaseTeam.getId();
        Referee referee = game.getReferees().get(0);
        databaseReferee = refereeDaoImpl.getRefereeByNameAndSurname(referee.getName(), referee.getSurname());
        Long refereeOneId = databaseReferee.getId();
        referee = game.getReferees().get(1);
        databaseReferee = refereeDaoImpl.getRefereeByNameAndSurname(referee.getName(), referee.getSurname());
        Long refereeTwoId = databaseReferee.getId();
        referee = game.getMainReferee();
        databaseReferee = refereeDaoImpl.getRefereeByNameAndSurname(referee.getName(), referee.getSurname());
        Long mainrefereeId = databaseReferee.getId() ;

        DatabaseGame databaseGame = new DatabaseGame();
        GameToDatabaseModel gameTransformer = new GameToDatabaseModel();
        databaseGame = gameTransformer.transformGameToDatabaseModel(game, teamOneId, teamTwoId, refereeOneId, refereeTwoId, mainrefereeId);

        GameDAOImpl gameDaoImpl = new GameDAOImpl();
        gameDaoImpl.saveGame(databaseGame);
    }
}
