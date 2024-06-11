package git.olafgoud.objecten;

public class OnLoadPoint extends Thread{
	
	private String location;
	private Integer rate;
	
	
	public OnLoadPoint(String location, Integer rate) {
		setLocation(location);
		setRate(rate);
	}

	@Override
	public void run() {
		try {
			Thread.sleep(1000 * rate);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public Integer getRate() {
		return rate;
	}


	public void setRate(Integer rate) {
		this.rate = rate;
	}
	
	
}
