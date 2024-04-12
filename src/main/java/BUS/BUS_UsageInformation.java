package BUS;

import DAO.DAO_UsageInformation;
import Entity._Device;
import Entity._Member;
import Entity._UsageInformation;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class BUS_UsageInformation {
    private DAO_UsageInformation dao;

    public BUS_UsageInformation() {
        dao = new DAO_UsageInformation();
    }

    public Boolean accessStudyArea(_Member member) {
        return dao.accessStudyArea(member);
    }

    public List<Object> getAllUsageInformation() {
        return dao.getAllUsageInformation();
    }

    public List<Object> getMembersInStudyArea() {
        return dao.getMembersInStudyArea();
    }

//    public _Member getMembersNotInStudyAreaById(int memberId) {
//        return dao.getMembersNotInStudyAreaById(memberId);
//    }
//
//    public List<_UsageInformation> getDevicesAndMembers() {
//        return dao.getDevicesAndMembers();
//    }
    
    public List<Object[]> getDevicesAndMembers(String state, LocalDateTime borrowedStartTime, LocalDateTime borrowedEndTime){
        return dao.getDevicesAndMembers(state, borrowedStartTime, borrowedEndTime);
    }
    
    public List<Object[]> getAllUsageInformation(String memberId, String memberName, String deviceId, String deviceName, Date joinStartTime, Date joinEndTime, Date borrowedTime, Date returnTime){
        return dao.getAllUsageInformation(memberId, memberName, deviceId, deviceName, joinStartTime, joinEndTime, borrowedTime, returnTime);
    }

//    public List<_UsageInformation> getAllUsageInformationByTime(Date joinTime, Date leaveTime) {
//        return dao.getAllUsageInformationByTime(joinTime, leaveTime);
//    }

    public Boolean leaveStudyArea(_Member member) {
        return dao.leaveStudyArea(member);
    }
    
    public Boolean borrowDevice(_Member member, _Device device) {
        return dao.borrowDevice(member, device);
    }
}