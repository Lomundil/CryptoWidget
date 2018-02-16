package jFX.cryptoWidget.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import jFX.cryptoWidget.model.*;
import jFX.cryptoWidget.util.Connection;

public class AjouterDialogueController {
	
	@FXML
	private TextField nomCrypto;
	
	private Stage addDialogStage;
	
	private CryptoMonnaie cM;
	
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
			CryptoMonnaie cryptoTemp = new CryptoMonnaie();
			cryptoTemp.setNomCrypto(nomCrypto.getText());
			con.setURL(cryptoTemp.getNomCrypto());
			con.creationCryptoFile(cryptoTemp.getNomCrypto());
		}
		else {
			// Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(addDialogStage);
            alert.setTitle("Crypto non trouvé");
            alert.setHeaderText("Champ invalide");
            alert.setContentText("Veuillez vérifier le nom de la crypto entrée");

            alert.showAndWait();
		}
	}
	
	private boolean inputNameCryptoValid() {
		//Vérification que le nom existe sur le site coinmarketCap
		Connection con = new Connection();
		return (con.estExistant(nomCrypto.getText()));
		
	}

	/**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        addDialogStage.close();
    }
	
	
	
}
