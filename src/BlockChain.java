import com.google.gson.GsonBuilder;
import java.util.ArrayList;

public class BlockChain {

    public static ArrayList<Block> blockchain = new ArrayList<Block>();

    public static void main(String[] args) {
        /* Create Genesis block (0); Add blocks to our blockchain */
        blockchain.add(new Block("1st block", "0"));
        // Go back -1 in ArrayList 'blockchain' to get previous hash
        blockchain.add(new Block("2nd block", blockchain.get(blockchain.size()-1).hash));
        blockchain.add(new Block("3rd block", blockchain.get(blockchain.size()-1).hash));
        // Creating string that will output blockchain hashes in Json format
        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println(blockchainJson);

    }

    /* Boolean isChainValid()
       - Ensures previous hash, is the correct hash
       - Checks all blocks in blockchain, and compares hashes.
       - Method completes this by, checking (hash variable == calculated hash) && (previousHash variable == prev hash)
       - Any changes made to blockchain's block == return false;
      */
    public static Boolean isChainValid(){
        Block currentBlock;
        Block prevBlock;

        // Check through all hashes; starting at block after Genesis block
        for(int i = 1; i < blockchain.size(); ++i){

            currentBlock = blockchain.get(i);
            prevBlock = blockchain.get(i-1);

            // Compare registered hash & calculated hash
            if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
                System.out.println("Current hashes not equal");
                return false;
            }

            if(!prevBlock.hash.equals(currentBlock.previousHash)){
                System.out.println("Previous hash not equal");
                return false;
            }

        }
        return true;
    }
}
