package com.gw.tool.utils;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
/**
 * 
 *                       
 * @Filename DesUtils.java
 *
 * @Description 
 *
 * @Version 1.0
 *
 * @Author yangjun
 *
 * @Email yangjun@yiji.com
 *       
 * @History
 *<li>Author: yangjun</li>
 *<li>Date: 2016年5月10日</li>
 *<li>Version: 1.0</li>
 *<li>Content: create</li>
 *
 */
public class DesUtils {
	
	/**
	 * 密钥算法
	 */
	public static final String KEY_ALGORITHM = "AES";
	/**
	 * 加密、解密算法 / 工作模式 / 填充方式 
	 */
	public static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
	
	/**
	 * 转换密钥
	 * @param key 二进制密钥
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private static Key toKey(byte[] key) throws Exception{
		//实例化密钥材料
		SecretKey secretKey = new SecretKeySpec(key,KEY_ALGORITHM);
		return secretKey;
	}
	
	/**
	 * 转换密钥
	 * @param key 二进制密钥
	 * @param keyAlgorithm 密钥算法
	 * @return
	 * @throws Exception
	 */
	private static Key toKey(byte[] key,String keyAlgorithm) throws Exception{
		
		keyAlgorithm = StringUtils.isNotBlank(keyAlgorithm)?keyAlgorithm:KEY_ALGORITHM;
		//实例化密钥材料
		SecretKey secretKey = new SecretKeySpec(key,keyAlgorithm);
		return secretKey;
	}
	
