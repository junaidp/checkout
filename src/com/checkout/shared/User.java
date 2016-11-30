package com.checkout.shared;

import java.io.Serializable;

public class User implements Serializable{
	String Vorname;
	String Nachname;
	String Firmenname;
	String EmailAdresse;
	String Strabe;
	String Hausnr;
	String PLZ;
	String ORT;
	int LandID;

	public User(){
		this.Vorname = "";
		this.Nachname = "";
		this.Firmenname = "";
		this.EmailAdresse = "";
		this.Strabe = "";
		this.Hausnr = "";
		this.PLZ = "";
		this.ORT = "";
		this.LandID = 0;
	}

	public User(String Vorname,String Nachname,String Firmenname,String EmailAdresse,String Strabe,
			String Hausnr,String PLZ,String ORT,int LandID){

		if(Vorname==null)this.Vorname = "";
		this.Vorname = Vorname;

		if(Nachname==null)this.Nachname = "";
		this.Nachname = Nachname;

		if(Firmenname==null)this.Firmenname = "";
		this.Firmenname = Firmenname;

		if(EmailAdresse==null)this.EmailAdresse = "";
		this.EmailAdresse = EmailAdresse;

		if(Strabe==null)this.Strabe = "";
		this.Strabe = Strabe;

		if(Hausnr==null)this.Hausnr = "";
		this.Hausnr = Hausnr;

		if(PLZ==null)this.PLZ = "";
		this.PLZ = PLZ;

		if(ORT==null)this.ORT = "";
		this.ORT = ORT;

		this.LandID = LandID;
	}

	public String getVorname() {
		return Vorname;
	}

	public void setVorname(String vorname) {
		if(vorname==null)this.ORT = "";
		Vorname = vorname;
	}

	public String getNachname() {
		return Nachname;
	}

	public void setNachname(String nachname) {
		if(nachname==null)this.ORT = "";
		Nachname = nachname;
	}

	public String getFirmenname() {
		return Firmenname;
	}

	public void setFirmenname(String firmenname) {
		if(firmenname==null)this.ORT = "";
		Firmenname = firmenname;
	}

	public String getEmailAdresse() {
		return EmailAdresse;
	}

	public void setEmailAdresse(String emailAdresse) {
		if(emailAdresse==null)this.ORT = "";
		EmailAdresse = emailAdresse;
	}

	public String getStrabe() {
		return Strabe;
	}

	public void setStrabe(String strabe) {
		if(strabe==null)this.ORT = "";
		Strabe = strabe;
	}

	public String getHausnr() {
		return Hausnr;
	}

	public void setHausnr(String hausnr) {
		if(hausnr==null)this.Hausnr = "";
		Hausnr = hausnr;
	}

	public String getPLZ() {
		return PLZ;
	}

	public void setPLZ(String pLZ) {
		if(pLZ==null)this.PLZ = "";
		PLZ = pLZ;
	}

	public String getORT() {
		return ORT;
	}

	public void setORT(String oRT) {
		if(oRT==null)this.ORT = "";
		ORT = oRT;
	}

	public int getLandID() {
		return LandID;
	}

	public void setLandID(int landID) {
		LandID = landID;
	}




}
