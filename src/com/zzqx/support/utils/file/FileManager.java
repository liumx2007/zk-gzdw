package com.zzqx.support.utils.file;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import com.zzqx.support.utils.DateManager;
import com.zzqx.support.utils.StringHelper;

import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;
import jcifs.smb.SmbFileOutputStream;
import net.coobird.thumbnailator.Thumbnails;

public class FileManager {
	
	private static int seq = 1;
	private static String lastTime = "";
	private static String FFMPEG = getResource(FileManager.class, "//exe//ffmpeg//ffmpeg.exe");
	private static String PDF2SWF = getResource(FileManager.class, "//exe//swftools//pdf2swf.exe");
	
	private static final int wdFormatPDF = 17;
	private static final int xlTypePDF = 0;
	private static final int ppSaveAsPDF = 32;
	
	private FileManager() {}
	
	public static String getResource(Class<?> clazz, String filePath) {
		URL url = clazz.getResource(filePath);
		if(url == null) return "";
		String path = url.getPath();
		try {
			URI uri = new URI(path);
			path = uri.getPath();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return path;
	}
	
	private static synchronized String getSeq() {
		String seq;
		String time = DateManager.getTime("yyyyMMddHHmmss");
		if(time.equals(FileManager.lastTime)) {
			seq = time+(++FileManager.seq);
		} else {
			FileManager.lastTime = time;
			FileManager.seq = 1;
			seq = time+FileManager.seq;
		}
		return seq;
	}
	
	public static String getServerPath() {
		String classPath = FileManager.class.getResource("/").getPath();
		try {
			classPath = URLDecoder.decode(classPath.substring(0, StringHelper.lastOrdinalIndexOf(classPath, "/", 3)+1), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return classPath;
	}

	public static String getNewFileName(String fileType) {
		fileType = fileType.startsWith(".")?fileType:"."+fileType;
		return getSeq()+fileType;
	}
	
	public static String getFileNameWithOutSuffix(String fileName) {
		if(StringHelper.isNotBlank(fileName) && fileName.contains(".")) {
			fileName = fileName.substring(0, fileName.lastIndexOf("."));
		}
		return fileName;
	}
	
	public static String getSuffix(String fileName) {
		if(StringHelper.isBlank(fileName)) {
			return "";
		}
		String fileType = "";
		if(fileName.contains(".")) {
			fileType = fileName.substring(fileName.lastIndexOf("."));
		}
		return fileType;
	}
	
	//将路径以"/"结尾
	public static String getNormalPath(String path) {
		if(path == null) return null;
		return Stream.of(path.split("/")).filter(StringHelper::isNotBlank).collect(Collectors.joining("/"))+"/";
	}
	
	public static String getFileTypeName(String fileName) {
		String suffix = getSuffix(fileName);
		String typeName;
		switch (suffix) {
			case ".exe" : typeName = "可执行文件"; break;
			case ".doc" : typeName = "word文本文档";break;
			case ".docx": typeName = "word文本文档";break;
			case ".rtf" : typeName = "多文本格式文档";break;
			case ".xls" : typeName = "excel表格文档";break;
			case ".xlsx" : typeName = "excel表格文档";break;
			case ".ppt" : typeName = "演示文档";break;
			case ".pptx" : typeName = "演示文档";break;
			case ".pdf" : typeName = "便携式文档";break;
			case ".swf" : typeName = "flash文件";break;
			case ".dll" : typeName = "应用程序动态链接库文件";break;
			case ".msi" : typeName = "安装软件";break;
			case ".chm" : typeName = "帮助文件";break;
			case ".cab" : typeName = "压缩包文件";break;
			case ".ocx" : typeName = "PE文件";break;
			case ".jar"  : typeName = "Java归档文件";break;
			case ".rar"  : typeName = "压缩文件";break;
			case ".tar"  : typeName = "压缩文件";break;
			case ".tgz"  : typeName = "压缩文件";break;
			case ".zip"  : typeName = "压缩文件";break;
			case ".z"  : typeName = "压缩文件";break;
			case ".ogg" : typeName = "音频文件";break;
			case ".real" : typeName = "音频文件";break;
			case ".mp4" : typeName = "视频文件";break;
			case ".3gp" : typeName = "视频文件";break;
			case ".mkv" : typeName = "视频文件";break;
			case ".mov" : typeName = "视频文件";break;
			case ".vob" : typeName = "视频文件";break;
			case ".flv" : typeName = "视频文件";break;
			case ".f4v" : typeName = "视频文件";break;
			case ".rmvb" : typeName = "视频文件";break;
			case ".wav" : typeName = "音频文件";break;
			case ".wma" : typeName = "音频文件";break;
			case ".wmv" : typeName = "视频文件";break;
			case ".avi" : typeName = "视频文件";break;
			case ".mp3" : typeName = "音频文件";break;
			case ".mp2" : typeName = "音频文件";break;
			case ".mpe" : typeName = "音频文件";break;
			case ".mpeg" : typeName = "音频文件";break;
			case ".mpg" : typeName = "音频文件";break;
			case ".rm" : typeName = "视频文件";break;
			case ".mid" : typeName = "音频文件";break;
			case ".midi" : typeName = "音频文件";break;
			case ".rmi" : typeName = "音频文件";break;
			case ".bmp" : typeName = "图片文件";break;
			case ".gif" : typeName = "图片文件";break;
			case ".png" : typeName = "图片文件";break;
			case ".tif" : typeName = "图片文件";break;
			case ".tiff" : typeName = "图片文件";break;
			case ".jpe" : typeName = "图片文件";break;
			case ".jpeg" : typeName = "图片文件";break;
			case ".jpg" : typeName = "图片文件";break;
			case ".txt" : typeName = "文本文件";break;
			case ".xml" : typeName = "xml文件";break;
			case ".jsp" : typeName = "网页文件";break;
			case ".asp" : typeName = "网页文件";break;
			case ".html" : typeName = "网页文件";break;
			case ".css" : typeName = "样式文件";break;
			case ".js" : typeName = "网页脚本文件";break;
			case ".mht" : typeName = "web档案";break;
			case ".mhtml" : typeName = "web档案";break;
			default : typeName = "其它文件";
		}
		return typeName;
	}
	
	public static boolean makeDirectory(String filePath) {
        File folder = new File(filePath);
        return (folder.exists() && folder.isDirectory()) || folder.mkdirs();
	}
	
	public static boolean deleteFile(String path) {
	    boolean flag = false;
	    File file = new File(path);
	    if (file.isFile() && file.exists()) {  
	        flag = file.delete();
	    }
	    return flag;
	}
	
	public static boolean deleteFile(String directory, String fileName) {
	    boolean flag = false;
	    if(fileName != null) {
		    File file = new File(directory, fileName);
		    if (file.isFile() && file.exists()) {
				flag = file.delete();
		    }
	    }
	    return flag;
	}
	
    /**
     * 删除目录（文件夹）以及目录下的文件
     * @param   path 被删除目录的文件路径
     * @return  目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String path) {
        //如果path不以文件分隔符结尾，自动添加文件分隔符
        if (!path.endsWith(File.separator)) {
            path = path + File.separator;
        }
        File dirFile = new File(path);
        //如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
         boolean flag = true;
        //删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for (int i = 0; files != null && i < files.length; i++) {
            //删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag) break;
            } //删除子目录
            else {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag) break;
            }
        }
        if (!flag) return false;
        //删除当前目录
        return dirFile.delete();
    }
    
    /**
     *  根据路径删除指定的目录或文件，无论存在与否
     *@param path  要删除的目录或文件
     *@return 删除成功返回 true，否则返回 false。
     */
    public static boolean deleteFolder(String path) {
        File file = new File(path);
        // 判断目录或文件是否存在
        if (!file.exists()) {  // 不存在返回 false
            return false;
        } else {
            // 判断是否为文件
            if (file.isFile()) {  // 为文件时调用删除文件方法
                return deleteFile(path);
            } else {  // 为目录时调用删除目录方法
                return deleteDirectory(path);
            }
        }
    }
    
    /** 
    * Description: 从共享目录拷贝文件到本地 
    * @param remoteUrl 共享目录上的文件路径 
    * @param toLocalFilePath 本地目录
    */ 
    public static boolean smbGet(String remoteUrl, String toLocalFilePath) {
    	InputStream in = null;
    	OutputStream out = null;
    	boolean b = false;
    	try {
    		SmbFile remoteFile = new SmbFile(remoteUrl); 
//	    	String fileName = remoteFile.getName(); 
//	    	File localFile = new File(localDir+File.separator+fileName;
    		File localFile = new File(toLocalFilePath);
    		remoteFile.renameTo(remoteFile);//判断文件是否还在使用
	    	in = new BufferedInputStream(new SmbFileInputStream(remoteFile)); 
	    	out = new BufferedOutputStream(new FileOutputStream(localFile)); 
	    	byte[] buffer = new byte[1024]; 
	    	while(in.read(buffer)!=-1) {
				out.write(buffer);
	    		buffer = new byte[1024];
	    	}
	    	b = true;
    	} catch (Exception e) { 
    		e.printStackTrace();
    		System.out.println(remoteUrl+" 正在使用！");
    	} finally { 
	    	try {
	    		if(out != null) {
					out.flush();
					out.close();
	    		}
	    		if(in != null) {
	    			in.close();
	    		}
	    	} catch (IOException e) { 
		    	e.printStackTrace(); 
	    	} 
    	}
		return b;
	} 
	/** 
	* Description: 从本地上传文件到共享目录 
	* @param remoteUrl 共享文件目录 
	* @param localFilePath 本地文件绝对路径 
	*/ 
	public static boolean smbPut(String remoteUrl,String localFilePath) { 
    	InputStream in = null;
    	OutputStream out = null;
    	try {
	    	File localFile = new File(localFilePath); 
	    	String fileName = localFile.getName(); 
	    	SmbFile remoteFile = new SmbFile(remoteUrl+"/"+fileName);
	    	if(!localFile.renameTo(localFile)) {//判断文件是否还在使用
				return false;
			}
	    	in = new BufferedInputStream(new FileInputStream(localFile)); 
	    	out = new BufferedOutputStream(new SmbFileOutputStream(remoteFile)); 
	    	byte[] buffer = new byte[1024]; 
	    	while(in.read(buffer)!=-1){
	    		out.write(buffer);
	    		buffer = new byte[1024];
	    	}
    	} catch (Exception e) {
    		//e.printStackTrace();
    		System.out.println(remoteUrl+" 正在使用！");
    	} finally { 
	    	try {
	    		if(out != null) {
					out.flush();
	    			out.close(); 
	    		}
	    		if(in != null) {
	    			in.close(); 
	    		}
	    	} catch (IOException e) { 
	    		e.printStackTrace(); 
	    	} 
    	}
    	return true;
	}
	
	 public static void copyFileChannel(File source, File target) {
		 FileInputStream fi = null;
		 FileOutputStream fo = null;
		 FileChannel in = null;
	     FileChannel out = null;
	     try {
	    	 fi = new FileInputStream(source);
	         fo = new FileOutputStream(target);
	         in = fi.getChannel();//得到对应的文件通道
	         out = fo.getChannel();//得到对应的文件通道
	         in.transferTo(0, in.size(), out);//连接两个通道，并且从in通道读取，然后写入out通道
	     } catch (IOException e) {
	    	 e.printStackTrace();
	     } finally {
	    	 try {
				 if(fi != null) {
					 fi.close();
				 }
				 if(in != null) {
					 in.close();
				 }
				 if(fo != null) {
					 fo.close();
				 }
				 if(out != null) {
					 out.close();
				 }
	    	 } catch (IOException e) {
	    		 e.printStackTrace();
    		 }
    	 }
     }
	 
	 public static void copyFile(File source, File target) throws IOException{
		 FileOutputStream fos = new FileOutputStream(target); 
		 FileInputStream br = new FileInputStream(source); 
		 int len;
		 while((len=br.read())!=-1){
			 fos.write(len);
		 }
		 br.close();
		 fos.close();
		 System.out.println("复制文件成功");
	 }
	
	/** 
	* Description: 为图片和视频格式的文件生成缩略图。
	* @param filePath 源文件路径
	* @param outFile 输出缩略图文件路径
	*/
	public static boolean processImg(String filePath, String outFile, String contentType) {
		File file = new File(filePath);
		if(!file.exists()) {
			return false;
		}
		PropertiesHelper prop = new PropertiesHelper("config");
		int iWidth = prop.readInteger("content.thumbnail.width");
		int iHeight = prop.readInteger("content.thumbnail.height");
		if(iWidth == 0 || iHeight == 0) {
			return false;
		}
		System.out.println("正在生成缩略图。文件类型："+contentType);
		if(contentType.startsWith("image")) {
			try {
				Thumbnails.of(filePath).size(iWidth, iHeight).toFile(outFile);
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		} else {
			Map<String, String> info = getVideoInfo(filePath);
			if(info == null) return false;
			List<String> commands = new ArrayList<>();
			commands.add(FFMPEG);
			commands.add("-i");
			commands.add(filePath);
			commands.add("-y");
			commands.add("-f");
			commands.add("image2");
			commands.add("-ss");
			int time = prop.readInteger("content.thumbnail.pointTime");
			commands.add(time+"");
			commands.add("-s");
			Double width = Double.valueOf(info.get("resolution").split("x")[0]);
			Double height = Double.valueOf(info.get("resolution").split("x")[1]);
			Double sw = iWidth/width;
			Double sh = iHeight/height;
			Double scale = sw > sh ? sh : sw;
			int w = new Double(width*scale).intValue();
			int h = new Double(height*scale).intValue();
			commands.add(w+"*"+h);
			commands.add(outFile);
			ProcessBuilder pb = new ProcessBuilder();
			pb.command(commands);
			try {
				pb.start();
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 改变图片的大小到宽为size，然后高随着宽等比例变化
	 * @param is 上传的图片的输入流
	 * @param os 改变了图片的大小后，把图片的流输出到目标OutputStream
	 * @param newWidth 新图片的宽
	 * @param format 新图片的格式
	 * @throws IOException 异常
	 */
	public static boolean resizeImage(InputStream is, OutputStream os, int newWidth, String format) {
		BufferedImage prevImage = null;
		try {
			prevImage = ImageIO.read(is);
			if(prevImage == null) return false;
			double width = prevImage.getWidth();
			double height = prevImage.getHeight();
			double percent = newWidth/width;
			int newW = (int)(width * percent);
			int newH = (int)(height * percent);
			BufferedImage image = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_BGR);
			Graphics graphics = image.createGraphics();
			graphics.drawImage(prevImage, 0, 0, newW, newH, null);
			ImageIO.write(image, format, os);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			if(prevImage != null) {
				prevImage.flush();
			}
			try {
				if(os != null) {
					os.flush();
					os.close();
				}
				if(is != null) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
	
	public static Map<String, String> getVideoInfo(String filePath) {
		Map<String, String> map = new HashMap<>();
		File file = new File(filePath);
		if(!file.exists()) {
			return null;
		}
		List<String> commands = new ArrayList<>();
		commands.add(FFMPEG);
		commands.add("-i");
		commands.add(filePath);
		ProcessBuilder pb = new ProcessBuilder();
		pb.command(commands);
		try {
			Process p = pb.start();
			InputStream stderr = p.getErrorStream();
			InputStreamReader isr = new InputStreamReader(stderr);
			BufferedReader br = new BufferedReader(isr);
			String line;
			StringBuilder sb = new StringBuilder();
			while ( (line = br.readLine()) != null) {
				sb.append(line);
			}
			String info = sb.toString();
			System.out.println(info);
			info = info.substring(info.lastIndexOf("Duration: "));
			String time = info.substring(info.indexOf("Duration: ")+10).substring(0, info.substring(info.indexOf("Duration: ")+10).indexOf(","));
			String bitrate = info.substring(info.indexOf("bitrate: ")+9).substring(0, info.substring(info.indexOf("bitrate: ")+9).indexOf(" kb/s"));
			String code = info.substring(info.indexOf("Video: ")+7).substring(0, info.substring(info.indexOf("Video: ")+7).indexOf(","));
			String s = info.substring(info.indexOf("Video: ")+7).substring(0, info.substring(info.indexOf("Video: ")+7).indexOf("["));
			String resolution = s.split(",")[2].trim();
			String rate = info.substring(info.indexOf("], ")+3, info.indexOf(" tb"));
			map.put("duration", time);
			map.put("bitrate", bitrate);
			map.put("code", code);
			map.put("resolution", resolution);
			map.put("rate", rate);
			return map;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean pdf2Swf(String pdfPath, String swfFile) {
		try {
			String fontPath = getResource(FileManager.class, "//exe//swftools//xpdf-chinese-simplified");
			Process p = Runtime.getRuntime().exec(PDF2SWF + " " + pdfPath + " -o " + swfFile + " -j 100 -t -T 9 -F " + fontPath);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while (bufferedReader.readLine() != null);
			p.waitFor();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (InterruptedException e) {
		    e.printStackTrace();
		    return false;
		}
		return true;
	}
	
	public static boolean word2Pdf(String inputFile,String pdfFile){
		System.out.println("word =====> pdf");
		try{
			//打开word应用程序
			ActiveXComponent app = new ActiveXComponent("Word.Application");
			//设置word不可见
			app.setProperty("Visible", false);
			//获得word中所有打开的文档,返回Documents对象
			Dispatch docs = app.getProperty("Documents").toDispatch();
			//调用Documents对象中Open方法打开文档，并返回打开的文档对象Document
			Dispatch doc = Dispatch.call(docs,"Open",inputFile,false,true).toDispatch();
			//调用Document对象的SaveAs方法，将文档保存为pdf格式
			Dispatch.call(doc,"ExportAsFixedFormat",pdfFile,wdFormatPDF);
			//关闭文档
			Dispatch.call(doc, "Close",false);
			//关闭word应用程序
			app.invoke("Quit", 0);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		} catch(Error e) {
			e.printStackTrace();
			return false;
		}
	}
	public static boolean excel2Pdf(String inputFile,String pdfFile){
		System.out.println("excel =====> pdf");
		try{
			ActiveXComponent app = new ActiveXComponent("Excel.Application");
			app.setProperty("Visible", false);
			Dispatch excels = app.getProperty("Workbooks").toDispatch();
			Dispatch excel = Dispatch.call(excels,"Open",inputFile,false,true).toDispatch();
			Dispatch.call(excel,"ExportAsFixedFormat",xlTypePDF,pdfFile);
			Dispatch.call(excel, "Close",false);
			app.invoke("Quit");
			return true;
		}catch(Exception e){
			return false;
		} catch(Error e) {
			return false;
		}
	}
	public static boolean ppt2Pdf(String inputFile,String pdfFile){
		System.out.println("ppt =====> pdf");
		try{
			ActiveXComponent app = new ActiveXComponent("PowerPoint.Application");
//			app.setProperty("Visible", false);
			Dispatch ppts = app.getProperty("Presentations").toDispatch();
			Dispatch ppt = Dispatch.call(ppts,"Open",inputFile,true,true,false).toDispatch();
			Dispatch.call(ppt,"SaveAs",pdfFile,ppSaveAsPDF);
			Dispatch.call(ppt, "Close");
			app.invoke("Quit");
			return true;
		} catch(Exception e){
			return false;
		} catch(Error e) {
			return false;
		}
	}
	
	public static boolean office2Pdf(String inputFile, String pdfFile) {
		String suffix = FileManager.getSuffix(inputFile).toLowerCase();
		File file = new File(inputFile);
		if(!file.exists()){
			System.out.println("文件不存在！");
			return false;
		}
		switch (suffix) {
			case ".pdf":
				System.out.println("PDF not need to convert!");
				return false;
			case ".doc":
			case ".docx":
			case ".txt":
				return word2Pdf(inputFile,pdfFile);
			case ".ppt":
			case ".pptx":
				return ppt2Pdf(inputFile,pdfFile);
			case ".xls":
			case ".xlsx":
				return excel2Pdf(inputFile,pdfFile);
			default:
				System.out.println("文件格式不支持转换!");
				return false;
		}
	}
	
	public static String doc2FormatString(Document doc) {         
        StringWriter stringWriter = null;  
        try {  
            stringWriter = new StringWriter();  
            if(doc != null){  
                OutputFormat format = new OutputFormat(doc,"UTF-8",true);  
                //format.setIndenting(true);//设置是否缩进，默认为true  
                //format.setIndent(4);//设置缩进字符数  
                //format.setPreserveSpace(false);//设置是否保持原来的格式,默认为 false  
                //format.setLineWidth(500);//设置行宽度  
                XMLSerializer serializer = new XMLSerializer(stringWriter,format);  
                serializer.asDOMSerializer();  
                serializer.serialize(doc);  
                return stringWriter.toString();  
            }
        } catch (Exception e) {  
            e.printStackTrace();
        } finally {  
            if(stringWriter != null){  
                try {  
                    stringWriter.close();  
                } catch (IOException e) {
					e.printStackTrace();
                }
            }  
        }
        return null;
    }
	
	public static Document string2Doc(String xml) {  
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
        DocumentBuilder builder;
        Document doc;
        InputSource source;
        StringReader reader = null;  
        try {  
            builder = factory.newDocumentBuilder();  
            reader = new StringReader(xml);  
            source = new InputSource(reader);//使用字符流创建新的输入源  
            doc = builder.parse(source);  
            return doc;  
        } catch (Exception e) {  
            return null;  
        } finally {  
            if(reader != null){  
                reader.close();  
            }  
        }  
    }
}