package Database.hibernateORM;

import Database.HibernateUtil;
import Database.TeamDAO_ORM;
import Models.Database.DatabaseTeam;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class TeamDAOImpl implements TeamDAO_ORM {

    public void saveTeam(DatabaseTeam team) {

        if(checkTeamExistence(team.getName())){
            updateTeam(team);
        } else {
            addNewTeam(team);
        }
    }

    public boolean checkTeamExistence(String teamName) {
        DatabaseTeam databaseTeam;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            String queryString = "from DatabaseTeam where name = :name";
            Query query = session.createQuery(queryString);
            query.setParameter("name", teamName);
            databaseTeam = (DatabaseTeam) query.uniqueResult();
            if(databaseTeam != null)
                return true;
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return false;
    }

    public void addNewTeam(DatabaseTeam team) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.save(team);
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

    public void updateTeam(DatabaseTeam team) {
        DatabaseTeam teamInDatabase = getTeamByName(team.getName());

        team.setScore(teamInDatabase.getScore() + team.getScore());
        team.setWinInMainTime(teamInDatabase.getWinInMainTime() + team.getWinInMainTime());
        team.setLoseInMainTime(teamInDatabase.getLoseInMainTime() + team.getLoseInMainTime());
        team.setWinInAdditionalTime(teamInDatabase.getWinInAdditionalTime() + team.getWinInAdditionalTime());
        team.setLoseInAdditionalTime(teamInDatabase.getLoseInAdditionalTime() + team.getLoseInAdditionalTime());
        team.setScoredGoals(teamInDatabase.getScoredGoals() + team.getScoredGoals());
        team.setLostGoals(teamInDatabase.getLostGoals() + team.getLostGoals());
        team.setId(teamInDatabase.getId());

        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.update(team);
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

    public DatabaseTeam getTeamByName(String name) {
        DatabaseTeam databaseTeam = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            String queryString = "from DatabaseTeam where name = :name";
            Query query = session.createQuery(queryString);
            query.setParameter("name", name);
            databaseTeam = (DatabaseTeam) query.uniqueResult();

        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return databaseTeam;
    }

    @Override
    public List<DatabaseTeam> getAllTeams() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<DatabaseTeam> teamsList = null;
        try {
            session.beginTransaction();
            Query query = session.createQuery(" from DatabaseTeam ");
            teamsList = query.list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return teamsList;
    }
}
