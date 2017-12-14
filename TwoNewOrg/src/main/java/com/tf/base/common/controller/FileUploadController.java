package com.tf.base.common.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.tf.base.common.constants.CommonConstants;
import com.tf.base.common.constants.CommonConstants.LOG_OPERATION_TYPE;
import com.tf.base.common.domain.DataFile;
import com.tf.base.common.persistence.DataFileMapper;
import com.tf.base.common.service.BaseService;
import com.tf.base.common.service.LogService;
import com.tf.base.common.utils.ApplicationProperties;
import com.tf.base.unpublic.domain.AttachmentCommonInfo;
import com.tf.base.unpublic.persistence.AttachmentCommonInfoMapper;

import net.sf.json.JSONObject;

@Controller
public class FileUploadController {

	
	@Autowired
	private BaseService baseService;
	@Autowired
	private LogService logService;
	@Autowired
	private DataFileMapper dataFileMapper;
	@Autowired
	private AttachmentCommonInfoMapper attachmentCommonInfoMapper;
	
	@RequestMapping(value = {"*/fileUpload","*/*/fileUpload"})
    public void onSubmit(String modelTbId, String model, MultipartHttpServletRequest multipartRequest, HttpServletResponse response) {
		
		// 取得文件列表
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        
        Date now = new Date();
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        
        // 设置文件路径
       // String ctxPath = "/data/order/change/pic/" + dateFormat.format(now) + "/";
        ApplicationProperties app = new ApplicationProperties();
        String filePath = app.getValueByKey("file.upload.path") + dateFormat.format(now) + "/";

        File folder = new File(filePath);

		if (!folder.exists()) {
			folder.mkdirs();
		}
        List<String> urls = new ArrayList<String>();
        
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
        	
             // 上传文件名
             MultipartFile mf = entity.getValue();
             String prefix = mf.getOriginalFilename().substring(mf.getOriginalFilename().lastIndexOf(".")+1);
             String fileName = modelTbId + "_" + now.getTime() + "." + prefix;
             File uploadFile = new File(filePath + fileName);
             try {
				FileCopyUtils.copy(mf.getBytes(), uploadFile);
				DataFile f = new DataFile();
				f.setFilename(fileName);
				f.setUploadfilename(mf.getOriginalFilename());
				f.setFilesize(String.valueOf(mf.getSize()));
				f.setModel(model);
				f.setModelTbId(modelTbId);
				f.setPathname(filePath);
				f.setStatus(CommonConstants.STATUS_FLAG_VALID);
				f.setType("1");
				f.setUploader(baseService.getShowName());
				f.setUploadTime(new Date());
				dataFileMapper.insertSelective(f);
				
				urls.add(filePath + fileName);
				
				try{
				   //上传附件记录日志
					logService.saveLog(LOG_OPERATION_TYPE.CREATE.toString(), 
							logService.getDetailInfo("log.fileupload.create",
									baseService.getUserName(),mf.getOriginalFilename()));
				}catch(Exception e){
					e.printStackTrace();
				}
			 } catch (IOException e) {
				
				e.printStackTrace();
			 }
        }
        
        Map<String, Object> resMap = new HashMap<String, Object>();

        resMap.put("status", "1");
        resMap.put("url" , urls);

