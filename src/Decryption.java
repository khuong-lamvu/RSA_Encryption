import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class Decryption {
	public static void main(String[] args) {
		//Doc file chua private key
		try {
			FileInputStream fis = new FileInputStream("C:/Users/Administrator/Desktop/HKI_2021-2022/CT229_Web_Sercurity/Lab/Lab5/YeuCau/KeyPair/privateKey");
			byte[] b = new byte[fis.available()];
			fis.read(b);
			fis.close();
			
			//Tao private key de giai ma
			PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(b);
			KeyFactory factory = KeyFactory.getInstance("RSA");
			PrivateKey priKey = factory.generatePrivate(spec);
			
			//Nhap du lieu can giai ma
			Scanner kb = new Scanner(System.in);
			System.out.println("Nhap du lieu de giai ma: ");
			String in = kb.nextLine();
			//Giai ma
			Cipher c = Cipher.getInstance("RSA");
			c.init(Cipher.DECRYPT_MODE, priKey);
			// byte decryptOut[] = c.doFinal(Base64.getDecoder().decode(
			// 	"tghGxN0r16MAfqBV5wHzJf76B3sJc0i98msHnW4KhtBD0B57MCNGqAHTQMOoqhEf9Lbzz/1eRxnaiZ36Cyqd0iFl6XykgpxRt81wjaU5fZOCcBbggo1hSCYogSDHpv0Rd8emWzQ6vqFGR/xaR3kiEobn9j5/pfbtZEKsvQQlrMnAmhjlDJTi5cSeTxt3fAlw23LXSZAcQhM0hpeJITSOV2W2PTwJffSfWxV9q3uPTGooeXakTcqoukp0/QJohwCk9WXPBTnFBUStiNw8EX1wSrhSqFQOqNYlK37Zv8uksz+G5viH79142QtaZTyeLSG9+4DtvvpqEJj+QAZ/hms1oQ=="
			// 	));
			// System.out.println("Du lieu duoc giai ma >>: " + new String(decryptOut));			
			byte[] dec = c.doFinal((Base64.getDecoder().decode(in.getBytes())));
			System.out.println("Du lieu duoc giai ma >>: " + new String(dec));

		}
		catch (FileNotFoundException e) {			
			System.out.println("Khong tim thay File");
		}
		catch (IOException e){
			System.out.println("Loi IO");
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Loi: " + e);
		} catch (InvalidKeySpecException e) {
			System.out.println("Loi tao Private Key");
		} catch (NoSuchPaddingException e) {
			System.out.println("Loi giai ma");
		} catch (InvalidKeyException e) {
			System.out.println("Khong the tao khoa");
		} catch (IllegalBlockSizeException e) {
			System.out.println("Loi kich thuoc");
		} catch (BadPaddingException e) {
			System.out.println("Loi giai ma");
		}
	}
}

//Tham khao: https://viblo.asia/p/java-ma-hoa-va-giai-ma-voi-thuat-toan-rsa-bJzKmW3Xl9N