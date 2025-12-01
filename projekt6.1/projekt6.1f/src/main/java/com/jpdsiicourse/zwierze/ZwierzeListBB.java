package com.jpdsiicourse.zwierze;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ejb.EJB;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.Flash;

import com.jpdsii.dao.ZwierzeDAO;
import com.jpdsii.entities.Zwierze;

@Named("zwierzeListBB")
@RequestScoped
public class ZwierzeListBB {

    private String imie;
    private String gatunek;

    @Inject
    ExternalContext extcontext;

    @Inject
    Flash flash;

    @EJB
    ZwierzeDAO zwierzeDAO;

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getGatunek() {
        return gatunek;
    }

    public void setGatunek(String gatunek) {
        this.gatunek = gatunek;
    }

    public List<Zwierze> getFullList() {
        return zwierzeDAO.getFullList();
    }

    public List<Zwierze> getList() {
        Map<String, Object> params = new HashMap<>();

        if (imie != null && !imie.isEmpty()) {
            params.put("imie", imie);
        }

        if (gatunek != null && !gatunek.isEmpty()) {
            params.put("gatunek", gatunek);
        }

        return zwierzeDAO.getList(params);
    }

    public String newZwierze() {
        Zwierze z = new Zwierze();
        flash.put("zwierze", z);
        return "zwierzeEdit?faces-redirect=true";
    }

    public String editZwierze(Zwierze z) {
        flash.put("zwierze", z);
        return "zwierzeEdit?faces-redirect=true";
    }

    public String deleteZwierze(Zwierze z) {
        zwierzeDAO.remove(z);
        return null;
    }
}
