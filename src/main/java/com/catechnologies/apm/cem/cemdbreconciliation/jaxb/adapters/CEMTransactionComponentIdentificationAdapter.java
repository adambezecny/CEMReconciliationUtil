package com.catechnologies.apm.cem.cemdbreconciliation.jaxb.adapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.catechnologies.apm.cem.cemdbreconciliation.jpa.entity.CEMTransactionComponentIdentification;

/**
 * XML adapters are used to adjust default JAXB serialization/deserialization process. Typically they are used to set up
 * back pointers of child classes towards their parents during deserialization. In this case they enable to render member variables of 
 * embedded CEMTransactionComponentIdentificationPK instance on same level (as attributes) as members of CEMTransactionComponentIdentification. Otherwise
 * CEMTransactionComponentIdentificationPK would be rendered as embedded element.
 * @author bezad01
 *
 */
public class CEMTransactionComponentIdentificationAdapter extends XmlAdapter<CEMTransactionComponentIdentificationAdapted,CEMTransactionComponentIdentification> {

	
	@Override
	public CEMTransactionComponentIdentification unmarshal(CEMTransactionComponentIdentificationAdapted v) throws Exception {
		return new CEMTransactionComponentIdentification(v);
	}

	@Override
	public CEMTransactionComponentIdentificationAdapted marshal(CEMTransactionComponentIdentification v) throws Exception {
		return new CEMTransactionComponentIdentificationAdapted(v);
	}

}
