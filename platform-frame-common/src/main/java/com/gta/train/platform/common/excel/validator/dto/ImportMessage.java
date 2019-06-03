package com.gta.train.platform.common.excel.validator.dto;

import java.util.ArrayList;
import java.util.List;

public class ImportMessage {
private List<String> message=new ArrayList<String>();
private boolean isImport=true; 
private boolean isDateValidator=true; 
private boolean isProfessionalValidator=true; 

private List<LineMessage> lineMessageList;
public ImportMessage(int lineSize){
	lineMessageList= new ArrayList<LineMessage>();
	for (int i = 0; i < lineSize; i++) {
		lineMessageList.add(new LineMessage());
		
	}
	
}
public List<String> getMessage() {
	return message;
}

public void setMessage(List<String> message) {
	this.message = message;
}

public boolean isImport() {
	return isImport;
}

public void setImport(boolean isImport) {
	this.isImport = isImport;
}

public boolean isDateValidator() {
	return isDateValidator;
}

public void setDateValidator(boolean isDateValidator) {
	this.isDateValidator = isDateValidator;
}

public boolean isProfessionalValidator() {
	return isProfessionalValidator;
}

public void setProfessionalValidator(boolean isProfessionalValidator) {
	this.isProfessionalValidator = isProfessionalValidator;
}

public List<LineMessage> getLineMessageList() {
	 
	return lineMessageList;
}

public void setLineMessageList(List<LineMessage> lineMessageList) {
	this.lineMessageList = lineMessageList;
}
@Override
public String toString() {
	return "ImportMessage [message=" + message + ", isImport=" + isImport + ", isDateValidator=" + isDateValidator
			+ ", isProfessionalValidator=" + isProfessionalValidator + ", lineMessageList=" + lineMessageList + "]";
}
 
public String print() {
	StringBuffer sb = new StringBuffer();
 
 
	for (String string : message) {
		 sb.append(", message=").append(string);
	}
	sb.append("\n");
	for ( LineMessage   lineMessage: lineMessageList) {
		 
		 
		 for (String string : lineMessage.getMessage()) {
			 sb.append(", message=").append(string);
		}
	}
	return sb.toString();
}
}
