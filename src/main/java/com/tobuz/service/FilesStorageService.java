package com.tobuz.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tobuz.controller.CommanRestController;

@Service
public class FilesStorageService {

	

	private static final Logger logger = LoggerFactory.getLogger(FilesStorageService.class);

	public static final String CLASS_NAME = "FilesStorageService";
	
	private Path root=null;
	
	@Value("${file.upload.path}")
	private String UploadPath;

	public void init() {
		try {
			root = Paths.get(UploadPath);
			logger.info(CLASS_NAME+": Path : {}",UploadPath);
			File file=new File(UploadPath);
			if(!file.exists())
			{
				Files.createDirectory(root);
			}
			else
			{
				logger.info(CLASS_NAME+": Path Already Exist : {}",UploadPath);
			}
		} catch (IOException e) {
			logger.info(CLASS_NAME+":Failed To created path : {}",UploadPath);
			throw new RuntimeException("Could not initialize folder for upload!");			
		}
	}

	public String uploadFile(MultipartFile file) {
		try {			
			init();
			String fileName=System.currentTimeMillis()+"_"+file.getOriginalFilename();
			Files.copy(file.getInputStream(), this.root.resolve(fileName));
			return fileName;
		} catch (Exception e) {
			throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
		}
	}
}
