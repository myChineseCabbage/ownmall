package com.zxb.ownmalladmin.decryption;

import com.alibaba.fastjson.JSONObject;
import com.zxb.ownmalladmin.medecryption.EncryptionOperationUtils;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Decrypt {

	private static Logger log4j = LoggerFactory.getLogger(EncryptionOperationUtils.class);
	
	public String CqrcbTest(String jsonstr) {
		log4j.debug("测试");
		return "{\"2\":\"efg\",\"1\":\"abc\"}";
	}
	
	/**
	 * 功能描述：解密方法
	 * @param ：业务数据串
	 * @param ：对业务数据串（methodParams）进行MD5加密，验签使用
	 * @param：服务请求端随机生成的aesKey。
	 * @param ：服务请求端随机生成的aesIv，向量值。
	 * @return JSONObject
	 * @author mafuying
	 * @date 2018-9-23 上午10:32:47
	 * @修改日志：
	 */
	public static JSONObject decryption(JSONObject requestParam) {
		// 解密、验签
		String methodParams = requestParam.getString("methodParams");
		String md5RequestParams = requestParam.getString("md5Params");
		String aesKey = requestParam.getString("aesKey");
		String aesIv = requestParam.getString("aesIv");
		
//		log4j.debug("解密前methodParams：" + methodParams);
//		log4j.debug("解密前aesKey：" + aesKey);
//		log4j.debug("解密前aesIv：" + aesIv);
		try {
			// 服务响应端，将传输数据串使用AES解密
			methodParams = RsaAesUtils.decryptContent(methodParams, aesKey, aesIv);
			log4j.debug("解密后methodParams：" + methodParams);
		} catch (Exception e) {
			log4j.error(e.getMessage(), e);
			requestParam.put("rc", "0");
			requestParam.put("msg", "解密不正确");
		}
		
		//验签
		String md5RsponseParams = AppMD5Util.getMD5(methodParams);
		log4j.debug("前端md5Params：" + md5RequestParams);
		log4j.debug("后端md5Params：" + md5RsponseParams);
		if (md5RequestParams.equals(md5RsponseParams)) {
			requestParam = JSONObject.parseObject(methodParams);
			requestParam.put("rc", "1");
		} else {
			requestParam.put("rc", "0");
			requestParam.put("msg", "验签不正确");
		}
		return requestParam;
	}
}
