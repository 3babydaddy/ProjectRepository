package com.tf.base.common.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

@Service
public class ExportFileService {
	
	public String createFilePath(){
		String saveName = UUID.randomUUID().toString().replaceAll("-", "");
		String filePath = System.getProperty("catalina.home") +"/webapps/export/"+saveName+".xlsx";
		File fileNew = new File(filePath);
		File rootFile=fileNew.getParentFile();//得到父文件夹
		if(!fileNew.exists()){
			try {
				rootFile.mkdirs();
				fileNew.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return filePath;
	}
	
	public void doDown(String filePath, String fileName, HttpServletResponse response){
		File file = new File(filePath);
        if (file.exists()) {
//        	if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {  
//        	    filename = URLEncoder.encode(f.getFilename(), "UTF-8");  
//        	} else {  
//        	    filename = new String(f.getFilename().getBytes("UTF-8"), "ISO8859-1");  
//        	}
    		response.reset();
            response.setContentType("application/force-download");// 设置强制下载不打开
            response.addHeader("Content-Disposition",
                    "attachment;fileName="+fileName);// 设置文件名
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                os.flush();
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                file.delete();
			}
		}
	}
	
	
	public String createFilePath(FileInputStream fis){
		String saveName = UUID.randomUUID().toString().replaceAll("-", "");
		String filePath = System.getProperty("catalina.home") +"/webapps/export/"+saveName+".xlsx";
		File fileNew = new File(filePath);
		File rootFile=fileNew.getParentFile();//得到父文件夹
		if(!fileNew.exists()){
			try {
				rootFile.mkdirs();
				fileNew.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try{
			BufferedInputStream bufis = new BufferedInputStream(fis);
	        FileOutputStream fos = new FileOutputStream(filePath);
	        BufferedOutputStream bufos = new BufferedOutputStream(fos);
	        int len = 0;
	        while ((len = bufis.read()) != -1) {
	            bufos.write(len);
	        }
	        bufis.close();
	        bufos.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return filePath;
	}
}
