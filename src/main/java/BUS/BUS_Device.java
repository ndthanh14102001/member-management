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

    public void addDevice(_Device device) throws Exception {
        dao_device = new DAO_Device();
        device.Validate();
        dao_device.AddDevice(device);
    }

    public void addDevices(List<_Device> devices) throws Exception {
        dao_device = new DAO_Device();
        dao_device.AddDevices(devices);
    }

    public void UpdateDevice(_Device device) {
        dao_device = new DAO_Device();
        dao_device.Update(device);
    }

    public void DeleteDevice(int device_id) {
        dao_device = new DAO_Device();
        dao_device.DeleteDevice(device_id);
    }

    public void DeleteDevices(List<String> ids) throws Exception {
        dao_device = new DAO_Device();
        dao_device.DeleteDevices(ids);
    }

    public List<_Device> getAllDevices() {
        dao_device = new DAO_Device();
        return dao_device.getAllDevices();
    }

    public List<_Device> getAllDevices(String type, String year, String id) {        //Lọc theo theo các điều kiện
        dao_device = new DAO_Device();
        return dao_device.getAllDevices(type, year, id);
    }

    public _Device SearchAvailableDevice(String id) {
        dao_device = new DAO_Device();
        return dao_device.getAvailableDevicebyID(id);
    }

    public List<_Device> getAllAvailableDevices() {
        dao_device = new DAO_Device();
        return dao_device.getAllAvailableDevices();
    }
}
