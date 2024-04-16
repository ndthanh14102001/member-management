/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity._Processing;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

/**
 *
 * @author HP
 */
public class DAO_Processing {

    private SessionFactory factory;
    private Session session;

    public DAO_Processing() {
        factory = new ConnectDB().getFactory();
        session = factory.openSession();
    }

    public List getAllProcessing() {
        try {
            List<Object> results = session.createQuery("FROM _Processing p JOIN p.maTV").getResultList();
            return results;
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return null;
    }

    public boolean createProcess(_Processing processing) {
        try {
            session.save(processing);
            return true;
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return false;
    }

    public boolean updateProcess(_Processing processing) {
        try {
            session.beginTransaction();
            Query query = session.createQuery("UPDATE _Processing SET soTien=:sT, trangThaiXL=:ttXL "
                    + "WHERE maXL=:maXL");
//            query.setParameter("htXL", processing.getHinhThucXL());
            query.setParameter("sT", processing.getSoTien());
            query.setParameter("ttXL", processing.getTrangThaiXL());
            query.setParameter("maXL", processing.getMaXL());

            int rowsUpdated = query.executeUpdate();

            if (rowsUpdated > 0) {
                return true;
            }
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return false;
    }

    public boolean deleteProcess(_Processing processing) {
        try {
            session.beginTransaction();
            Query query = session.createQuery("DELETE FROM _Processing WHERE maXL = :maXL");
            query.setParameter("maXL", processing.getMaXL());
            int rowsUpdated = query.executeUpdate();
            if (rowsUpdated > 0) {
                return true;
            }
            return true;
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return false;
    }

    public List<Object[]> getAllProcessing(String type, LocalDate startDate, LocalDate endDate) {
        try {
            StringBuilder jpql = new StringBuilder("SELECT p FROM _Processing p WHERE 1=1");
            boolean hasType = type != null && !type.isEmpty();
            boolean hasStartDate = startDate != null;
            boolean hasEndDate = endDate != null;

            if (hasType) {
                jpql.append(" AND p.type = :type");
            } else if (hasStartDate && hasEndDate) {
                jpql.append(" AND p.processingDate BETWEEN :startDate AND :endDate");
            } else if (hasStartDate) {
                jpql.append(" AND p.processingDate >= :startDate");
            } else if (hasEndDate) {
                jpql.append(" AND p.processingDate <= :endDate");
            }

            Query<Object[]> query = session.createQuery(jpql.toString(), Object[].class);

            if (hasType) {
                query.setParameter("type", type);
            } else if (hasStartDate) {
                query.setParameter("startDate", startDate.atStartOfDay());
            } else if (hasEndDate) {
                query.setParameter("endDate", endDate.atTime(LocalTime.MAX));
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
}
