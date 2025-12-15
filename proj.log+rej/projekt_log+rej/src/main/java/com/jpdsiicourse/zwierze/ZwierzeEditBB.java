package com.jpdsiicourse.zwierze;

import java.io.IOException;
import java.io.Serializable;

import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.Flash;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import com.jpdsii.dao.ZwierzeDAO;
import com.jpdsii.entities.Zwierze;

@Named
@ViewScoped
public class ZwierzeEditBB implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final String PAGE_ZWIERZE_LIST = "zwierzeList?faces-redirect=true";
    private static final String PAGE_STAY_AT_THE_SAME = null;

    private Zwierze zwierze = new Zwierze();
    private Zwierze loaded = null;

    @EJB
    ZwierzeDAO zwierzeDAO;

    @Inject
    FacesContext context;

    @Inject
    Flash flash;

    public Zwierze getZwierze() {
        return zwierze;
    }

    public void onLoad() throws IOException {

        // Load object from Flash scope
        loaded = (Zwierze) flash.get("zwierze");

        if (loaded != null) {
            zwierze = loaded;
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błędne użycie systemu", null));
        }
    }

    public String saveData() {

        // No Zwierze object received
        if (loaded == null) {
            return PAGE_STAY_AT_THE_SAME;
        }

        try {
            if (zwierze.getId() == null) {
                // New record
                zwierzeDAO.create(zwierze);
            } else {
                // Existing record
                zwierzeDAO.merge(zwierze);
            }
        } catch (Exception e) {
            e.printStackTrace();
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wystąpił błąd podczas zapisu", null));
            return PAGE_STAY_AT_THE_SAME;
        }

        return PAGE_ZWIERZE_LIST;
    }
}
