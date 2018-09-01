package armyBase.sd.request;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryption {
	 public static String encryptPassword(String password) {
			String encpass = password;
			try {
				   MessageDigest md = MessageDigest.getInstance("MD5");
			
				   md.update(password.getBytes());
		            //Get the hash's bytes
		            byte[] bytes = md.digest();
		            //This bytes[] has bytes in decimal format;
		            //Convert it to hexadecimal format
		            StringBuilder sb = new StringBuilder();
		            for(int i=0; i< bytes.length ;i++)
		            {
		                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		            }
		            //Get complete hashed password in hex format
		            encpass = sb.toString();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return encpass;
		}
}
