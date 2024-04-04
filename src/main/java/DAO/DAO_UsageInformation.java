/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity._Device;
import Entity._Member;
import Entity._UsageInformation;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
    
    public Boolean accessStudyArea(_Member member, _Device device) {
        Date time = new Date();
        _UsageInformation usageInformation = new _UsageInformation();
        usageInformation.setMaTB(device);
        usageInformation.setMaTV(member);
        usageInformation.setTGVao(time);
        usageInformation.setTGMuon(null);
        usageInformation.setTGTra(null);
        try {
            session.save(usageInformation);
            return true;
        } catch (Exception e) {
            System.err.println(e);
        } finally{
            session.close();
        }
        return false;
    }
    
    public List getAllUsageInformation(){
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
    
    public List getAllUsageInformationByMemberId(int memberId){
        List<_UsageInformation> usageInformationList = null;
        try {
            Query<_UsageInformation> usageInformation = session.createQuery("SELECT u FROM _UsageInformation u JOIN u.maTB d JOIN u.maTV m WHERE u.maTV = :member");
            _Member member = session.get(_Member.class, memberId);
            
            usageInformation.setParameter("member", member);
            usageInformationList = usageInformation.getResultList();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return usageInformationList;
    }
    
    public List getAllUsageInformationByMemberName(String memberName){
        List<_UsageInformation> usageInformationList = null;
        try{
            Query<_UsageInformation> usageInformation = session.createQuery("SELECT u FROM _UsageInformation u JOIN u.maTV m WHERE m.hoTen = :memberName");
            usageInformation.setParameter("memberName", memberName);
            usageInformationList = usageInformation.getResultList();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return usageInformationList;
    }
    
    public List getMembersNotInStudyArea() {
        try {
            List<Object> members = session.createQuery("SELECT m FROM _Member m WHERE m.maTV NOT IN (SELECT u.maTV.maTV FROM _UsageInformation u)").getResultList();
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
    
    public List getDevicesAndMembers() {
        try {
            List<_UsageInformation> usages = session.createQuery("SELECT u FROM _UsageInformation u JOIN u.maTB d JOIN u.maTV m " 
            + "WHERE u.tGVao IS NOT NULL AND u.tGTra IS NULL").getResultList();
            return usages;
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return null;
    }
    
    public List getDevicesAndMembersByJoinTime(Date startTime,Date endTime) {
        List<_UsageInformation> usageInformationList = null;
        try {
            Query<_UsageInformation> devices = session.createQuery("SELECT u FROM _UsageInformation u JOIN u.maTB d JOIN u.maTV m " 
            + "WHERE u.tGVao IS NOT NULL AND u.tGTra IS NULL " +
                 "AND u.tGVao >= :startTime AND u.tGVao <= :endTime");
            devices.setParameter("startTime", startTime);
            devices.setParameter("endTime", endTime);
            usageInformationList = devices.getResultList();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return usageInformationList;
    }
    
    public List getAllUsageInformationByDeviceId(int deviceId) {
        List<_UsageInformation> usageInformationList = null;
        try {
            //FROM _UsageInformation WHERE maTB = :maThietBi
            Query<_UsageInformation> usageInformation = session.createQuery("SELECT u FROM _UsageInformation u JOIN u.maTB d JOIN u.maTV m WHERE u.maTB = :device");
            _Device device = session.get(_Device.class, deviceId);
            usageInformation.setParameter("device", device);
            usageInformationList = usageInformation.getResultList();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return usageInformationList;
    }
    
    public List getAllUsageInformationByDeviceName(String deviceName) {
        List<_UsageInformation> usageInformationList = null;
        try{
            Query<_UsageInformation> usageInformation = session.createQuery("SELECT u FROM _UsageInformation u JOIN u.maTB d WHERE d.tenTB = :deviceName");
            usageInformation.setParameter("deviceName", deviceName);
            usageInformationList = usageInformation.getResultList();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return usageInformationList;
    }
    
    public List getAllUsageInformationByTime(Date joinTime,Date leaveTime) {
        List<_UsageInformation> usageInformationList = null;
        try {
            session.beginTransaction();
            Query<_UsageInformation> devices = session.createQuery("SELECT u FROM _UsageInformation u JOIN u.maTB d JOIN u.maTV m WHERE u.tGVao >= :joinTime AND u.tGTra <= :leaveTime");
            devices.setParameter("joinTime", joinTime);
            devices.setParameter("leaveTime", leaveTime);
            usageInformationList = devices.getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return usageInformationList;
    }
    
    public Boolean leaveStudyArea(int memberId){
        try {
            session.beginTransaction();
            Query<_UsageInformation> query = session.createQuery("SELECT u FROM _UsageInformation u WHERE u.maTV.maTV = :maTV AND u.tGVao IS NOT NULL AND u.tGTra IS NULL");
            query.setParameter("maTV", memberId);
//            List<_UsageInformation> usageInformation = query.getResultList();
            _UsageInformation usage = query.getSingleResultOrNull();
            System.out.println(usage.getTGVao());
            Timestamp leaveTime = new Timestamp(System.currentTimeMillis());
            System.out.println(leaveTime);
            System.out.println(leaveTime.getTime() - usage.getTGVao().getTime());
//            Date joinTime = usage.getTGVao();
//            long durationInMillis = leaveTime.getTime() - joinTime.getTime();
            usage.setTGTra(leaveTime);
//            usage.setTGMuon(new Date(durationInMillis));
            session.update(usage);
            session.getTransaction().commit();
//            for (_UsageInformation usageInfo : usageInformation) {
//                Date joinTime = usageInfo.getTGVao();
//                long durationInMillis = leaveTime.getTime() - joinTime.getTime();
//                usageInfo.setTGTra(leaveTime);
//                usageInfo.setTGMuon(new Date(durationInMillis));
//
//                session.update(usageInfo);
//            }
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println(e);
        } finally {
            session.close();
            
        }
        return false;
    }
}
