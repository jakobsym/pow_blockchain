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
    public PublicKey receiver;
    public float amount;
    public byte[] signature; // unique signature for wallet






}
