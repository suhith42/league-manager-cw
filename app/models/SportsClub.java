package models;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class SportsClub implements Serializable{
	private int clubId;
	private String name;
	private String address;

	public SportsClub(String name, String address) {
		this.name = name;
		this.address = address;
	}

	public SportsClub(){}

	public int getClubId() {
		return clubId;
	}

	public void setClubId(int clubId) {
		this.clubId = clubId;
	}



	public String getName(){
		return name;
	}

	public String getAddress(){
		return address;
	}

	public void setName(String name){
		this.name = name;
	}

	public void setAddress(String address){
		this.address = address;
	}

	@Override
	public boolean equals(Object obj) {
		SportsClub sc = (SportsClub) obj;
		if(this.getName().equals(sc.name) && this.getAddress().equals(sc.address)){
			return true;
		}else{
			return false;
		}
	}
}