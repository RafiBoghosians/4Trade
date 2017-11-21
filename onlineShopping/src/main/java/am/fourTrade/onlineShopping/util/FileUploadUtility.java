package am.fourTrade.onlineShopping.util;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtility {
	//absolute path which represent project location
	private static final String ABS_PATH = "D:\\4TradeProject\\4Trade\\onlineShopping\\src\\main\\webapp\\assets\\images\\";
	//real path where Tomcat deploys this particular application(based on directory that we have)
	private static String REAL_PATH= "";
	
	private static final Logger logger = LoggerFactory.getLogger(FileUploadUtility.class);

	
	
	public static void uploadFile(HttpServletRequest request, MultipartFile file, String code) {
		 			
				// get the Real Path
				REAL_PATH = request.getSession().getServletContext().getRealPath("/assets/images/");
				
				//print the REAL_PATH in the consul
				logger.info(REAL_PATH);

				// to make sure all the directory exists. if not exists
				// please create the directories
				if (!new File(ABS_PATH).exists()) {
					// create the directories
					new File(ABS_PATH).mkdirs();
				}

				if (!new File(REAL_PATH).exists()) {
					// create the directories
					new File(REAL_PATH).mkdirs();
				}

				try {
					// server upload
					file.transferTo(new File(REAL_PATH + code + ".jpg"));
					// project directory upload
					file.transferTo(new File(ABS_PATH + code + ".jpg"));

				} catch (IOException e) {
					
					e.printStackTrace();
		}
		
	}
	

}
