package com.catechnologies.apm.cem.cemdbreconciliation.util;

import com.catechnologies.apm.cem.cemdbreconciliation.jpa.entity.TsTransetGroup;
import com.catechnologies.apm.cem.cemdbreconciliation.jpa.entity.CEMBusinessTransaction;
import com.catechnologies.apm.cem.cemdbreconciliation.jpa.entity.CEMBusinessService;
import com.catechnologies.apm.cem.cemdbreconciliation.jpa.entity.TsTransetgroupTransetsMap;
import com.catechnologies.apm.cem.cemdbreconciliation.jpa.entity.TsTransetgroupTransetsMapPK;

/**
 * Class for helper DB methods.
 * @author bezad01
 *
 */
public class DbUtil {


	/**
	 * 
	 * @param parentCEMBusinessTransaction
	 */
	public static void populateDefaultTsTransetgroupTransetsMap(CEMBusinessTransaction parentCEMBusinessTransaction){
		
		parentCEMBusinessTransaction.getTsTransetgroupTransetsMaps().clear();
		
		TsTransetgroupTransetsMap map = new TsTransetgroupTransetsMap();
		
		map.setTsCreateDate(new java.sql.Timestamp(new java.util.Date().getTime()));
		map.setTsSoftDelete(Boolean.FALSE);
		map.setTsTransetGroup(parentCEMBusinessTransaction.getTsTranDefGroup().getTsTransetGroups().get(0));
		map.setVersionInfo(new Long("0"));
		TsTransetgroupTransetsMapPK pk = new TsTransetgroupTransetsMapPK();
		map.setId(pk);
		
		parentCEMBusinessTransaction.addTsTransetgroupTransetsMap(map);
		parentCEMBusinessTransaction.getTsTranDefGroup().getTsTransetGroups().get(0).addTsTransetgroupTransetsMap(map);
		
		
	}
	
	public static void populateDefaultTsTransetGroup(CEMBusinessService original, CEMBusinessService clone){
		
		clone.getTsTransetGroups().clear();
		
		TsTransetGroup tsTransetGroup = new TsTransetGroup();
		tsTransetGroup.setTsDescription(original.getTsDescription());
		tsTransetGroup.setTsImportanceId(original.getTsImportanceId());
		tsTransetGroup.setTsImportanceInherited(original.getTsImportanceInherited());
		tsTransetGroup.setTsName(original.getTsName());
		tsTransetGroup.setTsSigmaSla(original.getTsSigmaSla());
		tsTransetGroup.setTsSigmaSlaInherited(original.getTsSigmaSlaInherited());
		tsTransetGroup.setTsSoftDelete(original.getTsSoftDelete());
		tsTransetGroup.setTsSuccessRateSla(original.getTsSuccessRateSla());
		tsTransetGroup.setTsSuccessSlaInherited(original.getTsSuccessSlaInherited());
		tsTransetGroup.setTsTranTimeSla(original.getTsTranTimeSla());
		tsTransetGroup.setTsTranTimeSlaInherited(original.getTsTranTimeSlaInherited());
		tsTransetGroup.setVersionInfo(original.getVersionInfo());
		tsTransetGroup.setTsTransetgroupTransetsMaps(new java.util.ArrayList<TsTransetgroupTransetsMap>());
		
		clone.addTsTransetGroup(tsTransetGroup);
		
		
	}
	
}
