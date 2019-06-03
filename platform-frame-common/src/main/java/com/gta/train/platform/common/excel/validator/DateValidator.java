package com.gta.train.platform.common.excel.validator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gta.train.platform.common.excel.validator.dto.ColumnMessage;
import com.gta.train.platform.common.excel.validator.dto.ImportMessage;

public class DateValidator implements Observerable {
	private List<Observer> list;

	public DateValidator() {
		list = new ArrayList<Observer>();
	}

	@Override
	public void registerObserver(Observer o) {

		list.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		if (!list.isEmpty())
			list.remove(o);
	}
    @Override
    public void notifyObserver(List<List<String>> dataList, ImportMessage importMessage) {
         
        for (int i = 0; i < dataList.size(); i++) {
			List<String> columns = dataList.get(i);
			for (int j = 0; j < columns.size(); j++) {
				String column = columns.get(j);
				for(int k = 0; k < list.size(); k++) {
	                Observer oserver = list.get(k);
	                oserver.validatorcolumn(i,column,importMessage);
	            }
			}
			for(int k = 0; k < list.size(); k++) {
                Observer oserver = list.get(k);
                oserver.validatorLine(i,columns,importMessage);
            }

		}
    }
 

	@Override
	public void validatorDateObserver(String string) {
		// TODO 自动生成的方法存根

	}

 
}
