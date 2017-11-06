package rf.policy.ds;



import rf.policy.model.Endorsement;

import java.util.List;

public interface EndorsementService {

	public String create(Endorsement endorsement);

	public void save(Endorsement endorsement);

	public Endorsement load(String endorsementId);
	
	public void issue(Endorsement endorsement);
	
	public String generateWording(Endorsement endorsement);
	
	public void reject(String endorsementId);

	public boolean hasPendingEndorsement(String policyNumber);

	public List<Endorsement> findEndorsements(String policyNumber);

	public Endorsement loadByNumber(String endorsementNumber);
	
}
