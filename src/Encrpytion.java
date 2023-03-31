import java.io.*;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Scanner;

import javax.crypto.Cipher;

public class Encrpytion {
	public static void main(String[] args) {
		try{
			//Doc file chua public key
			FileInputStream fis = new FileInputStream("D:/HKI_2021_2022/CT229_Web_Sercurity/Lab/Lab5/YeuCau/KeyPair/publicKey");
			byte[] b = new byte[fis.available()];
			fis.read(b);
			fis.close();
			
			//Tao public key de ma hoa
			X509EncodedKeySpec spec = new X509EncodedKeySpec(b);
			KeyFactory factory = KeyFactory.getInstance("RSA");
			PublicKey pubKey = factory.generatePublic(spec);

			//Nhap du lieu tu ban phim
			Scanner kb = new Scanner(System.in);
			System.out.print("Nhap du lieu de ma hoa: ");
			String msg = kb.nextLine();

			//Ma hoa du lieu
			Cipher c = Cipher.getInstance("RSA");
			c.init(Cipher.ENCRYPT_MODE, pubKey);			
			byte encryptOut[] = c.doFinal(msg.getBytes());
			String strEncrypt = Base64.getEncoder().encodeToString(encryptOut);
			System.out.println(">> Chuoi sau khi ma hoa >>: " + strEncrypt);
		}
		catch (Exception e) {			
			System.out.println("Loi: " + e);
		}
		
	}
}

//Tham khao: https://viblo.asia/p/java-ma-hoa-va-giai-ma-voi-thuat-toan-rsa-bJzKmW3Xl9N