package com.gta.train.platform.saas.util.io;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * 文件上传下载工具类
 * 
 * @author chao.gao
 */
public class FileUpDownUtil {

	private static Logger log = Logger.getLogger(FileUpDownUtil.class);

	/**
	 * 下载文件
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @param filePath
	 *            文件完整路径名
	 * @param fileName
	 *            下载输出的文件名，如果为空将使用filePath中的文件名
	 * @throws Exception
	 *             下载文件异常
	 */
	public static void download(HttpServletRequest request,
			HttpServletResponse response, String filePath, String fileName)
			throws Exception {
		response.setContentType("text/html;charset=utf-8");
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		String downLoadPath = filePath;
		try {
			File file = new File(downLoadPath);
			if (!file.exists()) {
				throw new Exception("文件" + filePath + "不存在");
			}
			if (fileName == null || "".equals(fileName)) {
				fileName = file.getName();
			}
			String rtn = EncodingUtils.processFileName(request,fileName);

			response.setHeader("Content-disposition", "attachment;" + rtn);
			long fileLength = file.length();
			response.setContentType("application/x-msdownload;");

			response.setHeader("Content-Length", String.valueOf(fileLength));
			bis = new BufferedInputStream(new FileInputStream(downLoadPath));
			bos = new BufferedOutputStream(response.getOutputStream());
			//byte[] buff = new byte[FileSystemConfig.getDownloadFileBufferSize()];
			byte[] buff = new byte[1024];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {
			log.error("下载文件" + filePath + "出现错误", e);
			throw e;
		} finally {
			try {
				if (bis != null)
					bis.close();
				if (bos != null)
					bos.close();
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
		}
	}
}
