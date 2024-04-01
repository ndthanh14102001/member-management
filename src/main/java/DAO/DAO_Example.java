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
public class DAO_Example {

    private SessionFactory factory;
    Session session;

    public DAO_Example() {
        factory = new ConnectDB().getFactory();
        session = factory.openSession();
    }

    public void testCreateMember(_Member member) {
        session.save(member);
        session.close();
    }

    public void testJoin() {
        try {
            String jpql = "SELECT m, CASE WHEN COUNT(p) > 0 AND SUM(CASE WHEN p.trangThaiXL = 0 THEN 1 ELSE 0 END) > 0 THEN 'Chưa xử lý' ELSE '' END "
                    + "FROM _Member m LEFT JOIN m.processings p "
                    + "GROUP BY m";
            Query<Object[]> query = session.createQuery(jpql, Object[].class);
            List<Object[]> results = query.getResultList();

            for (Object[] result : results) {
                _Member member = (_Member) result[0];
                String status = (String) result[1];
                System.out.println("Member: " + member.getHoTen() + ", Status: " + status);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void testDeleteMember(int memberId) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            Query query = session.createQuery("DELETE FROM _Member WHERE maTV = :maTV");
            query.setParameter("maTV", memberId);

            int rowsAffected = query.executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
