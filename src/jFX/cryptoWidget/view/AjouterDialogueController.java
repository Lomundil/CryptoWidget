package jFX.cryptoWidget.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import jFX.cryptoWidget.MainApp;
import jFX.cryptoWidget.model.*;
import jFX.cryptoWidget.util.Connection;
import jFX.cryptoWidget.util.TransformationTxtCryptoObject;

public class AjouterDialogueController {
	
	@FXML
	private TextField nomCrypto;
	
	private Stage addDialogStage;
	
	private CryptoMonnaie cM;
	
	private MainApp mainApp;
	
	private boolean okClicked = false;
	
	/**
	 * Constructeur du controlleur de la fenêtre gérant l'ajout de nouvelle crypto
	 */
	public AjouterDialogueController(){
		
	}
	
	/**
	 * Initialise le constructeur. Cette méthode est appellé automatiquement après que les fichiers FXML ont été chargé.
	 */
	@FXML
	private void initialize() {
		
	}
	
	public void setStage(Stage stage) {
		this.addDialogStage = stage;
	}
	
	public void setCrypto(CryptoMonnaie cM) {
		this.cM = cM;
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	/**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }
	
	@FXML
	private void handleAccepter() {
		if(inputNameCryptoValid()) {
			//Connection au site coinMarketCap pour récupérer les informations
			Connection con = new Connection();
			this.cM.setNomCrypto(nomCrypto.getText());
			con.setURL(this.cM.getNomCrypto());
			con.creationCryptoFile(this.cM.getNomCrypto());
			
			//Transformer le .txt en object Crypto
			TransformationTxtCryptoObject transfo = new TransformationTxtCryptoObject();
			this.cM = transfo.transfo(this.cM);
			
			okClicked = true;
            addDialogStage.close();
		}
		else {
			// Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(addDialogStage);
            alert.setTitle("Crypto non trouvé ou déjà présente");
            alert.setHeaderText("Champ invalide");
            alert.setContentText("Veuillez vérifier le nom de la crypto entrée");

            alert.showAndWait();
		}
	}
	
	private boolean inputNameCryptoValid() {
		//Vérification que le nom existe sur le site coinmarketCap
		Connection con = new Connection();
		return (con.estExistant(nomCrypto.getText()) && !mainApp.getPosition().contains(nomCrypto.getText()));
		
	}
	
	

	/**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        addDialogStage.close();
    }
	
	
	
}
