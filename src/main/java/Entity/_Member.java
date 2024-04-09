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

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
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

    public void setKhoa(String khoa) {
        this.khoa = khoa;
    }

    public String getNganh() {
        return nganh;
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

}
