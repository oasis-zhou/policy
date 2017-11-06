package rf.policy.model;


import rf.policy.conf.FieldSpec;
import rf.policy.pub.Guid;

import java.math.BigDecimal;

/**
 * Created by zhengzhou on 16/4/12.
 */
public class Coverage extends ModelComponent {

    private String name;
    @FieldSpec(code = "COVERAGE_CODE", name = "coverage spec code")
    private String code;
    private String indemnityLimit;
    private BigDecimal premium;


    public Coverage(){
        this.setUuid(Guid.generateStrId());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIndemnityLimit() {
        return indemnityLimit;
    }

    public void setIndemnityLimit(String indemnityLimit) {
        this.indemnityLimit = indemnityLimit;
    }

    public BigDecimal getPremium() {
        return premium;
    }

    public void setPremium(BigDecimal premium) {
        this.premium = premium;
    }
}
