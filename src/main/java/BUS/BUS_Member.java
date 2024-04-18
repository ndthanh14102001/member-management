/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.DAO_Member;
import Entity._Member;
import java.sql.SQLException;
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

    public List<Object[]> getAllMembers(String memberId, String department, String majors,String year) {
        return memberDAO.getAllMembers(memberId, department, majors, year);
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

    public void updateMember(_Member memberInfo) throws Exception {
        memberDAO.updateMember(memberInfo);
    }

    public boolean deleteMember(int memberId) {
        return memberDAO.deleteMember(memberId);
    }

    public void deleteMembers(List<String> memberIds) throws SQLException {
        memberDAO.deleteMembers(memberIds);
    }
}