	/**
	 * 加密
	 * @param data 待加密数据
	 * @param key 密钥
	 * @param cipherAlgorithm 加密、解密算法 / 工作模式 / 填充方式
	 * @return byte[] 加密数据
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] data, byte[] key ,String keyAlgorithm,String cipherAlgorithm) throws Exception {
		
		keyAlgorithm = StringUtils.isNotBlank(keyAlgorithm)?keyAlgorithm:KEY_ALGORITHM;
		
		cipherAlgorithm = StringUtils.isNotBlank(cipherAlgorithm)?cipherAlgorithm:CIPHER_ALGORITHM;
		//还原密钥
		Key k = toKey(key,keyAlgorithm);
		//实例化
		Cipher cipher = Cipher.getInstance(cipherAlgorithm);
		//初始化，设置为解密模式
		cipher.init(Cipher.ENCRYPT_MODE, k);
		//执行解密操作
		return cipher.doFinal(data);
	}
	/**
	 * 加密
	 * @param data 待加密数据
	 * @param key 密钥
	 * @return byte[] 加密数据
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] data, byte[] key) throws Exception {
		return encrypt(data , key, KEY_ALGORITHM ,CIPHER_ALGORITHM);
	}
	/**
	 * 解密
	 * @param data 待解密数据
	 * @param key 密钥
	 * @param cipherAlgorithm 加密、解密算法 / 工作模式 / 填充方式
	 * @return byte[] 解密数据
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] data,byte[] key , String keyAlgorithm, String cipherAlgorithm) throws Exception{
		
		keyAlgorithm = StringUtils.isNotBlank(keyAlgorithm)?keyAlgorithm:KEY_ALGORITHM;
		
		cipherAlgorithm = StringUtils.isNotBlank(cipherAlgorithm)?cipherAlgorithm:CIPHER_ALGORITHM;
		//还原密钥
		Key k = toKey(key,keyAlgorithm);
		//实例化
		Cipher cipher = Cipher.getInstance(cipherAlgorithm);
		//初始化，设置为解密模式
		cipher.init(Cipher.DECRYPT_MODE, k);
		//执行解密操作
		return cipher.doFinal(data);
	}
	/**
	 * 解密
	 * @param data 待解密数据
	 * @param key 密钥
	 * @return byte[] 解密数据
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] data, byte[] key) throws Exception {
		return decrypt(data , key,KEY_ALGORITHM ,CIPHER_ALGORITHM);
	}
	
	/**
	 * 生成密钥
	 * @return byte[] 二进制密钥
	 * @throws Exception
	 */
	public static byte[] initKey() throws Exception{
		//实例化
		KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);
		//AES 要求密钥长度为128位、192位或256位
		kg.init(128);
		//生成秘密密钥
		SecretKey secretKey = kg.generateKey();
		//获取密钥的二进制编码形式
		return secretKey.getEncoded();
	}

	/**
	 * 生成密钥
	 * @return byte[] 二进制密钥
	 * @throws Exception
	 */
	public static byte[] initKey(String keyAlgorithm) throws Exception{
		//实例化
		KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);
		//AES 要求密钥长度为128位、192位或256位
		kg.init(128);
		//生成秘密密钥
		SecretKey secretKey = kg.generateKey();
		//获取密钥的二进制编码形式
		return secretKey.getEncoded();
	}
	
	/**
	 * 密钥
	 * @return 返回16进制字符串的KEY
	 * @throws Exception
	 */
	public static String getKey() throws Exception {
		return byteArr2HexStr(initKey());
	}
	public static String map2String(Map<String, Object> data) {
		StringBuffer sf = new StringBuffer("");

		if (data != null) {
//			TreeMap<String, Object> tree = new TreeMap<String, Object>();
			Iterator<Entry<String, Object>> it = data.entrySet().iterator();
//			while (it.hasNext()) {
//				Entry<String, Object> en = it.next();
//				if ("signature".equals(en.getKey().trim())) {
//					continue;
//				}
//				tree.put(en.getKey(), en.getValue());
//			}
//			it = tree.entrySet().iterator();

			while (it.hasNext()) {
				Entry<String, Object> en = it.next();
				sf.append(en.getKey() + "=" + en.getValue() + "&");
			}
		}

		return sf.substring(0, sf.length() - 1);
	}
	public static String enCodeByAES(Map<String, Object> paramMap, String keyText)  {
		String sourceDatas = map2String(paramMap);
		
		return enCodeByAES(sourceDatas.getBytes(),hexStr2ByteArr(keyText),KEY_ALGORITHM,CIPHER_ALGORITHM);
	}
	/**
	 * AES加密
	 * @param sourceDatas
	 * @param keyText
	 * @throws Exception 
	 */
	public static String enCodeByAES(String sourceDatas, String keyText)  {
		return enCodeByAES(sourceDatas.getBytes(),hexStr2ByteArr(keyText),KEY_ALGORITHM,CIPHER_ALGORITHM);
	}
	
	/**
	 * AES加密
	 * @param sourceDatas
	 * @param keyText
	 */
	public static String enCodeByAES(byte[] sourceDatas, byte[] keyText) {
		return enCodeByAES(sourceDatas,keyText,KEY_ALGORITHM,CIPHER_ALGORITHM);
	}
	
	/**
	 * AES加密
	 * @param sourceDatas
	 * @param keyText
	 * @param cipherAlgorithm 加密、解密算法 / 工作模式 / 填充方式
	 */
	public static String enCodeByAES(byte[] sourceDatas, byte[] keyText , String keyAlgorithm,String cipherAlgorithm ) {
		String enCode = "";
		try {
			byte[] encryptData = encrypt(sourceDatas, keyText,keyAlgorithm,cipherAlgorithm);
			enCode = byteArr2HexStr(encryptData);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return enCode;
	}

	/**
	 * AES解密
	 * @param secertDatas
	 * @param keyText
	 */
	public static String deCodeByAES(byte[] secertDatas, byte[] keyText) throws Exception{
		return deCodeByAES(secertDatas,keyText,KEY_ALGORITHM,CIPHER_ALGORITHM);
	}
	
	/**
	 * AES解密
	 * @param secertDatas
	 * @param keyText
	 */
	public static String deCodeByAES(String secertDatas, String keyText) throws Exception {
		return deCodeByAES(hexStr2ByteArr(secertDatas),hexStr2ByteArr(keyText),KEY_ALGORITHM,CIPHER_ALGORITHM);
	}
	
	/**
	 * AES解密
	 * @param secertDatas
	 * @param keyText
	 * @param cipherAlgorithm 加密、解密算法 / 工作模式 / 填充方式
	 */
	public static String deCodeByAES(byte[] secertDatas, byte[] keyText , String keyAlgorithm,String cipherAlgorithm ) {
		String deCode = "";
		try {
			byte[] decryptData = decrypt(secertDatas, keyText,keyAlgorithm,cipherAlgorithm);
			deCode =new String(decryptData);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return deCode;
	}
	
	/**
	 * 将byte数组转换为表示16进制值的字符串， 如：byte[]{8,18}转换为：0813， 和public static byte[]
	 * hexStr2ByteArr(String strIn) 互为可逆的转换过程
	 * 
	 * @param arrB 需要转换的byte数组
	 * @return 转换后的字符串
	 * @throws Exception 本方法不处理任何异常，所有异常全部抛出
	 */
	public static String byteArr2HexStr(byte[] arrB) throws Exception {
		int iLen = arrB.length;
		// 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍
		StringBuffer sb = new StringBuffer(iLen * 2);
		for (int i = 0; i < iLen; i++) {
			int intTmp = arrB[i];
			// 把负数转换为正数
			while (intTmp < 0) {
				intTmp = intTmp + 256;
			}
			// 小于0F的数需要在前面补0
			if (intTmp < 16) {
				sb.append("0");
			}
			sb.append(Integer.toString(intTmp, 16));
		}
		return sb.toString();
	}
	
	/**
	 * 将表示16进制值的字符串转换为byte数组， 和public static String byteArr2HexStr(byte[] arrB)
	 * 互为可逆的转换过程
	 * 
	 * @param strIn 需要转换的字符串
	 * @return 转换后的byte数组
	 * @throws Exception 本方法不处理任何异常，所有异常全部抛出
	 * @author abc
	 */
	public static byte[] hexStr2ByteArr(String strIn) {
		byte[] arrB = strIn.getBytes();
		int iLen = arrB.length;
		
		// 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
		byte[] arrOut = new byte[iLen / 2];
		for (int i = 0; i < iLen; i = i + 2) {
			String strTmp = new String(arrB, i, 2);
			arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
		}
		return arrOut;
	}

}
