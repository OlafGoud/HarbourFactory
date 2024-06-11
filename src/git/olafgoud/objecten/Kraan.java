package git.olafgoud.objecten;

import java.util.ArrayList;

import git.olafgoud.HarbourFactoryMain;
import git.olafgoud.utils.Location;

public class Kraan extends Thread{
	private String naam;
	private String vracht;
	private Integer aantal;
	private Integer gewicht;
	private Location location;
	private Integer rate;
	private int alive;
	private ArrayList<Douane> douaneList;
	
	
	public Kraan(String naam, String vracht, Integer aantal, Integer gewicht, Integer rate, ArrayList<Douane> douaneList) {
		this.naam = naam;
		this.vracht = vracht;
		this.setAantal(aantal);
		this.setGewicht(gewicht);
		this.setRate(rate);
		this.alive = 1;
		this.douaneList = douaneList;
	}

	
	@Override
    public void run() { 
		Integer i = 0;
		while (this.alive == 1) {
			
			System.out.println(vracht);
			VrachtWagen wagen = new VrachtWagen("A" + i, vracht, location, aantal, gewicht, 2, douaneList);
			wagen.start();
			HarbourFactoryMain.addTruckListItem(0, wagen);
			try {
				Thread.sleep(rate * 1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			}
			i++;
		}
		System.out.println("kraan removed");
    }
	
	public void removeThread() {
		this.alive = 0;
	}
	

	public String getNaam() {
		return naam;
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


	public void setGewicht(Integer gewicht) {
		this.gewicht = gewicht;
	}


	public Integer getRate() {
		return rate;
	}


	public void setRate(Integer rate) {
		this.rate = rate;
	}
	
}
