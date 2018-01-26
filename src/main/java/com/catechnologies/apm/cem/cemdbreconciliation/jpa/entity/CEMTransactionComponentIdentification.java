package com.catechnologies.apm.cem.cemdbreconciliation.jpa.entity;

import java.io.Serializable;



//import java.util.List;
import javax.persistence.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.catechnologies.apm.cem.cemdbreconciliation.jaxb.adapters.CEMTransactionComponentIdentificationAdapted;

/**
 * The persistent class for the ts_params database table.
 * For JAXB mapping see adapted class CEMTransactionComponentIdentificationAdapted.
 * 
 */
@Entity
@Table(name="ts_params")
@NamedQuery(name="CEMTransactionComponentIdentification.findAll", query="SELECT c FROM CEMTransactionComponentIdentification c WHERE c.tsSoftDelete=false")
public class CEMTransactionComponentIdentification extends CEMEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	static final Logger log = LogManager.getLogger(CEMTransactionComponentIdentification.class.getName());

	@EmbeddedId
	private CEMTransactionComponentIdentificationPK id;

	@Column(name="ts_autogen_sequence")
	private Integer tsAutogenSequence;

	@Column(name="ts_name_type")
	private Integer tsNameType;

	@Column(name="ts_operator")
	private Integer tsOperator;

	@Column(name="ts_original_value")
	private String tsOriginalValue;

	@Column(name="ts_pattern")
	private String tsPattern;

	@Column(name="ts_soft_delete")
	private Boolean tsSoftDelete;

	@Column(name="version_info")
	private Long versionInfo;

	//bi-directional many-to-one association to CEMTransactionComponent
	@ManyToOne
	@MapsId("tsTrancompId") //propagates CEMTransactionComponent.tsId to embedded attribute this.id.tsTrancompId
							//it seems eclipse link is propagating this id properly even without this annotation but to be
							//compliant with JPA 2 we keep it here
							//https://www.eclipse.org/forums/index.php/t/209086/
							//http://www.objectdb.com/api/java/jpa/MapsId
	@JoinColumn(name="ts_trancomp_id")
	private CEMTransactionComponent tsTrancomp;

	public CEMTransactionComponentIdentification() {
	}
	
	/**
	 * this constructor is used by JAXB adapter
	 * @param v
	 */
	public CEMTransactionComponentIdentification(CEMTransactionComponentIdentificationAdapted v) {
		
		if(this.id==null)
			this.id = new CEMTransactionComponentIdentificationPK();
		
		this.id.setTsName(v.getTsName());
		this.id.setTsParamType(v.getTsParamType());
		this.id.setTsTrancompId(v.getTsTrancompId());
		
		this.setTsAutogenSequence(v.getTsAutogenSequence());
		this.setTsNameType(v.getTsNameType());
		this.setTsOperator(v.getTsOperator());
		this.setTsOriginalValue(v.getTsOriginalValue());
		this.setTsPattern(v.getTsPattern());
		this.setTsSoftDelete(v.getTsSoftDelete());
		this.setVersionInfo(v.getVersionInfo());
		this.setTsTrancomp(v.getTsTrancomp());
		
	}
	

	public CEMTransactionComponentIdentificationPK getId() {
		return this.id;
	}

	public void setId(CEMTransactionComponentIdentificationPK id) {
		this.id = id;
	}

	public Integer getTsAutogenSequence() {
		return this.tsAutogenSequence;
	}

	public void setTsAutogenSequence(Integer tsAutogenSequence) {
		this.tsAutogenSequence = tsAutogenSequence;
	}

	public Integer getTsNameType() {
		return this.tsNameType;
	}

	public void setTsNameType(Integer tsNameType) {
		this.tsNameType = tsNameType;
	}

	public Integer getTsOperator() {
		return this.tsOperator;
	}

	public void setTsOperator(Integer tsOperator) {
		this.tsOperator = tsOperator;
	}

	public String getTsOriginalValue() {
		return this.tsOriginalValue;
	}

	public void setTsOriginalValue(String tsOriginalValue) {
		this.tsOriginalValue = tsOriginalValue;
	}

	public String getTsPattern() {
		return this.tsPattern;
	}

	public void setTsPattern(String tsPattern) {
		this.tsPattern = tsPattern;
	}

	public Boolean getTsSoftDelete() {
		return this.tsSoftDelete;
	}

	@Override
	public void setTsSoftDelete(Boolean tsSoftDelete) {
		this.tsSoftDelete = tsSoftDelete;
	}

	public Long getVersionInfo() {
		return this.versionInfo;
	}

	public void setVersionInfo(Long versionInfo) {
		this.versionInfo = versionInfo;
	}

	public CEMTransactionComponent getTsTrancomp() {
		return this.tsTrancomp;
	}

	public void setTsTrancomp(CEMTransactionComponent tsTrancomp) {
		this.tsTrancomp=tsTrancomp;
	}

	@Override
	public String toString() {
		return "CEMTransactionComponentIdentification [id=" + id.toString()
				+ ", tsAutogenSequence=" + tsAutogenSequence + ", tsNameType="
				+ tsNameType + ", tsOperator=" + tsOperator
				+ ", tsOriginalValue=" + tsOriginalValue + ", tsPattern="
				+ tsPattern + ", tsSoftDelete=" + tsSoftDelete
				//+ ", versionInfo=" + versionInfo + ", tsTrancomp=" + tsTrancomp //this would cause cyclic referencing and Stack Overflow
				+ ", versionInfo=" + versionInfo
				+ "]";
	}

	
	/**
	 * returns fully qualified transaction component identification name
	 * @return
	 */
	public String getFQN(){
		return this.tsTrancomp.getFQN() + "_" + this.id.getTsParamType() + "_" + this.id.getTsName();
	}
	

	@Override
	public void copyTo(CEMEntity target) {
		
		CEMTransactionComponentIdentification _target = (CEMTransactionComponentIdentification)target;
		
		log.entry(this, target);
		
		
		//_target.setTsAutogenSequence(this.getTsAutogenSequence());//calculated in addToParentPostprocessing
		_target.setTsNameType(this.getTsNameType());
		_target.setTsOperator(this.getTsOperator());
		_target.setTsOriginalValue(this.getTsOriginalValue());
		_target.setTsPattern(this.getTsPattern());
		_target.setTsSoftDelete(this.getTsSoftDelete());
		_target.setVersionInfo(this.getVersionInfo());			
		
		log.exit(target);
		
	}

	@PrePersist
	private void prePersistCustomAction(){
		//log.debug("prePersistCustomAction()>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		/**
		 * CEMTransactionComponentIdentificationPK.tsTrancompId is properly populated by JPA/EclipseLink when commit is done.
		 * This propagation works fine even WITHOUT @MapsId annotation on CEMTransactionComponentIdentification.tsTrancomp 
		 * (which is strange, probably some deviation of EclipseLink implementation from pure JPA 2.0 specification)! But even when entity
		 * manager is flushed in App class (during dry run processing) this ID will be not visible in XML (though it will be properly distributed into database).
		 * To see it even in output XML during dry run following line below must be executed.
		 */
		this.id.setTsTrancompId(this.getTsTrancomp().getTsId());
		
	}
	
	
	@Override
	public void addToTargetParent(CEMEntity targetParent) {
		
		CEMTransactionComponent _targetParent = (CEMTransactionComponent)targetParent;
		
		log.entry(this, targetParent);
		
		_targetParent.addTsParam(this);
		this.id.setTsTrancompId(_targetParent.getTsId());//change PK to new target value
		
		
		/*
		 * 
		 * not sure whether this is actually needed, entries in ts_params have ts_autogen_sequence set to null 
		 
		List<CEMTransactionComponentIdentification> targetList = _targetParent.getTsParams();
		Integer max = 0;
		
		for(int i=0;i<targetList.size();i++){
			
			CEMTransactionComponentIdentification iteratedItem = targetList.get(i);
			
			if(iteratedItem.getTsAutogenSequence() == null) continue;//ts_autogen_sequence might be zero which would case NullPointerException
			if(iteratedItem.equals(this)) continue;//we are not interested in comparing with same item, i.e. item that was just added
			
			if(iteratedItem.getTsAutogenSequence().intValue() > max)	max = iteratedItem.getTsAutogenSequence().intValue();
		}
		
		
		this.setTsAutogenSequence(new Integer(++max));
		log.debug("setTsAutogenSequence(): " + this.getTsAutogenSequence() );
		*/
		
		
		log.exit(this);
		
	}

	@Override
	public CEMEntity deepCopy() throws Exception {
		
		CEMTransactionComponentIdentification clone = (CEMTransactionComponentIdentification)super.deepCopy();
		
		//after cloning the entity we need to reset all ts_id attributes across the whole object tree so that
		//JPA will generate new IDs from respective DB sequences.!!!!
		
		clone.id.setTsTrancompId(null);
		
		return clone;
	}
	
	
	
	
}