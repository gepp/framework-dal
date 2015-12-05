package com.jdk2010.framework.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;

public class FileUtil {

 
	private static final int BUFFER = 1024;

	/**
	 * 功 能: 拷贝文件(只能拷贝文件)
	 * 
	 * @param strSourceFileName
	 *            指定的文件全路径名
	 * @param strDestDir
	 *            拷贝到指定的文件夹
	 * @return 如果成功true;否则false
	 */
	public static boolean copyTo(String strSourceFileName, String strDestDir) {
		File fileSource = new File(strSourceFileName);
		File fileDest = new File(strDestDir);

		// 如果源文件不存或源文件是文件夹
		if (!fileSource.exists() || !fileSource.isFile()) {
			System.out.println("源文件[" + strSourceFileName + "],不存在或是文件夹!");
			return false;
		}

		// 如果目标文件夹不存在
		if (!fileDest.isDirectory() || !fileDest.exists()) {
			if (!fileDest.mkdirs()) {
				System.out.println("目录文件夹不存，在创建目标文件夹时失败!");
				return false;
			}
		}

		try {
			String strAbsFilename = strDestDir + File.separator
					+ fileSource.getName();

			FileInputStream fileInput = new FileInputStream(strSourceFileName);
			FileOutputStream fileOutput = new FileOutputStream(strAbsFilename);

			System.out.println("开始拷贝文件:");

			int count = -1;

			long nWriteSize = 0;
			long nFileSize = fileSource.length();

			byte[] data = new byte[BUFFER];

			while (-1 != (count = fileInput.read(data, 0, BUFFER))) {

				fileOutput.write(data, 0, count);

				nWriteSize += count;

				long size = (nWriteSize * 100) / nFileSize;
				long t = nWriteSize;

				String msg = null;

				if (size <= 100 && size >= 0) {
					msg = "\r拷贝文件进度:   " + size + "%   \t" + "\t   已拷贝:   " + t;
					System.out.println(msg);
				} else if (size > 100) {
					msg = "\r拷贝文件进度:   " + 100 + "%   \t" + "\t   已拷贝:   " + t;
					System.out.println(msg);
				}

			}

			fileInput.close();
			fileOutput.close();

			System.out.println("拷贝文件成功!");
			return true;

		} catch (Exception e) {
			System.out.println("异常信息：[");
			System.out.println(e);
			System.out.println("]");
			return false;
		}
	}

	/**
	 * 删除指定的文件
	 * 
	 * @param strFileName
	 *            指定绝对路径的文件名
	 * @return 如果删除成功true否则false
	 */
	public static boolean delete(String strFileName) {
		File fileDelete = new File(strFileName);

		if (!fileDelete.exists() || !fileDelete.isFile()) {
			System.out.println("错误: " + strFileName + "不存在!");
			return false;
		}

		return fileDelete.delete();
	}
	
	/**
	 * 关闭
	 * 
	 * @param closeable
	 *            被关闭的对象
	 */
	public static void close(Closeable closeable) {
		if (closeable == null)
			return;
		try {
			closeable.close();
		} catch (IOException e) {
		}
	}

	/**
	 * 移动文件(只能移动文件)
	 * 
	 * @param strSourceFileName
	 *            是指定的文件全路径名
	 * @param strDestDir
	 *            移动到指定的文件夹中
	 * @return 如果成功true; 否则false
	 */
	public static boolean moveFile(String strSourceFileName, String strDestDir) {
		if (copyTo(strSourceFileName, strDestDir))
			return delete(strSourceFileName);
		else
			return false;
	}

	/**
	 * 创建文件夹
	 * 
	 * @param strDir
	 *            要创建的文件夹名称
	 * @return 如果成功true;否则false
	 */
	public static boolean mkdir(String strDir) {
		File fileNew = new File(strDir);

		if (!fileNew.exists()) {
			System.out.println("文件夹不存在--创建文件夹");
			return fileNew.mkdirs();
		} else {
			System.out.println("文件夹存在");
			return true;
		}
	}
	/**
	 * 创建文件
	 * @param filePath
	 * @return
	 */
	public static File createFile(String filePath) {
		File file = new File(filePath);
		try {
			
			file.getParentFile().mkdirs();
			if (!file.exists())
				file.createNewFile();
		} catch (Exception e) {
			System.out.println("创建文件失败:" + e.toString());
		}
		return file;
	}

	/**
	 * 获得一个输出流对象
	 * 
	 * @param path
	 *            输出到的文件路径，绝对路径
	 * @return 输出流对象
	 * @throws IOException
	 */
	public static OutputStream getOutputStream(String path) throws IOException {
		return new FileOutputStream(createFileBackFilePath(path));
	}
	
	public static String createFileBackFilePath(String filePath) {
		try {
			File file = new File(filePath);
			if (!file.exists())
				file.createNewFile();
		} catch (Exception e) {
			System.out.println("创建文件失败:" + e.toString());
		}
		return filePath;
	}
	/**
	 * 删除文件夹
	 * 
	 * @param strDir
	 *            要删除的文件夹名称
	 * @return 如果成功true;否则false
	 */
	public static boolean rmdir(String strDir) {
		File rmDir = new File(strDir);
		if (rmDir.isDirectory() && rmDir.exists()) {
			String[] fileList = rmDir.list();

			for (int i = 0; i < fileList.length; i++) {
				String subFile = strDir + File.separator + fileList[i];
				File tmp = new File(subFile);
				if (tmp.isFile())
					tmp.delete();
				else if (tmp.isDirectory())
					rmdir(subFile);
				else {
					System.out.println("error!");
				}
			}
			rmDir.delete();
		} else
			return false;
		return true;
	}

