package de.unidue.inf.is.domain;

import java.text.DecimalFormat;

public final class Project {
	private String titel;
	private String beschreibung;
	private String status;
	private float finanzierungsLimit;
	private String ersteller;
	private int kategorie;
	private float spendenbetrag;
	
	public Project(String title, String beschreibung, String status, float finanzierungsLimit, String ersteller, int kategorie, float spendenbetrag) 
	{
		this.titel = title;
		this.beschreibung = beschreibung;
		this.status = status;
		this.finanzierungsLimit = finanzierungsLimit;
		this.ersteller = ersteller;
		this.kategorie = kategorie;
		this.spendenbetrag = spendenbetrag;
	}

	public String getTitel() {
		return titel;
	}

	public String getStatus() {
		return status;
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public float getFinanzierungsLimit() {
		return finanzierungsLimit;
	}

	public String getErsteller() {
		return ersteller;
	}

	public int getKategorie() {
		return kategorie;
	}

	public String getSpendenbetrag() {
		DecimalFormat decimalFormat = new DecimalFormat("0.00");
		return decimalFormat.format(spendenbetrag);
	}
}