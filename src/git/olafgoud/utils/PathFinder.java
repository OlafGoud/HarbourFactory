package git.olafgoud.utils;

import java.util.ArrayList;

import org.json.JSONObject;

import git.olafgoud.HarbourFactoryMain;

public class PathFinder extends Thread{

	private Location beginLoc;
	private Location eindLoc;
	private ArrayList<String> list;
	
	
	public PathFinder(Location beginLoc, Location eindLoc) {
		this.beginLoc = beginLoc;
		this.eindLoc = eindLoc;
		
		
				
		
		
		
	}
	
	
	@Override
	public void run() {
		JSONObject map = HarbourFactoryMain.map;
		
		Integer verschilY = -(beginLoc.getY() - eindLoc.getY());
		Integer verschilX = -(beginLoc.getX() - eindLoc.getX());
		Integer directionX = (int) (verschilX / Math.sqrt(Math.pow(verschilX, 2)));
		Integer directionY = (int) (verschilY / Math.sqrt(Math.pow(verschilY, 2)));
		Integer currentX = beginLoc.getX();
		Integer currentY = beginLoc.getY();
		System.out.println(directionX + ", "+ directionY);
		ArrayList<Location> locList = new ArrayList<>();
		for(int i = 0; i < 100; i++) {
			if (beginLoc.equals(eindLoc)) {
				System.out.println("Is same tile");
				break;
			}
			if (currentX == eindLoc.getX() && currentY == eindLoc.getY()) {
				System.out.println("Location Found");
				break;
			}
			verschilY = -(currentY - eindLoc.getY());
			verschilX = -(currentX - eindLoc.getX());
			if (map.getJSONObject((currentY + directionY)+ "-" + currentX).getString("material").equals("weg") && !(verschilY == 0)) {
				currentY = currentY + directionY;
				locList.add(new Location(currentX, currentY));
			} else if (map.getJSONObject((currentY)+ "-" + (currentX + directionX)).getString("material").equals("weg") && !(verschilX == 0)) {
				currentX = currentX + directionX;
				locList.add(new Location(currentX, currentY));
			} else if (map.getJSONObject((currentY - directionY)+ "-" + currentX).getString("material").equals("weg") && !(verschilY == 0)) {
				currentY = currentY - directionY;
				locList.add(new Location(currentX, currentY));
			} else if (map.getJSONObject((currentY)+ "-" + (currentX - directionX)).getString("material").equals("weg") && !(verschilX == 0)) {
				currentX = currentX - directionX;
				locList.add(new Location(currentX, currentY));
			} else {
				System.out.println("No path found");

				break;
			}
		}
		System.out.println(locList.toString());
		this.list = new ArrayList<>();
		for (int i = 0; i < locList.size(); i++) {
			Location loc = locList.get(i);
			
			
			this.list.add(loc.getY() + "-" + loc.getX());
		}
		
		
	}


	public ArrayList<String> getList() {
		return this.list;
	}
	
	
	
	
}
