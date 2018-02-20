package jFX.cryptoWidget;
import jFX.cryptoWidget.model.*;
import jFX.cryptoWidget.view.*;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;



public class MainApp extends Application {

	private Stage primaryStage;
	private ScrollPane rootScrollPane;
	private BorderPane rootLayout;
	private VBox gestionCrypto;
	
	/**
	 * Les données des cryptos comme une liste de cryptos observables
	 */
	private ObservableList<CryptoMonnaie> cryptoData = FXCollections.observableArrayList();
	
	/**
	 * Constructeur
	 */
	public MainApp() {
		cryptoData.add(new CryptoMonnaie("Bitcoin"));
		cryptoData.add(new CryptoMonnaie("Ethereum"));
	}
	
	public ObservableList<CryptoMonnaie> getCryptoData(){
		return cryptoData;
	}
	/**
	 * Initialisation du rootLayout 
	 */
	public void initRootLayout() {
		try {
            // Chargement du rootLayout de notre application
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootCryptoWidget.fxml"));
            rootLayout = (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
        } 
	}
	
	public void initRootScrollPane() {
		try {
            // Chargement du rootLayout de notre application
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootScrollPane.fxml"));
            rootScrollPane = (ScrollPane) loader.load();
            
            rootLayout.setCenter(rootScrollPane);
           
        } catch (IOException e) {
            e.printStackTrace();
        } 
	}
	
	/**
     * Shows the GestionCrypto inside the root layout.
     */
    public void showGestionCrypto() {
        try {
            // Load gestionCrypto.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/GestionCrypto.fxml"));
            gestionCrypto = (VBox) loader.load();

            // Set person overview into the center of root layout.
            rootScrollPane.setContent(gestionCrypto);   

            // Give the controller access to the main app.
            GestionCryptoController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showFicheCrypto(int index) {
    	try {
    		// Load ficheCrypto.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/FicheCrypto.fxml"));
            AnchorPane ficheCrypto = (AnchorPane) loader.load();

            // Set gestionCrypto into the center of root layout.
            gestionCrypto.getChildren().add(ficheCrypto);

            // Give the controller access to the main app.
            FicheCryptoController controller = loader.getController();
            controller.setMainApp(this, this.getCryptoData().get(index));
            

        } catch (IOException e) {
            e.printStackTrace();
    	}
    }
	
    public boolean showAjouterDialogController(CryptoMonnaie cM) {
    	try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/AjouterDialogue.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("AjouterCrypto");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            AjouterDialogueController controller = loader.getController();
            controller.setStage(dialogStage);
            controller.setCrypto(cM);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("CryptoWidget");
		
		/**
		 * Ajout d'une icône pour notre application
		 */
		
		this.primaryStage.getIcons().add(new Image("file:resources/images/cryptoWidgetIcone.png"));
		
		initRootLayout();
		initRootScrollPane();
		showGestionCrypto();
		//Affichage de toute les cryptos en mémoire
		//Prévoir une sauvegarde
		for(int index = 0; index<this.getCryptoData().size(); index++) {
			showFicheCrypto(index);
		}
		
		
		
	}
	

	public static void main(String[] args) {
		launch(args);
	}
}
