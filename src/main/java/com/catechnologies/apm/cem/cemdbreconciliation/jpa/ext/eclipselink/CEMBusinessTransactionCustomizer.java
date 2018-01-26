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
public class CEMBusinessTransactionCustomizer implements DescriptorCustomizer {

	@Override
	public void customize(ClassDescriptor descriptor) throws Exception {

		
		OneToManyMapping mapping1 = (OneToManyMapping) descriptor.getMappingForAttributeName("tsTranunits");
		OneToManyMapping mapping2 = (OneToManyMapping) descriptor.getMappingForAttributeName("tsDefectDefs");
		OneToManyMapping mapping3 = (OneToManyMapping) descriptor.getMappingForAttributeName("tsTransetgroupTransetsMaps");

	    ExpressionBuilder eb1 = new ExpressionBuilder();
	    ExpressionBuilder eb2 = new ExpressionBuilder();
	    ExpressionBuilder eb3 = new ExpressionBuilder();
	    
		Expression fkExp1 = eb1.getField("ts_tranunits.ts_transet_id").equal(eb1.getParameter("ts_transets.ts_id"));
		Expression softDeleteExp1 = eb1.getField("ts_tranunits.ts_soft_delete").equal(false);
		
		Expression fkExp2 = eb2.getField("ts_defect_defs.ts_transet_id").equal(eb2.getParameter("ts_transets.ts_id"));
		Expression softDeleteExp2 = eb2.getField("ts_defect_defs.ts_soft_delete").equal(false);
		Expression tranUnitIdExp2 = eb2.getField("ts_defect_defs.ts_tranunit_id").isNull();
		Expression tranCompIdExp2 = eb2.getField("ts_defect_defs.ts_trancomp_id").isNull();
		
		Expression fkExp3 = eb3.getField("ts_transetgroup_transets_map.ts_transet_id").equal(eb3.getParameter("ts_transets.ts_id"));
		Expression softDeleteExp3 = eb3.getField("ts_transetgroup_transets_map.ts_soft_delete").equal(false);
		

		mapping1.setSelectionCriteria(fkExp1.and(softDeleteExp1));	    
		mapping2.setSelectionCriteria(fkExp2.and(softDeleteExp2).and(tranUnitIdExp2).and(tranCompIdExp2));
		mapping3.setSelectionCriteria(fkExp3.and(softDeleteExp3));
	    
	}

}
