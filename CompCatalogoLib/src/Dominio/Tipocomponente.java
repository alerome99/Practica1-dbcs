/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Propietario
 */
@Entity
@Table(name = "TIPOCOMPONENTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipocomponente.findAll", query = "SELECT t FROM Tipocomponente t"),
    @NamedQuery(name = "Tipocomponente.findByIdtipocomponente", query = "SELECT t FROM Tipocomponente t WHERE t.idtipocomponente = :idtipocomponente"),
    @NamedQuery(name = "Tipocomponente.findByNombretipocomponente", query = "SELECT t FROM Tipocomponente t WHERE t.nombretipocomponente = :nombretipocomponente")})
public class Tipocomponente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IDTIPOCOMPONENTE")
    private Short idtipocomponente;
    @Basic(optional = false)
    @Column(name = "NOMBRETIPOCOMPONENTE")
    private String nombretipocomponente;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipo", fetch = FetchType.EAGER)
    private List<Descripcioncomponente> descripcioncomponenteList;

    public Tipocomponente() {
    }

    public Tipocomponente(Short idtipocomponente) {
        this.idtipocomponente = idtipocomponente;
    }

    public Tipocomponente(Short idtipocomponente, String nombretipocomponente) {
        this.idtipocomponente = idtipocomponente;
        this.nombretipocomponente = nombretipocomponente;
    }

    public Short getIdtipocomponente() {
        return idtipocomponente;
    }

    public void setIdtipocomponente(Short idtipocomponente) {
        this.idtipocomponente = idtipocomponente;
    }

    public String getNombretipocomponente() {
        return nombretipocomponente;
    }

    public void setNombretipocomponente(String nombretipocomponente) {
        this.nombretipocomponente = nombretipocomponente;
    }

    @XmlTransient
    public List<Descripcioncomponente> getDescripcioncomponenteList() {
        return descripcioncomponenteList;
    }

    public void setDescripcioncomponenteList(List<Descripcioncomponente> descripcioncomponenteList) {
        this.descripcioncomponenteList = descripcioncomponenteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtipocomponente != null ? idtipocomponente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipocomponente)) {
            return false;
        }
        Tipocomponente other = (Tipocomponente) object;
        if ((this.idtipocomponente == null && other.idtipocomponente != null) || (this.idtipocomponente != null && !this.idtipocomponente.equals(other.idtipocomponente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Dominio.Tipocomponente[ idtipocomponente=" + idtipocomponente + " ]";
    }
    
}
