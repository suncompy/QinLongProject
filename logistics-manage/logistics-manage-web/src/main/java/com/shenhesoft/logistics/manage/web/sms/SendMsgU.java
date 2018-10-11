package com.shenhesoft.logistics.manage.web.sms;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

public class SendMsgU {

    //产品名称:云通信短信API产品,开发者无需替换
    static final String product = "Dysmsapi";
    //产品域名,开发者无需替换
    static final String domain = "dysmsapi.aliyuncs.com";

    // TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    static final String accessKeyId = "LTAI19jBXrKzFvof";
    static final String accessKeySecret = "P3QO7gErLwnWFSifsv1lDl5nE4aOPH";
    
	public static String sendMsg(String telephone,String sigName,String code,String[] args){
		
		//错误提示码,发送短信成功返回"ok"  失败返回"error"
		String errorCode = null;
		
		try {
			
			//可自助调整超时时间
			System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
			System.setProperty("sun.net.client.defaultReadTimeout", "10000");
			
			//初始化acsClient,暂不支持region化
			IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
			
			IAcsClient acsClient = new DefaultAcsClient(profile);
			
			//组装请求对象-具体描述见控制台-文档部分内容
			SendSmsRequest request = new SendSmsRequest();
			//必填:待发送手机号
			request.setPhoneNumbers(telephone);
			//必填:短信签名-可在短信控制台中找到
			//request.setSignName("合肥市深合软件");
			request.setSignName(sigName);
			//必填:短信模板-可在短信控制台中找到
			//request.setTemplateCode("SMS_113450687");
			request.setTemplateCode(code);
			//可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
			
			if(code.equals("SMS_113450687")){
				
				request.setTemplateParam("{\"code\":\""+args[0]+"\"}");
			}
			
		//	request.setTemplateParam("{\"code\":\""+checkedCode+"\"}");
			//可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
		//	request.setOutId("yourOutId");
			
			
			//hint 此处可能会抛出异常，注意catch(这个是短信发送的重要语句)
			SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
			
			errorCode = "ok";
			
		} catch (Exception e) {
			errorCode = "error";
			e.printStackTrace();
		}

        return errorCode;
    }
	
	/*public SendSmsRequest setTemplateParam(String code,String[] arg){
		if(code.equals("SMS_113450687")){
			request.set
		}
		
	}*/
	
	
	//测试用的主方法
	public static void main(String[] args) {

		//errorCode 如果程序出错反error  运行正确反ok
	/*	String errorcode =SendMsgU.sendMsg("18715083549", "2456145");
		
		if(errorcode==null||!errorcode.equals("ok")){
			System.err.println("激活码发送出现异常");
		}else{
			System.out.println("激活码发送成功");
		}*/
	}
}
