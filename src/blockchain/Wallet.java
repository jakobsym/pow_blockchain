package blockchain;
import java.security.*;
import java.security.spec.ECGenParameterSpec;

public class Wallet {

    public PrivateKey privateKey; // Public address (displayed on-chain)
    public PublicKey publicKey;  // private address (user keeps secret)

    // Generate keys using Elliptic-curve cryptography via 'Java.security.KeyPairGenerator'

    public Wallet(){
        generateKeyPair();
    }
    public void generateKeyPair() {
        try{
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDSA", "BC");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            ECGenParameterSpec ecSpec = new ECGenParameterSpec("prime192v1");

            // generate keyPair after init of key generator
            keyGen.initialize(ecSpec, random); //256 byte as security ECDSA = 2^256 operations for attacker
            KeyPair keyPair = keyGen.generateKeyPair();

            // assign public/private generated keys
            privateKey = keyPair.getPrivate();
            publicKey = keyPair.getPublic();

        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
