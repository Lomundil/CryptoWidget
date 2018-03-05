package jFX.cryptoWidget.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

import jFX.cryptoWidget.model.*;

/**
 * Classe permettant de transférer les informations des fichiers.txt dans les objets CryptoMonnaies.
 * 
 * @author Charlie
 *
 */
public class TransformationTxtCryptoObject {

	private BufferedReader br;

	/**
	 * Constructeur par défaut
	 */
	public TransformationTxtCryptoObject() {
		
	}
	
	public CryptoMonnaie transfo(CryptoMonnaie cM) {
		
		try {
			br = new BufferedReader(new FileReader("resources/cryptoFile/"+ cM.getNomCrypto() + ".txt"));
			int nbrLine = 0;
			String sCurrentLine;
		    while((sCurrentLine = br.readLine()) != null) {
		    	nbrLine++;
		    	switch(nbrLine) {
		    		
		    		case 7: String prix = this.recupInfo(sCurrentLine);
		    				cM.setPrix(Double.parseDouble(prix));
		    				break;
		    		case 9: String volume = this.recupInfo(sCurrentLine);
		    				cM.setVolume(Double.parseDouble(volume));
		    				break;
		    		case 10: String marketCap = this.recupInfo(sCurrentLine);
		    				 cM.setMarketcap(Double.parseDouble(marketCap));
		    				 break;
		    		case 15: String variation24 = this.recupInfo(sCurrentLine);
		    				 cM.setVariation(Float.parseFloat(variation24));
		    				 break;
		    		default: break;
		    	}
		    }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return cM;
	}
	
	private String recupInfo(String line) {
		String info = new String();
		StringReader sr = new StringReader(line);
		int compteurGuil = 0;
		char guillemet = '"';
		int index1 = 0, index2 = 0;
		try {
			for(int endLine = 0,index = 0; endLine != -1; endLine = sr.read())  {
				if(endLine == guillemet ) {
					
					compteurGuil++;
					if(compteurGuil == 3) {
						index1=index;
						index++;
					}
					else if(compteurGuil == 4) {
						index2=index-1;
						break;
					}
					else {
						index++;					
					}
				}
				else {
					index++;
				}		
						 
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		info = line.substring(index1, index2);
		return info;
	}
	
}