        outputJSONResult(resMap, response);
	}
	
	@RequestMapping(value = {"*/downFile","*/*/downFile"})
	public void onDown(String id, HttpServletResponse response) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		if(StringUtils.isEmpty(id)){
			resMap.put("status", "0");
	        resMap.put("msg" , "附件ID为NULL！");

		}else{
			
			DataFile f = dataFileMapper.selectByPrimaryKey(id);
		
			File file = new File(f.getPathname(), f.getFilename());
            if (file.exists()) {
	                response.setContentType("application/force-download");// 设置强制下载不打开
	                response.addHeader("Content-Disposition",
	                        "attachment;fileName=" + f.getUploadfilename());// 设置文件名
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
	                }
	                
				resMap.put("status", "1");
		        resMap.put("msg" , "下载附件成功！");
		        
		        
		        try{
				   //下载附件记录日志
					logService.saveLog(LOG_OPERATION_TYPE.CREATE.toString(), 
							logService.getDetailInfo("log.fileupload.view",
									baseService.getUserName(),f.getUploadfilename()));
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		
		 outputJSONResult(resMap, response);
	}
	
	@RequestMapping(value = {"*/deleteFile","*/*/deleteFile"})
	public void onDelete(String id, HttpServletResponse response) {
		
		Map<String, Object> resMap = new HashMap<String, Object>();
		if(StringUtils.isEmpty(id)){
			resMap.put("status", "0");
	        resMap.put("msg" , "附件ID为NULL！");

		}else{
			
			DataFile oldF = dataFileMapper.selectByPrimaryKey(id);
			DataFile f = new DataFile();
			f.setId(Integer.parseInt(id));
			f.setStatus(CommonConstants.STATUS_FLAG_INVALID);
			dataFileMapper.updateByPrimaryKeySelective(f);
			
			resMap.put("status", "1");
	        resMap.put("msg" , "删除附件成功！");
	        
	        
	        try{
			   //删除附件记录日志
				logService.saveLog(LOG_OPERATION_TYPE.CREATE.toString(), 
						logService.getDetailInfo("log.fileupload.delete",
								baseService.getUserName(),oldF.getUploadfilename()));
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		

        outputJSONResult(resMap, response);
	}
	
    private static void outputJSONResult(Object result, HttpServletResponse response) {

        JSONObject jsonObject = JSONObject.fromObject(result);
        try {
            response.setHeader("ContentType", "text/json");
            response.setCharacterEncoding("utf-8");
            PrintWriter pw = response.getWriter();
            pw.write(jsonObject.toString());
            pw.flush();
            pw.close();

        } catch (IOException e) {
        	
        	e.printStackTrace();
        }
    }
    
    @RequestMapping(value = {"*/fileUploads","*/*/fileUploads"})
    public void doSubmit(String modelTbId, String model, String type, String action,
    		MultipartHttpServletRequest multipartRequest, HttpServletResponse response) {
		
		// 取得文件列表
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String fileId = "";
        String uploadFileName = "";
        // 设置文件路径
       // String ctxPath = "/data/order/change/pic/" + dateFormat.format(now) + "/";
        ApplicationProperties app = new ApplicationProperties();
        String filePath = app.getValueByKey("file.upload.path") + dateFormat.format(now) + "/";

        File folder = new File(filePath);

		if (!folder.exists()) {
			folder.mkdirs();
		}
        List<String> urls = new ArrayList<String>();
        
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
        	
             // 上传文件名
             MultipartFile mf = entity.getValue();
             String prefix = mf.getOriginalFilename().substring(mf.getOriginalFilename().lastIndexOf(".")+1);
             String fileName = modelTbId + "_" + now.getTime() + "." + prefix;
             uploadFileName = mf.getOriginalFilename();
             File uploadFile = new File(filePath + fileName);
             try {
				FileCopyUtils.copy(mf.getBytes(), uploadFile);
				AttachmentCommonInfo f = new AttachmentCommonInfo();
				f.setFilename(mf.getOriginalFilename());
				f.setSaveName(fileName);
				//f.setUploadfilename(mf.getOriginalFilename());
				//f.setModular(modelTbId);
				if(modelTbId != "" && modelTbId != null && modelTbId.length() > 0){
					f.setMainTableId(Integer.parseInt(modelTbId));
				}
				f.setModular(model);
				f.setType(type);
				f.setAction(action);
				f.setPathname(filePath);
				f.setStatus(1);
				f.setUploader(baseService.getShowName());
				f.setUploadTime(new Date());
				attachmentCommonInfoMapper.insertSelective(f);
				fileId = f.getId()+"";
				urls.add(filePath + fileName);
				
				try{
				   //上传附件记录日志
					logService.saveLog(LOG_OPERATION_TYPE.CREATE.toString(), 
							logService.getDetailInfo("log.fileupload.create",
									baseService.getUserName(),mf.getOriginalFilename()));
				}catch(Exception e){
					e.printStackTrace();
				}
			 } catch (IOException e) {
				
				e.printStackTrace();
			 }
        }
        
        Map<String, Object> resMap = new HashMap<String, Object>();

        resMap.put("status", "1");
        resMap.put("url" , urls);
        resMap.put("fileId" , fileId);
        resMap.put("fileName" , uploadFileName);

        outputJSONResult(resMap, response);
	}
	
	@RequestMapping(value = {"*/downFiles","*/*/downFiles"})
	public void doDown(String id, HttpServletResponse response, HttpServletRequest request)throws Exception {
		Map<String, Object> resMap = new HashMap<String, Object>();
		String filename = "";
		if(StringUtils.isEmpty(id)){
			resMap.put("status", "0");
	        resMap.put("msg" , "附件ID为NULL！");

		}else{
			
			AttachmentCommonInfo f = attachmentCommonInfoMapper.selectByPrimaryKey(id);
		
			File file = new File(f.getPathname(), f.getSaveName());
            if (file.exists()) {
            	if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {  
            	    filename = URLEncoder.encode(f.getFilename(), "UTF-8");  
            	} else {  
            	    filename = new String(f.getFilename().getBytes("UTF-8"), "ISO8859-1");  
            	}
        		response.reset();
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition",
                        "attachment;fileName=" + filename);// 设置文件名
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
                }
	                
				//resMap.put("status", "1");
		       // resMap.put("msg" , "下载附件成功！");
		        
		        
		        try{
				   //下载附件记录日志
					logService.saveLog(LOG_OPERATION_TYPE.CREATE.toString(), 
							logService.getDetailInfo("log.fileupload.view",
									baseService.getUserName(),f.getFilename()));
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		
		// outputJSONResult(resMap, response);
	}
	
	@RequestMapping(value = {"*/deleteFiles","*/*/deleteFiles"})
	public void doDelete(String id, HttpServletResponse response) {
		
		Map<String, Object> resMap = new HashMap<String, Object>();
		if(StringUtils.isEmpty(id)){
			resMap.put("status", "0");
	        resMap.put("msg" , "附件ID为NULL！");

		}else{
			
			AttachmentCommonInfo oldF = attachmentCommonInfoMapper.selectByPrimaryKey(id);
			AttachmentCommonInfo f = new AttachmentCommonInfo();
			f.setId(Integer.parseInt(id));
			f.setStatus(0);
			attachmentCommonInfoMapper.updateByPrimaryKeySelective(f);
			
			resMap.put("status", "1");
	        resMap.put("msg" , "删除附件成功！");
	        
	        try{
			   //删除附件记录日志
				logService.saveLog(LOG_OPERATION_TYPE.CREATE.toString(), 
						logService.getDetailInfo("log.fileupload.delete",
								baseService.getUserName(),oldF.getFilename()));
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		

        outputJSONResult(resMap, response);
	}
}
