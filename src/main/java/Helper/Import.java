/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Helper;

import java.awt.Component;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Admin
 */
public class Import {

    public String selectFile(Component parent) {
        JFileChooser fileChooser = new JFileChooser();

        // Thiết lập bộ lọc cho các loại tệp tin
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel Files", "xlsx", "xls");
        fileChooser.setFileFilter(filter);

        // Mở cửa sổ chọn tệp
        int returnValue = fileChooser.showOpenDialog(parent);

        // Kiểm tra nếu người dùng đã chọn tệp
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            return selectedFile.getAbsolutePath();
        } else {
            return "";
        }
    }
}
