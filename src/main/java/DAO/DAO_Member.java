/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity._Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
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
    private List<_Member> results;

    public DAO_Member() {
        factory = new ConnectDB().getFactory();
        session = factory.openSession();

    }

    public List<Object[]> getAllMembers(String memberId, String department, String majors) {
        try {
            StringBuilder jpql = new StringBuilder("SELECT m, CASE WHEN COUNT(p) > 0 AND SUM(CASE WHEN p.trangThaiXL = 0 THEN 1 ELSE 0 END) > 0 THEN 'Chưa xử lý' ELSE '' END "
                    + "FROM _Member m LEFT JOIN m.processings p ");
            boolean hasMemberId = !memberId.isEmpty();
            boolean hasDepartment = !department.isEmpty();
            boolean hasMajors = !majors.isEmpty();

            if (hasDepartment || hasMajors) {
                jpql.append(" WHERE");
                if (hasDepartment) {
                    jpql.append(" m.khoa LIKE :khoa");
                }
                if (hasMajors) {
                    if (hasDepartment) {
                        jpql.append(" AND");
                    }
                    jpql.append(" m.nganh LIKE :nganh");
                }
                if (hasMemberId) {
                    if (hasDepartment || hasMajors) {
                        jpql.append(" AND");
                    }
                    jpql.append(" m.maTV = :maTV");
                }
            }
            jpql.append(" GROUP BY m");
            Query<Object[]> query = session.createQuery(jpql.toString(), Object[].class);
            if (hasMemberId) {
                query.setParameter("maTV", memberId);
            }

            if (hasDepartment) {
                query.setParameter("khoa", "%" + department + "%");
            }

            if (hasMajors) {
                query.setParameter("nganh", "%" + majors + "%");
            }
            List<Object[]> results = query.getResultList();
            return results;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return new ArrayList<>();
    }

    public List<_Member> getAllMembers(){
        try {
            results = session.createQuery("FROM _Member").getResultList();           
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return results;
    }
    
    public void addMember(_Member member) throws Exception {
        member.checkMaTVFormat();
        member.setKhoa(member.getKhoaById());
        member.setNganh(member.getNganhById());
        if (isMaTVExists(member.getMaTV())) {
            throw new Exception("Mã thành viên " + member.getMaTV() + " đã tồn tại");
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(member);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public boolean isMaTVExists(String maTV) {
        Query query = session.createQuery("SELECT COUNT(maTV) FROM _Member m WHERE m.maTV = :maTV");
        query.setParameter("maTV", maTV);
        long count = (long) query.getSingleResult();
        return count > 0;
    }

    public void addMembers(List<_Member> members) throws Exception {
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            for (_Member member : members) {
                member.checkMaTVFormat();
                member.setKhoa(member.getKhoaById());
                member.setNganh(member.getNganhById());
                if (isMaTVExists(member.getMaTV())) {
                    transaction.rollback();
                    throw new Exception("Mã thành viên " + member.getMaTV() + " đã tồn tại");
                }
                session.save(member);
            }
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
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
