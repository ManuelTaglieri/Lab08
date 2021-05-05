package it.polito.tdp.extflightdelays.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {
	
	private Graph<Airport, DefaultWeightedEdge> grafo;
	private Map<String, Rotta> rotte;
	
	public void creaGrafo(double x) {
		
		this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		ExtFlightDelaysDAO dao = new ExtFlightDelaysDAO();
		List<Airport> aeroportiList = dao.loadAllAirports();
		
		Map<Integer, Airport> aeroportiMap = new HashMap<>();
		
		for (Airport a : aeroportiList) {
			aeroportiMap.put(a.getId(), a);
		}
		
		Graphs.addAllVertices(this.grafo, aeroportiList);
		
		List<Flight> voli = dao.loadAllFlights();
		
		this.rotte = new HashMap<>();
		
		for (Flight f : voli) {
			String id1 = f.getDestinationAirportId()+"-"+f.getOriginAirportId();
			String id2 = f.getOriginAirportId()+"-"+f.getDestinationAirportId();
			if (rotte.get(id1)==null && rotte.get(id2)==null) {
				rotte.put(id2, new Rotta(f.getOriginAirportId(), f.getDestinationAirportId(), f.getDistance()));
			} else if (rotte.get(id1)!=null){
				rotte.get(id1).setDistanzaMedia(f.getDistance());
			} else if (rotte.get(id2)!=null) {
				rotte.get(id2).setDistanzaMedia(f.getDistance());
			}
		}
		
		for (Rotta r : rotte.values()) {
			if (r.getDistanzaMedia()>x)
				Graphs.addEdge(this.grafo, aeroportiMap.get(r.getOriginAirportId()), aeroportiMap.get(r.getDestinationAirportId()), r.getDistanzaMedia());
		}
		
	}

	public Graph<Airport, DefaultWeightedEdge> getGrafo() {
		return grafo;
	}
	
	public int getArchi() {
		return this.grafo.edgeSet().size();
	}
	
	public int getVertici() {
		return this.grafo.vertexSet().size();
	}
	

}
