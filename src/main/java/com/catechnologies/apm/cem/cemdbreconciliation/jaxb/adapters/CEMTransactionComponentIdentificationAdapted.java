package com.catechnologies.apm.cem.cemdbreconciliation.jaxb.adapters;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import org.eclipse.persistence.oxm.annotations.XmlInverseReference;
import com.catechnologies.apm.cem.cemdbreconciliation.jpa.entity.CEMTransactionComponent;
import com.catechnologies.apm.cem.cemdbreconciliation.jpa.entity.CEMTransactionComponentIdentification;

/**
 * Adapted object used by CEMTransactionComponentIdentificationAdapter
 * @author bezad01
 *
 */
@XmlRootElement(name="componentIdentification")
@XmlAccessorType(XmlAccessType.NONE)
public class CEMTransactionComponentIdentificationAdapted {

	@XmlAttribute(name="ts_name")
	private String tsName;

	@XmlAttribute(name="ts_trancomp_id")
	private Long tsTrancompId;

	@XmlAttribute(name="ts_param_type")
	private String tsParamType;
	
	@XmlAttribute(name="ts_autogen_sequence")
	private Integer tsAutogenSequence;

	@XmlAttribute(name="ts_name_type")
	private Integer tsNameType;

	@XmlAttribute(name="ts_operator")
	private Integer tsOperator;

	@XmlAttribute(name="ts_original_value")
	private String tsOriginalValue;

	@XmlAttribute(name="ts_pattern")
	private String tsPattern;

	@XmlAttribute(name="ts_soft_delete")
	private Boolean tsSoftDelete;

	@XmlAttribute(name="version_info")
	private Long versionInfo;
	
	@XmlInverseReference(mappedBy="tsParams")
	private CEMTransactionComponent tsTrancomp;
	
	

	public Long getTsTrancompId() {
		return tsTrancompId;
	}

	public void setTsTrancompId(Long tsTrancompId) {
		this.tsTrancompId = tsTrancompId;
	}

	public String getTsParamType() {
		return tsParamType;
	}

	public void setTsParamType(String tsParamType) {
		this.tsParamType = tsParamType;
	}

	public String getTsName() {
		return tsName;
	}

	public void setTsName(String tsName) {
		this.tsName = tsName;
	}

	public Integer getTsAutogenSequence() {
		return tsAutogenSequence;
	}

	public void setTsAutogenSequence(Integer tsAutogenSequence) {
		this.tsAutogenSequence = tsAutogenSequence;
	}

	public Integer getTsNameType() {
		return tsNameType;
	}

	public void setTsNameType(Integer tsNameType) {
		this.tsNameType = tsNameType;
	}

	public Integer getTsOperator() {
		return tsOperator;
	}

	public void setTsOperator(Integer tsOperator) {
		this.tsOperator = tsOperator;
	}

	public String getTsOriginalValue() {
		return tsOriginalValue;
	}

	public void setTsOriginalValue(String tsOriginalValue) {
		this.tsOriginalValue = tsOriginalValue;
	}

	public String getTsPattern() {
		return tsPattern;
	}

	public void setTsPattern(String tsPattern) {
		this.tsPattern = tsPattern;
	}

	public Boolean getTsSoftDelete() {
		return tsSoftDelete;
	}

	public void setTsSoftDelete(Boolean tsSoftDelete) {
		this.tsSoftDelete = tsSoftDelete;
	}

	public Long getVersionInfo() {
		return versionInfo;
	}

	public void setVersionInfo(Long versionInfo) {
		this.versionInfo = versionInfo;
	}

	
	public CEMTransactionComponent getTsTrancomp() {
		return tsTrancomp;
	}

	public void setTsTrancomp(CEMTransactionComponent tsTrancomp) {
		this.tsTrancomp = tsTrancomp;
	}

	public CEMTransactionComponentIdentificationAdapted(){
		//empty constructor with no arguments is required by JAXB
	}
	
	public CEMTransactionComponentIdentificationAdapted(CEMTransactionComponentIdentification obj) {
		this.tsName = obj.getId().getTsName();
		this.tsTrancompId = obj.getId().getTsTrancompId();
		this.tsParamType = obj.getId().getTsParamType();
		this.tsAutogenSequence = obj.getTsAutogenSequence();
		this.tsNameType = obj.getTsNameType();
		this.tsOperator = obj.getTsOperator();
		this.tsOriginalValue = obj.getTsOriginalValue();
		this.tsPattern = obj.getTsPattern();
		this.tsSoftDelete = obj.getTsSoftDelete();
		this.versionInfo = obj.getVersionInfo();
		this.tsTrancomp = obj.getTsTrancomp();
	}

	
	
}
