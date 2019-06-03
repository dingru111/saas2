package com.gta.train.platform.common.excel.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.gta.train.platform.common.excel.validator.dto.ColumnMessage;
import com.gta.train.platform.common.excel.validator.dto.ImportMessage;
import com.gta.train.platform.common.excel.validator.dto.LineMessage;

public class DateObserver implements Observer{
	private List<ColumnMessage> columnMessageList;
	private List<List<Object>> resultList;
	@Override
	public void validatorLine(int lineNum, List<String> list, ImportMessage importMessage) {
		// TODO 自动生成的方法存根
		List<Object> objectList=new ArrayList<Object>();
 
			if(columnMessageList.size()<=list.size()) {
				for (int i = 0; i < columnMessageList.size(); i++) {
					ColumnMessage columnMessage=columnMessageList.get(i);
					 String column=list.get(i);
					 //可以为空但是没有值
					 if(columnMessage.isNULL()&&StringUtils.isBlank(column)) {
						 objectList.add(null);
						 continue;
					 }
					 //不可以为空但是没有值 (异常数据)
					 if((!columnMessage.isNULL())&&StringUtils.isBlank(column)) {
						 objectList.add(null);
						 setMessage(lineNum, importMessage,"没有数据！");
						 continue;
					 }
					 column=column.trim();
					 if ("string".equals(columnMessage.getColumnClass())) {
						 objectList.add(column.trim());
					 }
					 if ("int".equals(columnMessage.getColumnClass())) {
						 if(isInteger(column)) {
							 objectList.add(Integer.parseInt(column));
						 }else {
							 setMessage(lineNum, importMessage,"不是整数 ！ 数值为："+column);
							 objectList.add(null);
						 }
						 
					 }
					 if ("double".equals(columnMessage.getColumnClass())) {
						 if(isNumber(column)) {
							 objectList.add(Double.parseDouble(column));
						 }else {
							 setMessage(lineNum, importMessage,"不是数字 ！值为："+column);
							 objectList.add(null);
						 }
					 }
					 if ("date".equals(columnMessage.getColumnClass())) {
						
						try {
							 Date date  = new SimpleDateFormat(columnMessage.getFormat()).parse(column.trim());
							 objectList.add(date);
						} catch (ParseException e) {
							// TODO 自动生成的 catch 块
							setMessage(lineNum, importMessage,"日期格式不正确");
							e.printStackTrace();
						}
						 
					 }
				}
				resultList.add(objectList);
			 
		}  
	}
	private void setMessage(int lineNum, ImportMessage importMessage,String meaasge) {
		LineMessage lineMessage=importMessage.getLineMessageList().get(lineNum);
		lineMessage.setMessage(meaasge);
	}
	public   boolean isInteger(String str) {  
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");  
        return pattern.matcher(str).matches();  
	}
	public   boolean isNumber(String str) {  
		return str.matches("-?[0-9]+.*[0-9]*");
		 
	}
	
	
	@Override
	public void validatorcolumn(int columnNum, String column, ImportMessage importMessage) {
		// TODO 自动生成的方法存根
		
	}
	   public static void main(String[] args) {
	        String strDate="26.1-";
	        //注意：SimpleDateFormat构造函数的样式与strDate的样式必须相符
	        //必须捕获异常
	        System.out.println(strDate.matches("-?[0-9]+.*[0-9]*"));
	    }
	public List<ColumnMessage> getColumnMessageList() {
		return columnMessageList;
	}
	public void setColumnMessageList(List<ColumnMessage> columnMessageList) {
		this.columnMessageList = columnMessageList;
	}
	public List<List<Object>> getResultList() {
		return resultList;
	}
	public void setResultList(List<List<Object>> resultList) {
		this.resultList = resultList;
	}

}
