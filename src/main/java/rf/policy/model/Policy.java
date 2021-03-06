package rf.policy.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Maps;
import org.joda.time.Days;
import org.joda.time.LocalDateTime;
import rf.policy.conf.FieldSpec;
import rf.policy.model.enums.ContractStatus;
import rf.policy.model.enums.TerminalReason;
import rf.policy.pub.Guid;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;


public class Policy extends ModelComponent {

    @FieldSpec(code = "PRODUCT_CODE", name = "policy product code")
    private String productCode;
    private String productName;
    @FieldSpec(code = "CHANNEL_CODE", name = "policy channel id")
    private String channelCode;
    private String policyNumber;
    @FieldSpec(code = "POLICY_EFFECTIVE_DATE", name = "policy effective date")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", locale = "zh", timezone = "GMT+8")
    private Date effectiveDate;
    @FieldSpec(code = "POLICY_EXPIRED_DATE", name = "policy expired date")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", locale = "zh", timezone = "GMT+8")
    private Date expiredDate;
    @FieldSpec(code = "POLICY_POI", name = "period of insurance")
    private Integer poi;
    private String proposalNumber;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", locale = "zh", timezone = "GMT+8")
    private Date proposalDate;
    private ContractStatus contractStatus;
    private String businessOrgan;
    private String businessSource;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", locale = "zh", timezone = "GMT+8")
    private Date issueDate;
    private String quotationNumber;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", locale = "zh", timezone = "GMT+8")
    private Date quotationDate;
    private TerminalReason terminalReason;
    private Map<String,Object> underwritingReason = Maps.newHashMap();
    private String extReferenceNumber;
    private String sku;
    private BigDecimal premium;

    public Policy(){
        this.setUuid(Guid.generateStrId());
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
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
        if(this.expiredDate != null && this.effectiveDate != null){
            Days days = Days.daysBetween(new LocalDateTime(this.effectiveDate), new LocalDateTime(this.expiredDate));
            this.poi = days.getDays() + 1;
        }
    }

    public String getProposalNumber() {
        return proposalNumber;
    }

    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
    }

    public Integer getPoi() {
        return poi;
    }

    public void setPoi(Integer poi) {
        this.poi = poi;
    }

    public Date getProposalDate() {
        return proposalDate;
    }

    public void setProposalDate(Date proposalDate) {
        this.proposalDate = proposalDate;
    }

    public ContractStatus getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(ContractStatus contractStatus) {
        this.contractStatus = contractStatus;
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

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public String getQuotationNumber() {
        return quotationNumber;
    }

    public void setQuotationNumber(String quotationNumber) {
        this.quotationNumber = quotationNumber;
    }

    public Date getQuotationDate() {
        return quotationDate;
    }

    public void setQuotationDate(Date quotationDate) {
        this.quotationDate = quotationDate;
    }

    public Map<String, Object> getUnderwritingReason() {
        return underwritingReason;
    }

    public TerminalReason getTerminalReason() {
        return terminalReason;
    }

    public void setTerminalReason(TerminalReason terminalReason) {
        this.terminalReason = terminalReason;
    }

    public void setUnderwritingReason(Map<String, Object> underwritingReason) {
        this.underwritingReason = underwritingReason;
    }

    public String getExtReferenceNumber() {
        return extReferenceNumber;
    }

    public void setExtReferenceNumber(String extReferenceNumber) {
        this.extReferenceNumber = extReferenceNumber;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public BigDecimal getPremium() {
        return premium;
    }

    public void setPremium(BigDecimal premium) {
        this.premium = premium;
    }

}
