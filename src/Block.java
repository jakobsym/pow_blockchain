import java.util.Date;

public class Block {

    public String hash;
    public String previousHash;
    private String data; // unique transaction or some data, will be using a message in this example
    private long timeStamp; // number milliseconds since genesis block


    //Block Constructor
    public Block(String data, String previousHash){
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }


    // Block Methods
    public String calculateHash(){

        return StringUtil.applySha256(previousHash + timeStamp + data);
    }
    /* Boolean isChainValid()
       - Ensures previous hash, is the correct hash
       - E.G: Checks all blocks in blockchain, and compares hashes.
       Method completes this by, checking (calculated hash == actual hash)
    *  */
    public static Boolean isChainValid(){

    }


}
