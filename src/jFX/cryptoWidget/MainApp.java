package jFX.cryptoWidget;
import jFX.cryptoWidget.model.*;
import jFX.cryptoWidget.util.Connection;
import jFX.cryptoWidget.util.TestTransfo;
import jFX.cryptoWidget.util.TransformationTxtCryptoObject;
import jFX.cryptoWidget.view.*;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
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
	private BufferedWriter fileout;
	private BufferedReader filein;
	
	/**
	 * Les données des cryptos comme une liste de cryptos observables
	 */
	private ObservableList<CryptoMonnaie> cryptoData = FXCollections.observableArrayList();
	
	/**
	 * Constructeur
	 */
	public MainApp() {
		
		try {
			File f = new File("resources/cryptoFile/Sauvegarde.txt");
			if(f.exists()) {
				recupSauvegarde();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ObservableList<CryptoMonnaie> getCryptoData(){
		return this.cryptoData;
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

            // Set gestionCrypto into the center of root layout.
            rootScrollPane.setContent(gestionCrypto);   
            
            // Give the controller access to the main app.
            GestionCryptoController controller = loader.getController();
            controller.setMainApp(this);
            controller.actuFiche();
            

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
    
    public void showFicheCrypto(CryptoMonnaie cM) {
    	try {
    		// Load ficheCrypto.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/FicheCrypto.fxml"));
            AnchorPane ficheCrypto = (AnchorPane) loader.load();

            // Set gestionCrypto into the center of root layout.
            gestionCrypto.getChildren().add(ficheCrypto);

            // Give the controller access to the main app.
            FicheCryptoController controller = loader.getController();
            controller.setMainApp(this, cM);
            

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
	
    /*
     * Sauvegarde des cryptos ajoutés par l'utilisateur
     */
    private void sauvegardeCrypto() throws IOException {
    	try {
			fileout = new BufferedWriter(new FileWriter("resources/cryptoFile/Sauvegarde.txt"));
			for(int index = 0; index < this.getCryptoData().size(); index++) {
				fileout.write(this.getCryptoData().get(index).getNomCrypto());
				fileout.newLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			fileout.close();
		}
    }
    
    /*
     * Récupération des cryptos mis en sauvegarde
     */
    private void recupSauvegarde() throws IOException {
    	TransformationTxtCryptoObject tr = new TransformationTxtCryptoObject();
    	try {
    		filein = new BufferedReader(new FileReader("resources/cryptoFile/Sauvegarde.txt"));
    		String strNom;
    		while((strNom = filein.readLine()) != null) {
    			CryptoMonnaie cMTemp = new CryptoMonnaie(strNom);
    			Connection co = new Connection();
    			co.setURL(strNom);
    			co.creationCryptoFile(strNom);
    			cMTemp = tr.transfo(cMTemp);
    			this.getCryptoData().add(cMTemp);
    		}
    	} catch(FileNotFoundException fe) {
    		fe.printStackTrace();
    	} catch(IOException e) {
    		System.out.println(e);
    	} finally {
    		filein.close();
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
		System.out.println("test3");
		//Affichage de toute les cryptos en mémoire
		//Prévoir une sauvegarde
		for(int index = 0; index<this.getCryptoData().size(); index++) {
			showFicheCrypto(index);
		}
		
		this.primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent e) {
				try {
					sauvegardeCrypto();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
	}
	
	

	public static void main(String[] args) {
		launch(args);
	}
}
