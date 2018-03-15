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
			//Connection au site coinMarketCap pour r�cup�rer les informations
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
            alert.setTitle("Crypto non trouv� ou d�j� pr�sente");
            alert.setHeaderText("Champ invalide");
            alert.setContentText("Veuillez v�rifier le nom de la crypto entr�e");

            alert.showAndWait();
		}
	}
	
	private boolean inputNameCryptoValid() {
		//V�rification que le nom existe sur le site coinmarketCap
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
