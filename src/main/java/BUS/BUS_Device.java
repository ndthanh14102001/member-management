/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import Entity._Device;
import DAO.DAO_Device;
import java.util.List;
/**
 *
 * @author Admin
 */
public class BUS_Device {
    private DAO_Device dao_device;
    public boolean addDevice( _Device device){
        dao_device = new DAO_Device();
        if(device.Validate() && dao_device.AddDevice(device))       //Nếu thêm thành công và xác thực thành công thì xuất hiện thông báo thành công
            return true;
        else
            return false;
    }
    
    public boolean addDevices( List<_Device> devices){
        dao_device = new DAO_Device();
        boolean isValid = true;
        for(_Device d : devices){
            if(d.Validate()==false)
            {
                 isValid = false;
                 break; // Ngừng vòng lặp khi có một d.Validate() trả về false
            }
        }
        if(isValid && dao_device.AddDevices(devices))
            return true;
        else
            return false;
    }
    
    
    public void UpdateDevice( _Device device){
        dao_device = new DAO_Device();
        dao_device.Update(device);
    }
    
    public void DeleteDevice(int device_id){
        dao_device = new DAO_Device();
        dao_device.DeleteDevice(device_id);
    }
    
    public void DeleteDevices(List<Integer> ids){
        dao_device = new DAO_Device();
        dao_device.DeleteDevices(ids);
    }
    
    public List<_Device> getAllDevices(){
        dao_device = new DAO_Device();
        return dao_device.getAllDevices();
    }
    
    public List<_Device> getAllDevices(String id, String type, String year){        //Lọc theo theo các điều kiện
        dao_device = new DAO_Device();
        return dao_device.getAllDevices(id, type, year);
    }
    
     public _Device SearchAvailableDevice(String id){
        dao_device = new DAO_Device();
        return dao_device.getAvailableDevicebyID(id);
    }
     
    public List<_Device> getAllAvailableDevices(){
        dao_device = new DAO_Device();
        return dao_device.getAllAvailableDevices();
    }
}