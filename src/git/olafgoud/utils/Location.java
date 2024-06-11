package git.olafgoud.utils;

public class Location {
	
	private Integer x;
	private Integer y;

	
	
	
	
	public Location(Integer x, Integer y) {
		this.setX(x);
		this.setY(y);

	}


	public Integer getY() {
		return y;
	}




	public void setY(Integer y) {
		this.y = y;
	}




	public Integer getX() {
		return x;
	}
	
	@Override
	public String toString() {
	return "X: " + x + " ,Y: " + y;
	}


	public void setX(Integer x) {
		this.x = x;
	}
}
