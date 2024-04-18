/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import Entity._Device;
import Entity._Member;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class GUI_Device extends javax.swing.JFrame {

    /**
     * Creates new form GUI_Device
     */
    private DefaultTableModel model;
    private List<_Device> devices = new ArrayList<>();
    private _Device selectedDevice;

    public GUI_Device() {
        initComponents();
        model = new DefaultTableModel();
        model.addColumn("Mã Thiết Bị");
        model.addColumn("Tên Thiết Bị");
        model.addColumn("Mô Tả");

        tbDevices.setModel(model);
        displayDataInTable();
        tbDevices.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDevicesMouseClicked(evt);
            }
        });
        addOnChangeFilterEvent();
    }

    private void displayDataInTable() {
        // Xóa dữ liệu cũ
        model.setRowCount(0);
        this.devices.clear();
        // Lấy dữ liệu từ bảng 1 và thêm vào model1
        List<_Device> deviceList = new BUS.BUS_Device().getAllDevices(txtFilterType.getText(), txtFilterYear.getText(), txtFilterMaTB.getText());

        if (deviceList != null) {
            for (_Device device : deviceList) {
                this.devices.add(device);
                model.addRow(new Object[]{
                    device.getMaTB(),
                    device.getTenTB(),
                    device.getMoTaTB()
                });
            }
        }
    }

    private void tbDevicesMouseClicked(java.awt.event.MouseEvent evt) {
        // Lấy chỉ số của hàng được chọn
        int row = tbDevices.getSelectedRow();

        // Kiểm tra nếu hàng được chọn hợp lệ
        if (row >= 0 && row < tbDevices.getRowCount()) {
            this.selectedDevice = devices.get(row);
            String maTB = (String) tbDevices.getValueAt(row, 0);
            String tenTB = (String) tbDevices.getValueAt(row, 1);
            String moTaTB = (String) tbDevices.getValueAt(row, 2);

            txtMaTB.setText(String.valueOf(maTB));
            txtTenTB.setText(tenTB);
            txtMoTaTB.setText(moTaTB);
        }
    }

    private void addOnChangeFilterEvent() {
        txtFilterMaTB.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                // Xử lý khi có sự thay đổi trong văn bản (khi có chèn văn bản)
                displayDataInTable();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                // Xử lý khi có sự thay đổi trong văn bản (khi có xóa văn bản)
                displayDataInTable();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Xử lý khi có sự thay đổi trong văn bản (khi có thay đổi thuộc tính văn bản khác)
                displayDataInTable();
            }

        });
        txtFilterYear.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                // Xử lý khi có sự thay đổi trong văn bản (khi có chèn văn bản)
                displayDataInTable();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                // Xử lý khi có sự thay đổi trong văn bản (khi có xóa văn bản)
                displayDataInTable();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Xử lý khi có sự thay đổi trong văn bản (khi có thay đổi thuộc tính văn bản khác)
                displayDataInTable();
            }

        });
        txtFilterType.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                // Xử lý khi có sự thay đổi trong văn bản (khi có chèn văn bản)
                displayDataInTable();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                // Xử lý khi có sự thay đổi trong văn bản (khi có xóa văn bản)
                displayDataInTable();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Xử lý khi có sự thay đổi trong văn bản (khi có thay đổi thuộc tính văn bản khác)
                displayDataInTable();
            }

        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        labelTitle = new javax.swing.JLabel();
        LabelId = new javax.swing.JLabel();
        LabelYear = new javax.swing.JLabel();
        LabelType = new javax.swing.JLabel();
        txtFilterMaTB = new javax.swing.JTextField();
        txtFilterYear = new javax.swing.JTextField();
        txtFilterType = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbDevices = new javax.swing.JTable();
        LabelId2 = new javax.swing.JLabel();
        LabelNameDevice = new javax.swing.JLabel();
        LabelDescriptionDevice = new javax.swing.JLabel();
        txtMaTB = new javax.swing.JTextField();
        txtTenTB = new javax.swing.JTextField();
        txtMoTaTB = new javax.swing.JTextField();
        btnDelete = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        ButtonEdit = new javax.swing.JButton();
        ButtonAddExcel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        labelTitle.setFont(new java.awt.Font("Times New Roman", 1, 22)); // NOI18N
        labelTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitle.setText("THIẾT BỊ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(383, Short.MAX_VALUE)
                .addComponent(labelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(442, 442, 442))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelTitle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        LabelId.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        LabelId.setText("Mã thiết bị");

        LabelYear.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        LabelYear.setText("Năm");

        LabelType.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        LabelType.setText("Loại");

        txtFilterMaTB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFilterMaTBActionPerformed(evt);
            }
        });

        txtFilterYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFilterYearActionPerformed(evt);
            }
        });

        txtFilterType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFilterTypeActionPerformed(evt);
            }
        });

        tbDevices.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã thiết bị", "Tên thiết bị", "Mô tả thiết bị"
            }
        ));
        jScrollPane1.setViewportView(tbDevices);

        LabelId2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        LabelId2.setText("Mã thiết bị");

        LabelNameDevice.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        LabelNameDevice.setText("Tên thiết bị");

        LabelDescriptionDevice.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        LabelDescriptionDevice.setText("Mô tả thiết bị");

        txtMaTB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaTBActionPerformed(evt);
            }
        });

        txtTenTB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenTBActionPerformed(evt);
            }
        });

        txtMoTaTB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMoTaTBActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnDelete.setText("Xoá");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnAdd.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnAdd.setText("Thêm");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        ButtonEdit.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        ButtonEdit.setText("Cập nhật");
        ButtonEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonEditActionPerformed(evt);
            }
        });

        ButtonAddExcel.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        ButtonAddExcel.setText("Nhập bằng file Excel");
        ButtonAddExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonAddExcelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 962, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(LabelYear, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFilterYear))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(LabelId, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFilterMaTB, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(148, 148, 148)
                        .addComponent(LabelType, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFilterType, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(83, 83, 83)
                        .addComponent(ButtonEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(86, 86, 86)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(94, 94, 94)
                        .addComponent(ButtonAddExcel))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(LabelId2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtMaTB, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(37, 37, 37)
                            .addComponent(LabelNameDevice, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtTenTB, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(LabelDescriptionDevice, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtMoTaTB))))
                .addContainerGap(61, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelId, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelType, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFilterMaTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFilterType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelYear, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFilterYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelId2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelNameDevice, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelDescriptionDevice, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMoTaTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonAddExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(65, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtFilterMaTBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFilterMaTBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFilterMaTBActionPerformed

    private void txtFilterYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFilterYearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFilterYearActionPerformed

    private void txtFilterTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFilterTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFilterTypeActionPerformed

    private void txtMaTBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaTBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaTBActionPerformed

    private void txtTenTBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenTBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenTBActionPerformed

    private void txtMoTaTBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMoTaTBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMoTaTBActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tbDevices.getModel();
        int[] selectedRows = tbDevices.getSelectedRows();
        List<String> deviceIds = new ArrayList<>();
        // Duyệt qua mỗi dòng đã chọn
        for (int row : selectedRows) {

            String maTB = (String) model.getValueAt(row, 0);
            deviceIds.add(maTB);
        }
        try {
            new BUS.BUS_Device().DeleteDevices(deviceIds);
            displayDataInTable();
            JOptionPane.showMessageDialog(null, "Xóa các thiết bị thành công !", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        displayDataInTable();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        _Device device = new _Device();
        device.setMaTB(txtMaTB.getText());
        device.setTenTB(txtTenTB.getText());
        device.setMoTaTB(txtMoTaTB.getText());
        try {
            new BUS.BUS_Device().addDevice(device);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        displayDataInTable();
    }//GEN-LAST:event_btnAddActionPerformed

    private void ButtonEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonEditActionPerformed
        // TODO add your handling code here:
        _Device device = new _Device();
        device.setMaTB(this.selectedDevice.getMaTB());
        device.setTenTB(txtTenTB.getText());
        device.setMoTaTB(txtMoTaTB.getText());
        new BUS.BUS_Device().UpdateDevice(device);
        displayDataInTable();
    }//GEN-LAST:event_ButtonEditActionPerformed

    private void ButtonAddExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonAddExcelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ButtonAddExcelActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI_Device.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI_Device.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI_Device.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI_Device.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI_Device().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonAddExcel;
    private javax.swing.JButton ButtonEdit;
    private javax.swing.JLabel LabelDescriptionDevice;
    private javax.swing.JLabel LabelId;
    private javax.swing.JLabel LabelId2;
    private javax.swing.JLabel LabelNameDevice;
    private javax.swing.JLabel LabelType;
    private javax.swing.JLabel LabelYear;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JLabel labelTitle;
    private javax.swing.JTable tbDevices;
    private javax.swing.JTextField txtFilterMaTB;
    private javax.swing.JTextField txtFilterType;
    private javax.swing.JTextField txtFilterYear;
    private javax.swing.JTextField txtMaTB;
    private javax.swing.JTextField txtMoTaTB;
    private javax.swing.JTextField txtTenTB;
    // End of variables declaration//GEN-END:variables
}
