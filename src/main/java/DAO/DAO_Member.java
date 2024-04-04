/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity._Member;
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

    public List<_Member> getAllMembers() {
        List<_Member> members = session.createQuery(
                "SELECT m FROM _Member m JOIN FETCH m.processings p WHERE p.trangThaiXL = 'Chưa xử lý'",
                _Member.class)
                .getResultList();
        return members;
    }

    public List<_Member> getMembersById(int memberId) {
        Query query = session.createQuery("SELECT m FROM _Member m JOIN FETCH m.processings p WHERE p.trangThaiXL = 'Chưa xử lý' and m.maTV = :maTV");
        query.setParameter("maTV", memberId);
        List<_Member> members = query.getResultList();
        return members;
    }

    public List<_Member> getMembersByDepartment(String department) {
        Query query = session.createQuery("SELECT m FROM _Member m JOIN FETCH m.processings p WHERE p.trangThaiXL = 'Chưa xử lý' and m.khoa = :khoa");
        query.setParameter("khoa", department);
        List<_Member> members = query.getResultList();
        return members;
    }

    public List<_Member> getMembersByMajors(String majors) {
        Query query = session.createQuery("SELECT m FROM _Member m JOIN FETCH m.processings p WHERE p.trangThaiXL = 'Chưa xử lý' and m.nganh = :nganh");
        query.setParameter("nganh", majors);
        List<_Member> members = query.getResultList();
        return members;
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
            return rowsAffected > 1;
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