	/**
	 * 列举所有文件
	 * 
	 * @param files
	 * @return
	 */
	public static List<File> listFiles(String[] files) {
		List list = new ArrayList();
		String[] arrayOfString = files;
		int j = files.length;
		for (int i = 0; i < j; i++) {
			String file = arrayOfString[i];
			list.add(new File(file.trim()));
		}
		return list;
	}

	/**
	 * 获取文件大小
	 * 
	 * @param f
	 * @return
	 * @throws Exception
	 */
	public static long getFileSize(File f) throws Exception {
		long s = 0L;
		if (f.exists()) {
			FileInputStream fis = null;
			fis = new FileInputStream(f);
			s = fis.available();
		} else {
			f.createNewFile();
			System.out.println("文件不存在");
		}
		return s;
	}

	/**
	 * 获取文件大小格式化后的
	 * 
	 * @param fileS
	 * @return
	 */
	public static String getFormetFileSize(long fileSize) {
		DecimalFormat df = new DecimalFormat("#.000");
		String fileSizeString = "";
		if (fileSize < 1024L)
			fileSizeString = df.format(fileSize) + "B";
		else if (fileSize < 1048576L)
			fileSizeString = df.format(fileSize / 1024.0D) + "KB";
		else if (fileSize < 1073741824L)
			fileSizeString = df.format(fileSize / 1048576.0D) + "MB";
		else {
			fileSizeString = df.format(fileSize / 1073741824.0D) + "GB";
		}
		return fileSizeString;
	}

	long folderCount = 0L;
	long totalSize = 0L;
	long flieCount = 0L;

	/**
	 * 获取文件夹大小
	 * 
	 * @param f
	 * @throws Exception
	 */
	public HashMap<String, Object> getFolderSize(File f) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();

		File[] flist = f.listFiles();
		for (File fc : flist) {
			if (fc.isDirectory()) {
				folderCount += 1L;
				getFolderSize(fc);
			} else {
				flieCount += 1L;
				totalSize += fc.length();
			}
		}
		map.put("folderCount", folderCount);
		map.put("totalSize", totalSize);
		map.put("flieCount", flieCount);
		return map;
	}

	/**
	 * 下载文件
	 * 
	 * @param path
	 * @param response
	 * @param allPath
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 */

	public static void downFile(String path, HttpServletResponse response,
			String allPath) throws FileNotFoundException, IOException,
			UnsupportedEncodingException {
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		OutputStream fos = null;
		InputStream fis = null;
		File uploadFile = new File(allPath);
		fis = new FileInputStream(uploadFile);
		bis = new BufferedInputStream(fis);
		fos = response.getOutputStream();
		bos = new BufferedOutputStream(fos);
		response.setHeader("Content-disposition", "attachment;filename="
				+ URLEncoder.encode(path, "utf-8"));
		int bytesRead = 0;
		byte[] buffer = new byte[8192];
		while ((bytesRead = bis.read(buffer, 0, 8192)) != -1) {
			bos.write(buffer, 0, bytesRead);
		}
		bos.flush();
		fis.close();
		bis.close();
		fos.close();
		bos.close();
	}

	/**
	 * 判断文件是否存在
	 * 
	 * @param path
	 * @return
	 */
	public static boolean isExists(String path) {
		File f = new File(path);
		return f.exists();
	}

	/**
	 * 写文件
	 * 
	 * @param path
	 * @param content
	 * @throws IOException
	 */
	public static void write(String path, String content) throws IOException {
		Writer fw = new BufferedWriter(new FileWriter(new File(path)));
		fw.write(content);
		fw.close();
	}

	/**
	 * 获得文件的扩展名
	 * 
	 * @param fileName
	 *            文件名
	 * @return 扩展名
	 */
	public static String getExtension(String fileName) {
		if (fileName == null) {
			return null;
		}
		int index = fileName.lastIndexOf(".");
		if (index == -1) {
			return "";
		} else {
			String ext = fileName.substring(index + 1);
			// 扩展名中不能包含路径相关的符号
			return (ext.contains("/") || ext.contains("\\")) ? "" : ext;
		}
	}
	/**
	 * 获取目录下所有的后缀名
	 * @param path
	 * @param ext
	 * @return
	 */
	public static List<File> getPathAllFileExt(String path,String ext){
		List<File> list =new ArrayList<File>();
		
		getPathAllFileExt(list,path,ext);
		
		return list;
		
	}
	private  static  List<File> getPathAllFileExt(List<File> list,String path,String ext){
		
		File dir=new File(path);
		if(dir.isDirectory()==false)
			return list;
		
		File[] files=dir.listFiles();
		
	if(files==null||files.length<1)
		return list;
	for(File f:files){
		if(f.isDirectory()){
			String dirPath=f.getAbsolutePath();
			getPathAllFileExt(list,dirPath,ext);
		}else{
			if(f.getName().endsWith(ext)){
				
				list.add(f);
			}
		}
	}
		return list;
	}
	public static void main(String[] args) throws Exception {
		long fileLength = Long.parseLong(new FileUtil().getFolderSize(
				new File("d:/abc")).get("totalSize")
				+ "");
		System.out.println(getPathAllFileExt("E:/geppProject/personwork/电子商务/coderesource/V8Shop/","java"));
	}
}
