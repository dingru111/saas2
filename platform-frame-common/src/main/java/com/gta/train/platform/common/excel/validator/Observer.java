package com.gta.train.platform.common.excel.validator;

import java.util.List;

import com.gta.train.platform.common.excel.validator.dto.ImportMessage;

public interface Observer {
	public  void validatorLine(int lineNum,List<String> list,ImportMessage importMessage);
	public  void validatorcolumn(int columnNum,String column,ImportMessage importMessage);

}
