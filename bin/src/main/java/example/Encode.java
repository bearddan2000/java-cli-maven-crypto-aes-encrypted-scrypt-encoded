package example;

import org.bouncycastle.crypto.generators.SCrypt;
import java.io.IOException;
import javax.xml.bind.DatatypeConverter;

public class Encode {
  private static final String SALT = "abc123";

  // DifficultyFactor
  // These should be powers of 2
  private static final int cpu = 8;
  private static final int memory = 8;
  private static final int parallelism = 8;
  private static final int outputLength = 32;

  final Encryption encrypt = new Encryption();

  public String hashpw(String pass){
    byte[] hash = SCrypt.generate(pass.getBytes(), SALT.getBytes(), cpu, memory, parallelism, outputLength);

    try {

      String stored = new String(hash);

      return encrypt.encryptPasswordBased(stored);

    } catch (Exception e) {

      return null;

    }
  }

  public boolean verify(String pass, String hash){

    try{

      pass = hashpw(pass);

      return pass.equals(hash);

    } catch (Exception e) {

      System.out.println("Encode verify error");

      e.printStackTrace();

      return false;
    }
  }
}
