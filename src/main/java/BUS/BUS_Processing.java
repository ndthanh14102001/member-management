/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.DAO_Processing;
import Entity._Processing;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Admin
 */
public class BUS_Processing {
    private DAO_Processing dao;

    public BUS_Processing() {
        dao = new DAO_Processing();
    }

    public List getAllProcessing() {
        return dao.getAllProcessing();
    }
    
    public List<_Processing> getAllProcessings(){
        return dao.getAllProcessings();
    }

    public boolean createProcess(_Processing processing) {
        return dao.createProcess(processing);
    }

    public boolean updateProcess(_Processing processing) {
        return dao.updateProcess(processing);
    }

    public boolean deleteProcess(_Processing processing) {
        return dao.deleteProcess(processing);
    }

    public List<Object[]> getProcessingList(String type, LocalDate startDate, LocalDate endDate) {
        return dao.getProcessingList(type, startDate, endDate);
    }
    
}