import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;

public class SecurityKeyPairGenerator {
	public static void main(String[] args) {
		try{
			SecureRandom sr = new SecureRandom();
		//Tao cap khoa RSA - voi kich thuoc cua khoa
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
		kpg.initialize(2048, sr); 
		
		//Khoi tao cap khoa tu KeyPair
		KeyPair kp = kpg.genKeyPair();
		//---PublicKey
		PublicKey publicK = kp.getPublic();
		//---PrivateKey
		PrivateKey privateK = kp.getPrivate();

		//Tao File de luu khoa
		File publicKeyFile = createKeyFile(new File("C:/Users/Administrator/Desktop/HKI_2021-2022/CT229_Web_Sercurity/Lab/Lab5/YeuCau/KeyPair/publicKey.rsa"));
		File privateKeyFile = createKeyFile(new File("C:/Users/Administrator/Desktop/HKI_2021-2022/CT229_Web_Sercurity/Lab/Lab5/YeuCau/KeyPair/privateKey.rsa"));
		
		//Luu PublicKey
		FileOutputStream fos = new FileOutputStream(publicKeyFile);
		fos.write(publicK.getEncoded()); //Ma hoa
		fos.close();
		//Luu PrivateKey
		fos = new FileOutputStream(privateKeyFile);
		fos.write(privateK.getEncoded()); //Ma hoa
		fos.close();
		}
		catch(Exception e){
			System.out.println("Loi: " + e);
		}
		
		
	}
	private static File createKeyFile(File file) throws IOException{
		if(!file.exists()){
			file.createNewFile();
		}
		else{
			file.delete();
			file.createNewFile();
		}
		return file;
	}
}
