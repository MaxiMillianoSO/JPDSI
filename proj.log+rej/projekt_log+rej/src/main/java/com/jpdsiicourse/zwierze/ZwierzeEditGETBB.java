 package com.jpdsiicourse.zwierze;

import java.io.IOException;
import java.io.Serializable;

import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import com.jpdsii.dao.ZwierzeDAO;
import com.jpdsii.entities.Zwierze;

@Named
@ViewScoped
public class ZwierzeEditGETBB implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final String PAGE_ZWIERZE_LIST = "zwierzeList?faces-redirect=true";
    private static final String PAGE_STAY_AT_THE_SAME = null;

    private Zwierze zwierze = new Zwierze();
    private Zwierze loaded = null;

    @Inject
    FacesContext context;

    @EJB
    ZwierzeDAO zwierzeDAO;

    public Zwierze getZwierze() {
        return zwierze;
    }

    public void onLoad() throws IOException {
        if (!context.isPostback()) {
            if (!context.isValidationFailed() && zwierze.getId() != null) {
                loaded = zwierzeDAO.find(zwierze.getId());
            }

            if (loaded != null) {
                zwierze = loaded;
            } else {
                context.addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błędne użycie systemu", null)
                );
            }
        }
    }

    public String saveData() {
        if (loaded == null) { // niepoprawne wywołanie
            return PAGE_STAY_AT_THE_SAME;
        }

        try {
            if (zwierze.getId() == null) {
                // Nowe zwierzę
                zwierzeDAO.create(zwierze);
            } else {
                // Edycja
                zwierzeDAO.merge(zwierze);
            }
        } catch (Exception e) {
            e.printStackTrace();
            context.addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wystąpił błąd podczas zapisu", null)
            );
            return PAGE_STAY_AT_THE_SAME;
        }

        return PAGE_ZWIERZE_LIST;
    }
}
