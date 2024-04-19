/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity._Device;
import java.sql.SQLException;

import java.util.List;
import org.hibernate.HibernateException;
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

    public void AddDevice(_Device device) throws Exception {
        Transaction transaction = null;
        try {
            if (isMaTBExists(device.getMaTB())) {
                throw new Exception("Mã thiết bị đã tồn tại");
            }
            transaction = session.beginTransaction();
            session.save(device);
            transaction.commit();
        } catch (Exception e) {
            session.close();
            throw e;
        } finally {
            session.close();
        }
    }

    public boolean isMaTBExists(String maTB) {
        Query query = session.createQuery("SELECT COUNT(maTB) FROM _Device m WHERE m.maTB = :maTB");
        query.setParameter("maTB", maTB);
        long count = (long) query.getSingleResult();
        return count > 0;
    }

    public void AddDevices(List<_Device> devices) throws Exception {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            for (_Device device : devices) {
                device.Validate();
                if (isMaTBExists(device.getMaTB())) {
                    throw new Exception("Mã thiết bị " + device.getMaTB() + " đã tồn tại");
                }
                session.save(device);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            session.close();
            throw e;
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
        } finally {
            session.clear();
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
        } finally {
            session.clear();
        }
    }

    public void DeleteDevices(List<String> ids) throws Exception {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            for (String i : ids) {

                boolean hasRelatedData = checkRelatedData(i);

                if (hasRelatedData) {
                    throw new SQLException("Mã TB " + i + " không thể xóa.");
                }
            }
            String hql = "DELETE FROM _Device WHERE maTB IN :deviceIds";
            int rowsAffected = session.createQuery(hql)
                    .setParameterList("deviceIds", ids)
                    .executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            session.clear();
            throw e;
        } finally {
            session.clear();
        }
    }

    public boolean checkRelatedData(String maTB) {
        try {
            // Kiểm tra dữ liệu liên kết trong entity _UsageInformation
            String jpqlUsage = "SELECT COUNT(u) FROM _UsageInformation u WHERE u.maTB.maTB = :maTB";
            Query<Long> queryUsage = session.createQuery(jpqlUsage, Long.class);
            queryUsage.setParameter("maTB", maTB);
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

    public List<_Device> getAllDevices(String deviceType, String year, String maTB) {
        try {
            StringBuilder hql = new StringBuilder("FROM _Device d ");
            boolean hasDeviceType = !deviceType.isEmpty();
            boolean hasYear = !year.isEmpty();
            boolean hasMaTB = !maTB.isEmpty();

            if (hasDeviceType || hasYear || hasMaTB) {
                hql.append(" WHERE");
                if (hasDeviceType) {
                    hql.append(" SUBSTRING(d.maTB, 1, 3) = :deviceType");
                }
                if (hasYear) {
                    if (hasDeviceType) {
                        hql.append(" AND");
                    }
                    hql.append(" SUBSTRING(d.maTB, 4, 4) = :year");
                }
                if (hasMaTB) {
                    if (hasDeviceType || hasYear) {
                        hql.append(" AND");
                    }
                    hql.append(" d.maTB = :maTB");
                }
            }
            Query<_Device> query = session.createQuery(hql.toString(), _Device.class);
            if (hasDeviceType) {
                query.setParameter("deviceType", deviceType);
            }
            if (hasYear) {
                query.setParameter("year", year);
            }
            if (hasMaTB) {
                query.setParameter("maTB", maTB);
            }

            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.clear();
        }
        return null;
    }

    public _Device getAvailableDevicebyID(String id) {
        try {
            Query query = session.createQuery("FROM _UsageInformation u RIGHT JOIN _Device d ON u.maTB = d.maTB WHERE maTB =: maTB");
            query.setParameter("maTB", id);
            device = (_Device) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.clear();
        }
        return device;
    }

    public List<_Device> getAllAvailableDevices() {
        try {
            Query query = session.createQuery("FROM _UsageInformation u RIGHT JOIN _Device d ON u.maTB = d.maTB WHERE maTB =: maTB");
            results = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.clear();
        }
        return results;
    }

}
