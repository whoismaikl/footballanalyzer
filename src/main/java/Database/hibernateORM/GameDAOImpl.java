package Database.hibernateORM;

import Database.GameDAO_ORM;
import Database.HibernateUtil;
import Models.Database.DatabaseGame;
import Models.Domain.Game;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class GameDAOImpl implements GameDAO_ORM {

    public void saveGame(DatabaseGame game) {
        if (!checkGameExistence(game)) {
            addNewGame(game);
        } else{
            System.out.println("Game already exists. " + game.toString());
        }
    }

    public boolean checkGameExistence(DatabaseGame game) {
        DatabaseGame databaseGame;

        Session session = HibernateUtil.getSessionFactory().openSession();

        String date = game.getDate();
        long teamOneId = game.getTeamOneId();
        long teamTwoId = game.getTeamOneId();

        try {
            session.beginTransaction();
            String queryString = "from DatabaseGame where date = :date and teamOneId = :teamOneId and teamTwoId = :teamTwoId";
            Query query = session.createQuery(queryString);
            query.setParameter("date", date);
            query.setParameter("teamOneId", teamOneId);
            query.setParameter("teamTwoId", teamTwoId);

            databaseGame = (DatabaseGame) query.uniqueResult();
            if (databaseGame != null)
                return true;

        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return false;
    }

    public void addNewGame(DatabaseGame game) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.save(game);
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

    @Override
    public boolean checkGameExistence(Game game) {
        DatabaseGame databaseGame;

        Session session = HibernateUtil.getSessionFactory().openSession();

        String date = game.getGameDate();
        String location = game.getLocation();
        int watchers = game.getWatcherCount();

        try {
            session.beginTransaction();
            String queryString = "from DatabaseGame where date = :date and location = :location and watchers = :watchers";
            Query query = session.createQuery(queryString);
            query.setParameter("date", date);
            query.setParameter("location", location);
            query.setParameter("watchers", watchers);

            databaseGame = (DatabaseGame) query.uniqueResult();
            if (databaseGame != null)
                return true;

        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return false;
    }
}
