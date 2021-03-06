package jFX.cryptoWidget.util;

import java.io.*;
import java.net.*;
import jFX.cryptoWidget.model.*;



/**
 * Classe permettant de se connecter � une adresse web et de r�cup�rer des infos
 * 
 * @author Charlie
 *
 */
public class Connection {


	private BufferedWriter fileout;
	private BufferedReader inputReader;
	private URL url;
	


	/**
	 * Constructeur par d�faut de la classe Connection
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
	 * Methode permettant de v�rifier l'url donn� en param�tre
	 * 
	 * @param urlCrypto
	 * @return false si l'url n'est pas valide ou si la connection ne peut �tre �tablie ou
	 *  true si tout est bon
	 */
	public boolean estExistant(String urlCrypto) {
		try {
		    this.setURL(urlCrypto);
		    URLConnection conn = url.openConnection();
		    conn.connect();
		    //V�rification de la taille du contenu de la page pour v�rifier que l'url est valide
		    if (conn.getContentLength() < 50) {
		    	return false;
		    }
		    else {
		    	return true;
		    }
		} catch (SocketTimeoutException e) {
			return false;
		} catch (IOException e) {
			System.out.println(e.getClass());
			return false;
		}	
	}
	
	/**
	 * M�thode cr�ant un fichier txt avec toute les donn�es du site coinMarketCap
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
