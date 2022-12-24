package blockchain;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/* Takes String as user input, and converts into  SHA-256 hash */
public class StringUtil {
    /* Applies SHA256 to a string and returns its result */
    public static String applySha256(String input){
        // code may have exceptions so using try and catch blocks
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            // Turns input into SHA-256 hash
            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));

            // Using StringBuffer class, as its more secure than StringBuilder; Creates placeholder StringBuffer we fill
            StringBuffer hexString = new StringBuffer();

            for(int i = 0; i < hash.length; ++i){
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();

        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
