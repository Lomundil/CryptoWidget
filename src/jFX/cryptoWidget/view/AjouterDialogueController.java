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
	 * Constructeur du controlleur de la fen�tre g�rant l'ajout de nouvelle crypto
	 */
	public AjouterDialogueController(){
		
	}
	
	/**
	 * Initialise le constructeur. Cette m�thode est appell� automatiquement apr�s que les fichiers FXML ont �t� charg�.
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
			//Connection au site coinMarketCap pour r�cup�rer les informations
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
            alert.setTitle("Crypto non trouv�");
            alert.setHeaderText("Champ invalide");
            alert.setContentText("Veuillez v�rifier le nom de la crypto entr�e");

            alert.showAndWait();
		}
	}
	
	private boolean inputNameCryptoValid() {
		//V�rification que le nom existe sur le site coinmarketCap
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
