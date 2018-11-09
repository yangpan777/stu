package com.jt.manage.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.druid.filter.AutoLoad;
import com.jt.common.vo.PicUploadResult;
import com.jt.manage.service.FileService;

@Controller
public class FileController {
	
	@Autowired
	private FileService fileService;
	
	@RequestMapping("/file")
	public String file(MultipartFile pic) throws IllegalStateException, IOException{
		//1.定义文件夹
		File file = new File("D:/keqianziliao/putPic");
		
		//2.检验文件夹及是否存在
		
		if(!file.exists()){
			//创建多级文件夹
			file.mkdirs();
		}
		//获取文件名称   abc.jpg
		String fileName = pic.getOriginalFilename();
		pic.transferTo(new File("D:/keqianziliao/putPic/"+fileName));//实现文件上传
		
		return "redirect:/File.jsp";
	}
	@RequestMapping("/pic/upload")
	@ResponseBody
	public PicUploadResult fileUpload(MultipartFile uploadFile){
		return fileService.fileUpload(uploadFile);
	}
}

