/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

/*Uloha 4 - mapovanie Person na T_OSOBA*/
/*Uloha 5 - modifikacie:

- odstrani sa born - vypise vsetko okrem born
- prida sa age - chyba
- prida sa age @Transient - ok

*/
@Table(name="T_OSOBA")
@Entity
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="meno")
    private String name;
    
    @Column(name="narodena")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date born;
    @Column(name="vaha")
    private Float weight;


    @Transient
    private int age;
    
    private void setAge() {
        if (this.born != null){
        DateFormat formatter = new SimpleDateFormat("yyyyMMdd");                           
        int d1 = Integer.parseInt(formatter.format(this.born));                            
        int d2 = Integer.parseInt(formatter.format(new Date()));                          
        int a = (d2 - d1) / 10000;                                                       
        this.age = a;
        }
    }

    public int getAge() {
        return age;
    }
    
    public Person() {
        this.setAge();
    }

    public Person(String name, Float weight) {
        this.name = name;
        this.weight = weight;
    }

    
    
    public Person(String name, Date born, Float weight) {
        this.name = name;
        this.born = born;
        this.weight = weight;
        this.setAge();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBorn() {
        return born;
    }

    public void setBorn(Date born) {
        this.born = born;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }
    
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Person{" + "id=" + id + ", name=" + name + ", born=" + born + ", weight=" + weight + " age=" + age +'}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.id);
        hash = 43 * hash + Objects.hashCode(this.name);
        hash = 43 * hash + Objects.hashCode(this.born);
        hash = 43 * hash + Objects.hashCode(this.weight);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Person other = (Person) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.born, other.born)) {
            return false;
        }
        if (!Objects.equals(this.weight, other.weight)) {
            return false;
        }
        return true;
    }



    
}
