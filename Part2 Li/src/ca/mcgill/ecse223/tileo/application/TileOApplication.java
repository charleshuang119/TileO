package ca.mcgill.ecse223.tileo.application;

import ca.mcgill.ecse223.tileo.model.TileO;
import ca.mcgill.ecse223.tileo.persistence.PersistenceObjectStream;
import ca.mcgill.ecse223.tileo.view.TileODesignPage;
import ca.mcgill.ecse223.tileo.view.TileOPlayPage;
import ca.mcgill.ecse223.tileo.view.WelcomePage;
import ca.mcgill.ecse223.tileo.model.Game;
import ca.mcgill.ecse223.tileo.model.Game.Mode;
import ca.mcgill.ecse223.tileo.model.Player;

public class TileOApplication {
	private static TileO tileO;
	private static String filename = "data.tileO";
	public static TileODesignPage dp = new TileODesignPage();
	public static TileOPlayPage pp = new TileOPlayPage();
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// start UI
		// TODO startup the UI corresponding to the right mode?
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            	new WelcomePage().setVisible(true);
                if (tileO.hasGames()) {
                	if (getTileO().getCurrentGame().getMode() == Mode.DESIGN){
                    	dp.setVisible(true);       
                    }
                    else {
                    	pp.setVisible(true);
                    }
                }
            }
        });        
	}

	public static TileO getTileO() {
		if (tileO == null) {
			// load model
			tileO = load();
		}
 		return tileO;
	}
	
	public static void save() {
		PersistenceObjectStream.serialize(tileO);
	}
	
	public static TileO load() {
		PersistenceObjectStream.setFilename(filename);
		tileO = (TileO) PersistenceObjectStream.deserialize();
		// model cannot be loaded - create empty TileO
		if (tileO == null) {
			tileO = new TileO();
		}
		return tileO;
	}
	
	public static void setFilename(String newFilename) {
		filename = newFilename;
	}
}