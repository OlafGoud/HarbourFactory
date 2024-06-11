package git.olafgoud.utils;

import java.util.ArrayList;

public class Path {

	
	private ArrayList<String> pathTiles;
	
	public Path(ArrayList<String> tiles) {
		
	}

	public ArrayList<String> getPathTiles() {
		return pathTiles;
	}

	public void setPathTiles(ArrayList<String> pathTiles) {
		this.pathTiles = pathTiles;
	}
	
	public String getPathTile() {
		String path = pathTiles.getFirst();
		this.pathTiles.removeFirst();
		return path;
	}
}
