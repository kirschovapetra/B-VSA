/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vsa
 */
@Entity
@Table(name = "OSOBA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Osoba.findAll", query = "SELECT o FROM Osoba o"),
    @NamedQuery(name = "Osoba.findById", query = "SELECT o FROM Osoba o WHERE o.id = :id"),
    @NamedQuery(name = "Osoba.findByName", query = "SELECT o FROM Osoba o WHERE o.name = :name"),
    @NamedQuery(name = "Osoba.findWithoutName", query = "SELECT o FROM Osoba o WHERE o.name IS NULL"),
    @NamedQuery(name = "Osoba.findByAge", query = "SELECT o FROM Osoba o WHERE o.age = :age"),
    @NamedQuery(name = "Osoba.findMaxWeight", query = "SELECT o FROM Osoba o ORDER BY o.weight DESC"),
    @NamedQuery(name = "Osoba.findByWeight", query = "SELECT o FROM Osoba o WHERE o.weight = :weight")})
public class Osoba implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "AGE")
    private Integer age;
    @Column(name = "WEIGHT")
    private Float weight;

    public Osoba() {
    }

    public Osoba(String name, Integer age, Float weight) {
        this.name = name;
        this.age = age;
        this.weight = weight;
    }

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
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
        if (!(object instanceof Osoba)) {
            return false;
        }
        Osoba other = (Osoba) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Osoba{" + "id=" + id + ", name=" + name + ", age=" + age + ", weight=" + weight + '}';
    }

  
    
}
