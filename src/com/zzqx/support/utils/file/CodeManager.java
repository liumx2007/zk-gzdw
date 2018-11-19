package com.zzqx.support.utils.file;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO; 
import java.io.File; 
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage; 
    
    
public class CodeManager {
	
	private final int BLACK = 0xFF000000;
	private final int WHITE = 0xFFFFFFFF;
	
	private static class holder {
		private static final CodeManager instance = new CodeManager();
	}
	
	private CodeManager() {}
	
	public static CodeManager getInstance() {
		return holder.instance;
	}
	
	public OutputStream encode(String content, OutputStream stream, BarcodeFormat format, int width, int height, Map<EncodeHintType, ?> hints) {
		BufferedImage image;
		try {
	   		BitMatrix bitMatrix = new MultiFormatWriter().encode(content, format, width, height);
	   		image = toBufferedImage(bitMatrix);
	   		ImageIO.write(image, "png", stream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stream;
	}
	
	public File encode(String content, File file, BarcodeFormat format, int width, int height, Map<EncodeHintType, ?> hints) {
		BufferedImage image;
		try {
	   		BitMatrix bitMatrix = new MultiFormatWriter().encode(content, format, width, height);
	   		image = toBufferedImage(bitMatrix);
	   		ImageIO.write(image, "png", file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return file;
	}
	
	public BufferedImage toBufferedImage(BitMatrix matrix) {
		int width = matrix.getWidth(); 
		int height = matrix.getHeight(); 
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); 
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) { 
				image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE); 
			} 
		}
		return image; 
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String decode(File file) {
		BufferedImage image;
		String content = null;
		try {
			image = ImageIO.read(file);
			LuminanceSource source = new BufferedImageLuminanceSource(image);
			BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
			Map hints = new HashMap();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
			Result result = new MultiFormatReader().decode(bitmap, hints);
			content = result.getText();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}
}

final class BufferedImageLuminanceSource extends LuminanceSource {
	private final BufferedImage image;  
	private final int left;
	private final int top;
	
	public BufferedImageLuminanceSource(BufferedImage image) {
		this(image, 0, 0, image.getWidth(), image.getHeight());  
	}  
	  
	public BufferedImageLuminanceSource(BufferedImage image, int left, int top, int width, int height) {  
		super(width, height);  
	  
	    int sourceWidth = image.getWidth();  
	    int sourceHeight = image.getHeight();  
	    if (left + width > sourceWidth || top + height > sourceHeight) {  
	    	throw new IllegalArgumentException("Crop rectangle does not fit within image data.");  
	    }  
	    for (int y = top; y < top + height; y++) {  
	    	for (int x = left; x < left + width; x++) {  
		        if ((image.getRGB(x, y) & 0xFF000000) == 0) {  
		        	image.setRGB(x, y, 0xFFFFFFFF); // = white  
		        }  
	    	}  
	    }  
	    this.image = new BufferedImage(sourceWidth, sourceHeight, BufferedImage.TYPE_BYTE_GRAY);  
	    this.image.getGraphics().drawImage(image, 0, 0, null);  
	    this.left = left;  
	    this.top = top;
    }  
	  
	@Override  
	public byte[] getRow(int y, byte[] row) {  
		if(y < 0 || y >= getHeight()) {  
			throw new IllegalArgumentException("Requested row is outside the image: " + y);  
	    }  
	    int width = getWidth();  
	    if(row == null || row.length < width) {  
	    	row = new byte[width];  
	    }  
	    image.getRaster().getDataElements(left, top + y, width, 1, row);  
	    return row;  
	}  
	  
	@Override  
	public byte[] getMatrix() {  
		int width = getWidth();  
	    int height = getHeight();  
	    int area = width * height;  
	    byte[] matrix = new byte[area];  
	    image.getRaster().getDataElements(left, top, width, height, matrix);  
	    return matrix;  
	}  
	  
	@Override  
	public boolean isCropSupported() {  
		return true;  
	}  
	  
	@Override  
	public LuminanceSource crop(int left, int top, int width, int height) {  
		return new BufferedImageLuminanceSource(image, this.left + left, this.top + top, width, height);  
	}  
	  
	@Override  
	public boolean isRotateSupported() {  
		return true;  
	}  
	  
	@Override  
	public LuminanceSource rotateCounterClockwise() {  
	  
		int sourceWidth = image.getWidth();  
	    int sourceHeight = image.getHeight();  
	  
	    AffineTransform transform = new AffineTransform(0.0, -1.0, 1.0, 0.0, 0.0, sourceWidth);  
	  
	    BufferedImage rotatedImage = new BufferedImage(sourceHeight, sourceWidth, BufferedImage.TYPE_BYTE_GRAY);  
	  
	    Graphics2D g = rotatedImage.createGraphics();  
	    g.drawImage(image, transform, null);  
	    g.dispose();  
	  
	    int width = getWidth();  
	    return new BufferedImageLuminanceSource(rotatedImage, top, sourceWidth - (left + width), getHeight(), width);  
	}
}