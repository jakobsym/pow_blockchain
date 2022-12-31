package blockchain;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;

/* Takes String as input, and converts into  SHA-256 hash
*   - Input: previousHash + timeStamp + nonce + data
*/
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
    /*
      applyECDSASig(x,y)
        - takes senders private key, and string input, signs and returns as array of bytes
     */
    public static byte[] applyECDSASig(PrivateKey privateKey, String input){
        Signature dsa;
        byte[] output; //already init to all 0

        try {
            dsa = Signature.getInstance("ECDSA", "BC");
            dsa.initSign(privateKey);

            byte[] strByte = input.getBytes();
            dsa.update(strByte);

            byte[] realSig = dsa.sign();
            output = realSig;

        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
        return output;
    }

    // Verify String signature
    public static boolean verifyECDSASig(PublicKey publicKey, String data, byte[] signature){
        try{
            Signature ecdsaVerify = Signature.getInstance("ECDSA", "BC");
            ecdsaVerify.initVerify(publicKey);
            ecdsaVerify.update(data.getBytes());

            return ecdsaVerify.verify(signature);

        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public static String getStringFromKey(Key key) {
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }

}
