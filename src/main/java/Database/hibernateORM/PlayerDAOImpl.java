package Database.hibernateORM;

import Database.HibernateUtil;
import Database.PlayerDAO_ORM;
import Models.Database.DatabasePlayer;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PlayerDAOImpl implements PlayerDAO_ORM {

    public void savePlayer(DatabasePlayer player) {

        if (checkPlayerExistence(player)) {
            updatePlayer(player);
        } else {
            addNewPlayer(player);
        }
    }

    public boolean checkPlayerExistence(DatabasePlayer player) {
        DatabasePlayer databasePlayer;

        Session session = HibernateUtil.getSessionFactory().openSession();
        Long teamId = player.getTeamId();
        int playerNumber = player.getNumber();
        try {
            session.beginTransaction();
            String queryString = "from DatabasePlayer where teamId = :teamId and number = :playerNumber";
            Query query = session.createQuery(queryString);
            query.setParameter("teamId", teamId);
            query.setParameter("playerNumber", playerNumber);
            databasePlayer = (DatabasePlayer) query.uniqueResult();
            if (databasePlayer != null)
                return true;

        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return false;
    }

    public void addNewPlayer(DatabasePlayer player) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.save(player);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }

    public void updatePlayer(DatabasePlayer player) {
        DatabasePlayer databasePlayer = getPlayerByTeamAndNumber(player.getTeamId(), player.getNumber());

        player.setGamesPlayed(databasePlayer.getGamesPlayed() + player.getGamesPlayed());
        player.setTimePlayed(databasePlayer.getTimePlayed() + player.getTimePlayed());
        player.setTimesAsMainPlayer(databasePlayer.getTimesAsMainPlayer() + player.getTimesAsMainPlayer());
        player.setGoals(databasePlayer.getGoals() + player.getGoals());
        player.setAssists(databasePlayer.getAssists() + player.getAssists());
        player.setYellowCards(databasePlayer.getYellowCards() + player.getYellowCards());
        player.setRedCards(databasePlayer.getRedCards() + player.getRedCards());
        player.setId(databasePlayer.getId());

        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.update(player);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }

    public DatabasePlayer getPlayerByTeamAndNumber(Long teamId, int playerNumber) {
        DatabasePlayer databasePlayer = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            String queryString = "from DatabasePlayer where teamId = :teamId and number = :playerNumber";
            Query query = session.createQuery(queryString);
            query.setParameter("teamId", teamId);
            query.setParameter("playerNumber", playerNumber);
            databasePlayer = (DatabasePlayer) query.uniqueResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return databasePlayer;
    }

    public List<DatabasePlayer> getAllPlayers() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<DatabasePlayer> playerList = null;
        try {
            session.beginTransaction();
            Query query = session.createQuery(" from DatabasePlayer where role != 'V'");
            playerList = query.list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return playerList;
    }

    @Override
    public List<DatabasePlayer> getAllGoalkeepers() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<DatabasePlayer> playerList = null;
        try {
            session.beginTransaction();
            Query query = session.createQuery(" from DatabasePlayer where role = 'V'");
            playerList = query.list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return playerList;
    }

    @Override
    public List<DatabasePlayer> getRudestPlayers() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<DatabasePlayer> playerList = null;
        try {
            session.beginTransaction();
            Query query = session.createQuery(" from DatabasePlayer where yellowCards != 0 and redCards != 0");
            playerList = query.list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return playerList;
    }

    @Override
    public List<DatabasePlayer> getAllPlayersByTeamId(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<DatabasePlayer> playerList = null;
        try {
            session.beginTransaction();
            String queryString = "from DatabasePlayer where teamId = :teamId";
            Query query = session.createQuery(queryString);
            query.setParameter("teamId", id);
            playerList = query.list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return playerList;
    }
}
