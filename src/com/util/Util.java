package com.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.dao.BankuaiDao;
import com.dao.UserDao;
import com.model.Bankuai;
import com.model.User;



public class Util {

	
	// 获取当前月份
	public static String getYuefen() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Date date = new Date();
		return sdf.format(date.getTime());
	}
	
	// 获取当前系统时间
	public static String getTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		return sdf.format(date.getTime());
	}

	// 获取当前系统时间
	public static String getTime2() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		return sdf.format(date.getTime());
	}

	// 上传文件/复制文件。
	public static void copyFile(File src, File dst) {
		try {
			int BUFFER_SIZE = 16 * 1024;
			InputStream in = null;
			OutputStream out = null;
			try {
				in = new BufferedInputStream(new FileInputStream(src),
						BUFFER_SIZE);
				out = new BufferedOutputStream(new FileOutputStream(dst),
						BUFFER_SIZE);
				byte[] buffer = new byte[BUFFER_SIZE];
				for (int byteRead = 0; (byteRead = in.read(buffer)) > 0;) {
					out.write(buffer, 0, byteRead);
				}

			} finally {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

	// 初始化系统
	public static void init(HttpServletRequest request) {
		WebApplicationContext app = WebApplicationContextUtils
				.getWebApplicationContext(request.getSession()
						.getServletContext());
		UserDao userDao = (UserDao) app.getBean("userDao");
		
		BankuaiDao bankuaiDao = (BankuaiDao) app.getBean("bankuaiDao");
		
		User user = userDao
				.selectBean(" where username='admin' and deletestatus=0  ");
		if (user == null) {
			user = new User();
			user.setPassword("111111");
			user.setRole(1);
			user.setTruename("admin");
			user.setUsername("admin");
			userDao.insertBean(user);
			
			
			Bankuai b1 = new Bankuai();
			
			b1.setBankuaiming("交流区");
			
			bankuaiDao.insertBean(b1);
			
			Bankuai b2 = new Bankuai();
			
			b2.setBankuaiming("互助区");
			
			bankuaiDao.insertBean(b2);
			
			
		}
	}

	public static void createZip(String src, String nilename, String path)
			throws Exception {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ZipOutputStream zipOut = new ZipOutputStream(bos);
		zipOut.setEncoding("gbk");

		File file = new File(path, src);
		byte[] buffer = new byte[4096];
		int bytes_read;
		InputStream fis = new FileInputStream(file);
		zipOut.putNextEntry(new ZipEntry(src));
		while ((bytes_read = fis.read(buffer)) != -1) {
			zipOut.write(buffer, 0, bytes_read);
		}
		zipOut.closeEntry();
		fis.close();

		zipOut.close();
		FileOutputStream fout = new FileOutputStream(new File(path, nilename));
		bos.writeTo(fout);
		fout.flush();
		fout.close();
	}

}
