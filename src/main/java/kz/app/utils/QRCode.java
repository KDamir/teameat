package kz.app.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Damir <damir.keldibekov@gmail.com>
 */
public class QRCode {
	
	public static BufferedImage writeQRCode(String content) throws IOException {
		QRCodeWriter writer = new QRCodeWriter();
		int width = 256, height = 256;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); // create an empty image
		int white = 255 << 16 | 255 << 8 | 255;
		int black = 0;
		try {
			BitMatrix bitMatrix = writer.encode(content, BarcodeFormat.QR_CODE, width, height);
			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					 image.setRGB(i, j, bitMatrix.get(i, j) ? black : white); // set pixel one by one
				}
			}
		} catch (WriterException ex) {
			Logger.getLogger(QRCode.class.getName()).log(Level.SEVERE, null, ex);
		}
		return image;
//		ImageIO.write(image, "jpg", new File("dynamsoftbarcode.jpg")); // можно использовать, чтобы сохранить QR-код в файл
		
	}
	
	/**
	 * Этот метод использовать в kazynaCard.
	 * Главная задача - расспарсить данные из возвращаемой строки
	 * @param fileName
	 * @return 
	 */
	public static String readQRCode(String fileName) {
		File file = new File(fileName);
		BufferedImage image = null;
		BinaryBitmap bitmap = null;
		Result result = null;
		
		try {
			image = ImageIO.read(file);
			int[] pixels = image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth());
			RGBLuminanceSource source = new RGBLuminanceSource(image.getWidth(), image.getHeight(), pixels);
			bitmap = new BinaryBitmap(new HybridBinarizer(source));
		} catch (IOException ex) {
			Logger.getLogger(QRCode.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		if (bitmap == null)
			 return null;
		
		QRCodeReader reader = new QRCodeReader();
		try {
			result = reader.decode(bitmap);
		} catch (NotFoundException | ChecksumException | FormatException ex) {
			Logger.getLogger(QRCode.class.getName()).log(Level.SEVERE, null, ex);
		}
		return result.getText();
	}
	
}
