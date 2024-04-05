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
    public BUS_Processing () {
        dao = new DAO_Processing();
    }
    public List getAllProcessing(){
    return dao.getAllProcessing();
    }
     public boolean createProcess(_Processing processing) {
    return dao.createProcess(processing);
    }
    public boolean updateProcess(_Processing processing) {
    return dao.updateProcess(processing);
    }
    public boolean deleteProcess(int id) {
    return dao.deleteProcess(id);
    }
    public List<_Processing> getProcessingListByProcessingType(String type) {
    return dao.getProcessingListByProcessingType(type);
    }
    public List<_Processing> getProcessingListByProcessingDate(LocalDate startDate, LocalDate endDate){
    return dao.getProcessingListByProcessingDate(startDate, endDate);
    }
    
}