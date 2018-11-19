package com.zzqx.support.utils.security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class DESUtils {
	
	private static final String DEFAULT_KEY = "ZZQX-VERSION-201609";
	
	public static String encode(String value) throws Exception {
		return encode(DEFAULT_KEY, value);
	}

	public static String encode(String id, String value) throws Exception {
		byte rawKeyData[] = id.getBytes();
		DESKeySpec dks = new DESKeySpec(rawKeyData);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey key = keyFactory.generateSecret(dks);
		Cipher c = Cipher.getInstance("DES");
		c.init(Cipher.ENCRYPT_MODE, key);
		byte[] cipherByte = c.doFinal(value.getBytes());
		String dec = new BASE64Encoder().encode(cipherByte);
		return dec;
	}

	public static String decode(String key) throws Exception {
		return decode(DEFAULT_KEY, key);
	}
	
	public static String decode(String id, String key) throws Exception {
		byte[] dec = new BASE64Decoder().decodeBuffer(key);
		byte rawKeyData[] = id.getBytes();
		DESKeySpec dks = new DESKeySpec(rawKeyData);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey k = keyFactory.generateSecret(dks);
		Cipher c = Cipher.getInstance("DES");
		c.init(Cipher.DECRYPT_MODE, k);
		byte[] clearByte = c.doFinal(dec);
		return new String(clearByte);
	}
}
