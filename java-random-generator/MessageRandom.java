import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * CreateSalt
 */
public class MessageRandom {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        // initialization of the application
        SecureRandom prng = SecureRandom.getInstance("SHA1PRNG");

        // generate a random number
        String randomNum = new Integer(prng.nextInt()).toString();

        // get its digest
        MessageDigest sha = MessageDigest.getInstance("SHA-1");
        byte[] result = sha.digest(randomNum.getBytes());

        System.out.println("Random number: " + randomNum);
        System.out.println("Message digest: " + new String(result));
    }
}