package com.gta.train.platform.saas.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class ListUtil {
	public static <T> List<T> removeDuplicate(List<T> list) {   
	    HashSet<T> h = new HashSet<T>(list);   
	    list.clear();   
	    list.addAll(h);   
	    return list;   
	}  

	public static   List<String> getDuplicate(List<Map<String, Object>> list,String title[]) {
		List<String> result = new ArrayList<String>();
		for (int i = 0; i < list.size() - 1; i++) {
			for (int j = list.size() - 1; j > i; j--) {

				if (list.get(j).equals(list.get(i))) {
					list.remove(j);
					boolean notNull=true;
					Map<String, Object> map=list.get(i);
					for (int k = 0; k < title.length; k++) {
						if(map.get(title[k])==null||StringUtils.isNotBlank(map.get(title[k]).toString())) {
							notNull=false;
						}
					}
					if(notNull) {
						result.add("第" + (i + 1) + "和" + (j + 1) + "行重复。");
					}
					
				}
			}
		}
		return result;
	}
	
	public static   List<String> getDuplicate(List<Map<String, Object>> list,String title,String s ) {
		List<String> result = new ArrayList<String>();
		for (int i = 0; i < list.size() - 1; i++) {
			for (int j = list.size() - 1; j > i; j--) {
				 
				String value1=null;
				if(list.get(i).get(title)!=null) {
					value1=list.get(i).get(title).toString();
				}
				String value2=null;
				if(list.get(j).get(title)!=null) {
					value2=list.get(j).get(title).toString();
				}
				if(value1!=null) {
					if (value1.equals(value2)) {
						//System.out.println(list.get(i));
						//System.err.println(list.get(j));
					
						result.add("第" + list.get(i).get("line") + "和" +  list.get(j).get("line")  + "行"+s+"重复。");
						//list.remove(j);
					}
				}
				 
			}
		}
		return result;
	}



	/**
	 * @description 批量转账，个人数据判重
	 * @author huan.xu
	 * @date  2018-12-29 17:09
	 * @param [list, title]
	 * @return java.util.List<java.lang.String>
	 **/
	@SuppressWarnings("all")
	public static   List<String> getDuplicateForPerson(List<Map<String, Object>> list,String title[]) {
		List<String> result = new ArrayList<String>();
		for (int i = 0; i < list.size() - 1; i++) {
			Map<String, Object> mapI=list.get(i);
			//空值去重
			int n=0;
			for (int k = 0; k < title.length; k++) {
				if(mapI.get(title[k])==null || StringUtils.isBlank(mapI.get(title[k]).toString())) {
					n++;
				}
			}
			if(n==title.length){
				list.remove(i);
				continue;
			}
			for (int j = list.size() - 1; j > i; j--) {
				if (list.get(j).equals(list.get(i))) {
					boolean notNull=true;
					Map<String, Object> mapJ=list.get(j);
					for (int k = 0; k < title.length; k++) {
						if(mapI.get(title[k])==null||StringUtils.isNotBlank(mapI.get(title[k]).toString()) && (!mapI.get(title[k]).toString().equals(mapJ.get(title[k]).toString())) ) {
							notNull=false;
						}
					}
					if(notNull) {
						result.add("第" + (i + 2) + "和第" + (j + 2) + "行重复。");
					}
					list.remove(j);
				}
			}
		}
		return result;
	}
}
