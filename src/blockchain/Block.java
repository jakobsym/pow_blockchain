package blockchain;

import java.util.Date;

public class Block {

    public String hash;
    public String previousHash;
    private String data; // unique transaction or some data, will be using a message in this example
    private long timeStamp; // number milliseconds since genesis block
    private int nonce; // nonce is an arbitrary pseudo-random number


    //Block Constructor
    public Block(String data, String previousHash){
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }


    // Block Methods
    public String calculateHash(){
        return StringUtil.applySha256(previousHash + timeStamp + nonce + data);
    }

    /* public void mineBlock(int difficulty)
        - create new char array of size difficulty, which is turned into a string
        - This string is filled with 0's which must be solved for as proof of work
        - I.E: difficulty = 5, target = '00000'
        - Miner must

        - This method will trigger on e/a new block
        - This method will trigger when checking if Blocks are valid(solved)
    */
    public void mineBlock(int difficulty){
        String target = new String(new char[difficulty]).replace('\0', '0');
        // While hash[0 - difficulty] != target; nonce is incremented by 1 and hash is calculated
        while(!hash.substring(0, difficulty).equals(target)){
            nonce++;
            hash = calculateHash();
        }
        System.out.println("Block mined: " + hash);
    }
}
