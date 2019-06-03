package com.gta.train.platform.saas.util;

/**
 * <pre>
 * 表格样式
 * 每一列，对表头，索引对应
 * for exporting
 * 参见 TableHeader.java
 * <pre>
 * 
 * @author JINBO.CHEN
 *
 */
public final class TableSytle {

	/**
	 * 权限功能  显示宽度
	 */
	public static final int[] STYLE_WIDTH_POWER = new int[]{
		15*256,15*256,15*256,30*256,30*256,30*256,15*256,25*256
	};
	/**
	 * 用户组  显示宽度
	 */
	public static final int[] STYLE_WIDTH_USERGROUP = new int[]{
		15*256,15*256,15*256,15*256,15*256,15*256,15*256,15*256,15*256,15*256,15*256
	};
	/**
	 * 角色功能  显示宽度
	 */
	public static final int[] STYLE_WIDTH_ROLE = new int[]{
		6*256,8*256,8*256,6*256,30*256,20*256,30*256,20*256,40*256
	};
	/**
	 * 日志功能  显示宽度
	 */
	public static final int[] STYLE_WIDTH_LOG = new int[]{
		6*256,8*256,8*256,6*256,20*256,20*256,20*256,100*256,40*256
	};
	/**
	 * 用户功能  显示宽度
	 */
	public static final int[] STYLE_WIDTH_USER = new int[]{
		6*256,8*256,8*256,6*256,20*256,20*256,20*256,100*256,40*256
	};
	
}
