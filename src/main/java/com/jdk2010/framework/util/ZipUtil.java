package com.jdk2010.framework.util;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

 
public class ZipUtil {
 	private static int BUF_SIZE = 1024;
	private static String ZIP_ENCODEING = "GBK";

	public ZipUtil() {
		this(1024 * 10);
	}

	public ZipUtil(int bufSize) {
		BUF_SIZE = bufSize;
	}

	/**
	 * 压缩文件或文件夹
	 * 
	 * @param zipFileName
	 * @param inputFile
	 * @throws Exception
	 */
	public static  void zip(String zipFileName, String inputFile) throws Exception {
		zip(zipFileName, new File(inputFile));
	}

	/**
	 * 压缩文件或文件夹
	 * 
	 * @param zipFileName
	 * @param inputFile
	 * @throws Exception
	 */
	public static void zip(String zipFileName, File inputFile) throws Exception {
		// 未指定压缩文件名，默认为"ZipFile"
		if (zipFileName == null || zipFileName.equals(""))
			zipFileName = "ZipFile";

		// 添加".zip"后缀
		if (!zipFileName.endsWith(".zip"))
			zipFileName += ".zip";

		// 创建文件夹
		String path = Pattern.compile("[\\/]").matcher(zipFileName).replaceAll(File.separator);
		int endIndex = path.lastIndexOf(File.separator);
		path = path.substring(0, endIndex);
		File f = new File(path);
		f.mkdirs();
		// 开始压缩
		{
			ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipFileName)));
			//zos.setEncoding(ZIP_ENCODEING);
			compress(zos, inputFile, "");
 			zos.close();
		}
	}
 

	/**
	 * 压缩一个文件夹或文件对象到已经打开的zip输出流 <b>不建议直接调用该方法</b>
	 * 
	 * @param zos
	 * @param f
	 * @param fileName
	 * @throws Exception
	 */
	public static void compress(ZipOutputStream zos, File f, String fileName) throws Exception {
 		if (f.isDirectory()) {
			// 压缩文件夹
			File[] fl = f.listFiles();
			zos.putNextEntry(new ZipEntry(fileName + "/"));
			fileName = fileName.length() == 0 ? "" : fileName + "/";
			for (int i = 0; i < fl.length; i++) {
				compress(zos, fl[i], fileName + fl[i].getName());
			}
		} else {
			// 压缩文件
			zos.putNextEntry(new ZipEntry(fileName));
			FileInputStream fis = new FileInputStream(f);
			inStream2outStream(fis, zos);
			fis.close();
			zos.closeEntry();
		}
	}

	private static void inStream2outStream(InputStream is, OutputStream os) throws IOException {
		BufferedInputStream bis = new BufferedInputStream(is);
		BufferedOutputStream bos = new BufferedOutputStream(os);
		int bytesRead = 0;
		byte[] buffer = new byte[BUF_SIZE];
		
		
		   while (bis.read(buffer, 0, BUF_SIZE)!= -1) {
			   bos.write(buffer); // 将流写入
           }
		  // bos.flush();
		   //bos.close();
//		for (byte[] buffer = new byte[BUF_SIZE]; ((bytesRead = bis.read(buffer, 0, BUF_SIZE)) != -1);) {
//			bos.write(buffer, 0, bytesRead); // 将流写入
//		}
	} 

	public static void main(String[] args) {
		try {
 			ZipUtil.zip("D:\\1.zip", "D:\\1");
			//t.unZip("d:\\temp\\xzk2.zip", "E:\\驾校模拟考试2");
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}
}
