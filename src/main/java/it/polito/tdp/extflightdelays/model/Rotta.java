package it.polito.tdp.extflightdelays.model;

public class Rotta {
	
	private String idRotta;
	private int originAirportId;
	private int destinationAirportId;
	private double distanzaMedia;
	private int numeroVoli;
	
	public Rotta(int originAirportId, int destinationAirportId, int distanzaMedia) {
		super();
		this.idRotta = originAirportId+"-"+destinationAirportId;
		this.originAirportId = originAirportId;
		this.destinationAirportId = destinationAirportId;
		this.distanzaMedia = distanzaMedia;
		this.numeroVoli = 1;
	}

	public String getIdRotta() {
		return idRotta;
	}

	public double getDistanzaMedia() {
		return distanzaMedia;
	}

	public void setDistanzaMedia(int distanzaMedia) {
		this.distanzaMedia = (distanzaMedia + this.distanzaMedia*this.numeroVoli)/(this.numeroVoli+1);
		this.numeroVoli++;
	}

	public int getOriginAirportId() {
		return originAirportId;
	}

	public int getDestinationAirportId() {
		return destinationAirportId;
	}
	
	

}
