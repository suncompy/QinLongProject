package com.shenhesoft.logistics.utils;

import java.io.File;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Base64Utils;

import com.shenhesoft.logistics.common.util.Constants;
import com.shenhesoft.logistics.common.util.LogisticsResult;
import com.shenhesoft.logistics.common.util.RandomNum;
import com.shenhesoft.logistics.common.util.StringUtil;

/**
 * @description:图片删除
 * 
 * @author shilvfei
 * 
 * @date 2017年11月22日
 */
public class ImageUtil {
	
	/**
	 * @description 删除文件
	 * @date 2017年11月29日
	 * @author shilvfei
	 * @param f
	 * @return
	 */
	public static LogisticsResult recurDelete(File f){  
	    try{  
	    for(File fi:f.listFiles()){  
	        if(fi.isDirectory()){  
	            recurDelete(fi);  
	        }  
	        else{  
	            fi.delete();  
	        }  
	    }  
	    	f.delete();  
	    }  
	    catch(NullPointerException n){  
	       return LogisticsResult.build(400, "不存在该文件");
	    } 
	    return LogisticsResult.ok();
	}  
	private static Logger logger =LoggerFactory.getLogger(ImageUtil.class);
	
	public static LogisticsResult base64UpLoad(String photoPath,String base64Data, HttpSession session){ 
		
        try{  
           // logger.debug("上传文件的数据："+base64Data);
            String dataPrix = "";
            String data = "";
            
            logger.debug("对数据进行判断");
            if(base64Data == null || "".equals(base64Data)){
            	return LogisticsResult.build(400, "上传失败，上传图片数据为空");
            }else{
                String [] d = base64Data.split("base64,");
                if(d != null && d.length == 2){
                    dataPrix = d[0];
                    data = d[1];
                }else{
                	return LogisticsResult.build(400, "上传失败，数据不合法");
                }
            }

            logger.debug("对数据进行解析，获取文件名和流数据");
            String suffix = "";
            if("data:image/jpeg;".equalsIgnoreCase(dataPrix)){//data:image/jpeg;base64,base64编码的jpeg图片数据
                suffix = ".jpg";
            } else if("data:image/x-icon;".equalsIgnoreCase(dataPrix)){//data:image/x-icon;base64,base64编码的icon图片数据
                suffix = ".ico";
            } else if("data:image/gif;".equalsIgnoreCase(dataPrix)){//data:image/gif;base64,base64编码的gif图片数据
                suffix = ".gif";
            } else if("data:image/png;".equalsIgnoreCase(dataPrix)){//data:image/png;base64,base64编码的png图片数据
                suffix = ".png";
            }else{
                return LogisticsResult.build(400, "上传图片格式不合法");
            }
            String tempFileName = RandomNum.getUUid() + suffix;
            logger.debug("生成文件名为："+tempFileName);

            //因为BASE64Decoder的jar问题，此处使用spring框架提供的工具包
            byte[] bs = Base64Utils.decodeFromString(data);
            try{
                //使用apache提供的工具类操作流
            	String path = null;
            	if(!StringUtil.isEmpty(Constants.FILE_PATH)) {
            		path=session.getServletContext().getRealPath(photoPath); 
            	} else {
            		path = Constants.FILE_PATH.trim() + photoPath;
            	}
                FileUtils.writeByteArrayToFile(new File(path, tempFileName), bs);
            }catch(Exception ee){
                //throw new Exception(""+ee.getMessage());
                return LogisticsResult.build(400, "上传失败，写入文件失败");
            }
            logger.debug("上传成功");
            return LogisticsResult.ok(tempFileName);
        }catch (Exception e) {  
        	return LogisticsResult.build(400, "上传失败，写入文件失败");
        }
    }
	
}
