package com.data.dashboard.utils;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

@Service
public class Base64Utils {

	public byte[] encode(String encode) {
		byte[] bytesEncoded = Base64.encodeBase64(encode.getBytes());
		System.err.println("encoded value is " + new String(bytesEncoded));
		return bytesEncoded;
	}

	public String decode(byte[] decode) {
		// Decode data on other side, by processing encoded data
		byte[] valueDecoded = Base64.decodeBase64(decode);
		System.err.println("Decoded value is " + new String(valueDecoded));
		return new String(valueDecoded);
	}

}
