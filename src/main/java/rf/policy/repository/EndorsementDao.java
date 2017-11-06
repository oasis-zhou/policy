package rf.policy.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import rf.policy.repository.pojo.TEndorsement;

import java.util.List;

public interface EndorsementDao extends CrudRepository<TEndorsement, String>{

	@Query("SELECT m FROM #{#entityName} m WHERE m.policyNumber =:policyNumber order by m.sequence desc")
	public TEndorsement findLastEndorsement(@Param("policyNumber") String policyNumber);
	
	@Query("SELECT MAX(m.sequence) FROM #{#entityName} m WHERE m.policyNumber =:policyNumber")
	public Integer findMaxSequence(@Param("policyNumber") String policyNumber);
	
	@Query("SELECT m FROM #{#entityName} m WHERE m.policyNumber =:policyNumber and (m.statusCode = 'QUOTATION' or m.statusCode = 'UNDERWRITING')")
	public TEndorsement findPendingEndorsement(@Param("policyNumber") String policyNumber);

	@Query("SELECT m FROM #{#entityName} m WHERE m.policyNumber =:policyNumber and m.statusCode = 'ISSUE'")
	public List<TEndorsement> findEndorsement(@Param("policyNumber") String policyNumber);

	@Query("SELECT m FROM #{#entityName} m WHERE m.endorsementNumber =:endorsementNumber")
	public TEndorsement findByNumber(@Param("endorsementNumber") String endorsementNumber);
}
