package rf.policy.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by zhouzheng on 2017/5/3.
 */
public class PolicyIndex extends ModelComponent {

    private String policyNumber;
    private String proposalNumber;
    private String quotationNumber;
    private String productName;
    private String productCode;
    private BigDecimal premium;
    private Date effectiveDate;
    private Date expiredDate;
    private Date proposalDate;
    private Date issueDate;
    private String contractStatusCode;
    private String businessOrgan;
    private String businessSource;
    private String extReferenceNumber;

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

    public String getQuotationNumber() {
        return quotationNumber;
    }

    public void setQuotationNumber(String quotationNumber) {
        this.quotationNumber = quotationNumber;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public BigDecimal getPremium() {
        return premium;
    }

    public void setPremium(BigDecimal premium) {
        this.premium = premium;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

    public Date getProposalDate() {
        return proposalDate;
    }

    public void setProposalDate(Date proposalDate) {
        this.proposalDate = proposalDate;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public String getContractStatusCode() {
        return contractStatusCode;
    }

    public void setContractStatusCode(String contractStatusCode) {
        this.contractStatusCode = contractStatusCode;
    }

    public String getBusinessOrgan() {
        return businessOrgan;
    }

    public void setBusinessOrgan(String businessOrgan) {
        this.businessOrgan = businessOrgan;
    }

    public String getBusinessSource() {
        return businessSource;
    }

    public void setBusinessSource(String businessSource) {
        this.businessSource = businessSource;
    }

    public String getExtReferenceNumber() {
        return extReferenceNumber;
    }

    public void setExtReferenceNumber(String extReferenceNumber) {
        this.extReferenceNumber = extReferenceNumber;
    }
}
