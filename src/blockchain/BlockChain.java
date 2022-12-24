package blockchain;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;

public class BlockChain {

    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    public static int difficulty = 5;

    public static void main(String[] args) {
        /* Create Genesis block (0); Add blocks to our blockchain */
        blockchain.add(new Block("1st block", "0"));
        System.out.println("Attempting to mine block1...");
        blockchain.get(0).mineBlock(difficulty);
        // Go back -1 in ArrayList 'blockchain' to get previous hash
        blockchain.add(new Block("2nd block", blockchain.get(blockchain.size()-1).hash));
        System.out.println("Attempting to mine block2...");
        blockchain.get(1).mineBlock(difficulty);

        blockchain.add(new Block("3rd block", blockchain.get(blockchain.size()-1).hash));
        System.out.println("Attemping to mine block3...");
        blockchain.get(2).mineBlock(difficulty);
        // Check if hashes of blockchain are valid
        System.out.println("\nblocks of blockchain are valid: " + isChainValid());
        // Creating string that will output blockchain hashes in Json format
        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println("\nBlockChain: ");
        System.out.println(blockchainJson);

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

