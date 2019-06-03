package com.gta.train.platform.common.excel.validator.dto;

import java.util.ArrayList;
import java.util.List;

public class LineMessage {
private int line;
private List<String> message=new ArrayList<String>();
private boolean isRight=true;
private boolean isDateValidator=true;
private boolean isProfessionalValidator=true;
public int getLine() {
	return line;
}
public void setLine(int line) {
	this.line = line;
}
public List<String> getMessage() {
	return message;
}
public void setMessage(List<String> message) {
	this.message = message;
}
public void setMessage(String message) {
	if(this.message==null) {
		this.message=new ArrayList<String>();
	}
	this.message.add(message);
}
public boolean isRight() {
	return isRight;
}
public void setRight(boolean isRight) {
	this.isRight = isRight;
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
@Override
public String toString() {
	return "LineMessage [line=" + line + " , isRight=" + isRight + ", isDateValidator="
			+ isDateValidator + ", isProfessionalValidator=" + isProfessionalValidator + "]";
} 

}
