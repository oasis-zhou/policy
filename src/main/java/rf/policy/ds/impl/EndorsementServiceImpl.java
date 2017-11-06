package rf.policy.ds.impl;

import insurance.fd.numbering.NumberingFactor;
import insurance.fd.numbering.NumberingService;
import insurance.fd.numbering.NumberingType;
import insurance.fd.utils.JsonHelper;
import insurance.pa.constants.PolicyConstants;
import insurance.pa.ds.EndorsementService;
import insurance.pa.ds.PolicyLogService;
import insurance.pa.ds.PolicyService;
import insurance.pa.model.Endorsement;
import insurance.pa.model.Policy;
import insurance.pa.model.enums.EndorsementStatus;
import insurance.pa.model.enums.LogType;
import insurance.pa.repository.EndorsementDao;
import insurance.pa.repository.pojo.TEndorsement;
import org.assertj.core.util.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EndorsementServiceImpl implements EndorsementService {

	@Autowired
	private EndorsementDao endoDao;

	@Autowired
	private PolicyLogService logService;

	@Autowired
	private PolicyService policyService;


	@Autowired
	private JsonHelper jsonHelper;

	@Autowired
	private NumberingService numberingService;


	@Override
	public String create(Endorsement endorsement){

		Assert.notNull(endorsement.getPolicyNumber());
		Assert.notNull(endorsement.getEndorsementType());

		endorsement.setEndorsementStatus(EndorsementStatus.QUOTATION);
		save(endorsement);

		String endoId = endorsement.getUuid();

		//backup policy to log table
		logService.logPolicy(endorsement.getUuid(), LogType.ISSUE_LOG.name(), endorsement.getPolicyNumber());

		return endoId;
	}

	@Override
	public void save(Endorsement endorsement) {

		TEndorsement po = endoDao.findOne(endorsement.getUuid());
		if(po == null)
			po = new TEndorsement();

		BeanUtils.copyProperties(endorsement, po);
		po.setType(endorsement.getEndorsementType().name());
		if(endorsement.getEndorsementStatus() != null)
			po.setStatusCode(endorsement.getEndorsementStatus().name());

		if(endorsement.getApplicationType() != null)
			po.setApplyType(endorsement.getApplicationType().name());

		if(endorsement.getSequence() == null){

			Integer maxSequence = endoDao.findMaxSequence(endorsement.getPolicyNumber());
			Integer sequence = new Integer(1);

			if(maxSequence != null){
				sequence = maxSequence + 1;
			}
			endorsement.setSequence(sequence);
			po.setSequence(sequence);

		}

		String content = jsonHelper.toJSON(endorsement);

		po.setContent(content);

		endoDao.save(po);

	}

	@Override
	public Endorsement load(String endorsementId) {
		TEndorsement po = endoDao.findOne(endorsementId);

		Endorsement endorsement = jsonHelper.fromJSON(po.getContent(), Endorsement.class);

		return endorsement;
	}


	@Override
	public void issue(Endorsement endorsement) {

		endorsement.setIssueDate(new Date());
		endorsement.setEndorsementStatus(EndorsementStatus.ISSUE);

		if(endorsement.getEndorsementNumber() == null){
			Map<NumberingFactor, String> factors = new HashMap<NumberingFactor, String>();
			Date date = new Date();
			factors.put(NumberingFactor.TRANS_YEAR, new SimpleDateFormat("yyyy").format(date));

			//E{863100}4{TRANS_YEAR}7{SEQUENCE}
			String endorsementNumber = numberingService.generateNumber(NumberingType.ENDORSEMENT_NUMBER,factors);
			endorsement.setEndorsementNumber(endorsementNumber);
		}

		BigDecimal app = endorsement.getEndoFeeByCode(PolicyConstants.FEE_APP).getValue();
		endorsement.setPremium(app);

		save(endorsement);
	}

	@Override
	public String generateWording(Endorsement endorsement){

		return "";
	}

	@Override
	public void reject(String endorsementId){

		TEndorsement p = endoDao.findOne(endorsementId);

		p.setStatusCode(EndorsementStatus.REJECT.name());

		endoDao.save(p);

		//TODO recall policy info
		Policy policy = logService.loadLogPolicy(endorsementId, LogType.ISSUE_LOG.name());

		policyService.savePolicy(policy);

		logService.disablePolicyLog(endorsementId);
	}

	public boolean hasPendingEndorsement(String policyNumber){
		TEndorsement endorsement = endoDao.findPendingEndorsement(policyNumber);

		Boolean result = false;
		if(endorsement != null)
			result = true;

		return result;
	}

	@Override
	public List<Endorsement> findEndorsements(String policyNumber) {
		List<TEndorsement> pos = endoDao.findEndorsement(policyNumber);
		List<Endorsement> endorsements = Lists.newArrayList();

		for(TEndorsement po : pos){
			Endorsement endorsement = jsonHelper.fromJSON(po.getContent(), Endorsement.class);
			endorsements.add(endorsement);
		}
		return endorsements;
	}

	@Override
	public Endorsement loadByNumber(String endorsementNumber) {
		TEndorsement po = endoDao.findByNumber(endorsementNumber);

		Endorsement endorsement = jsonHelper.fromJSON(po.getContent(), Endorsement.class);

		return endorsement;
	}


}
