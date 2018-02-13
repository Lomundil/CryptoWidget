package jFX.cryptoWidget.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleFloatProperty;



/**
 *
 * Classe modèle pour une cryptomonnaie.
 * @author Charlie Maréchal
 */

public class CryptoMonnaie {

	private StringProperty nomCrypto;
	private StringProperty siteCrypto;
	private IntegerProperty prix;
	private DoubleProperty marketCap;
	private FloatProperty variation;
	private DoubleProperty volume;
	
/**
 * Construteur par défaut	
 */
	public CryptoMonnaie() {
		this(null);
	}
	
/**
 * Constructeur avec une valeur initiale
 * 
 * @param nomCrypto
 */
	public CryptoMonnaie(String nom) {
		this.nomCrypto = new SimpleStringProperty(nom);
		this.siteCrypto = new SimpleStringProperty("https://api.coinmarketcap.com/v1/ticker/" + nom + "/");
		this.prix = new SimpleIntegerProperty(2);
		this.marketCap = new SimpleDoubleProperty(1500000);
		this.variation = new SimpleFloatProperty(4);
		this.volume = new SimpleDoubleProperty(120000);
		
	}
	
	public String getNomCrypto() {
		return nomCrypto.get();
	}
	
	public void setNomCrypto(String nom) {
		this.nomCrypto.set(nom);
	}
	
	public StringProperty nomCryptoProperty() {
		return nomCrypto;
	}
	
	public int getPrix() {
		return prix.get();
	}
	
	public void setPrix(int prix) {
		this.prix.set(prix);
	}
	
	public IntegerProperty prixProperty() {
		return prix;
	}
	
	public double getMarketcap() {
		return marketCap.get();
	}
	
	public void setMarketcap(double mC) {
		this.marketCap.set(mC);
	}
	
	public DoubleProperty marketCapProperty() {
		return marketCap;
	}
	
	public float getVariation() {
		return variation.get();
	}
	
	public void setVariation(float var) {
		this.variation.set(var);
	}
	
	public FloatProperty variationProperty() {
		return variation;
	}
	
	public double getVolume() {
		return volume.get();
	}
	
	public void setVolume(double vol) {
		this.volume.set(vol);
	}
	
	public DoubleProperty volumeProperty() {
		return volume;
	}
		
		
	
}
