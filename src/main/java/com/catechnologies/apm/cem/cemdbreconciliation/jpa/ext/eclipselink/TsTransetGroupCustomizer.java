package com.catechnologies.apm.cem.cemdbreconciliation.jpa.ext.eclipselink;


import org.eclipse.persistence.config.DescriptorCustomizer;
import org.eclipse.persistence.descriptors.ClassDescriptor;
import org.eclipse.persistence.expressions.*;
import org.eclipse.persistence.mappings.OneToManyMapping;

/**
 * 
 * JPA specification allows entity mapping only via foreign keys. We need to map also using condition '...WHERE TS_SOFT_DELETE=false'
 * since JPA does not address this feature we need to use eclipse link (JPA implementation used by this application) extension mechanism for this. For details see:
 * http://wiki.eclipse.org/EclipseLink/Examples/JPA/MappingSelectionCriteria
 * @author bezad01
 *
 */
public class TsTransetGroupCustomizer implements DescriptorCustomizer {

	@Override
	public void customize(ClassDescriptor descriptor) throws Exception {
		
		OneToManyMapping mapping1 = (OneToManyMapping) descriptor.getMappingForAttributeName("tsTransetgroupTransetsMaps");
		ExpressionBuilder eb1 = new ExpressionBuilder();
		
		Expression fkExp1 = eb1.getField("ts_transetgroup_transets_map.ts_transet_group_id").equal(eb1.getParameter("ts_transet_groups.ts_id"));
		Expression softDeleteExp1 = eb1.getField("ts_transetgroup_transets_map.ts_soft_delete").equal(false);
		
		mapping1.setSelectionCriteria(fkExp1.and(softDeleteExp1));
		
	}

}
