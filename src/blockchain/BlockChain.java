package blockchain;


import java.security.Security;
import java.util.ArrayList;
import java.util.Base64;
import com.google.gson.GsonBuilder;


public class BlockChain {

    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    public static int difficulty = 5;
    public static Wallet walletA;
    public static Wallet walletB;

    public static void main(String[] args) {
        // Set Bouncy castle as sec. provider
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

        walletA = new Wallet();
        walletB = new Wallet();
        // Test pub/priv keys
        System.out.println("Private and public keys: ");
        System.out.println(StringUtil.getStringFromKey(walletA.privateKey));
        System.out.println(StringUtil.getStringFromKey(walletA.publicKey));

        // test transaction from walletA -> walletB
        Transaction transaction = new Transaction(walletA.publicKey, walletB.publicKey, 5, null);
        transaction.generateSignature(walletA.privateKey);
        //verify signature works
        System.out.println("Is signature verified");
        System.out.println(transaction.verifySignature());

    }

    /* public static Boolean isChainValid()
       - Ensures previous hash, is the correct hash
       - Checks all blocks in blockchain, and compares hashes.
       - Method completes this by, checking (hash variable == calculated hash) && (previousHash variable == prev hash)
       - Any changes made to blockchain's block == return false;
      */
    public static Boolean isChainValid(){
        Block currentBlock;
        Block prevBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');
        // Check through all hashes; starting at block after Genesis block
        for(int i = 1; i < blockchain.size(); ++i){

            currentBlock = blockchain.get(i);
            prevBlock = blockchain.get(i-1);

            // Compare registered hash & calculated hash
            if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
                System.out.println("Current hashes not equal");
                return false;
            }
            // Compare previous hash and registered previous hash
            if(!prevBlock.hash.equals(currentBlock.previousHash)){
                System.out.println("Previous hash not equal");
                return false;
            }
            // Check if hash has been solved
            if(!currentBlock.hash.substring(0, difficulty).equals(hashTarget)){
                System.out.println("This block has not been mined.");
                return false;
            }

        }
        return true;
    }
}

