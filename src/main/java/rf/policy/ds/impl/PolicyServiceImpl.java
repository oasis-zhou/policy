package rf.policy.ds.impl;


import com.google.common.collect.Lists;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import rf.policy.ds.PolicyService;
import rf.policy.exception.GenericException;
import rf.policy.model.Policy;
import rf.policy.model.PolicyIndex;
import rf.policy.model.PolicyQueryCondition;
import rf.policy.model.enums.ContractStatus;
import rf.policy.repository.PolicyDao;
import rf.policy.repository.pojo.TPolicy;
import rf.policy.utils.JsonHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zhengzhou on 16/8/8.
 */
@Service
public class PolicyServiceImpl implements PolicyService {

    @Autowired
    private PolicyDao dao;
    @Autowired
    private JsonHelper jsonHelper;
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public String generateProposal(Policy policy) {
        TPolicy po = dao.findByProposalNumber(policy.getProposalNumber());

        if(po == null) {
            po = new TPolicy();
        }else{
            throw new GenericException(30002L);
        }

        BeanUtils.copyProperties(policy, po);

        po.setContractStatusCode(ContractStatus.PENDING_EFFECTIVE.name());
        po.setProposalDate(new Date());

        String content = jsonHelper.toJSON(policy);
        po.setContent(content);

        dao.save(po);

        return po.getProposalNumber();
    }

    @Override
    public String issuePolicy(Policy policy) {
        TPolicy po = dao.findByPolicyNumber(policy.getPolicyNumber());

        if(po == null) {
            po = new TPolicy();
        }else{
            throw new GenericException(30001L);
        }

        BeanUtils.copyProperties(policy, po);

        po.setContractStatusCode(ContractStatus.EFFECTIVE.name());
        po.setIssueDate(new Date());

        String content = jsonHelper.toJSON(policy);
        po.setContent(content);

        dao.save(po);

        return po.getPolicyNumber();
    }

    @Override
    public Policy loadPolicyByPolicyNumber(String policyNumber){
        TPolicy po = dao.findByPolicyNumber(policyNumber);

        Policy policy = null;
        if(po != null) {
            policy = jsonHelper.fromJSON(po.getContent(), Policy.class);
        }

        return policy;
    }

    @Override
    public Policy loadPolicyByPolicyNumberOnLock(String policyNumber){
        TPolicy po = dao.findByPolicyNumberOnLock(policyNumber);

        Policy policy = null;
        if(po != null) {
            policy = jsonHelper.fromJSON(po.getContent(), Policy.class);
        }

        return policy;
    }

    @Override
    public Policy loadPolicyByProposalNumber(String proposalNumber) {
        TPolicy po = dao.findByProposalNumber(proposalNumber);

        Policy policy = null;
        if(po != null) {
            policy = jsonHelper.fromJSON(po.getContent(), Policy.class);
        }

        return policy;
    }

    @Override
    public Policy loadPolicyByProposalNumberOnLock(String proposalNumber) {
        TPolicy po = dao.findByProposalNumberOnLock(proposalNumber);

        Policy policy = null;
        if(po != null) {
            policy = jsonHelper.fromJSON(po.getContent(), Policy.class);
        }

        return policy;
    }

    @Override
    public void savePolicy(Policy policy){
        TPolicy po = dao.findOne(policy.getUuid());

        if(po == null)
            po = new TPolicy();

        BeanUtils.copyProperties(policy, po);

        po.setContractStatusCode(policy.getContractStatus().name());

        String content = jsonHelper.toJSON(policy);
        po.setContent(content);

        dao.save(po);
    }

    @Override
    public List<PolicyIndex> findPolicy(PolicyQueryCondition condition) {

        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

       String sql = "select * from t_policy as p  where 1 = 1 ";

        List<PolicyIndex> indexs = Lists.newArrayList();

        List <Object> queryConditions=new ArrayList<Object>();
        if (condition.getPolicyNumber() != null && !condition.getPolicyNumber().equals("")) {
            sql += " and p.policyNumber = ? ";
            queryConditions.add(condition.getPolicyNumber());
        }
        if (condition.getProposalNumber() != null && !condition.getProposalNumber().equals("")) {
            sql += " and p.getProposalNumber = ? ";
            queryConditions.add(condition.getProposalNumber());
        }
        if (condition.getProductCode() != null && !condition.getProductCode().equals("")) {
            sql += " and p.productCode = ? ";
            queryConditions.add(condition.getProductCode());
        }
        if (condition.getContractStatus() != null && !condition.getContractStatus().equals("")) {
            sql += " and p.contractStatusCode = ? ";
            queryConditions.add(condition.getContractStatus());
        }
        if (condition.getProposalDateStart() != null) {
            sql += " and p.proposalDate >= ? ";
            queryConditions.add(fmt.print(new DateTime(condition.getProposalDateStart())));
        }
        if (condition.getProposalDateEnd() != null) {
            sql += " and p.proposalDate <= ? ";
            queryConditions.add(fmt.print(new DateTime(condition.getProposalDateEnd())));
        }

        indexs = jdbcTemplate.query(sql,queryConditions.toArray(),new BeanPropertyRowMapper(PolicyIndex.class));


        return indexs;
    }

}
