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
public class CEMBusinessApplicationCustomizer implements DescriptorCustomizer {

	@Override
	public void customize(ClassDescriptor descriptor) throws Exception {

		
		OneToManyMapping mapping1 = (OneToManyMapping) descriptor.getMappingForAttributeName("tsTranDefGroups");
		OneToManyMapping mapping2 = (OneToManyMapping) descriptor.getMappingForAttributeName("tsTransets");
		OneToManyMapping mapping3 = (OneToManyMapping) descriptor.getMappingForAttributeName("tsLoginIdParameters");
		OneToManyMapping mapping4 = (OneToManyMapping) descriptor.getMappingForAttributeName("tsSessionIdParameters");
		OneToManyMapping mapping5 = (OneToManyMapping) descriptor.getMappingForAttributeName("tsUsergroupIdParameters");
		
	    ExpressionBuilder eb1 = new ExpressionBuilder();
	    ExpressionBuilder eb2 = new ExpressionBuilder();
	    ExpressionBuilder eb3 = new ExpressionBuilder();
	    ExpressionBuilder eb4 = new ExpressionBuilder();
	    ExpressionBuilder eb5 = new ExpressionBuilder();
	    
	    Expression fkExp1 = eb1.getField("ts_tran_def_groups.ts_app_id").equal(eb1.getParameter("ts_apps.ts_id"));
		Expression softDeleteExp1 = eb1.getField("ts_tran_def_groups.ts_soft_delete").equal(false);
	    
		Expression fkExp2 = eb2.getField("ts_transets.ts_app_id").equal(eb2.getParameter("ts_apps.ts_id"));
		Expression softDeleteExp2 = eb2.getField("ts_transets.ts_soft_delete").equal(false);

		Expression fkExp3 = eb3.getField("ts_login_id_parameters.ts_app_id").equal(eb3.getParameter("ts_apps.ts_id"));
		Expression softDeleteExp3 = eb3.getField("ts_login_id_parameters.ts_soft_delete").equal(false);

		Expression fkExp4 = eb4.getField("ts_session_id_parameters.ts_app_id").equal(eb4.getParameter("ts_apps.ts_id"));
		Expression softDeleteExp4 = eb4.getField("ts_session_id_parameters.ts_soft_delete").equal(false);

		Expression fkExp5 = eb5.getField("ts_usergroup_id_parameters.ts_app_id").equal(eb5.getParameter("ts_apps.ts_id"));
		Expression softDeleteExp5 = eb5.getField("ts_usergroup_id_parameters.ts_soft_delete").equal(false);
		
		
		mapping1.setSelectionCriteria(fkExp1.and(softDeleteExp1));
		mapping2.setSelectionCriteria(fkExp2.and(softDeleteExp2));	    
		mapping3.setSelectionCriteria(fkExp3.and(softDeleteExp3));
		mapping4.setSelectionCriteria(fkExp4.and(softDeleteExp4));
		mapping5.setSelectionCriteria(fkExp5.and(softDeleteExp5));
		
	    
	}
	
	
}
