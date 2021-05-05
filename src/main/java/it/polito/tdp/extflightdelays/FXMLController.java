/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.extflightdelays;

import java.net.URL;
import java.util.ResourceBundle;

import org.jgrapht.graph.DefaultWeightedEdge;

import it.polito.tdp.extflightdelays.model.Model;
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
    	
    	txtResult.clear();
    	
    	if (distanzaMinima.getText()==null || distanzaMinima.getText().equals("")) {
    		txtResult.setText("Inserire un valore di distanza minima.");
    	} else {
    		try {
    			
    			double distanzaMin = Double.parseDouble(distanzaMinima.getText());
    			
    			this.model.creaGrafo(distanzaMin);
    			
    			txtResult.appendText("Numero di aeroporti: "+this.model.getVertici()+"\n");
    			txtResult.appendText("Numero di rotte: "+this.model.getArchi()+"\n");
    			
    			txtResult.appendText("Elenco rotte:\n");
    			for (DefaultWeightedEdge r : this.model.getGrafo().edgeSet()) {
    				txtResult.appendText(r.toString()+"; Distanza media: "+this.model.getGrafo().getEdgeWeight(r)+"\n");
    			}
    			
    			
    		} catch (NumberFormatException e) {
    			txtResult.setText("Inserire solo numeri nel campo distanza minima.");
    			return;
    		}
    	}
    	
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
