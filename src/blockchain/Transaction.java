package blockchain;

import java.security.*;
import java.util.ArrayList;

/* E/a transaction holds:
    - pub. key of sender of funds
    - pub. key of receiver of funds
    - amount of funds transferred
    - ref. to prev. transactions (proves sender has funds to send)
    - signature that proves sender is the one sending transaction
* */

public class Transaction {

    public String transactionId; // transaction hash
    public PublicKey sender;
    public PublicKey reciepient;
    public float amount;
    public byte[] signature; // unique signature for wallet


    public ArrayList<TransactionInput> inputs = new ArrayList<TransactionInput>();
    public ArrayList<TransactionOutput> outputs = new ArrayList<TransactionOutput>();

    // counter of num transactions
    private static int sequence = 0;

    // Constructors
    public Transaction(PublicKey from, PublicKey to, float amount, ArrayList<TransactionInput> inputs){
        this.sender = from;
        this.reciepient = to;
        this.amount = amount;
        this.inputs = inputs;
    }

    private String calcHash(){
        sequence++; // increment to avoid 2 transactions w/ same hash

        return StringUtil.applySha256(StringUtil.getStringFromKey(sender) + StringUtil.getStringFromKey(reciepient) + Float.toString(amount) + sequence);
    }
    // method that signs all data (bare minimum)
    // I.E: We could also sign for output/input or time-stamp...
    public void generateSignature(PrivateKey privateKey){
        String data = StringUtil.getStringFromKey(sender) + StringUtil.getStringFromKey(reciepient) + Float.toString(amount);

        signature = StringUtil.applyECDSASig(privateKey, data);
    }

    // verify data we signed hasnt been tampered

    public boolean verifySignature(){
        String data = StringUtil.getStringFromKey(sender) + StringUtil.getStringFromKey(reciepient) + Float.toString(amount);

        return StringUtil.verifyECDSASig(sender, data, signature);
    }

}

