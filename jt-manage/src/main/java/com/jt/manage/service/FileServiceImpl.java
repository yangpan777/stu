package com.jt.manage.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jt.common.vo.PicUploadResult;

@Service
public class FileServiceImpl implements FileService{
	
	private String localPath = "D:/keqianziliao/putPic/";
	private String imageUrl = "http://image.jt.com/";
/**
 * 1.判断上传的文件是否为图片jpg/png/gif
 * 2.判断文件是否为恶意软件
 * 3.为了提高检索效率，采用分文件存储
 * 4.为了防止文件名名称重复 UUID + 随机数0~999
 * 5.判断文件夹是否存在，实现文件上传
 */
	@Override
	public PicUploadResult fileUpload(MultipartFile uploadFile) {
		
		PicUploadResult uploadResult = new PicUploadResult();
		
		//一.判断图片类型
		//2.获取文件名称  abc.jpg
		String fileName = uploadFile.getOriginalFilename();
		
		//3.为了文件名称大小写一致，全部转换成小写
		fileName = fileName.toLowerCase();
		if(!fileName.matches("^.*\\.(jpg|png|gif)$")){
			uploadResult.setError(1);
			return uploadResult;
		}
		//二.判断是否为恶意程序
		//1.将文件转成图片类型，货物宽度和高度
		try {
			BufferedImage bufferedImage = 
					ImageIO.read(uploadFile.getInputStream());
			int height = bufferedImage.getHeight();
			int width = bufferedImage.getWidth();
			
			if(height == 0 || width == 0){
				uploadResult.setError(1);	//表示不是图片
				return uploadResult;
			}
			
			//表示上传的文件是图片
			//三.分文件存储
			String dateDir= new SimpleDateFormat("yyyy/MM/dd").format(new Date());
			
			//3.1判断文件夹是否存在
			String localDirPath = localPath + dateDir;
			File fileDir = new File(localDirPath);
			if(!fileDir.exists()){
				fileDir.mkdirs();
			}
			//四.防止文件名称重复
			String uuid = UUID.randomUUID().toString().replace("-", "");	//32为UUID
			int randomNum = new Random().nextInt(1000);						//0~999随机数
			String fileType = 		//.jpg
					fileName.substring(fileName.lastIndexOf("."));			//截取后缀名
			
			String realFileName = uuid + randomNum + fileType;
			
			//五.实现文件上传,将文件上传到本地磁盘下
			String localFilePath = localDirPath + "/" + realFileName;
			uploadFile.transferTo(new File(localFilePath));
			/**
			 * 实现图片回显需要编辑虚拟路径
			 */
			String imageUrlPath = imageUrl + dateDir + "/" + realFileName;
			uploadResult.setUrl(imageUrlPath);
			uploadResult.setHeight(height+"");
			uploadResult.setWidth(width+"");
			
		} catch (IOException e) {
			e.printStackTrace();
			uploadResult.setError(1);//表示上传的不是图片
			return uploadResult;
		}
		return uploadResult;
	}

}
