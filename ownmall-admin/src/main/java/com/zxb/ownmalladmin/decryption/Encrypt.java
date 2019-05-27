package com.zxb.ownmalladmin.decryption;

import com.alibaba.fastjson.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Encrypt {

	private static Logger log4j = LoggerFactory.getLogger(Encrypt.class);
	
	public String CqrcbTest(String jsonstr)
	{
		log4j.debug("测试");
		return "{\"2\":\"efg\",\"1\":\"abc\"}";
	}
	
	/**
	 * 功能描述：加密方法
	 * @param
	 * @return String
	 * @author mafuying
	 * @date 2018-9-23 上午10:35:31
	 * @修改日志：
	 */
	public static String encypt(String jsonStr){
		JSONObject requestParam = new JSONObject();
		try {
			// 服务响应端，AES加密
			String encryptStr = RsaAesUtils.encryptContent(jsonStr);
			
			requestParam = JSONObject.parseObject(encryptStr);
			// 将传输业务数据串使用MD5加密,做验签使用
			requestParam.put("md5Params", AppMD5Util.getMD5(jsonStr));
			requestParam.put("rc", "1");
			requestParam.put("msg", "操作成功！");
		} catch (Exception e) {
			log4j.error("加密失败");
			log4j.error(e.getMessage(), e);
			requestParam.put("rc", "0");
			requestParam.put("msg", "加密失败！");
		}
//		log4j.debug("加密后返回参数：" + requestParam.toString());
		return requestParam.toString();
	}
}
