/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity._Device;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Paul
 */
public class DAO_Device {

    private List<_Device> results;
    private _Device device;
    private SessionFactory factory;
    private Session session;

    public DAO_Device() {
        factory = new ConnectDB().getFactory();
        session = factory.openSession();
    }

    public boolean AddDevice(_Device device) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(device);
            transaction.commit();
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            session.close();
        }
    }

    public boolean AddDevices(List<_Device> devices) {
        try {
            for (_Device device : devices) {
                session.save(device);
            }
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            session.close();
        }
    }

    public void Update(_Device device) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(device);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void DeleteDevice(int id) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            Query query = session.createQuery("DELETE FROM _Device WHERE maTB = :maTB");
            query.setParameter("maTB", id);

            int rowsAffected = query.executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void DeleteDevices(List<Integer> ids) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            for (int i : ids) {
                Query query = session.createQuery("DELETE FROM _Device WHERE maTB = :maTB");
                query.setParameter("maTB", i);
                int rowsAffected = query.executeUpdate();
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<_Device> getAllDevices() {
        try {
            results = session.createQuery("FROM _Device").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return results;
    }

    public List<_Device> getAllDevices(String id, String type, String year) {
        try {
            Query query = session.createQuery("FROM _Device WHERE maTB LIKE :id AND maTB LIKE :type AND maTB LIKE :year");
            query.setParameter("type", type + "%");
            query.setParameter("year", "%" + year + "%");
            query.setParameter("id", "%" + id);
            results = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return results;
    }

    public _Device getAvailableDevicebyID(String id) {
        try {
            Query query = session.createQuery("FROM _UsageInformation u RIGHT JOIN _Device d ON u.maTB = d.maTB WHERE maTB =: maTB");
            query.setParameter("maTB", id);
            device = (_Device) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return device;
    }

    public List<_Device> getAllAvailableDevices() {
        try {
            Query query = session.createQuery("FROM _UsageInformation u RIGHT JOIN _Device d ON u.maTB = d.maTB WHERE maTB =: maTB");
            results = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

}
