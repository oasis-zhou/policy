package rf.policy.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import rf.policy.conf.FieldSpec;
import rf.policy.model.enums.EndorsementApplicationType;
import rf.policy.model.enums.EndorsementStatus;
import rf.policy.model.enums.EndorsementType;
import rf.policy.pub.Guid;

import java.math.BigDecimal;
import java.util.Date;


public class Endorsement extends ModelComponent {

    private String policyNumber;
    private String productCode;
    private String channelCode;
    @FieldSpec(code = "ENDO_TYPE", name = "endorsement type")
    private EndorsementType endorsementType;
    @FieldSpec(code = "ENDO_CODE", name = "endorsement sub type code")
    private String code;
    private String name;
    private EndorsementStatus endorsementStatus;
    @FieldSpec(code = "ENDO_EFFECTIVE_DATE", name = "endorsement effective date")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", locale = "zh", timezone = "GMT+8")
    private Date effectiveDate;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", locale = "zh", timezone = "GMT+8")
    private Date applicationDate;
    private Date issueDate;
    private String endorsementNumber;
    private String wording;
    private EndorsementApplicationType applicationType;
    private Integer sequence;
    private BigDecimal premium;

    public Endorsement(){
        this.setUuid(Guid.generateStrId());
    }


    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public EndorsementType getEndorsementType() {
        return endorsementType;
    }

    public void setEndorsementType(EndorsementType endorsementType) {
        this.endorsementType = endorsementType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EndorsementStatus getEndorsementStatus() {
        return endorsementStatus;
    }

    public void setEndorsementStatus(EndorsementStatus endorsementStatus) {
        this.endorsementStatus = endorsementStatus;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public String getEndorsementNumber() {
        return endorsementNumber;
    }

    public void setEndorsementNumber(String endorsementNumber) {
        this.endorsementNumber = endorsementNumber;
    }

    public String getWording() {
        return wording;
    }

    public void setWording(String wording) {
        this.wording = wording;
    }

    public EndorsementApplicationType getApplicationType() {
        return applicationType;
    }

    public void setApplicationType(EndorsementApplicationType applicationType) {
        this.applicationType = applicationType;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public BigDecimal getPremium() {
        return premium;
    }

    public void setPremium(BigDecimal premium) {
        this.premium = premium;
    }
}
