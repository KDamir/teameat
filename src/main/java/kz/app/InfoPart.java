package kz.app;

public class InfoPart {
	private String namePart;
	private Double ves_chasti = 0.0;
	private Double cena_za_kg = 0.0;
	private Double totalCoast = 0.0;
	
	public String getNamePart() {
		return namePart;
	}
	public void setNamePart(String namePart) {
		this.namePart = namePart;
	}
	public Double getVes_chasti() {
		return ves_chasti;
	}
	public void setVes_chasti(Double ves_chasti) {
		this.ves_chasti = ves_chasti;
	}
	public Double getCena_za_kg() {
		return cena_za_kg;
	}
	public void setCena_za_kg(Double cena_za_kg) {
		this.cena_za_kg = cena_za_kg;
	}
	public Double getTotalCoast() {
		return totalCoast;
	}
	public void setTotalCoast(Double totalCoast) {
		this.totalCoast = totalCoast;
	}
}
