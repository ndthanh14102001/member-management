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

    public List<Object[]> getAllMembers(String memberId, String department, String majors) {
        return memberDAO.getAllMembers(memberId, department, majors);
    }
    
    public List<_Member> getAllMembers() {
        return memberDAO.getAllMembers();
    }
//Example
//        List<Object[]> results = new BUS_Member().getAllMembers("00017CNTTSPTH", "", "SPTH");
//        for (Object[] result : results) {
//            _Member mebmer = (_Member) result[0];
//            String trangThai = (String) result[1];
//            System.out.println(mebmer.getMaTV() + " " + trangThai);
//        }

    public void addMember(_Member member) throws Exception {
        memberDAO.addMember(member);
    }

    public void addMembers(List<_Member> members) throws Exception {
        memberDAO.addMembers(members);
    }

    public boolean updateMember(String memberId, _Member memberInfo) {
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
