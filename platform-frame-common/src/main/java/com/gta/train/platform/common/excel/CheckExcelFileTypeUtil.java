package com.gta.train.platform.common.excel;

import java.io.FileInputStream;  
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;

import org.apache.commons.lang.StringUtils;  
  
/** 
 * @author guoxk 
 *  
 * @version 创建时间 2016年7月17日 上午10:47:26 
 *  
 * 类描述：获取和判断文件头信息  
 *    |--文件头是位于文件开头的一段承担一定任务的数据，一般都在开头的部分。 
 *    |--头文件作为一种包含功能函数、数据接口声明的载体文件，用于保存程序的声明(declaration),而定义文件用于保存程序的实现(implementation)。 
 *    |--为了解决在用户上传文件的时候在服务器端判断文件类型的问题，故用获取文件头的方式，直接读取文件的前几个字节，来判断上传文件是否符合格式。 
 *  
 */  
public class CheckExcelFileTypeUtil {  
    // 缓存文件头信息-文件头信息  
    public static final HashMap<String, String> mFileTypes = new HashMap<String, String>();  
    static {  
        // images  
        mFileTypes.put("FFD8FF", "jpg");  
        mFileTypes.put("89504E47", "png");  
        mFileTypes.put("47494638", "gif");  
        mFileTypes.put("49492A00", "tif");  
        mFileTypes.put("424D", "bmp");  
        //  
        mFileTypes.put("41433130", "dwg"); // CAD  
        mFileTypes.put("38425053", "psd");  
        mFileTypes.put("7B5C727466", "rtf"); // 日记本  
        mFileTypes.put("3C3F786D6C", "xml");  
        mFileTypes.put("68746D6C3E", "html");  
        mFileTypes.put("44656C69766572792D646174653A", "eml"); // 邮件  
        mFileTypes.put("D0CF11E0", "doc");  
        mFileTypes.put("D0CF11E0", "xls");//excel2003版本文件  
        mFileTypes.put("5374616E64617264204A", "mdb");  
        mFileTypes.put("252150532D41646F6265", "ps");  
        mFileTypes.put("255044462D312E", "pdf");  
        mFileTypes.put("504B0304", "docx");  
        mFileTypes.put("504B0304", "xlsx");//excel2007以上版本文件  
        mFileTypes.put("52617221", "rar");  
        mFileTypes.put("57415645", "wav");  
        mFileTypes.put("41564920", "avi");  
        mFileTypes.put("2E524D46", "rm");  
        mFileTypes.put("000001BA", "mpg");  
        mFileTypes.put("000001B3", "mpg");  
        mFileTypes.put("6D6F6F76", "mov");  
        mFileTypes.put("3026B2758E66CF11", "asf");  
        mFileTypes.put("4D546864", "mid");  
        mFileTypes.put("1F8B08", "gz");  
    }  
  
    /** 
     * @author guoxk 
     * 
     * 方法描述：根据文件路径获取文件头信息 
     * @param filePath 文件路径 
     * @return 文件头信息 
     */  
    public static String getFileType(String filePath) {  
//      System.out.println(getFileHeader(filePath));  
//      System.out.println(mFileTypes.get(getFileHeader(filePath)));  
        return mFileTypes.get(getFileHeader(filePath));  
    }  
    
    /** 
     * @author guoxk 
     * 
     * 方法描述：根据文件路径获取文件头信息 
     * @param filePath 文件路径 
     * @return 文件头信息 
     */  
    public static String getFileType(InputStream is) {  
//      System.out.println(getFileHeader(filePath));  
//      System.out.println(mFileTypes.get(getFileHeader(filePath)));  
        return mFileTypes.get(getFileHeader(is));  
    }  
  
