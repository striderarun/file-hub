package com.bootmongo.service.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import javax.annotation.Resource;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Service;

import com.bootmongo.domain.File;
import com.bootmongo.request.UploadRequest;
import com.bootmongo.service.MongoService;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;

@Service
public class MongoServiceImpl implements MongoService {

	@Autowired
	private GridFsOperations gridOperations;
	
	@Autowired
	private MongoOperations mongoOperations;
	
	@Override
	public void uploadFile() {
		DBObject metaData = new BasicDBObject();
		List<String> words = Arrays.asList("This","is", "about","spring","data");
		
		metaData.put("author", "Arun Mohan");
		metaData.put("domain", "Database");
		metaData.put("technology", "NoSQL");
		metaData.put("projectName", "Prices");
		metaData.put("keywords", words);

		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream("/Users/Arun_Subramonian/Downloads/Zip.zip");
			gridOperations.store(inputStream, "NewZip", "application/zip", metaData);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		System.out.println("Done");
	}
	
	@Override
	public void uploadZipFile(UploadRequest uploadRequest) {
		DBObject metaData = new BasicDBObject();
		List<String> words = new ArrayList<String>();
		
		StringTokenizer tokenizer = new StringTokenizer(uploadRequest.getDescription());
		while (tokenizer.hasMoreElements()) {
			String keyword = (String) tokenizer.nextElement();
			words.add(keyword);
		}
		
		metaData.put("author", uploadRequest.getAuthor());
		metaData.put("domain", uploadRequest.getDomain());
		metaData.put("technology", uploadRequest.getTechnology());
		metaData.put("projectName", uploadRequest.getProjectName());
		metaData.put("keywords", words);

		InputStream inputStream = null;
		try {
			inputStream = uploadRequest.getFile().getInputStream();
			gridOperations.store(inputStream, uploadRequest.getFile().getOriginalFilename(), uploadRequest.getFile().getContentType(), metaData);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		System.out.println("Done");
	}
	
	@Override
	public void downloadFile(String id) {
		ObjectId objectId = new ObjectId(id);
		List<GridFSDBFile> result = gridOperations.find(new Query().addCriteria(Criteria.where("_id").is(objectId)));
		for (GridFSDBFile file : result) {
			try {
				System.out.println(file.getFilename());
				System.out.println(file.getContentType());
				file.writeTo("/Users/Arun_Subramonian/Downloads/"  + file.getFilename());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		System.out.println("Done");
	}
	
	
	@Override
	public File fetchFilesById(String id) {		
		ObjectId objectId = new ObjectId(id);		
		File file = mongoOperations.findById(objectId, File.class);
		return file;
	}
	
	
}
