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
public class CEMBusinessServiceCustomizer implements DescriptorCustomizer {

	@Override
	public void customize(ClassDescriptor descriptor) throws Exception {

		
		OneToManyMapping mapping1 = (OneToManyMapping) descriptor.getMappingForAttributeName("tsTransets");
		OneToManyMapping mapping2 = (OneToManyMapping) descriptor.getMappingForAttributeName("tsTransetGroups");

	    ExpressionBuilder eb1 = new ExpressionBuilder();
	    ExpressionBuilder eb2 = new ExpressionBuilder();
	    
		Expression fkExp1 = eb1.getField("ts_transets.ts_trandef_group_id").equal(eb1.getParameter("ts_tran_def_groups.ts_id"));
		Expression softDeleteExp1 = eb1.getField("ts_transets.ts_soft_delete").equal(false);
		
		Expression fkExp2 = eb2.getField("ts_transet_groups.ts_trandef_group_id").equal(eb2.getParameter("ts_tran_def_groups.ts_id"));
		Expression softDeleteExp2 = eb2.getField("ts_transet_groups.ts_soft_delete").equal(false);
		Expression parentIdExp2 = eb2.getField("ts_transet_groups.ts_parent_id").isNull();
		

		mapping1.setSelectionCriteria(fkExp1.and(softDeleteExp1));	    
		mapping2.setSelectionCriteria(fkExp2.and(softDeleteExp2).and(parentIdExp2));

	}
	
}
