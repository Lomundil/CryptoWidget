package jFX.cryptoWidget.util;
import jFX.cryptoWidget.util.*;
import jFX.cryptoWidget.model.*;

public class TestTransfo {
	
	public static void main(String arg[]) {
		CryptoMonnaie cMTest = new CryptoMonnaie("Tron");
		TransformationTxtCryptoObject tr = new TransformationTxtCryptoObject();
		cMTest = tr.transfo(cMTest);
		TestTransfo tt = new TestTransfo();
		tt.afficher(cMTest);
	}
	
	public void afficher(CryptoMonnaie cM) {
		System.out.println(cM.getNomCrypto());
		System.out.println(cM.getPrix());
		System.out.println(cM.getMarketcap());
	}
}
