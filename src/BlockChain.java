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













        Block genesisBlock = new Block("1st block", "0");
        System.out.println("Block 1 hash: " + genesisBlock.hash);

        Block secondBlock = new Block("2nd block", genesisBlock.hash);
        System.out.println("Block 2 hash: " + secondBlock.hash);


        Block thirdBlock = new Block("3rd block", secondBlock.hash);
        System.out.println("Block 3 hash: " + thirdBlock.hash);


    }
}
