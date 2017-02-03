package theFridge.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import theFridge.DAO.ShoppingListDAO;

public class ShoppingListQRCodePageModel {
	final static File FILEPATH = new File("src/theFridge/picture/QrCode.png");
	final File ShoppingPath = new File("src/theFridge/file/StockList.txt");
	
	public static BufferedImage createQRImage() throws WriterException, IOException {
		// Change ShoppingPath to String
		ShoppingListDAO a =new ShoppingListDAO();
		ArrayList<ListModel> shoppingListArrayList = a.getAllList();
		String shoppingList = "Shopping List - \tAmount to buy\n";
		for(ListModel lm:shoppingListArrayList){
			shoppingList += lm.getName() + "\t" + lm.getAmount() + "\n";
		}
		// Create the ByteMatrix for the QR-Code that encodes the given String
		Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<EncodeHintType, ErrorCorrectionLevel>();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix byteMatrix = qrCodeWriter.encode(shoppingList,
				BarcodeFormat.QR_CODE, 250, 250, hintMap);
		// Make the BufferedImage that are to hold the QRCode
		int matrixWidth = byteMatrix.getWidth();
		BufferedImage image = new BufferedImage(matrixWidth, matrixWidth,
				BufferedImage.TYPE_INT_RGB);
		image.createGraphics();

		Graphics2D graphics = (Graphics2D) image.getGraphics();
		graphics.setColor(new Color(252, 228, 236));
		graphics.fillRect(0, 0, matrixWidth, matrixWidth);
		// Paint and save the image using the ByteMatrix
		graphics.setColor(Color.BLACK);

		for (int i = 0; i < matrixWidth; i++) {
			for (int j = 0; j < matrixWidth; j++) {
				if (byteMatrix.get(i, j)) {
					graphics.fillRect(i, j, 1, 1);
				}
			}
		}
		ImageIO.write(image, "png", FILEPATH);
		return image;
	}
}
