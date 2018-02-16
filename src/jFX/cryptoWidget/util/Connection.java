package jFX.cryptoWidget.util;

import java.io.*;
import java.net.*;
import jFX.cryptoWidget.model.*;



/**
 * Classe permettant de se connecter à une adresse web et de récupérer des infos
 * 
 * @author Charlie
 *
 */
public class Connection {


	private BufferedWriter fileout;
	private BufferedReader inputReader;
	private URL url;
	


	/**
	 * Constructeur par défaut de la classe Connection
	 */
	public Connection() {
		
	}
	
	/**
	 * Methode set pour l'url
	 * @param nomCrypto
	 */
	public void setURL(String nomCrypto) {
		try {
			this.url = new URL("https://api.coinmarketcap.com/v1/ticker/" + nomCrypto+ "/");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Methode permettant de vérifier l'url donné en paramètre
	 * 
	 * @param urlCrypto
	 * @return false si l'url n'est pas valide ou si la connection ne peut être établie ou
	 *  true si tout est bon
	 */
	public boolean estExistant(String urlCrypto) {
		try {
		    URL url = new URL(urlCrypto);
		    URLConnection conn = url.openConnection();
		    conn.connect();
		} catch (MalformedURLException e) {
		    // L'url donné n'est pas valide
			return false;
		} catch (IOException e) {
		    // La connection n'a pas pu être établie
			return false;
		}
		return true;
	}
	
	/**
	 * Méthode créant un fichier txt avec toute les données du site coinMarketCap
	 * @param nomCrypto
	 */
	public void creationCryptoFile(String nomCrypto) {
		try {
			fileout = new BufferedWriter(new FileWriter("resources/cryptoFile/" + nomCrypto +".txt"));
			URLConnection con=url.openConnection();
			System.out.println(con.getContent());
		    InputStream input = con.getInputStream();
		    inputReader = new BufferedReader(new InputStreamReader(input));
		    String sCurrentLine;
		    while((sCurrentLine = inputReader.readLine()) != null) {
		    	
		    	fileout.write(sCurrentLine);
		    	fileout.newLine();
		    }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
			if (fileout != null)

				fileout.close();

	        } catch (IOException e) {

	        	e.printStackTrace();

	        }
		}
	}
}
