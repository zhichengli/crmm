package com.zcl.sms.utils;


import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

public class ALISMS {
    private static String aliRegionId="cn-hangzhou";
    private static String aliAccessKeyId="LTAI4FeNL1msEyMdtgK4Xuwn";
    private static String aliAccessKeySecret="OeonRbJBtLdHDLILYGXlBXllFOhfyw";
    public static void sendSMS(String phone, String code){

        DefaultProfile profile = DefaultProfile.getProfile(aliRegionId, aliAccessKeyId, aliAccessKeySecret);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
//        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "霹雳五号");
        request.putQueryParameter("TemplateCode", "SMS_180341789");
        StringBuffer sb = new StringBuffer();
        sb.append("{\"code\":\"");
        sb.append(String.valueOf(code));
        sb.append("\"}");
        request.putQueryParameter("TemplateParam", sb.toString());
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

}
