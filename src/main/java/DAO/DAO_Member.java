/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity._Member;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Admin
 */
public class DAO_Member {

    private SessionFactory factory;
    Session session;

    public DAO_Member() {
        factory = new ConnectDB().getFactory();
        session = factory.openSession();
    }

    public List<Object[]> getAllMembers() {
        try {
            String jpql = "SELECT m, CASE WHEN COUNT(p) > 0 AND SUM(CASE WHEN p.trangThaiXL = 0 THEN 1 ELSE 0 END) > 0 THEN 'Chưa xử lý' ELSE '' END "
                    + "FROM _Member m LEFT JOIN m.processings p "
                    + "GROUP BY m";
            Query<Object[]> query = session.createQuery(jpql, Object[].class);
            List<Object[]> results = query.getResultList();
            return results;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return new ArrayList<>();
    }

    public List<Object[]> getMembersById(int memberId) {
        String jpql = "SELECT m, CASE WHEN COUNT(p) > 0 AND SUM(CASE WHEN p.trangThaiXL = 0 THEN 1 ELSE 0 END) > 0 THEN 'Chưa xử lý' ELSE '' END "
                + "FROM _Member m LEFT JOIN m.processings p WHERE m.maTV = :maTV "
                + "GROUP BY m";
        Query<Object[]> query = session.createQuery(jpql, Object[].class);
        query.setParameter("maTV", memberId);
        List<Object[]> results = query.getResultList();
        return results;
    }

    public List<Object[]> getMembersByDepartment(String department) {
        String jpql = "SELECT m, CASE WHEN COUNT(p) > 0 AND SUM(CASE WHEN p.trangThaiXL = 0 THEN 1 ELSE 0 END) > 0 THEN 'Chưa xử lý' ELSE '' END "
                + "FROM _Member m LEFT JOIN m.processings p WHERE m.khoa LIKE :khoa "
                + "GROUP BY m";
        Query<Object[]> query = session.createQuery(jpql, Object[].class);
        query.setParameter("khoa",  "%" + department + "%");
        List<Object[]> results = query.getResultList();
        return results;
    }

    public List<Object[]> getMembersByMajors(String majors) {
        String jpql = "SELECT m, CASE WHEN COUNT(p) > 0 AND SUM(CASE WHEN p.trangThaiXL = 0 THEN 1 ELSE 0 END) > 0 THEN 'Chưa xử lý' ELSE '' END "
                + "FROM _Member m LEFT JOIN m.processings p WHERE m.nganh LIKE :nganh "
                + "GROUP BY m";
        Query<Object[]> query = session.createQuery(jpql, Object[].class);

        query.setParameter("nganh", "%" + majors + "%");
        List<Object[]> results = query.getResultList();
        return results;
    }

    public boolean addMember(_Member member) {
        try {
            session.save(member);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
        return true;
    }

    public boolean addMembers(List<_Member> members) {
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            for (_Member member : members) {
                session.save(member);
            }
            transaction.commit();
            return true;
        } catch (RuntimeException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    public boolean updateMember(_Member member) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(member);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
        return true;
    }

    public boolean deleteMember(int memberId) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            Query query = session.createQuery("DELETE FROM _Member WHERE maTV = :maTV");
            query.setParameter("maTV", memberId);

            int rowsAffected = query.executeUpdate();
            transaction.commit();
            return rowsAffected >= 1;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteMembers(List<Integer> memberIds) {
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            String jpql = "DELETE FROM _Member WHERE maTV IN :memberIds";
            session.createQuery(jpql)
                    .setParameterList("memberIds", memberIds)
                    .executeUpdate();
            transaction.commit();
            return true;
        } catch (RuntimeException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }
}