/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.member_management;

import DAO.DAO_Example;
import Entity._Member;

/**
 *
 * @author Admin
 */
public class Member_Management {

    public static void main(String[] args) {
        _Member member = new _Member();
        member.setHoTen("Nguyen van teo");
        member.setKhoa("Nguyen van teo");
        member.setNganh("123");
        member.setSdt("032681173");
        new DAO_Example().testCreateMember(member);
    }
}
