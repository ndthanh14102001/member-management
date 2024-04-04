/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.member_management;

import BUS.BUS_Member;
import Entity._Member;
import java.util.List;

/**
 *
 * @author Admin
 */
public class Member_Management {

    public static void main(String[] args) {
        List<_Member> members = new BUS_Member().getAllMembers();
        for (_Member member : members) {
            System.out.print(member);
        }
    }
}
