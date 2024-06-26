/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity._Device;
import Entity._Member;
import Entity._UsageInformation;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author trieu
 */
public class DAO_UsageInformation {

    private SessionFactory factory;
    Session session;

    public DAO_UsageInformation() {
        factory = new ConnectDB().getFactory();
        session = factory.openSession();
    }

    public Boolean accessStudyArea(_Member member) {
        Date time = new Date();
        _UsageInformation usageInformation = new _UsageInformation();
        usageInformation.setMaTV(member);

        usageInformation.setMaTB(null);
        usageInformation.setTGVao(time);
        usageInformation.setTGMuon(null);
        usageInformation.setTGTra(null);

        try {
            session.beginTransaction();
            session.save(usageInformation);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println(e);
        } finally {
            session.close();
        }
        return false;
    }

    public List getAllUsageInformation() {
        try {
            List<Object> usageInformationList = session.createQuery("FROM _UsageInformation u JOIN u.maTV m JOIN u.maTB d").getResultList();
            return usageInformationList;
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return null;
    }

    public List getMembersInStudyArea() {
        try {
            List<Object> members = session.createQuery("SELECT m FROM _Member m WHERE m.maTV IN (SELECT u.maTV.maTV FROM _UsageInformation u)").getResultList();
            return members;
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return null;
    }

    public _Member getMembersNotInStudyAreaById(int memberId) {
        try {
            _Member member = (_Member) session.createQuery("FROM _Member m WHERE m.maTV NOT IN (SELECT u.maTV.maTV FROM _UsageInformation u)");
            return member;
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return null;
    }

    public List<_Member> getMembersNotInStudyArea() {
        try {
            List<_Member> member = session.createQuery("FROM _Member m WHERE m.maTV NOT IN (SELECT u.maTV.maTV FROM _UsageInformation u)").getResultList();
            return member;
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return null;
    }

//    public List getDevicesAndMembers() {
//        try {
//            List<_UsageInformation> usages = session.createQuery("SELECT u FROM _UsageInformation u JOIN u.maTB d JOIN u.maTV m " 
//            + "WHERE u.tGVao IS NOT NULL AND u.tGTra IS NULL").getResultList();
//            return usages;
//        } catch (Exception e) {
//            System.err.println(e);
//        } finally {
//            session.close();
//        }
//        return null;
//    }
    public List<Object[]> getDevicesAndMembers(String state, LocalDateTime borrowedStartTime, LocalDateTime borrowedEndTime) {
        try {
            StringBuilder jpql = new StringBuilder("SELECT d, m FROM _Device d LEFT JOIN d.borrowedMember m WHERE 1=1");
            boolean hasState = state != null && !state.isEmpty();
            boolean hasBorrowedStartTime = borrowedStartTime != null;
            boolean hasBorrowedEndTime = borrowedEndTime != null;

            if (hasState) {
                switch (state) {
                    case "1":
                        jpql.append(" AND (d.maTB NOT IN (SELECT u.maTB FROM _UsageInformation u WHERE u.maTB = d.maTB) OR d.maTB IN (SELECT u.maTB FROM _UsageInformation u WHERE u.tGMuon IS NOT NULL AND u.tGTra IS NULL)");
                        break;
                    case "2":
                        jpql.append(" AND d.maTB NOT IN (SELECT u.maTB FROM _UsageInformation u WHERE u.maTB = d.maTB)");
                        break;
                    case "3":
                        jpql.append(" AND d.maTB IN (SELECT u.maTB FROM _UsageInformation u WHERE u.tGMuon IS NOT NULL AND u.tGTra IS NULL)");
                        break;
                    default:
                        break;
                }
            }

            if (hasBorrowedStartTime && hasBorrowedEndTime) {
                jpql.append(" AND ((d.maTB IN (SELECT u.maTB FROM _UsageInformation u WHERE u.tGMuon >= :borrowedStartTime AND u.tGMuon <= :borrowedEndTime AND u.tGTra IS NULL)");
            }

            Query<Object[]> query = session.createQuery(jpql.toString(), Object[].class);

            if (hasState) {
                if (state.equals("1") || state.equals("3")) {
                    query.setParameter("borrowedStartTime", borrowedStartTime);
                    query.setParameter("borrowedEndTime", borrowedEndTime);
                }
            }

            if (hasBorrowedStartTime) {
                query.setParameter("borrowedStartTime", borrowedStartTime);
            }

            if (hasBorrowedEndTime) {
                query.setParameter("borrowedEndTime", borrowedEndTime);
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

    public List<Object[]> getAvailableDevices() {
        List<Object[]> results = null;
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            String hql = """
                         SELECT u.maTB.maTB,u.maTB.tenTB FROM _UsageInformation u
                         WHERE u.maTB NOT IN (
                             SELECT u.maTB FROM _UsageInformation u
                             WHERE u.tGVao IS NULL AND u.tGMuon IS NOT NULL AND u.tGTra IS NULL
                         )
                         GROUP BY u.maTB
                         UNION
                         SELECT t.maTB, t.tenTB
                         FROM _Device t
                         WHERE t.maTB NOT IN (
                             SELECT u.maTB.maTB FROM _UsageInformation u 
                             WHERE u.maTB.maTB IS NOT NULL
                         )""";

            Query<Object[]> query = session.createQuery(hql);
            results = query.getResultList();
            Collections.sort(results, new Comparator<Object[]>() {
                @Override
                public int compare(Object[] obj1, Object[] obj2) {
                    String maTB1 = (String) obj1[0];
                    String maTB2 = (String) obj2[0];
                    return maTB1.compareTo(maTB2);
                }
            });
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return results;

    }

    public List<Object[]> getNotAvailableDevices() {
        List<Object[]> results = null;
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            String hql = """
                         SELECT u.maTB.maTB, u.maTB.tenTB, u.maTV.maTV, u.maTV.hoTen
                         FROM _UsageInformation u
                         WHERE u.tGVao IS NULL AND u.tGMuon IS NOT NULL AND u.tGTra IS NULL
                         """;

            Query<Object[]> query = session.createQuery(hql);
            results = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return results;

    }

//    public List getAllUsageInformationByTime(Date joinTime,Date leaveTime) {
//        List<_UsageInformation> usageInformationList = null;
//        try {
//            session.beginTransaction();
//            Query<_UsageInformation> devices = session.createQuery("SELECT u FROM _UsageInformation u JOIN u.maTB d JOIN u.maTV m WHERE u.tGVao >= :joinTime AND u.tGTra <= :leaveTime");
//            devices.setParameter("joinTime", joinTime);
//            devices.setParameter("leaveTime", leaveTime);
//            usageInformationList = devices.getResultList();
//            session.getTransaction().commit();
//        } catch (Exception e) {
//            System.err.println(e);
//        } finally {
//            session.close();
//        }
//        return usageInformationList;
//    }
    public List<Object[]> getAllUsageInformation(String memberId, String memberName, String deviceId, String deviceName, Date joinStartTime, Date joinEndTime, Date borrowedTime, Date returnTime) {
        try {
            StringBuilder jpql = new StringBuilder("SELECT u FROM _UsageInformation u JOIN u.maTV m JOIN u.maTB d WHERE 1=1");
            boolean hasMemberId = memberId != null && !memberId.isEmpty();
            boolean hasMemberName = memberName != null && !memberName.isEmpty();
            boolean hasDeviceId = deviceId != null && !deviceId.isEmpty();
            boolean hasDeviceName = deviceName != null && !deviceName.isEmpty();
            boolean hasJoinTimeRange = joinStartTime != null && joinEndTime != null;
            boolean hasBorrowedTimeRange = borrowedTime != null && returnTime != null;

            if (hasMemberId) {
                jpql.append(" AND m.maTV = :memberId");
            } else if (hasMemberName) {
                jpql.append(" AND m.hoTen LIKE :memberName");
            } else if (hasDeviceId) {
                jpql.append(" AND d.maTB = :deviceId");
            } else if (hasDeviceName) {
                jpql.append(" AND d.tenTB LIKE :deviceName");
            } else if (hasJoinTimeRange) {
                jpql.append(" AND u.tGVao BETWEEN :joinStartTime AND :joinEndTime");
            } else if (hasBorrowedTimeRange) {
                jpql.append(" AND u.tGMuon BETWEEN :borrowedTime AND :returnTime");
            }

            Query<Object[]> query = session.createQuery(jpql.toString(), Object[].class);

            if (hasMemberId) {
                query.setParameter("memberId", memberId);
            } else if (hasMemberName) {
                query.setParameter("memberName", "%" + memberName + "%");
            } else if (hasDeviceId) {
                query.setParameter("deviceId", deviceId);
            } else if (hasDeviceName) {
                query.setParameter("deviceName", "%" + deviceName + "%");
            } else if (hasJoinTimeRange) {
                query.setParameter("joinStartTime", joinStartTime);
                query.setParameter("joinEndTime", joinEndTime);
            } else if (hasBorrowedTimeRange) {
                query.setParameter("borrowedTime", borrowedTime);
                query.setParameter("returnTime", returnTime);
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

    public Boolean leaveStudyArea(_Member member) {
        try {
            session.beginTransaction();

            String hql = "UPDATE _UsageInformation SET tGTra = :TGTra WHERE maTV = :maTV";
            Query query = session.createQuery(hql);
            query.setParameter("TGTra", LocalDateTime.now());
            query.setParameter("maTV", member);
            int rowsUpdated = query.executeUpdate();

            if (rowsUpdated > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }

    public Boolean borrowDevice(_Member member, _Device device) {
        Date time = new Date();
        _UsageInformation usageInformation = new _UsageInformation();
        usageInformation.setMaTV(member);

        usageInformation.setMaTB(device);
        usageInformation.setTGVao(null);
        usageInformation.setTGMuon(time);
        usageInformation.setTGTra(null);

        try {
            session.beginTransaction();
            session.save(usageInformation);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println(e);
        } finally {
            session.close();
        }
        return false;
    }

    public Boolean returnDevice(_Device device) {
        try {
            session.beginTransaction();

            String hql = "UPDATE _UsageInformation SET tGTra = :TGTra WHERE maTB = :maTB and tGTra is NULL";
            Query query = session.createQuery(hql);
            query.setParameter("TGTra", LocalDateTime.now());
            query.setParameter("maTB", device);
            int rowsUpdated = query.executeUpdate();

            if (rowsUpdated > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }

    public List<_UsageInformation> getStudyAreaHistory(Date startTime, Date endTime, String department, String majors) {
        StringBuilder hql = new StringBuilder("FROM _UsageInformation u "
                + "WHERE u.tGMuon IS NULL AND u.tGTra IS NULL "
                + "AND (:startTime IS NULL OR u.tGVao >= :startTime) "
                + "AND (:endTime IS NULL OR u.tGVao <= :endTime) ");
        boolean hasDepartment = !department.isEmpty();
        boolean hasMajors = !majors.isEmpty();
        if (hasDepartment || hasMajors) {
            hql.append(" AND");
            if (hasDepartment) {
                hql.append(" u.maTV.khoa LIKE :khoa");
            }
            if (hasMajors) {
                if (hasDepartment) {
                    hql.append(" AND");
                }
                hql.append(" u.maTV.nganh LIKE :nganh");
            }

        }
        hql.append(" ORDER BY u.tGVao DESC");
        Query<_UsageInformation> query = session.createQuery(hql.toString(), _UsageInformation.class);

        query.setParameter("startTime", startTime);
        query.setParameter("endTime", endTime);
        if (hasDepartment) {
            query.setParameter("khoa", "%" + department + "%");
        }

        if (hasMajors) {
            query.setParameter("nganh", "%" + majors + "%");
        }
        return query.getResultList();
    }

    public List<_UsageInformation> getBorrowedDeviceHistory(Date startTime, Date endTime, String deviceName) {
        StringBuilder hql = new StringBuilder("FROM _UsageInformation u "
                + "WHERE u.tGVao IS NULL "
                + "AND u.tGMuon IS NOT NULL "
                + "AND u.tGTra IS NOT NULL "
                + "AND (:startTime IS NULL OR u.tGMuon >= :startTime) "
                + "AND (:endTime IS NULL OR u.tGTra <= :endTime) ");
        boolean hasDeviceName = !deviceName.isEmpty();

        if (hasDeviceName) {
            hql.append(" AND");
            hql.append(" u.maTB.tenTB LIKE :tenTB");

        }
        hql.append(" ORDER BY u.tGMuon DESC");
        Query<_UsageInformation> query = session.createQuery(hql.toString(), _UsageInformation.class);

        query.setParameter("startTime", startTime);
        query.setParameter("endTime", endTime);
        if (hasDeviceName) {
            query.setParameter("tenTB", "%" + deviceName + "%");
        }
        return query.getResultList();
    }

}
