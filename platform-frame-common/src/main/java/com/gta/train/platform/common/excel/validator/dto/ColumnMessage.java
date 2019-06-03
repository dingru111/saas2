package com.gta.train.platform.common.excel.validator.dto;

public class ColumnMessage {
	private boolean isNULL = false;
	private String columnClass;
	private String format = null;

	public ColumnMessage(String columnClass) {
		this.columnClass = columnClass;
		this.isNULL = false;
	}

	public ColumnMessage(boolean isNULL, String columnClass, String format) {
		this.columnClass = columnClass;
		this.isNULL = isNULL;
		this.format = format;
	}
	public ColumnMessage( String columnClass, String format) {
		this.columnClass = columnClass;
		this.isNULL = isNULL;
		this.format = format;
	}
	public ColumnMessage(boolean isNULL, String columnClass) {
		this.columnClass = columnClass;
		this.isNULL = isNULL;
	}

	public boolean isNULL() {
		return isNULL;
	}

	public void setNULL(boolean isNULL) {
		this.isNULL = isNULL;
	}

	public String getColumnClass() {
		return columnClass;
	}

	public void setColumnClass(String columnClass) {
		this.columnClass = columnClass;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

}
