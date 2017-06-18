package com.bootmongo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.bootmongo.domain.File;
import com.bootmongo.request.UploadRequest;
import com.bootmongo.service.MongoService;

@RestController
@RequestMapping(value = "/file")
public class AppController {

	@Autowired
	private MongoService mongoService;
	
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public void uploadFile() {
       mongoService.uploadFile();
    }
	
	@RequestMapping(value = "/downloadFile", method = RequestMethod.GET)
    public void downloadFile(@RequestParam(value = "id", required = true) final String id) {
       mongoService.downloadFile(id);
    }
	
	@RequestMapping(value = "/getFileById", method = RequestMethod.GET)
    public File fetchFiles(@RequestParam(value = "id", required = true) final String id) {
       return mongoService.fetchFilesById(id);
    }

	@RequestMapping(value="/upload", method=RequestMethod.POST)
	@ResponseBody
    public void handleFileUpload(@RequestParam("author") String author, @RequestParam("domain") String domain, @RequestParam("technology") String technology, @RequestParam("projectName") String projectName, @RequestParam("description") String description, @RequestParam("file") MultipartFile file){
        UploadRequest uploadRequest = new UploadRequest();
        uploadRequest.setAuthor(author);
        uploadRequest.setDescription(description);
        uploadRequest.setDomain(domain);
        uploadRequest.setProjectName(projectName);
        uploadRequest.setTechnology(technology);
        uploadRequest.setFile(file);
        mongoService.uploadZipFile(uploadRequest);
        
    }
}