package com.catechnologies.apm.cem.cemdbreconciliation.jaxb.adapters;

import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.catechnologies.apm.cem.cemdbreconciliation.jpa.entity.TsTransetGroup;
import com.catechnologies.apm.cem.cemdbreconciliation.jpa.entity.CEMBusinessTransaction;
import com.catechnologies.apm.cem.cemdbreconciliation.jpa.entity.TsTransetgroupTransetsMap;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

/**
 * Adapted object used by TsTransetgroupTransetsMapAdapter
 * @author bezad01
 *
 */
@XmlRootElement(name="tsTransetgroupTransetsMap")
@XmlAccessorType(XmlAccessType.NONE)
public class TsTransetgroupTransetsMapAdapted {

	@XmlAttribute(name="ts_transet_id")
	private Long tsTransetId;

	@XmlAttribute(name="ts_transet_incarnation_id")
	private Long tsTransetIncarnationId;
	
	@XmlAttribute(name="ts_transet_group_id")
	private Long tsTransetGroupId;

	@XmlAttribute(name="ts_create_date")
	@XmlJavaTypeAdapter(TimestampAdapter.class)
	private Timestamp tsCreateDate;
	
	@XmlAttribute(name="ts_delete_date")
	@XmlJavaTypeAdapter(TimestampAdapter.class)
	private Timestamp tsDeleteDate;
	
	@XmlAttribute(name="ts_soft_delete")
	private Boolean tsSoftDelete;
	
	@XmlAttribute(name="version_info")
	private Long versionInfo;

	@XmlInverseReference(mappedBy="tsTransetgroupTransetsMaps")
	private TsTransetGroup tsTransetGroup;
	
	@XmlInverseReference(mappedBy="tsTransetgroupTransetsMaps")
	private CEMBusinessTransaction tsTranset;

	public Long getTsTransetId() {
		return tsTransetId;
	}

	public void setTsTransetId(Long tsTransetId) {
		this.tsTransetId = tsTransetId;
	}

	public Long getTsTransetIncarnationId() {
		return tsTransetIncarnationId;
	}

	public void setTsTransetIncarnationId(Long tsTransetIncarnationId) {
		this.tsTransetIncarnationId = tsTransetIncarnationId;
	}

	public Long getTsTransetGroupId() {
		return tsTransetGroupId;
	}

	public void setTsTransetGroupId(Long tsTransetGroupId) {
		this.tsTransetGroupId = tsTransetGroupId;
	}

	public Timestamp getTsCreateDate() {
		return tsCreateDate;
	}

	public void setTsCreateDate(Timestamp tsCreateDate) {
		this.tsCreateDate = tsCreateDate;
	}

	public Timestamp getTsDeleteDate() {
		return tsDeleteDate;
	}

	public void setTsDeleteDate(Timestamp tsDeleteDate) {
		this.tsDeleteDate = tsDeleteDate;
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

	public TsTransetGroup getTsTransetGroup() {
		return tsTransetGroup;
	}

	public void setTsTransetGroup(TsTransetGroup tsTransetGroup) {
		this.tsTransetGroup = tsTransetGroup;
	}

	public CEMBusinessTransaction getTsTranset() {
		return tsTranset;
	}

	public void setTsTranset(CEMBusinessTransaction tsTranset) {
		this.tsTranset = tsTranset;
	}

	
	public TsTransetgroupTransetsMapAdapted(){
		//required by JAXB
	}

	public TsTransetgroupTransetsMapAdapted(TsTransetgroupTransetsMap obj){
		
		this.tsTransetId = obj.getId().getTsTransetId();
		this.tsTransetIncarnationId = obj.getId().getTsTransetIncarnationId();
		this.tsTransetGroupId = obj.getId().getTsTransetGroupId();
		this.tsCreateDate = obj.getTsCreateDate();
		this.tsDeleteDate = obj.getTsDeleteDate();
		this.tsSoftDelete = obj.getTsSoftDelete();
		this.versionInfo = obj.getVersionInfo();
		this.tsTransetGroup = obj.getTsTransetGroup();
		this.tsTranset = obj.getTsTranset();
		
	}
	
	
}
