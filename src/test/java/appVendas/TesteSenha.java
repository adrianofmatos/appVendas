package appVendas;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;

public class TesteSenha {

	@Test
	public void MD() {
	       String s="123";
	       MessageDigest m;
		try {
			m = MessageDigest.getInstance("MD5");
			m.update(s.getBytes(),0,s.length());
			System.out.println("MD5: "+new BigInteger(1,m.digest()).toString(16));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
