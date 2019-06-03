package com.gta.train.platform.excel;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

import org.hibernate.validator.constraints.NotEmpty;

public class TestDto {
	 
	@DecimalMin(value="1",message="不能小于1的数")       
	@DecimalMax(value="999999999",message="不能大于999999999的数")
	private String intValue;
	@DecimalMin(value="1",message="不能小于1的数")       
	@DecimalMax(value="999999999",message="不能大于999999999的数")
	@NotEmpty(message="intValueNotNull不能空")
	private String intValueNotNull;

	@DecimalMin(value="0.01" ,message="不能小于0.01的数")       
	@DecimalMax(value="999999999",message="不能大于999999999的数")
	private String doubleValue;

	@NotEmpty(message="doubleValueNotNull不能空")
	@DecimalMin(value="0.01",message="不能小于0.01的数")       
	@DecimalMax(value="999999999",message="不能大于999999999的数")
	private String doubleValueNotNull;

	private String stringValue;
	@NotEmpty(message="stringValueNotNull不能空")
	private String stringValueNotNull;
	public String getIntValue() {
		return intValue;
	}
	public void setIntValue(String intValue) {
		this.intValue = intValue;
	}
	public String getIntValueNotNull() {
		return intValueNotNull;
	}
	public void setIntValueNotNull(String intValueNotNull) {
		this.intValueNotNull = intValueNotNull;
	}
	public String getDoubleValue() {
		return doubleValue;
	}
	public void setDoubleValue(String doubleValue) {
		this.doubleValue = doubleValue;
	}
	public String getDoubleValueNotNull() {
		return doubleValueNotNull;
	}
	public void setDoubleValueNotNull(String doubleValueNotNull) {
		this.doubleValueNotNull = doubleValueNotNull;
	}
	public String getStringValue() {
		return stringValue;
	}
	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}
	public String getStringValueNotNull() {
		return stringValueNotNull;
	}
	public void setStringValueNotNull(String stringValueNotNull) {
		this.stringValueNotNull = stringValueNotNull;
	}
	 
	
}
