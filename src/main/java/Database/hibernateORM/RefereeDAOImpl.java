package Database.hibernateORM;

import Database.HibernateUtil;
import Database.RefereeDAO_ORM;
import Models.Database.DatabaseReferee;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class RefereeDAOImpl implements RefereeDAO_ORM{

    public void saveReferee(DatabaseReferee referee) {
        if (checkRefereeExistence(referee.getName(), referee.getSurname())) {
            updateReferee(referee);
        } else {
            addNewReferee(referee);
        }
    }

    public boolean checkRefereeExistence(String name, String surname) {
        DatabaseReferee databaseReferee;

        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            String queryString = "from DatabaseReferee where name = :name and surname = :surname";
            Query query = session.createQuery(queryString);
            query.setParameter("name", name);
            query.setParameter("surname", surname);

            databaseReferee = (DatabaseReferee) query.uniqueResult();
            if (databaseReferee != null)
                return true;
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return false;
    }

    public void addNewReferee(DatabaseReferee referee) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.save(referee);
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

    public void updateReferee(DatabaseReferee referee) {
        DatabaseReferee databaseReferee = getRefereeByNameAndSurname(referee.getName(), referee.getSurname());

        referee.setAssignedPenalties(databaseReferee.getAssignedPenalties() + referee.getAssignedPenalties());

        referee.setId(databaseReferee.getId());
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.update(referee);
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

    public DatabaseReferee getRefereeByNameAndSurname(String name, String surname) {
        DatabaseReferee databaseReferee = null;

        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            String queryString = "from DatabaseReferee where name = :name and surname = :surname";
            Query query = session.createQuery(queryString);
            query.setParameter("name", name);
            query.setParameter("surname", surname);
            databaseReferee = (DatabaseReferee) query.uniqueResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return databaseReferee;
    }

    @Override
    public List<DatabaseReferee> getAllReferees() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<DatabaseReferee> refereeList = null;
        try {
            session.beginTransaction();
            Query query = session.createQuery(" from DatabaseReferee ");
            refereeList = query.list();

        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return refereeList;
    }
}