    /** 
     * @author guoxk 
     * 
     * 方法描述：根据文件路径获取文件头信息 
     * @param filePath 文件路径 
     * @return 文件头信息 
     */  
    public static String getFileHeader(String filePath) {  
        FileInputStream is = null;  
        String value = null;  
        try {  
            is = new FileInputStream(filePath);  
            byte[] b = new byte[4];  
            /* 
             * int read() 从此输入流中读取一个数据字节。int read(byte[] b) 从此输入流中将最多 b.length 
             * 个字节的数据读入一个 byte 数组中。 int read(byte[] b, int off, int len) 
             * 从此输入流中将最多 len 个字节的数据读入一个 byte 数组中。 
             */  
            is.read(b, 0, b.length);  
            value = bytesToHexString(b);  
        } catch (Exception e) {  
        	e.printStackTrace();
        } finally {  
            if (null != is) {  
                try {  
                    is.close();  
                } catch (IOException e) {  
                	e.printStackTrace();
                }  
            }  
        }  
        return value;  
    }  
    public static String getFileHeader(InputStream is) {  
        String value = null;  
        try {  
            byte[] b = new byte[4];  
            /* 
             * int read() 从此输入流中读取一个数据字节。int read(byte[] b) 从此输入流中将最多 b.length 
             * 个字节的数据读入一个 byte 数组中。 int read(byte[] b, int off, int len) 
             * 从此输入流中将最多 len 个字节的数据读入一个 byte 数组中。 
             */  
            is.read(b, 0, b.length);  
            value = bytesToHexString(b);  
        } catch (Exception e) {  
        } finally {  
            if (null != is) {  
                try {  
                    is.close();  
                } catch (IOException e) {  
                }  
            }  
        }  
        return value;  
    }
    /** 
     * @author guoxk 
     * 
     * 方法描述：将要读取文件头信息的文件的byte数组转换成string类型表示 
     * @param src 要读取文件头信息的文件的byte数组 
     * @return   文件头信息 
     */  
    private static String bytesToHexString(byte[] src) {  
        StringBuilder builder = new StringBuilder();  
        if (src == null || src.length <= 0) {  
            return null;  
        }  
        String hv;  
        for (int i = 0; i < src.length; i++) {  
            // 以十六进制（基数 16）无符号整数形式返回一个整数参数的字符串表示形式，并转换为大写  
            hv = Integer.toHexString(src[i] & 0xFF).toUpperCase();  
            if (hv.length() < 2) {  
                builder.append(0);  
            }  
            builder.append(hv);  
        }  
//      System.out.println(builder.toString());  
        return builder.toString();  
    }  
   
    public static CheckExcelMessage getXlsxTypeMessage( InputStream is) {
    	String type=getFileType(is);
    	if(StringUtils.isBlank(type)) {
    		return new CheckExcelMessage(false,"请导入文件类型为xlsx的excel");
    	}else if("xlsx".equals(type)){
    		return new CheckExcelMessage(true,"");
    	}else {
    		return new CheckExcelMessage(false,"请导入文件类型为xlsx的excel，您导入的是"+type+"类型的文件！");
    	}
    }
    public static CheckExcelMessage getXlsxTypeMessage(String filePath) { 
    	String type=getFileType(filePath);
    	if(StringUtils.isBlank(type)) {
    		return new CheckExcelMessage(false,"请导入文件类型为xlsx的excel");
    	}else if("xlsx".equals(type)){
    		return new CheckExcelMessage(true,"");
    	}else {
    		return new CheckExcelMessage(false,"请导入文件类型为xlsx的excel，您导入的是"+type+"类型的文件！");
    	}
    }
    
    /** 
     * @author guoxk 
     * 
     * 方法描述：测试 
     * @param args 
     * @throws Exception 
     */  
    public static void main(String[] args) throws Exception {  
         //String fileType = getFileType("E:\\test.xls");  
        System.out.println( CheckExcelFileTypeUtil.getXlsxTypeMessage("E:\\test.xls"));  
        System.out.println( CheckExcelFileTypeUtil.getXlsxTypeMessage("E:\\test.xlsx"));  
    	System.out.println( CheckExcelFileTypeUtil.getXlsxTypeMessage(new FileInputStream("E:\\test.xls")));  
    	System.out.println( CheckExcelFileTypeUtil.getXlsxTypeMessage(new FileInputStream("E:\\test.xlsx")));  
    	   
    	  
    }
}