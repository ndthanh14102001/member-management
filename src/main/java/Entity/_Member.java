/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "thanhvien")
@NamedQueries({
    @NamedQuery(name = "_Member.findAll", query = "SELECT _ FROM _Member _"),
    @NamedQuery(name = "_Member.findByMaTV", query = "SELECT _ FROM _Member _ WHERE _.maTV = :maTV"),
    @NamedQuery(name = "_Member.findByHoTen", query = "SELECT _ FROM _Member _ WHERE _.hoTen = :hoTen"),
    @NamedQuery(name = "_Member.findByKhoa", query = "SELECT _ FROM _Member _ WHERE _.khoa = :khoa"),
    @NamedQuery(name = "_Member.findByNganh", query = "SELECT _ FROM _Member _ WHERE _.nganh = :nganh"),
    @NamedQuery(name = "_Member.findBySdt", query = "SELECT _ FROM _Member _ WHERE _.sdt = :sdt")})
public class _Member implements Serializable {

    @Basic(optional = false)
    @Column(name = "Email")
    private String email;
    @Basic(optional = false)
    @Column(name = "Password")
    private String password;

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "MaTV")
    private String maTV;
    @Basic(optional = false)
    @Column(name = "HoTen")
    private String hoTen;
    @Column(name = "Khoa")
    private String khoa;
    @Column(name = "Nganh")
    private String nganh;
    @Column(name = "SDT")
    private String sdt;

    @OneToMany(mappedBy = "maTV", cascade = CascadeType.ALL)
    private List<_Processing> processings;

    public _Member() {
    }

    public _Member(String maTV) {
        this.maTV = maTV;
    }

    public _Member(String maTV, String hoTen) {
        this.maTV = maTV;
        this.hoTen = hoTen;
    }

    public String getMaTV() {
        return maTV;
    }

    public void setMaTV(String maTV) {
        this.maTV = maTV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getKhoa() {
        return khoa;
    }

    public String getKhoaById() {
        try {
            return maTV.substring(5, 9);
        } catch (Exception e) {
            return "";
        }
    }

    public String getKhoaById(String maTV) {
        try {
            return maTV.substring(5, 9);
        } catch (Exception e) {
            return "";
        }
    }

    public void setKhoa(String khoa) {
        this.khoa = khoa;
    }

    public String getNganh() {
        return nganh;
    }

    public String getNganhById() {
        try {
            return maTV.substring(9);
        } catch (Exception e) {
            return "";
        }
    }

    public String getNganhById(String maTV) {
        try {
            return maTV.substring(9);
        } catch (Exception e) {
            return "";
        }
    }

    public void setNganh(String nganh) {
        this.nganh = nganh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public List<_Processing> getProcessings() {
        return processings;
    }

    public void setProcessings(List<_Processing> processings) {
        this.processings = processings;
    }

    public String getSTTById() {
        try {
            return maTV.substring(0, 5);
        } catch (Exception e) {
            return "";
        }
    }

    public void checkMaTVFormat() throws Exception {
        if (maTV == null || maTV.length() != 13) {
            throw new Exception("Mã TV phải có độ dài 13 ký tự.");
        }
        String stt = getSTTById();
        try {
            int sttInt = Integer.parseInt(stt);
        } catch (NumberFormatException e) {
            throw new Exception("Số thứ tự (5 ký tự đầu) phải là số.");
        }
    }

    public void checkEmailFormat() throws Exception {
        if (!this.isValidEmail(email)) {
            throw new Exception("Email không hợp lệ");
        }
    }

    private boolean isValidEmail(String email) {
        // Biểu thức chính quy để kiểm tra tính hợp lệ của email
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        // Tạo một đối tượng Pattern từ biểu thức chính quy
        Pattern pattern = Pattern.compile(emailRegex);

        // Kiểm tra xem email có khớp với biểu thức chính quy không
        return pattern.matcher(email).matches();
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maTV != null ? maTV.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof _Member)) {
            return false;
        }
        _Member other = (_Member) object;
        if ((this.maTV == null && other.maTV != null) || (this.maTV != null && !this.maTV.equals(other.maTV))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity._Member[ maTV=" + maTV + " ]";
    }

    public int getTrangThaiVP() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
