package com.catechnologies.apm.cem.cemdbreconciliation.jpa.entity;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

/**
 * The primary key class for the ts_params database table.
 * 
 */
@Embeddable
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name="componentIdentificationPK")
public class CEMTransactionComponentIdentificationPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ts_trancomp_id", insertable=false, updatable=false)
	@XmlAttribute(name="ts_trancomp_id")
	private Long tsTrancompId;
	

	@Column(name="ts_param_type")
	@XmlAttribute(name="ts_param_type")
	private String tsParamType;

	@Column(name="ts_name")
	@XmlAttribute(name="ts_name")
	private String tsName;

	public CEMTransactionComponentIdentificationPK() {
	}
	
	
	public Long getTsTrancompId() {
		return this.tsTrancompId;
	}
	
	public void setTsTrancompId(Long tsTrancompId) {
		this.tsTrancompId = tsTrancompId;
	}
	
	
	public String getTsParamType() {
		return this.tsParamType;
	}
	
	public void setTsParamType(String tsParamType) {
		this.tsParamType = tsParamType;
	}
	
	public String getTsName() {
		return this.tsName;
	}
	
	public void setTsName(String tsName) {
		this.tsName = tsName;
	}


	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CEMTransactionComponentIdentificationPK)) {
			return false;
		}
		CEMTransactionComponentIdentificationPK castOther = (CEMTransactionComponentIdentificationPK)other;
		return 
			this.tsTrancompId.equals(castOther.tsTrancompId)
			&& this.tsParamType.equals(castOther.tsParamType)
			&& this.tsName.equals(castOther.tsName);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.tsTrancompId.hashCode();
		hash = hash * prime + this.tsParamType.hashCode();
		hash = hash * prime + this.tsName.hashCode();
		
		return hash;
	}
	
	@Override
	public String toString() {
		return "CEMTransactionComponentIdentificationPK [tsTrancompId="
				+ tsTrancompId + ", tsParamType=" + tsParamType + ", tsName="
				+ tsName + "]";
	}

}