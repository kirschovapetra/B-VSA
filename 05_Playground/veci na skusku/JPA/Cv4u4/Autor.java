/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv4u4;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

/**
 *
 * @author vsa
 */
@Entity
public class Autor implements Serializable {



    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String meno;
    
    @ManyToMany(mappedBy = "autori", cascade = CascadeType.PERSIST)
    private Set<Kniha> knihy = new HashSet<>();

    public Autor(String meno) {
        this.meno = meno;
    }
    public Autor(){}
    public Long getId() {
        return id;
    }

    public String getMeno() {
        return meno;
    }

    public void setMeno(String meno) {
        this.meno = meno;
    }

    public Set<Kniha> getKnihy() {
        return knihy;
    }

    public void setKnihy(Set<Kniha> knihy) {
        this.knihy = knihy;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Autor)) {
            return false;
        }
        Autor other = (Autor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cv4u4.Autor[ id=" + id + " ]";
    }
    
}
