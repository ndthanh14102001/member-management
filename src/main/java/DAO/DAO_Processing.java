/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity._Processing;
import java.time.LocalDate;
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
            session.update(processing);
            return true;
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return false;
    }
    
    public boolean deleteProcess(int id) {
        try {
            _Processing pro = session.get(_Processing.class, id);
            if (pro != null) {
                session.delete(pro);
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
    
    public List<_Processing> getProcessingListByProcessingType(String type) {
        List<_Processing> processingList = null;
        try {
            Query<_Processing> processing = session.createNamedQuery("_Processing.findByHinhThucXL", _Processing.class);
            processing.setParameter("hinhThucXL", type);
            processingList = processing.getResultList();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return processingList;
    }
    
    public List<_Processing> getProcessingListByProcessingDate(LocalDate startDate, LocalDate endDate){
        List<_Processing> processingList = null;
        try {
            Query<_Processing> processing = session.createQuery("FROM _Processing WHERE ngayXL BETWEEN :startDate AND :endDate");
            processing.setParameter("startDate", startDate);
            processing.setParameter("endDate", endDate);
            processingList = processing.getResultList();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return processingList;
    }
}
