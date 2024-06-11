package git.olafgoud.objecten;


import java.util.ArrayList;

import git.olafgoud.HarbourFactoryMain;
import git.olafgoud.utils.AStarSearch;
import git.olafgoud.utils.Location;
import git.olafgoud.utils.PathFinder;

public class Truck extends Thread{
	
	private String naam;
	private Location currentLocation;
	private Location destenationLocation;
	private String vracht;
	private ArrayList<String> array;
	
	public Truck(String naam, Location location, Location destination, String vracht) {
		
		this.setNaam(naam);
		this.setCurrentLocation(location);
		this.setDestenationLocation(destination);
		this.setVracht(vracht);
		
		
		
		
		
		this.array = new ArrayList<>();
		array = AStarSearch.getPath(location, destination);
		
	}
	
	
	@Override
    public void run() { 
		if(this.array == null)  {
			return;
		}
		for(int i = 0; i < array.size(); i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (i != 0) {
				String[] coortsmin1 = ((String)array.get(i - 1)).split("-");
				HarbourFactoryMain.removeTruck(coortsmin1[0], coortsmin1[1]);
			}
			

			String[] coorts = ((String)array.get(i)).split("-");
			if ((HarbourFactoryMain.map.getJSONObject(coorts[0] + "-" + coorts[1])).getString("material").equals("weg")) {
				HarbourFactoryMain.setTruck(coorts[0], coorts[1]);
				if((HarbourFactoryMain.map.getJSONObject(coorts[0] + "-" + coorts[1])).getString("building").equals("onloadpoint")) {
					HarbourFactoryMain.loadFromOnloadPoint(coorts[0] + "-" + coorts[1]);
				}
				
			
			} else {
				array = null;
				array = AStarSearch.getPath(currentLocation, destenationLocation);
				if(array == null) {
					
					break;
				}
			
			}
			
			
			
			
		
		}
				
    }
	
	

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public Location getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(Location currentLocation) {
		this.currentLocation = currentLocation;
	}

	public Location getDestenationLocation() {
		return destenationLocation;
	}

	public void setDestenationLocation(Location destenationLocation) {
		this.destenationLocation = destenationLocation;
	}

	public String getVracht() {
		return vracht;
	}

	public void setVracht(String vracht) {
		this.vracht = vracht;
	}

	
}
