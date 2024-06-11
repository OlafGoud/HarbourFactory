package git.olafgoud.objecten;

import java.util.ArrayList;

import git.olafgoud.utils.PathFinder;

public class TruckList {
	
	private ArrayList<Truck> truckList;
	private PathFinder path;
	
	public TruckList(PathFinder pathFinder) {
		this.path = pathFinder;
	}
}
