package kz.app;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class MeatPart {
	
	private String name;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private Double ves = 0.0;
	private Double prod_cena = 0.0;
	private Double ves_chasti = 0.0;
	private Double cena_za_kg = 0.0;
	private Double proc_ot_vesa = 0.0;
	private Double suma_prodaj = 0.0;

	public Double getSuma_prodaj() {
		return suma_prodaj;
	}

	public void setSuma_prodaj(Double suma_prodaj) {
		this.suma_prodaj = suma_prodaj;
	}

	public Double getProc_ot_vesa() {
		return proc_ot_vesa;
	}

	public void setProc_ot_vesa(Double proc_ot_vesa) {
		this.proc_ot_vesa = proc_ot_vesa;
	}

	public Double getCena_za_kg() {
		return cena_za_kg;
	}

	public void setCena_za_kg(Double cena_za_kg) {
		this.cena_za_kg = cena_za_kg;
	}

	public Double getWeight() {
		return ves;
	}

	public void setWeight(Double weight) {
		this.ves = weight;
	}

	public Double getProd_cena() {
		return prod_cena;
	}

	public void setProd_cena(Double prod_cena) {
		this.prod_cena = prod_cena;
	}

	public Double getVes_chasti() {
		return ves_chasti;
	}

	public void setVes_chasti(Double ves_chasti) {
		this.ves_chasti = ves_chasti;
	}
	
	//public MeatPart(name,ves, prod_cena, ves_chasti, cena_za_kg, proc_ot_vesa, suma_prod) {
/*	@PostConstruct
	public void init() {
		this.name = "qwe";
		this.ves = 12.2;
		this.prod_cena = 12.2;
		this.ves_chasti = 12.2;
		this.cena_za_kg = 12.2;
		this.proc_ot_vesa = 12.2;
		this.suma_prodaj = 12.2;
	}*/
	
}
