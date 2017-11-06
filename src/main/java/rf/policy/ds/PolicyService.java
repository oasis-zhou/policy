package rf.policy.ds;



import rf.policy.model.Policy;
import rf.policy.model.PolicyIndex;
import rf.policy.model.PolicyQueryCondition;

import java.util.List;

/**
 * Created by zhengzhou on 16/8/8.
 */
public interface PolicyService {

    public String generateProposal(Policy policy);
    public String issuePolicy(Policy policy);
    public Policy loadPolicyByPolicyNumber(String policyNumber);
    public Policy loadPolicyByPolicyNumberOnLock(String policyNumber);
    public Policy loadPolicyByProposalNumber(String proposalNumber);
    public Policy loadPolicyByProposalNumberOnLock(String proposalNumber);
    public void savePolicy(Policy policy);
    public List<PolicyIndex> findPolicy(PolicyQueryCondition condition);

}
