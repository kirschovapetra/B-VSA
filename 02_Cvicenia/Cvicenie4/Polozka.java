/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Polozka implements Serializable {
    /*tato tabulka je prepojenie medzi Knihou a Obchodom, lebo vztah Kniha-Obchod 
    by bol Many to many (viacero knih v 1 obchode, 1 kniha vo viacerych obchodoch). 
    Treba vytvarat pre tuto tabulku samostatnu triedu, lebo este chceme ukladat aj cenu.
    Cize bude obsahovat 2 vztahy, obidva budu Many to one
*/
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double cena;
    
    /*
        - Viacero poloziek sa moze vztahovat k 1 knihe, nieco ako vytlacky alebo 
        take nieco, proste z 1 knihy mozes mat vela vytlackov
        - referencia na knihu - bude obsahovat cudzi kluc kniha_id
    */
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Kniha kniha;
    
    
    /*
        - Viacero poloziek sa moze nachadzat v 1 obchode
        - referencia na obchod - bude obsahovat cudzi kluc obchod_id
    */
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Obchod obchod;

    public Polozka(){}
    
    public Polozka(Double cena, Kniha kniha, Obchod obchod) {
        this.cena = cena;
        this.kniha = kniha;
        this.obchod = obchod;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }

    public Kniha getKniha() {
        return kniha;
    }

    public void setKniha(Kniha kniha) {
        this.kniha = kniha;
    }

    public Obchod getObchod() {
        return obchod;
    }

    public void setObchod(Obchod obchod) {
        this.obchod = obchod;
    }

   

    @Override
    public String toString() {
        return "entities.Polozka[ id=" + id + " ]";
    }
    
}
