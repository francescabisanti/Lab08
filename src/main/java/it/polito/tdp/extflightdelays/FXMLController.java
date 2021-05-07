/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.extflightdelays;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import org.jgrapht.graph.DefaultWeightedEdge;

import it.polito.tdp.extflightdelays.model.Flight;
import it.polito.tdp.extflightdelays.model.Model;
import it.polito.tdp.extflightdelays.model.Rotta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML // fx:id="distanzaMinima"
    private TextField distanzaMinima; // Value injected by FXMLLoader

    @FXML // fx:id="btnAnalizza"
    private Button btnAnalizza; // Value injected by FXMLLoader

    @FXML
    void doAnalizzaAeroporti(ActionEvent event) {
    	String input= this.distanzaMinima.getText();
    	int distanza=0;
    	try {
    		distanza=Integer.parseInt(input);
    	}catch(NumberFormatException e) {
    		this.txtResult.setText("Inserisci un numero corretto");
    		return;
    	}
    	this.model.creaGrafo(distanza);
    	String result="";
    	result=result+"Il numero di vertici e': "+this.model.getContoVertici()+"\n";
    	result= result+ "Il numero di archi e': "+this.model.getContoArchi()+"\n";
    	List <Flight> voli= this.model.getDao().loadAllFlights(distanza);
    	List <String> ciao= new ArrayList <String>();
    	/*Map<Integer,Flight> mappaVoli=new HashMap<Integer,Flight>();
    	for(Flight f1:this.model.getDao().loadAllFlights(distanza))
    	{
    		//if(!mappaVoli.containsKey(f1.getOriginAirportId()) )//|| (mappaVoli.containsKey(f1.getOriginAirportId()) && mappaVoli.get(f1.getId()).getDestinationAirportId()!=f1.getDestinationAirportId()))
    		if(!mappaVoli.containsKey(f1.getId())||(mappaVoli.containsKey(f1.getId())&&(mappaVoli.get(f1.getId()).getOriginAirportId()==f1.getOriginAirportId()))&&(mappaVoli.get(f1.getId()).getDestinationAirportId()!=f1.getDestinationAirportId()))
    		{
    			mappaVoli.put(f1.getOriginAirportId(), f1);
    		}
    	}
    	for(Flight f:mappaVoli.values())
    	{
    		result+=this.model.getIdMap().get(f.getOriginAirportId()).getAirportName()+"-"+this.model.getIdMap().get(f.getDestinationAirportId()).getAirportName()+" "+f.getDistance()+"\n";
    	}*/
    	/*for(Flight f:voli)
    	{
    		String r=this.model.getIdMap().get(f.getOriginAirportId()).getAirportName()+"-"+this.model.getIdMap().get(f.getDestinationAirportId()).getAirportName()+" "+f.getDistance()+"\n";;
    		if(!ciao.contains(r))
    			ciao.add(r);
    		
    	}
    	for(String s: ciao)
    		result=result+s;*/
    	for(Rotta r: this.model.getRotta()) {
    		result=result+r.getPartenza().getAirportName()+" - "+r.getArrivo().getAirportName()+" : "+r.getDistanza()+" \n";
    	}
    	 this.txtResult.setText(result);
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert distanzaMinima != null : "fx:id=\"distanzaMinima\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnAnalizza != null : "fx:id=\"btnAnalizza\" was not injected: check your FXML file 'Scene.fxml'.";
    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
}
