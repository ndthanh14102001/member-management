package BUS;

import DAO.DAO_UsageInformation;
import Entity._Device;
import Entity._Member;
import Entity._UsageInformation;
import java.util.Date;
import java.util.List;

public class BUS_UsageInformation {
    private DAO_UsageInformation dao;

    public BUS_UsageInformation() {
        dao = new DAO_UsageInformation();
    }

    public Boolean accessStudyArea(_Member member, _Device device) {
        return dao.accessStudyArea(member, device);
    }

    public List<Object> getAllUsageInformation() {
        return dao.getAllUsageInformation();
    }

    public List<_UsageInformation> getAllUsageInformationByMemberId(int memberId) {
        return dao.getAllUsageInformationByMemberId(memberId);
    }

    public List<_UsageInformation> getAllUsageInformationByMemberName(String memberName) {
        return dao.getAllUsageInformationByMemberName(memberName);
    }

    public List<Object> getMembersNotInStudyArea() {
        return dao.getMembersNotInStudyArea();
    }

    public _Member getMembersNotInStudyAreaById(int memberId) {
        return dao.getMembersNotInStudyAreaById(memberId);
    }

    public List<_UsageInformation> getDevicesAndMembers() {
        return dao.getDevicesAndMembers();
    }

    public List<_UsageInformation> getDevicesAndMembersByJoinTime(Date startTime, Date endTime) {
        return dao.getDevicesAndMembersByJoinTime(startTime, endTime);
    }

    public List<_UsageInformation> getAllUsageInformationByDeviceId(int deviceId) {
        return dao.getAllUsageInformationByDeviceId(deviceId);
    }

    public List<_UsageInformation> getAllUsageInformationByDeviceName(String deviceName) {
        return dao.getAllUsageInformationByDeviceName(deviceName);
    }

    public List<_UsageInformation> getAllUsageInformationByTime(Date joinTime, Date leaveTime) {
        return dao.getAllUsageInformationByTime(joinTime, leaveTime);
    }

    public Boolean leaveStudyArea(int memberId) {
        return dao.leaveStudyArea(memberId);
    }
}