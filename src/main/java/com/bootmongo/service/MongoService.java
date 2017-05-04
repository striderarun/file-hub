package com.bootmongo.service;

import com.bootmongo.domain.File;
import com.bootmongo.request.UploadRequest;

public interface MongoService {

	public void uploadFile();
	
	public void uploadZipFile(UploadRequest uploadRequest);
	
	public void downloadFile(String id);
	
	public File fetchFilesById(String id);
	
	
}
