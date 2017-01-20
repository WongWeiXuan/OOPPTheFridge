package theFridge.model;

import java.io.File;
import java.io.FileOutputStream;

/*
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.Hashtable;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
*/

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;

public class RedeemVoucherBarcode {

	public void generateBarcode(String barcodeMessage) {
		 BitMatrix bitMatrix;
	     	try {
	        	//  Write Barcode
	            bitMatrix = new Code128Writer().encode(barcodeMessage, BarcodeFormat.CODE_128, 150, 80, null);
	            MatrixToImageWriter.writeToStream(bitMatrix, "png", new FileOutputStream(new File("src/theFridge/picture/Barcode.png")));
	            System.out.println("Code128 barcode generated.");
	     	} catch (Exception e) {
	     		
	     	}
		/*
		int width = 250;
		int height = 250;
		barcodeMessage = "10% Off Discount";
		Color backgroundColor = Color.white;
		Color textColor = Color.black;

		BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = img.createGraphics();
		Font font = new Font("Times", Font.PLAIN, 11);
		g2d.setFont(font);
		FontMetrics fm = g2d.getFontMetrics();
		int textWidth = fm.stringWidth(barcodeMessage);
		int textHeight = fm.getHeight();
		g2d.dispose();
		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		g2d = img.createGraphics();
		g2d.setColor(backgroundColor);
		g2d.fillRect(0, 0, width, height);
		g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
		g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
		g2d.setFont(font);
		fm = g2d.getFontMetrics();
		g2d.setColor(textColor);
		g2d.drawString(barcodeMessage, Math.round(Math.floor((width-textWidth)/2))-2, height-fm.getAscent());
		g2d.dispose();

		// Barcode
		Code128Writer code128Writer = new Code128Writer();
		Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<EncodeHintType, ErrorCorrectionLevel>();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		BitMatrix bitMatrix = code128Writer.encode(barcodeMessage, BarcodeFormat.CODE_128, width, height-textHeight-(2*fm.getAscent()), hintMap);

		// Make the BufferedImage that are to hold the Code128
		int matrixWidth = bitMatrix.getWidth();
		int matrixHeight = bitMatrix.getHeight();

		Graphics2D graphics = (Graphics2D) img.getGraphics();
		graphics.setColor(textColor);
		for (int i = 0; i < matrixWidth; i++) {
		    for (int j = 0; j < matrixHeight; j++) {
		        if (bitMatrix.get(i, j)) {
		            graphics.fillRect(i, j+fm.getAscent(), 1, 1);
		        }
		    }
		}
	}
	*/
	}
	
	public static void main(String args[]) {
		String barcodeMessage = "Hello World!";
		
		RedeemVoucherBarcode barcode = new RedeemVoucherBarcode();
		barcode.generateBarcode(barcodeMessage);
	}
}