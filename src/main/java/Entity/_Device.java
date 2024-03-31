/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "thietbi")
@NamedQueries({
    @NamedQuery(name = "_Device.findAll", query = "SELECT _ FROM _Device _"),
    @NamedQuery(name = "_Device.findByMaTB", query = "SELECT _ FROM _Device _ WHERE _.maTB = :maTB"),
    @NamedQuery(name = "_Device.findByTenTB", query = "SELECT _ FROM _Device _ WHERE _.tenTB = :tenTB")})
public class _Device implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MaTB")
    private Integer maTB;
    @Basic(optional = false)
    @Column(name = "TenTB")
    private String tenTB;
    @Lob
    @Column(name = "MoTaTB")
    private String moTaTB;
    @OneToMany(mappedBy = "maTB")
    private Collection<_UsageInformation> usageInformationCollection;

    public _Device() {
    }

    public _Device(Integer maTB) {
        this.maTB = maTB;
    }

    public _Device(Integer maTB, String tenTB) {
        this.maTB = maTB;
        this.tenTB = tenTB;
    }

    public Integer getMaTB() {
        return maTB;
    }

    public void setMaTB(Integer maTB) {
        this.maTB = maTB;
    }

    public String getTenTB() {
        return tenTB;
    }

    public void setTenTB(String tenTB) {
        this.tenTB = tenTB;
    }

    public String getMoTaTB() {
        return moTaTB;
    }

    public void setMoTaTB(String moTaTB) {
        this.moTaTB = moTaTB;
    }

    public Collection<_UsageInformation> getUsageInformationCollection() {
        return usageInformationCollection;
    }

    public void setUsageInformationCollection(Collection<_UsageInformation> usageInformationCollection) {
        this.usageInformationCollection = usageInformationCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maTB != null ? maTB.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof _Device)) {
            return false;
        }
        _Device other = (_Device) object;
        if ((this.maTB == null && other.maTB != null) || (this.maTB != null && !this.maTB.equals(other.maTB))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity._Device[ maTB=" + maTB + " ]";
    }
    
}
