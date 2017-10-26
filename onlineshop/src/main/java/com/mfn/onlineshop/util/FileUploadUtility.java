package com.mfn.onlineshop.util;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtility {
	
	private static final Logger logger = LoggerFactory.getLogger(FileUploadUtility.class);
	
	private static final String ABS_PATH = "/home/fyodor/Projects/OnlineShop/onlineshop/src/main/webapp/assets/images/";
	private static String REAL_PATH = "";
	
	
	public static void uploadFile(HttpServletRequest request, MultipartFile file, String code) {
		
		REAL_PATH = request.getSession().getServletContext().getRealPath("/assets/images/");
		logger.info(REAL_PATH);
		
		if(!new File(ABS_PATH).exists()) {
			new File(ABS_PATH).mkdirs();
		}
		
		if(!new File(REAL_PATH).exists()) {
			new File(REAL_PATH).mkdirs();
		}
		
		try {
			//server upload
			file.transferTo(new File(REAL_PATH + code + ".jpg"));
			//project dir
			file.transferTo(new File(ABS_PATH + code + ".jpg"));
		} catch (IOException e) {
			// TODO: handle exception
		}
		
	}

}
