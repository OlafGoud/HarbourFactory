package git.olafgoud.objecten;

import git.olafgoud.HarbourFactoryMain;
import git.olafgoud.utils.Location;
import javafx.application.Platform;

public class Douane extends Thread{
	private int douaneNummer;
	private String vracht;
	private Location location;
	private Integer rate;
	private boolean running;
	
	
	public Douane(int douaneNummer, String vracht, Location location, Integer rate, TruckList list) {
		this.setDouaneNummer(douaneNummer);
		this.location = location;
		this.setRate(rate);
		this.running = true;
		HarbourFactoryMain.addTruckList(new TruckList());
	}


	
	
	@Override
    public void run() { 
		while (running) {
			VrachtWagen wagen = HarbourFactoryMain.getTruckList(douaneNummer).getTruck();
			if(wagen == null) {
				continue;
			}
			wagen.SetDeath();
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					HarbourFactoryMain.getScreen().addMoney(100);
				}
			});
			System.out.println("douane");
			try {
				Thread.sleep(rate * 1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("douane removed");
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




	public Integer getRate() {
		return rate;
	}




	public void setRate(Integer rate) {
		this.rate = rate;
	}




	public int getDouaneNummer() {
		return douaneNummer;
	}




	public void setDouaneNummer(int douaneNummer) {
		this.douaneNummer = douaneNummer;
	}
	
	
	
	
	
	
	
	
	
	
	
}
