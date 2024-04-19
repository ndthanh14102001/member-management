/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import BUS.BUS_Member;
import Entity._Member;
import Helper.Import;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.*;

/**
 *
 * @author a410
 */
public class GUI_Member extends javax.swing.JFrame {

    /**
     * Creates new form MemberGUI1
     */
    private DefaultTableModel model;

    private List<_Member> members = new ArrayList<>();
    private _Member selectedMember;

    public GUI_Member() {

        initComponents();
        setLocationRelativeTo(null);
        model = new DefaultTableModel();
        model.addColumn("Mã Thành Viên");
        model.addColumn("Họ Tên");
        model.addColumn("Khoa");
        model.addColumn("Ngành");
        model.addColumn("Số Điện Thoại");
        model.addColumn("Email");
        model.addColumn("Trạng thái vi phạm");

        tbMembers.setModel(model);
        displayDataInTable();
        tbMembers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbMemberMouseClicked(evt);
            }
        });
        addOnChangeMaTVEvent();
        addOnChangeDepartmentEvent();
        addOnChangeMajorsEvent();
        addOnChangeYearEvent();
    }

    private void displayDataInTable() {
        // Xóa dữ liệu cũ
        model.setRowCount(0);
        this.members.clear();
        // Lấy dữ liệu từ bảng 1 và thêm vào model1
        List<Object[]> memberList = new BUS.BUS_Member().getAllMembers(txtMaTV.getText(), txtDepartment.getText(), txtMajors.getText(), txtYear.getText());
        txtCountMember.setText(String.valueOf(memberList.size()));
        if (memberList != null) {
            for (Object[] memberData : memberList) {
                _Member mem = (_Member) memberData[0];
                this.members.add(mem);
                String processingStatus = (String) memberData[1];
                // Thêm dữ liệu vào model1
                model.addRow(new Object[]{
                    mem.getMaTV(),
                    mem.getHoTen(),
                    mem.getKhoa(),
                    mem.getNganh(),
                    mem.getSdt(),
                    mem.getEmail(),
                    processingStatus // Thêm trạng thái xử lý vào cột cuối cùng
                });
            }
        }
    }

    private void tbMemberMouseClicked(java.awt.event.MouseEvent evt) {
        // Lấy chỉ số của hàng được chọn
        int row = tbMembers.getSelectedRow();

        // Kiểm tra nếu hàng được chọn hợp lệ
        if (row >= 0 && row < tbMembers.getRowCount()) {
            this.selectedMember = members.get(row);
            // Lấy dữ liệu từ bảng và hiển thị vào các thành phần trên giao diện
            String maTV = (String) tbMembers.getValueAt(row, 0);
            String hoten = (String) tbMembers.getValueAt(row, 1);
            String sdt = (String) tbMembers.getValueAt(row, 4);
            String email = (String) tbMembers.getValueAt(row, 5);

        }
    }

    private void addOnChangeMaTVEvent() {
        txtMaTV.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                // Xử lý khi có sự thay đổi trong văn bản (khi có chèn văn bản)
                handleFilterChange();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                // Xử lý khi có sự thay đổi trong văn bản (khi có xóa văn bản)
                handleFilterChange();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Xử lý khi có sự thay đổi trong văn bản (khi có thay đổi thuộc tính văn bản khác)
                handleFilterChange();
            }

        });
    }

    private void addOnChangeMajorsEvent() {
        txtMajors.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                // Xử lý khi có sự thay đổi trong văn bản (khi có chèn văn bản)
                handleFilterChange();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                // Xử lý khi có sự thay đổi trong văn bản (khi có xóa văn bản)
                handleFilterChange();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Xử lý khi có sự thay đổi trong văn bản (khi có thay đổi thuộc tính văn bản khác)
                handleFilterChange();
            }

        });
    }

    private void addOnChangeYearEvent() {
        txtYear.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                // Xử lý khi có sự thay đổi trong văn bản (khi có chèn văn bản)
                handleFilterChange();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                // Xử lý khi có sự thay đổi trong văn bản (khi có xóa văn bản)
                handleFilterChange();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Xử lý khi có sự thay đổi trong văn bản (khi có thay đổi thuộc tính văn bản khác)
                handleFilterChange();
            }

        });
    }

    private void addOnChangeDepartmentEvent() {
        txtDepartment.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                // Xử lý khi có sự thay đổi trong văn bản (khi có chèn văn bản)
                handleFilterChange();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                // Xử lý khi có sự thay đổi trong văn bản (khi có xóa văn bản)
                handleFilterChange();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Xử lý khi có sự thay đổi trong văn bản (khi có thay đổi thuộc tính văn bản khác)
                handleFilterChange();
            }

        });
    }

    private void handleFilterChange() {
        // Lấy giá trị mới của JTextField khi giá trị thay đổi
        displayDataInTable();
    }

    public List<_Member> readExcelFile(String filePath) {
        List<_Member> members = new ArrayList<>();
        FileInputStream file;
        try {
            file = new FileInputStream(new File(filePath));

            // Tạo một Workbook từ FileInputStream
            Workbook workbook = WorkbookFactory.create(file);

            // Lấy Sheet đầu tiên từ Workbook
            Sheet sheet = (Sheet) workbook.getSheetAt(0);

            // Lấy Iterator cho tất cả các hàng trong Sheet
            Iterator<Row> rowIterator = sheet.iterator();

            // Bỏ qua hàng tiêu đề
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }

            // Duyệt qua từng hàng và tạo đối tượng _Member
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                if (row.getCell(0) != null) {
                    // Lấy dữ liệu từ các ô trong hàng
                    String maTV = String.valueOf((int) row.getCell(0).getNumericCellValue());
                    if (maTV.equals("0")) {
                        continue;
                    }
                    String hoTen = row.getCell(1).getStringCellValue();
                    String khoa = row.getCell(2).getStringCellValue();
                    String nganh = row.getCell(3).getStringCellValue();
                    String sdt = row.getCell(4).getStringCellValue();

                    String password = "";
                    try {
                        password = row.getCell(5).getStringCellValue();
                    } catch (Exception e) {
                        password = String.valueOf((int) row.getCell(5).getNumericCellValue());
                    }
                    String email = row.getCell(6).getStringCellValue();
                    // Tạo đối tượng _Member và thêm vào danh sách
                    _Member member = new _Member();
                    member.setMaTV(maTV);
                    member.setHoTen(hoTen);
                    member.setKhoa(khoa);
                    member.setNganh(nganh);
                    member.setSdt(sdt);
                    member.setEmail(email);
                    member.setPassword(password);
                    members.add(member);
                }

            }

            // Đóng FileInputStream
            file.close();
        } catch (Exception e) {
            e.printStackTrace();

        }

        return members;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtMaTV = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbMembers = new javax.swing.JTable();
        btnAdd = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnImportExcel = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        txtMajors = new javax.swing.JTextField();
        txtDepartment = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtYear = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtCountMember = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("THÀNH VIÊN");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setText("Mã thành viên");

        txtMaTV.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setText("Số lượng thành viên :");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel5.setText("Khoa");

        tbMembers.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tbMembers.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tbMembers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã Thành Viên", "Họ Tên", "Khoa", "Ngành", "Số Điện Thoại", "Trạng Thái VP"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbMembers);

        btnAdd.setBackground(new java.awt.Color(204, 204, 204));
        btnAdd.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnAdd.setText("Thêm");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(204, 204, 204));
        btnDelete.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnDelete.setText("Xoá");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnUpdate.setBackground(new java.awt.Color(204, 204, 204));
        btnUpdate.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnUpdate.setText("Cập Nhật");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnImportExcel.setBackground(new java.awt.Color(204, 204, 204));
        btnImportExcel.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnImportExcel.setText("Nhập bằng file Excel");
        btnImportExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportExcelActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(204, 204, 204));
        jButton2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jButton2.setText("Refresh");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        txtMajors.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        txtDepartment.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel6.setText("Năm kích hoạt");

        txtYear.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel7.setText("Ngành");

        txtCountMember.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(btnAdd)
                        .addGap(18, 18, 18)
                        .addComponent(btnDelete)
                        .addGap(18, 18, 18)
                        .addComponent(btnUpdate)
                        .addGap(18, 18, 18)
                        .addComponent(btnImportExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(264, 264, 264))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtMaTV, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtYear, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMajors, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(251, 251, 251))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCountMember, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtDepartment, txtMaTV, txtMajors, txtYear});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel2, jLabel4, jLabel5, jLabel6});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMaTV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel2))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(txtYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(txtDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMajors, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txtCountMember, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDelete)
                    .addComponent(btnUpdate)
                    .addComponent(btnImportExcel)
                    .addComponent(btnAdd)
                    .addComponent(jButton2))
                .addGap(14, 14, 14))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel2, jLabel4, jLabel5, jLabel6, txtCountMember, txtDepartment, txtMaTV, txtMajors});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        GUI_UpdateMember.main(selectedMember);
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnImportExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportExcelActionPerformed
        // TODO add your handling code here:
        String filePath = new Import().selectFile(this);
        if (filePath != "") {
            List<_Member> members = readExcelFile(filePath);

            try {
                if (!members.isEmpty()) {
                    int result = JOptionPane.showConfirmDialog(null, "Bạn có muốn thêm " + members.size() + " thành viên", "Confirmation", JOptionPane.YES_NO_OPTION);

                    // Kiểm tra kết quả từ hộp thoại xác nhận
                    if (result == JOptionPane.YES_OPTION) {
                        new BUS.BUS_Member().addMembers(members);
                        JOptionPane.showMessageDialog(null, "Thêm các thành viên thành công !", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        displayDataInTable();
                    }

                } else {
                    throw new Exception("File không hợp lệ");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnImportExcelActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        new GUI_AddMember().show();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tbMembers.getModel();
        int[] selectedRows = tbMembers.getSelectedRows();
        List<String> memberIds = new ArrayList<>();
        // Duyệt qua mỗi dòng đã chọn
        for (int row : selectedRows) {

            String maTV = (String) model.getValueAt(row, 0);
            memberIds.add(maTV);
        }
        try {
            new BUS_Member().deleteMembers(memberIds);
            displayDataInTable();
            JOptionPane.showMessageDialog(null, "Xóa các thành viên thành công !", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnDeleteActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        txtDepartment.setText("");
        txtMaTV.setText("");
        txtMajors.setText("");
        txtYear.setText("");
        displayDataInTable();
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(GUI_Member.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI_Member.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI_Member.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI_Member.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI_Member().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnImportExcel;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbMembers;
    private javax.swing.JLabel txtCountMember;
    private javax.swing.JTextField txtDepartment;
    private javax.swing.JTextField txtMaTV;
    private javax.swing.JTextField txtMajors;
    private javax.swing.JTextField txtYear;
    // End of variables declaration//GEN-END:variables
}
