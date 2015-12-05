package com.jdk2010.framework.util;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Properties;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.ServiceUI;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;

public class SystemTool {

 
	
	public static void main(String[] args)throws Exception {
		//printSystemProperty();
		//printSystenEnv();
 		//writeToClipboard("系统剪切板.aadsfafafd阿斯蒂芬撒旦法");
 		System.out.println(readFromClipboard());
		print("你好");
	}

	public static void printSystemProperty(){
		System.out.println("系统属性 : ");
		Properties p = System.getProperties();
		for(String name : p.stringPropertyNames()){
			System.out.println(name + " = " + p.getProperty(name));
		}
	}
	
	public static void printSystenEnv(){
		System.out.println("环境变量 : ");
		Map<String, String> env = System.getenv();
		for(String name : env.keySet()){
			System.out.println(name + " = " + env.get(name));
		}
	}
	
	public static void appendBinPath(Class<?> clazz){
		String path = getAppPath(clazz);
		appendBinPath(path);
	}
	public static void appendBinPath(String binPath){
		String oldBinPath = System.getProperty("java.library.path");
		System.setProperty("java.library.path", oldBinPath + File.pathSeparator + binPath);
	}
	
	 
	
	public static void appendClassPath(String classPath){
		String oldClassPath = System.getProperty("java.class.path");
		System.setProperty("java.class.path", oldClassPath + File.pathSeparator + classPath);
	}
	
 
	
	public static String getAppPath(Class<?> clazz){
		String appPath = "";
		String path = clazz.getProtectionDomain().getCodeSource().getLocation().toString();
		path = path.substring(5);
		if(path.endsWith(".jar")){//jar包 的完整路径
			appPath = new File(path).getParent();
		}else if(path.endsWith(".class")){
			String packagePath = "/" + clazz.getName().replace(".", "/") + ".class";
			appPath = path.substring(0, path.indexOf(packagePath));
		}else if(path.endsWith(File.separator)){
			appPath = path.substring(0, path.lastIndexOf(File.separator));
		}else if(path.endsWith("/")){//classpath 的根路径
			appPath = path.substring(0, path.lastIndexOf("/"));;
		}else{
			appPath = path;
		}
		return appPath;
	}
	
	public static String searchResource(Class<?> clazz, String fileName, String defaultDir){
		String appRootDir = SystemTool.getAppPath(clazz);
		String configFile = appRootDir + File.separator + fileName;
		if(!new File(configFile).exists()){
			File parentDir = new  File(appRootDir).getParentFile();
			appRootDir = parentDir.getAbsolutePath();
			if(new File(appRootDir + File.separator + fileName).exists()){
				return appRootDir + File.separator + fileName;
			}else{
				return appRootDir + File.separator + defaultDir + File.separator + fileName;
			}
		}
		return configFile;
	}
	public static InputStream searchResourceAsStream(Class<?> clazz, String fileName, String defaultDir) throws FileNotFoundException{
		String resourceFile = searchResource(clazz, fileName, defaultDir);
		if(new File(resourceFile).exists()){
			return new FileInputStream(resourceFile);
		}
		
		InputStream is = clazz.getResourceAsStream("/" + fileName);
		if(is == null){
			is = clazz.getResourceAsStream("/" + defaultDir + "/" + fileName);
		}
		return is;
	}
	
	public static String readFromConsole()throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		return br.readLine();
	}
	public static void writeToConsole(String line){
		PrintWriter pw = new PrintWriter(System.out);
		pw.println(line);
		pw.flush();
	}
	
	public static void writeToClipboard(String content){
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Clipboard clipboard = toolkit.getSystemClipboard();
		StringSelection ss = new StringSelection(content);
		clipboard.setContents(ss, ss);
	}
	
	public static String readFromClipboard(){
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Clipboard clipboard = toolkit.getSystemClipboard();
		Object data = null;
		try {
			data = clipboard.getData(DataFlavor.stringFlavor);
		} catch(Exception e) {
			e.printStackTrace();
		}
		if(data == null){
			return null;
		}
		return (String)data;
	}
	
	public static void print(Object printData, DocFlavor flavor)throws PrintException{
		PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
		PrintService svc = PrintServiceLookup.lookupDefaultPrintService();
		PrintRequestAttributeSet attrs = new HashPrintRequestAttributeSet();
		PrintService selectionPrintService = ServiceUI.printDialog(null, 100, 100, services, svc, null, attrs);
		if(selectionPrintService != null){
			DocPrintJob job = selectionPrintService.createPrintJob();
			Doc doc = new SimpleDoc(printData, flavor, null);
			job.print(doc, attrs); 
		}
	}
	
	public static void print(String content)throws PrintException{
		print(content, DocFlavor.STRING.TEXT_PLAIN);
	}
	
	public static void printImage(InputStream is)throws PrintException{
		print(is, DocFlavor.INPUT_STREAM.JPEG);
	}
}
