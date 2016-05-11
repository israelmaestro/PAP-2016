package br.com.shepherd.service.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class JSFUtil{

	public static String logTimeStamp(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd.HHmmss");

		return sdf.format(new Date());
	}

	public static String actualTimeStamp(){
		SimpleDateFormat sdf = new SimpleDateFormat("[dd/MM/yyyy HH:mm:ss] ");

		return sdf.format(new Date());
	}

	public static String dataNormal(Date pDate){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		return sdf.format(pDate);
	}

	private static String mensagemComHora(String message){
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		return sdf.format(new Date()) + " " + message;
	}

	public static void addInfoMessage(String message){
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
											mensagemComHora(message), "");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public static void addWarnMessage(String message){
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN,
											mensagemComHora(message), "");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public static void addErrorMessage(String message){
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
											mensagemComHora(message), "");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

}
