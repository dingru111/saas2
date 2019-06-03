package com.gta.train.platform.persis.mybatis.plugin;

 

public class FrameworkConstants {
 
	
	   public enum DEL {
		    NOT_DEL(0,"未删除"), IS_DEL(1,"已经删除"),TEMP_SAVE(2,"暂存");
	        private final Integer value;
	        private final String name;
	        /**
	         * 构造器默认也只能是private, 从而保证构造函数只能在内部使用
	         * 
	         */
	        private DEL(Integer value,String name) {
	            this.value = value;
	            this.name = name;
	        }

	        public Integer getValue() {
	            return value;
	        }
	        public String getName() {
	            return name;
	        }
	        // 普通方法  
	        public static String getName(Integer index) {  
	            for (DEL c : DEL.values()) {  
	                if (c.getValue() == index) {  
	                    return c.name;  
	                }  
	            }  
	            return null;  
	        } 
	    }
	   
	 
		
		 
		  
		   
}
