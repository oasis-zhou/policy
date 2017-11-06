package rf.policy.model;

import java.util.Date;

/**
 * Created by zhouzheng on 2017/5/24.
 */
public class PolicyQueryCondition {

    private String policyNumber;
    private String proposalNumber;
    private String productCode;
    private String businessOrgan;
    private String contractStatus;
    private Date proposalDateStart;
    private Date proposalDateEnd;

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public String getProposalNumber() {
        return proposalNumber;
    }

    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(String contractStatus) {
        this.contractStatus = contractStatus;
    }

    public Date getProposalDateStart() {
        return proposalDateStart;
    }

    public void setProposalDateStart(Date proposalDateStart) {
        this.proposalDateStart = proposalDateStart;
    }

    public Date getProposalDateEnd() {
        return proposalDateEnd;
    }

    public void setProposalDateEnd(Date proposalDateEnd) {
        this.proposalDateEnd = proposalDateEnd;
    }
}
