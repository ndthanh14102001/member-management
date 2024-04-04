/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author Admin
 */
public class BUS_Member {
    private MemberDAO memberDAO;
    
    public BUS_Member() {
        this.memberDAO = new MemberDAO();
    }
    
    //Thêm 1 thành viên
    public void addMember(MemberDTO member) throws SQLException {
        memberDAO.addMember(member);
    }
    
    //Thêm danh sách thành viên từ tệp Excel vào hệ thống
    public void addMembersFromExcel(String filePath) throws IOException, SQLException {
        List<MemberDTO> members = ExcelReader.readMembersFromExcel(filePath);
        for (MemberDTO member : members) {
            memberDAO.addMember(member);
        }
    }
    
    //Xóa 1 thành viên dựa trên ID
    public void deleteMember(int memberId) throws SQLException {
        memberDAO.deleteMember(memberId);
    }
    
    //Xóa nhiều thành viên theo điều kiện
    public void deleteMembersByCondition(String condition) throws SQLException {
        memberDAO.deleteMembersByCondition(condition);
    }
    
    //Sửa thông tin thành viên
    public void updateMember(int memberId, MemberDTO newMember) throws SQLException {
        memberDAO.updateMember(memberId, newMember);
    }
    
    //Vào khu vực học tập
    public void enterStudyArea(int memberId) {
        // Code to enter study area
    }
    
    //Mượn thiết bị
    public void borrowEquipment(int memberId, String equipment) {
        // Code to borrow equipment
    }
    
    //Trả thiết bị
    public void returnEquipment(int memberId, String equipment) {
        // Code to return equipment
    }
    
    //Cảnh báo vi phạm
    public void alertViolation(int memberId) {
        // Code to alert violation
    }
}
