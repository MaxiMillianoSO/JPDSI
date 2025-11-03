package com.jsfcourse.calc;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

@Named
@RequestScoped
//@SessionScoped
public class CalcBB {
	private Double kw;
	private int ok;
        private Double opr;
	private Double result;

	@Inject
	FacesContext ctx;

	public Double getKW() {
		return kw;
	}

	public void setKW(Double kw) {
		this.kw = kw;
	}

	public int getOK() {
		return ok;
	}

	public void setOK(int ok) {
		this.ok = ok;
	}
        public Double getOPR() {
		return opr;
	}

	public void setOPR(Double opr) {
		this.opr = opr;
	}

	public Double getResult() {
		return result;
	}

	public void setResult(Double result) {
		this.result = result;
	}

	public boolean doTheMath() {
		try {
			double n = (opr / 100.0) * ok;
                        double im = ok * 12.0;
                        
                        if (opr == 0) {
                            result = kw / im;
                        } else {
                            result = (kw * n) / im + (kw / im);
                        }
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Operacja wykonana poprawnie", null));
			return true;
		} catch (Exception e) {
			ctx.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd podczas przetwarzania parametrów", null));
			return false;
		}
	}

	
	public String calc() {
		if (doTheMath()) {
			return "showresult";
		}
		return null;
	}

	
	public String calc_AJAX() {
		if (doTheMath()) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Wynik: " + result, null));
		}
		return null;
	}

	public String info() {
		return "info";
	}
}
