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
	}
	
	public static void main(String args[]) {
		String barcodeMessage = "Hello World!";
		
		RedeemVoucherBarcode barcode = new RedeemVoucherBarcode();
		barcode.generateBarcode(barcodeMessage);
	}
}
