/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.DAO_Member;
import Entity._Member;
import java.util.List;

/**
 *
 * @author Admin
 */
public class BUS_Member {

    private DAO_Member memberDAO;

    public BUS_Member() {
        this.memberDAO = new DAO_Member();
    }

    public List<_Member> getAllMembers() {
        return memberDAO.getAllMembers();
    }

    public List<_Member> getMembersById(int memberId) {
        return memberDAO.getMembersById(memberId);
    }

    public List<_Member> getMembersByDepartment(String department) {
        return memberDAO.getMembersByDepartment(department);
    }

    public List<_Member> getMembersByMajors(String majors) {
        return memberDAO.getMembersByMajors(majors);
    }

    public boolean addMember(_Member member) {
        return memberDAO.addMember(member);
    }

    public boolean addMembers(List<_Member> members) {
        return memberDAO.addMembers(members);
    }

    public boolean updateMember(int memberId, _Member memberInfo) {
        memberInfo.setMaTV(memberId);
        return memberDAO.updateMember(memberInfo);
    }

    public boolean deleteMember(int memberId) {
        return memberDAO.deleteMember(memberId);
    }

    public boolean deleteMembers(List<Integer> memberIds) {
        return memberDAO.deleteMembers(memberIds);
    }
}
