package com.gta.train.platform.common.excel.validator;

import java.util.List;

import com.gta.train.platform.common.excel.validator.dto.ImportMessage;

public interface Observerable {
	public void validatorDateObserver(String string);

	public void registerObserver(Observer o);

	public void removeObserver(Observer o);

	public void notifyObserver(List<List<String>> list, ImportMessage importMessage);
}
