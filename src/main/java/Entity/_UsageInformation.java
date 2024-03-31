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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "thongtinsd")
@NamedQueries({
    @NamedQuery(name = "_UsageInformation.findAll", query = "SELECT _ FROM _UsageInformation _"),
    @NamedQuery(name = "_UsageInformation.findByMaTT", query = "SELECT _ FROM _UsageInformation _ WHERE _.maTT = :maTT"),
    @NamedQuery(name = "_UsageInformation.findByTGVao", query = "SELECT _ FROM _UsageInformation _ WHERE _.tGVao = :tGVao"),
    @NamedQuery(name = "_UsageInformation.findByTGMuon", query = "SELECT _ FROM _UsageInformation _ WHERE _.tGMuon = :tGMuon"),
    @NamedQuery(name = "_UsageInformation.findByTGTra", query = "SELECT _ FROM _UsageInformation _ WHERE _.tGTra = :tGTra")})
public class _UsageInformation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MaTT")
    private Integer maTT;
    @Column(name = "TGVao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tGVao;
    @Column(name = "TGMuon")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tGMuon;
    @Column(name = "TGTra")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tGTra;
    @JoinColumn(name = "MaTV", referencedColumnName = "MaTV")
    @ManyToOne(optional = false)
    private _Member maTV;
    @JoinColumn(name = "MaTB", referencedColumnName = "MaTB")
    @ManyToOne
    private _Device maTB;

    public _UsageInformation() {
    }

    public _UsageInformation(Integer maTT) {
        this.maTT = maTT;
    }

    public Integer getMaTT() {
        return maTT;
    }

    public void setMaTT(Integer maTT) {
        this.maTT = maTT;
    }

    public Date getTGVao() {
        return tGVao;
    }

    public void setTGVao(Date tGVao) {
        this.tGVao = tGVao;
    }

    public Date getTGMuon() {
        return tGMuon;
    }

    public void setTGMuon(Date tGMuon) {
        this.tGMuon = tGMuon;
    }

    public Date getTGTra() {
        return tGTra;
    }

    public void setTGTra(Date tGTra) {
        this.tGTra = tGTra;
    }

    public _Member getMaTV() {
        return maTV;
    }

    public void setMaTV(_Member maTV) {
        this.maTV = maTV;
    }

    public _Device getMaTB() {
        return maTB;
    }

    public void setMaTB(_Device maTB) {
        this.maTB = maTB;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maTT != null ? maTT.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof _UsageInformation)) {
            return false;
        }
        _UsageInformation other = (_UsageInformation) object;
        if ((this.maTT == null && other.maTT != null) || (this.maTT != null && !this.maTT.equals(other.maTT))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity._UsageInformation[ maTT=" + maTT + " ]";
    }
    
}
