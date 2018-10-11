package com.shenhesoft.logistics.common.util;

import java.util.LinkedHashMap;
import java.util.Map;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

public class SendMsgUtils {
    private static PropertieUtil propHelper = new PropertieUtil("properties/sms.properties");
    //产品名称:云通信短信API产品,开发者无需替换
    private static final String product = "Dysmsapi";
    //产品域名,开发者无需替换
    private static final String domain = "dysmsapi.aliyuncs.com";

    // TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    private static String accessKeyId = propHelper.get("accessKeyId");
    private static String accessKeySecret = propHelper.get("accessKeySecret");
    static{
      propHelper = new PropertieUtil("properties/sms.properties");
      accessKeyId = propHelper.get("accessKeyId");
      accessKeySecret = propHelper.get("accessKeySecret");
    }
    
	public static String sendMsg(String telephone,String checkedCode){
		
		//错误提示码,发送短信成功返回"ok"  失败返回"error"
		String errorCode = null;
		
		try {
			IAcsClient acsClient = new DefaultAcsClient(initProfile());
			
			//组装请求对象-具体描述见控制台-文档部分内容
			SendSmsRequest request = new SendSmsRequest();
			//必填:待发送手机号
			request.setPhoneNumbers(telephone);
			//必填:短信签名-可在短信控制台中找到
			request.setSignName(propHelper.get("chkCode.signName"));
			//必填:短信模板-可在短信控制台中找到
			request.setTemplateCode(propHelper.get("chkCode.tpl"));
			//可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
			request.setTemplateParam("{\"code\":\""+checkedCode+"\"}");
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
	public static boolean sendMsgOK(String accessKeyId,String accessKeySecret,String phone,String signName,String tpl,Map<String,Object> msgMap){
	  SendSmsResponse sendSmsResponse = sendMsg(accessKeyId,accessKeySecret,phone, signName, tpl, msgMap);
      return "OK".equalsIgnoreCase(sendSmsResponse.getCode())?true:false;
    }
	public static boolean sendMsgOK(String accessKeyId,String accessKeySecret,String phone,String signName,String tpl,String msg){
      SendSmsResponse sendSmsResponse = sendMsg(accessKeyId,accessKeySecret,phone, signName, tpl, msg);
      return "OK".equalsIgnoreCase(sendSmsResponse.getCode())?true:false;
    }
	private static SendSmsResponse sendMsg(String accessKeyId,String accessKeySecret,String phone,String signName,String tpl,Map<String,Object> msgMap){
      return sendMsg(accessKeyId,accessKeySecret,phone, signName, tpl, JsonUtils.objectToJson(msgMap), null);
    }
	private static SendSmsResponse sendMsg(String accessKeyId,String accessKeySecret,String phone,String signName,String tpl,String msg){
	  return sendMsg(accessKeyId,accessKeySecret,phone, signName, tpl, msg, null);
	}
	private static SendSmsResponse sendMsg(String accessKeyId,String accessKeySecret,String phone,String signName,String tpl,String msg,String outId){
      SendSmsResponse sendSmsResponse =null;
      try {
          IAcsClient acsClient = new DefaultAcsClient(initProfile(accessKeyId,accessKeySecret));
          //组装请求对象-具体描述见控制台-文档部分内容
          SendSmsRequest request = new SendSmsRequest();
          //必填:待发送手机号支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
          request.setPhoneNumbers(phone);
          //必填:短信签名-可在短信控制台中找到
          request.setSignName(signName);
          //必填:短信模板-可在短信控制台中找到
          request.setTemplateCode(tpl);
          //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为"{\"name\":\"Tom\", \"code\":\"123\"}"
          request.setTemplateParam(msg);
          //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
          if(null != outId){
            request.setOutId(outId);
          }
          //hint 此处可能会抛出异常，注意catch(这个是短信发送的重要语句)
          sendSmsResponse = acsClient.getAcsResponse(request);            
      } catch (Exception e) {
          e.printStackTrace();
      }
      return sendSmsResponse;
  }
	private static IClientProfile initProfile() throws ClientException{
      //可自助调整超时时间
      System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
      System.setProperty("sun.net.client.defaultReadTimeout", "10000");
      
      //初始化acsClient,暂不支持region化
      IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
      DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
      return profile;
    }
	private static IClientProfile initProfile(String accessKeyId,String accessKeySecret) throws ClientException{
      //可自助调整超时时间
      System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
      System.setProperty("sun.net.client.defaultReadTimeout", "10000");
      
      //初始化acsClient,暂不支持region化
      IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
      DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
      return profile;
	}
	//测试用的主方法
	public static void main(String[] args) {
	  Map<String,Object> msgMap = new LinkedHashMap<String,Object>();
	  msgMap.put("code", "aaa");
	  System.out.println(JsonUtils.objectToJson(msgMap));
	  msgMap = new LinkedHashMap<String,Object>();
      msgMap.put("content", "火运送达已出发");
      System.out.println(JsonUtils.objectToJson(msgMap));
      boolean b =SendMsgUtils.sendMsgOK("LTAI19jBXrKzFvof","P3QO7gErLwnWFSifsv1lDl5nE4aOPH","13956978367", "合肥市深合软件", "SMS_125021912", msgMap);
      System.out.println(b);
		//errorCode 如果程序出错反error  运行正确反ok
		/*String errorcode =SendMsgUtils.sendMsg("13956978367", "2456145");
		
		if(errorcode==null||!errorcode.equals("ok")){
			System.err.println("激活码发送出现异常");
		}else{
			System.out.println("激活码发送成功");
		}*/
	}
}
