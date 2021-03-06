package com.jersey.demo;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;

import org.apache.commons.io.FileUtils;

import com.jersey.utils.FileNameCreator;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

public class JerseyClient {

	public static void main(String[] args) throws IOException {
		// 要上传的文件
		JFileChooser chooser = new JFileChooser();
		chooser.showOpenDialog(null);
		File file = chooser.getSelectedFile();
		
		// 图片名称的生成器
		String url = FileNameCreator.creatRandomName("http://localhost:8080/file_server/upload/", file.getName());
		// jersey client
		Client client = new Client();
		// 设置请求路径
		WebResource resource = client.resource(url);
		// 将文件转换为byte数组
		byte[] buf = FileUtils.readFileToByteArray(file);
		//发送
		resource.put(String.class,buf);
		System.out.println("发送成功");
	}
}
