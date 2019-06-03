package com.gta.train.platform.common.excel;

public class CheckExcelMessage {
public CheckExcelMessage(boolean isSuccess,String massage) {
	this.massage=massage;
	this.isSuccess=isSuccess;
}
private String massage;
private boolean isSuccess;
public String getMassage() {
	return massage;
}
public boolean isSuccess() {
	return isSuccess;
}
public void setMassage(String massage) {
	this.massage = massage;
}
public void setSuccess(boolean isSuccess) {
	this.isSuccess = isSuccess;
}
@Override
public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("CheckExcelMessage [massage=");
	builder.append(massage);
	builder.append(", isSuccess=");
	builder.append(isSuccess);
	builder.append("]");
	return builder.toString();
}
 

}
