package git.olafgoud;

import java.util.ArrayList;

import org.json.JSONObject;

import git.olafgoud.gui.scenes.MainGameScreen;
import git.olafgoud.objecten.OnLoadPoint;
import git.olafgoud.objecten.Truck;
import git.olafgoud.objecten.TruckList;
import git.olafgoud.utils.Location;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.LoadListener;
import javafx.stage.Stage;

public class HarbourFactoryMain extends Application{

	private static ArrayList<TruckList> allTruckList;
	private static MainGameScreen screen;
	public static JSONObject map;
	
	private static ArrayList<OnLoadPoint> loadList;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		allTruckList = new ArrayList<TruckList>();
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {

		MainGameScreen screen = new MainGameScreen(stage, this);

		
		screen.setMainScreen();
		setScreen(screen);
		HarbourFactoryMain.screen = screen;

		
	}
	
	
	
	
	

	public static MainGameScreen getScreen() {
		return screen;
	}

	public static void setScreen(MainGameScreen screen1) {
		screen = screen1;
	}

	
	public static void startGame() {
		Truck truck = new Truck("asfd", new Location(5, 0), new Location(19, 14), "asdf");
		truck.start();
		
	}
	
	public static void setTruck(String col, String row) {
		
		
		int row1 = Integer.valueOf(row);
		int col1 = Integer.valueOf(col);
		
		Platform.runLater(new Runnable() {
		    @Override
		    public void run() {
		    	screen.setVrachtwagen(row1, col1);		    }
		});
		
	}
	
	public static void removeTruck(String col, String row) {
		int row1 = Integer.valueOf(row);
		int col1 = Integer.valueOf(col);
		
		Platform.runLater(new Runnable() {
		    @Override
		    public void run() {
		    	screen.removeTruck(row1, col1);		    
		    }
		});
		
	}

	public static void loadFromOnloadPoint(String location) {
		loadList.forEach(p -> {
			if(p.getLocation().equals(location)) {
				
			}
		});
	}

	
	

}
