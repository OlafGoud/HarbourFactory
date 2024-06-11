package git.olafgoud.objecten;

import java.util.ArrayList;

import git.olafgoud.utils.Location;

public class VrachtWagen extends Thread{
	private String kenteken;
	private String vracht;
	private Integer aantal;
	private Integer gewicht;
	private Location location;
	private Integer rate;
	private ArrayList<Douane> douaneList;
	private boolean alive;
	
	
	public VrachtWagen(String kenteken, String vracht, Location location, Integer aantal, Integer gewicht, Integer rate, ArrayList<Douane> douaneList) {
		this.kenteken = kenteken;
		this.setVracht(vracht);
		this.setAantal(aantal);
		this.setGewicht(gewicht);
		this.location = location;
		this.setDouaneList(douaneList);
		this.setRate(rate);
		this.alive = true;
	}


	
	
	@Override
    public void run() { 
		
		while(alive) {
			
			System.out.println("whaiting");
			
			try {
				Thread.sleep(rate * 1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		System.out.println("vrachtwagen removed");
    }
	
	
	public String getKenteken() {
		return kenteken;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}


	public String getVracht() {
		return vracht;
	}


	public void setVracht(String vracht) {
		this.vracht = vracht;
	}


	public Integer getAantal() {
		return aantal;
	}


	public void setAantal(Integer aantal) {
		this.aantal = aantal;
	}


	public Integer getGewicht() {
		return gewicht;
	}

	
	public void SetDeath() {
		this.alive = false;
	}
	

	public void setGewicht(Integer gewicht) {
		this.gewicht = gewicht;
	}




	public Integer getRate() {
		return rate;
	}




	public void setRate(Integer rate) {
		this.rate = rate;
	}




	public ArrayList<Douane> getDouaneList() {
		return douaneList;
	}




	public void setDouaneList(ArrayList<Douane> douaneList) {
		this.douaneList = douaneList;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
