package it.polito.tdp.extflightdelays.model;

public class Rotta {
	private float distanza;
	private Airport partenza;
	private Airport arrivo;
	public Rotta(float distanza, Airport partenza, Airport arrivo) {
		super();
		this.distanza = distanza;
		this.partenza = partenza;
		this.arrivo = arrivo;
	}
	public float getDistanza() {
		return distanza;
	}
	public void setDistanza(float distanza) {
		this.distanza = distanza;
	}
	public Airport getPartenza() {
		return partenza;
	}
	public void setPartenza(Airport partenza) {
		this.partenza = partenza;
	}
	public Airport getArrivo() {
		return arrivo;
	}
	public void setArrivo(Airport arrivo) {
		this.arrivo = arrivo;
	}
	
	
	
	
	
}
