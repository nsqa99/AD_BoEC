package com.example.demo.common;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

public class CloudinaryUtils {
	private static Cloudinary instance;
	
	@Value("${cloudinary.cloud_name}")
	private static String cloudName;
	@Value("${cloudinary.api_key}")
	private static String key;
	@Value("${cloudinary.api_secret}")
	private static String secret;
	
	public static Cloudinary getInstance() {
		if (instance == null) {
			instance = new Cloudinary(ObjectUtils.asMap(
					"cloud_name", "desp-ltd",
					"api_key", "324747688188936",
					"api_secret", "4tUME-mh6fXAsBOW8CE59UWOlNs"));
		}
		return instance;
	}
	
	public static String uploadImage(MultipartFile file) throws IOException {
		Map uploadResult = getInstance().uploader().upload(file.getBytes(), ObjectUtils.asMap("resource_type", "auto"));
		if (uploadResult == null) return null;
		return (String) uploadResult.get("url");
	}
}
