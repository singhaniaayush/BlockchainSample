import java.security.PublicKey;

/**
 * The Class TransactionOutput.
 */
public class TransactionOutput {
	
	public String id;
	public PublicKey reciepient;
	public float value;
	public String parentTransactionId;

	public TransactionOutput(PublicKey receiver, float value, String parentTransactionId) {
		this.reciepient = receiver;
		this.value = value;
		this.parentTransactionId = parentTransactionId;
	}
	
	public boolean isMine(PublicKey pubKey) {
		return (pubKey == reciepient);
	}
}
