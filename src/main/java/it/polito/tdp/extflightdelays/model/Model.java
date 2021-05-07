package it.polito.tdp.extflightdelays.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;


import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;



public class Model {
	Graph <Airport, DefaultWeightedEdge> grafo;
	ExtFlightDelaysDAO dao;
	Map <Integer, Airport> idMap;
	int contoVertici;
	int contoArchi;
	
	public Model() {
		dao= new ExtFlightDelaysDAO();
		idMap= dao.loadAllAirports();
		contoVertici=0;
		contoArchi=0;
		
	}
	
	public void creaGrafo(int distanza){
		
		this.grafo= new SimpleWeightedGraph <Airport, DefaultWeightedEdge> (DefaultWeightedEdge.class);
		Graphs.addAllVertices(this.grafo, idMap.values());
		contoVertici= this.idMap.values().size();
		for(Rotta f:dao.loadAllRotta(distanza, idMap)) {
			Graphs.addEdge(this.grafo, f.getPartenza(),f.getArrivo(), f.getDistanza());
			
		}
		
		contoArchi=this.grafo.edgeSet().size();
		
	}

	public int getContoVertici() {
		return contoVertici;
	}

	public void setContoVertici(int contoVertici) {
		this.contoVertici = contoVertici;
	}

	public int getContoArchi() {
		return contoArchi;
	}

	public void setContoArchi(int contoArchi) {
		this.contoArchi = contoArchi;
	}

	public Graph<Airport, DefaultWeightedEdge> getGrafo() {
		return grafo;
	}

	public void setGrafo(Graph<Airport, DefaultWeightedEdge> grafo) {
		this.grafo = grafo;
	}

	public ExtFlightDelaysDAO getDao() {
		return dao;
	}

	public void setDao(ExtFlightDelaysDAO dao) {
		this.dao = dao;
	}

	public Map<Integer, Airport> getIdMap() {
		return idMap;
	}

	public void setIdMap(Map<Integer, Airport> idMap) {
		this.idMap = idMap;
	} 
	
	public List <Rotta> getRotta(){
		List <Rotta> rotte= new ArrayList <Rotta>();
		for(DefaultWeightedEdge e: this.grafo.edgeSet()) {
			rotte.add(new Rotta((float) this.grafo.getEdgeWeight(e),this.grafo.getEdgeSource(e), this.grafo.getEdgeTarget(e)));
		}
		return rotte;
	}
	
	
}

