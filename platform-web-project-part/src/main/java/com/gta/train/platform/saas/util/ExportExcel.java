package com.gta.train.platform.saas.util;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;

 

public class ExportExcel<T> {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 格式化日期

	/**
	 * 导出excel
	 * 
	 * @param title标题
	 * @param dataset集合
	 * @param out输出流
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("all") 
	public void exportExcel(String title, Collection<T> dataset,
			List filetitle, List entityFile, ServletOutputStream out) {
		// 声明一个工作薄
		try {
			Iterator<T> its = null;
			// 首先检查数据看是否是正确的
			if(null != dataset){
				its = dataset.iterator();
			}
			if (null == its || dataset == null || !its.hasNext() || title == null
					|| out == null) {
				throw new Exception("传入的数据不对！");
			}
			// 取得实际泛型类
			T ts = (T) its.next();
			Class tCls = ts.getClass();
			HSSFWorkbook workbook = new HSSFWorkbook();
			// 生成一个表格
			HSSFSheet sheet = workbook.createSheet(title);
			// 设置表格默认列宽度为20个字节
			int[] width = TableSytle.STYLE_WIDTH_ROLE;
			sheet.setDefaultColumnWidth(20);

			// 生成一个样式
			//HSSFCellStyle style = workbook.createCellStyle();
			HSSFCellStyle style1 = workbook.createCellStyle();// 3，设置表格风格
			style1.setAlignment(CellStyle.ALIGN_CENTER);
			style1.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
			style1.setBorderBottom(HSSFCellStyle.BORDER_NONE);
			style1.setBorderLeft(HSSFCellStyle.BORDER_NONE);
			style1.setBorderRight(HSSFCellStyle.BORDER_NONE);
			style1.setBorderTop(HSSFCellStyle.BORDER_NONE);
			style1.setWrapText(true);
		 
			  HSSFCellStyle headStyle = workbook.createCellStyle();// 3，设置表格风格
			  headStyle.setAlignment(CellStyle.ALIGN_CENTER);
			  headStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
			  headStyle.setBorderBottom(HSSFCellStyle.BORDER_NONE);
			  headStyle.setBorderLeft(HSSFCellStyle.BORDER_NONE);
			  headStyle.setBorderRight(HSSFCellStyle.BORDER_NONE);
			  headStyle.setBorderTop(HSSFCellStyle.BORDER_NONE);
			  headStyle.setWrapText(true);
			  headStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			  headStyle.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
			// 设置标题样式
			// style = ExcelStyle.setHeadStyle(workbook, style);

			// 得到所有字段
			// Field filed[] = ts.getClass().getDeclaredFields();
			@SuppressWarnings("unused")
			Object[] filed = entityFile.toArray();
			// 标题 (字段标题，直接用数组传入)
			List<String> exportfieldtile = filetitle;
			// 导出的字段的get方法
			List<Method> methodObj = new ArrayList<Method>();

			// 遍历整个filed
			for (int i = 0; i < entityFile.size(); i++) {
				@SuppressWarnings("unused")
				int j = i;
				String fieldname = (String) entityFile.get(i);
				String getMethodName = "get"
						+ fieldname.substring(0, 1).toUpperCase()
						+ fieldname.substring(1);
				Method getMethod = tCls
						.getMethod(getMethodName, new Class[] {});
				methodObj.add(getMethod);
			}
			// 产生表格标题行
			HSSFRow row = sheet.createRow(0);
			row.setHeightInPoints(18);
			for (int i = 0; i < exportfieldtile.size(); i++) {
				HSSFCell cell = row.createCell(i);
				cell.setCellStyle(headStyle);
				HSSFRichTextString text = new HSSFRichTextString(
						exportfieldtile.get(i));
				cell.setCellValue(text);
			}

			int index = 0;

			// 循环整个集合
			its = dataset.iterator();
			@SuppressWarnings("unused")
			int i = 1;
			while (its.hasNext()) {
				// 从第二行开始写，第一行是标题
				index++;
				row = sheet.createRow(index);
				row.setHeightInPoints(18);
				T t = (T) its.next();
				for (int k = 0; k < methodObj.size() ; k++) {
					@SuppressWarnings("unused")
					int j = k;
					HSSFCell cell = row.createCell(k);
					sheet.setColumnWidth(index, width[4]);
					cell.setCellStyle(style1);
					/*if (k == 0) {// 每行第一个单元格是序号
						cell.setCellValue(i++);// i++是先赋值然后执行+1
					} else {*/
						Method getMethod = methodObj.get(k );
						Object value = getMethod.invoke(t, new Object[] {});
						String textValue = getValue(value);
						cell.setCellValue(textValue);
					//}
				}

			}
			workbook.write(out);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * 导出excel，表头加粗
	 * 
	 * @param title标题
	 * @param dataset集合
	 * @param out输出流
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("all")
	public void exportExcelHeadBold(String title, Collection<T> dataset,
			List filetitle, List entityFile, ServletOutputStream out) {
		// 声明一个工作薄
		try {
			Iterator<T> its = null;
			// 首先检查数据看是否是正确的
			if(null != dataset){
				its = dataset.iterator();
			}
			if (null == its || dataset == null || !its.hasNext() || title == null
					|| out == null) {
				throw new Exception("传入的数据不对！");
			}
			// 取得实际泛型类
			T ts = (T) its.next();
			Class tCls = ts.getClass();
			HSSFWorkbook workbook = new HSSFWorkbook();
			// 生成一个表格
			HSSFSheet sheet = workbook.createSheet(title);
			// 设置表格默认列宽度为20个字节
			int[] width = TableSytle.STYLE_WIDTH_ROLE;
			sheet.setDefaultColumnWidth(20);

			// 生成一个样式
			//HSSFCellStyle style = workbook.createCellStyle();
			HSSFCellStyle style1 = workbook.createCellStyle();// 3，设置表格风格
			style1.setAlignment(CellStyle.ALIGN_CENTER);
			style1.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
			style1.setBorderBottom(HSSFCellStyle.BORDER_NONE);
			style1.setBorderLeft(HSSFCellStyle.BORDER_NONE);
			style1.setBorderRight(HSSFCellStyle.BORDER_NONE);
			style1.setBorderTop(HSSFCellStyle.BORDER_NONE);
			style1.setWrapText(true);
			
			HSSFCellStyle style = workbook.createCellStyle();// 4，设置表头风格
			style.setAlignment(CellStyle.ALIGN_CENTER);
			style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
			style.setBorderBottom(HSSFCellStyle.BORDER_NONE);
			style.setBorderLeft(HSSFCellStyle.BORDER_NONE);
			style.setBorderRight(HSSFCellStyle.BORDER_NONE);
			style.setBorderTop(HSSFCellStyle.BORDER_NONE);
			style.setWrapText(true);
			HSSFFont headerFont = (HSSFFont) workbook.createFont();
			headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 字体加粗
			style.setFont(headerFont);

			// 设置标题样式
			// style = ExcelStyle.setHeadStyle(workbook, style);

			// 得到所有字段
			// Field filed[] = ts.getClass().getDeclaredFields();
			Object[] filed = entityFile.toArray();
			// 标题 (字段标题，直接用数组传入)
			List<String> exportfieldtile = filetitle;
			// 导出的字段的get方法
			List<Method> methodObj = new ArrayList<Method>();

			// 遍历整个filed
			for (int i = 0; i < entityFile.size(); i++) {
				int j = i;
				String fieldname = (String) entityFile.get(i);
				String getMethodName = "get"
						+ fieldname.substring(0, 1).toUpperCase()
						+ fieldname.substring(1);
				Method getMethod = tCls
						.getMethod(getMethodName, new Class[] {});
				methodObj.add(getMethod);
			}
			// 产生表格标题行
			HSSFRow row = sheet.createRow(0);
			row.setHeightInPoints(18);
			for (int i = 0; i < exportfieldtile.size(); i++) {
				HSSFCell cell = row.createCell(i);
				cell.setCellStyle(style);
				HSSFRichTextString text = new HSSFRichTextString(
						exportfieldtile.get(i));
				cell.setCellValue(text);
			}

			int index = 0;

			// 循环整个集合
			its = dataset.iterator();
			int i = 1;
			while (its.hasNext()) {
				// 从第二行开始写，第一行是标题
				index++;
				row = sheet.createRow(index);
				row.setHeightInPoints(18);
				T t = (T) its.next();
				for (int k = 0; k < methodObj.size() ; k++) {
					int j = k;
					HSSFCell cell = row.createCell(k);
					sheet.setColumnWidth(index, width[4]);
					cell.setCellStyle(style1);
					/*if (k == 0) {// 每行第一个单元格是序号
						cell.setCellValue(i++);// i++是先赋值然后执行+1
					} else {*/
						Method getMethod = methodObj.get(k );
						Object value = getMethod.invoke(t, new Object[] {});
						String textValue = getValue(value);
						cell.setCellValue(textValue);
					//}
				}

			}
			workbook.write(out);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private String getValue(Object value) throws ParseException {
		String textValue = "";
		if (null == value) {
			return textValue;
		}
		if (value instanceof Boolean) {
			boolean bValue = (Boolean) value;
			textValue = "是";
			if (!bValue) {
				textValue = "否";
			}
		} else if (value instanceof GregorianCalendar) {
			GregorianCalendar calendar = (GregorianCalendar) value;
			Date d = calendar.getTime();
			textValue = sdf.format(d);
		} else {
			textValue = value.toString();
		}
		return textValue;
	}

	/*
	 * @SuppressWarnings("unchecked") public static void main(String[] args)
	 * throws Exception { //构造一个模拟的List来测试，实际使用时，这个集合是从数据库中查出来的 List<User> list
	 * = new ArrayList<User>(); for (int i = 0; i < 10; i++) { User user = new
	 * User(); user.setUserCode("123456"); user.setEchoName("xiaom");
	 * list.add(user); } //构造输出对象，可以从response输出，直接向用户提供下载 ServletOutputStream
	 * out = new OutputStream(); //开始时间 Long l = System.currentTimeMillis();
	 * List filetitle =new ArrayList(); filetitle.add("账号");
	 * filetitle.add("昵称"); List entityFile = new ArrayList();
	 * entityFile.add("userCode"); entityFile.add("echoName"); //注意 new
	 * ExportExcel().exportExcel("测试",list, filetitle,entityFile,out);
	 * out.close(); //结束时间 Long s = System.currentTimeMillis();
	 * System.out.println("总共耗时："+(s-l)); }
	 */
}
