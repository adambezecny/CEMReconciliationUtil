package com.catechnologies.apm.cem.cemdbreconciliation.jpa.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the ts_usergroup_id_parameters database table.
 * 
 */
@Embeddable
public class CEMBusinessApplicationUserGroupIdentificationPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ts_app_id", insertable=false, updatable=false)
	private Long tsAppId;

	@Column(name="ts_type")
	private String tsType;

	@Column(name="ts_name")
	private String tsName;

	public CEMBusinessApplicationUserGroupIdentificationPK() {
	}
	public Long getTsAppId() {
		return this.tsAppId;
	}
	public void setTsAppId(Long tsAppId) {
		this.tsAppId = tsAppId;
	}
	public String getTsType() {
		return this.tsType;
	}
	public void setTsType(String tsType) {
		this.tsType = tsType;
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
		if (!(other instanceof CEMBusinessApplicationUserGroupIdentificationPK)) {
			return false;
		}
		CEMBusinessApplicationUserGroupIdentificationPK castOther = (CEMBusinessApplicationUserGroupIdentificationPK)other;
		return 
			this.tsAppId.equals(castOther.tsAppId)
			&& this.tsType.equals(castOther.tsType)
			&& this.tsName.equals(castOther.tsName);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.tsAppId.hashCode();
		hash = hash * prime + this.tsType.hashCode();
		hash = hash * prime + this.tsName.hashCode();
		
		return hash;
	}
	
	@Override
	public String toString() {
		return "CEMBusinessApplicationUserGroupIdentificationPK [tsAppId="
				+ tsAppId + ", tsType=" + tsType + ", tsName=" + tsName + "]";
	}
	
}