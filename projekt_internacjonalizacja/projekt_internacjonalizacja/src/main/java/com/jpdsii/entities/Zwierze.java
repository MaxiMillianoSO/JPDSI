package com.jpdsii.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "zwierze")
@NamedQueries({
    @NamedQuery(name = "Zwierze.findAll", query = "SELECT z FROM Zwierze z"),
    @NamedQuery(name = "Zwierze.findById", query = "SELECT z FROM Zwierze z WHERE z.id = :id"),
    @NamedQuery(name = "Zwierze.findByGatunek", query = "SELECT z FROM Zwierze z WHERE z.gatunek = :gatunek"),
    @NamedQuery(name = "Zwierze.findByImie", query = "SELECT z FROM Zwierze z WHERE z.imie = :imie"),
    @NamedQuery(name = "Zwierze.findByRasa", query = "SELECT z FROM Zwierze z WHERE z.rasa = :rasa"),
    @NamedQuery(name = "Zwierze.findByWiek", query = "SELECT z FROM Zwierze z WHERE z.wiek = :wiek")
})
public class Zwierze implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "gatunek")
    private String gatunek;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "imie")
    private String imie;

    @Size(max = 100)
    @Column(name = "rasa")
    private String rasa;

    @Basic(optional = false)
    @NotNull
    @Column(name = "wiek")
    private int wiek;

    public Zwierze() {
    }

    public Zwierze(Integer id) {
        this.id = id;
    }

    public Zwierze(Integer id, String gatunek, String imie, int wiek) {
        this.id = id;
        this.gatunek = gatunek;
        this.imie = imie;
        this.wiek = wiek;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGatunek() {
        return gatunek;
    }

    public void setGatunek(String gatunek) {
        this.gatunek = gatunek;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getRasa() {
        return rasa;
    }

    public void setRasa(String rasa) {
        this.rasa = rasa;
    }

    public int getWiek() {
        return wiek;
    }

    public void setWiek(int wiek) {
        this.wiek = wiek;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Zwierze)) {
            return false;
        }
        Zwierze other = (Zwierze) object;
        return !((this.id == null && other.id != null) ||
                 (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "com.jpdsii.entities.Zwierze[ id=" + id + " ]";
    }
}
