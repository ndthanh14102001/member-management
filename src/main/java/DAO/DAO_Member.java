/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity._Member;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
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

    public List<Object[]> getAllMembers(String memberId, String department, String majors, String year) {
        try {
            StringBuilder jpql = new StringBuilder("SELECT m, CASE WHEN COUNT(p) > 0 AND SUM(CASE WHEN p.trangThaiXL = 0 THEN 1 ELSE 0 END) > 0 THEN 'Chưa xử lý' ELSE '' END "
                    + "FROM _Member m LEFT JOIN m.processings p ");
            boolean hasMemberId = !memberId.isEmpty();
            boolean hasDepartment = !department.isEmpty();
            boolean hasMajors = !majors.isEmpty();
            boolean hasYear = !year.isEmpty();

            if (hasDepartment || hasMajors || hasMemberId || hasYear) {
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
                if (hasYear) {
                    if (hasDepartment || hasMajors || hasMemberId) {
                        jpql.append(" AND");
                    }
                    jpql.append(" SUBSTRING(m.maTV, 1, 4) = :year");
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

            if (hasYear) {
                query.setParameter("year", year);
            }

            List<Object[]> results = query.getResultList();
            return results;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.clear();
        }
        return new ArrayList<>();
    }

    public void addMember(_Member member) throws Exception {
        member.checkMaTVFormat();
        member.checkEmailFormat();
//        member.setKhoa(member.getKhoaById());
//        member.setNganh(member.getNganhById());
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
            session.clear();
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
//                member.setKhoa(member.getKhoaById());
//                member.setNganh(member.getNganhById());
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
            session.clear();
        }
    }

    public void updateMember(_Member member) throws Exception {
        member.checkEmailFormat();
//        member.setKhoa(member.getKhoaById());
//        member.setNganh(member.getNganhById());
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
            throw e;
        } finally {
            session.clear();
        }
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
        } finally {
            session.clear();
        }
    }

    public void deleteMembers(List<String> memberIds) throws SQLException {
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            // Kiểm tra xem có dữ liệu liên kết với thành viên đó không
            for (String memberId : memberIds) {
                boolean hasRelatedData = checkRelatedData(memberId); // Phương thức này cần được triển khai để kiểm tra dữ liệu liên kết

                if (hasRelatedData) {
                    throw new SQLException("Mã TV " + memberId + " không thể xóa.");
                }
            }

            String jpql = "DELETE FROM _Member WHERE maTV IN :memberIds";
            int rowsAffected = session.createQuery(jpql)
                    .setParameterList("memberIds", memberIds)
                    .executeUpdate();
            transaction.commit();

            // Kiểm tra xem có bản ghi nào đã bị xóa không
            if (rowsAffected != memberIds.size()) {
                throw new SQLException("Mã TV " + memberIds.get(rowsAffected) + " không thể xóa.");
            }
        } catch (HibernateException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } catch (SQLException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw new SQLException(e.getMessage());
        } finally {
            session.clear();
        }
    }

    public boolean checkRelatedData(String memberId) {
        try {
            // Kiểm tra dữ liệu liên kết trong entity _Processing
            String jpqlProcessing = "SELECT COUNT(p) FROM _Processing p WHERE p.maTV.maTV = :memberId";
            Query<Long> queryProcessing = session.createQuery(jpqlProcessing, Long.class);
            queryProcessing.setParameter("memberId", memberId);
            Long countProcessing = queryProcessing.getSingleResult();
            if (countProcessing > 0) {
                return true;
            }

            // Kiểm tra dữ liệu liên kết trong entity _UsageInformation
            String jpqlUsage = "SELECT COUNT(u) FROM _UsageInformation u WHERE u.maTV.maTV = :memberId";
            Query<Long> queryUsage = session.createQuery(jpqlUsage, Long.class);
            queryUsage.setParameter("memberId", memberId);
            Long countUsage = queryUsage.getSingleResult();
            // Trả về false nếu không có dữ liệu liên kết
            return countUsage > 0;
        } catch (HibernateException e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi kiểm tra dữ liệu liên kết.");
        } finally {
            session.clear();
        }
    }
}
