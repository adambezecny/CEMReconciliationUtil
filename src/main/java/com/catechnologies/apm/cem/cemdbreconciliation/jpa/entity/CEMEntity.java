package com.catechnologies.apm.cem.cemdbreconciliation.jpa.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * This is common class extended by all JPA entities. It defines common abstract and concrete methods.
 * @author bezad01
 *
 */
//we need to use this annotation otherwise tsSoftDelete method would be mapped from parent class (in parallel to mapped attribute of particular entity), 
//obviously some strange stuff within JAXB going on...
@XmlAccessorType(XmlAccessType.NONE)
public abstract class CEMEntity {

	/**
	 * This method is used to copy/merge two JPA entities of same type.
	 * we are not merging primary keys (since they might be different in source & target DB)
	 * also no need to merge attributes used in equal/hashCode or respective entities
	 * since merge is invoked only when equals is true (i.e. those attributes are identical, no need to merge)
	 * also foreign keys are not merged since they might differ between source & target
	 * @param target
	 * @throws Exception
	 */
	abstract public void copyTo(CEMEntity target) throws Exception;
	

	/**
	 * This method is used to attach cloned entity from source JPA entity manager to respective parent 
	 * object in object tree of target JPA entity manager. This is used when respective source object is not 
	 * present in target tree (e.g. source BS Service1 has business transaction BT1 and target service Service1 does not.
	 * In this case source BT1 is cloned and attached via call of this method to target Service1).
	 *  	
	 * @param targetParent
	 */
	abstract public void addToTargetParent(CEMEntity targetParent);

	/**
	 * This setter must be defined as abstract method since it is used in CEMEntityMergeHelper which is using
	 * OOP polymorphism, i.e. it is executing against CEMEntity rather than against concrete child classes.
	 * @param tsSoftDelete
	 */
	abstract public void setTsSoftDelete(Boolean tsSoftDelete);
	
	/**
	 * This getter must be defined as abstract method since it is used in CEMEntityMergeHelper which is using
	 * OOP polymorphism, i.e. it is executing against CEMEntity rather than against concrete child classes.
	 * @return
	 */
	abstract public Boolean getTsSoftDelete();
	
	/**
	 * Returns fully qualified name of the entity. This name should be based on ts_name attribute mostly. It should contain also names
	 * of predecessors. E.g. FQN of business service is FQN of business application (=ts_name of business app) + ts_name of business service.
	 * @return
	 */
	abstract public String getFQN();
	
	/**
	 * Common hashCode implementation based on fully qualified name of JPA entity
	 */
	@Override
	public int hashCode() {

		String tsName=getFQN();
		
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tsName == null) ? 0 : tsName.hashCode());
		return result;
	}

	
	/**
	 * Common equals implementation based on fully qualified name of JPA entity
	 */
	@Override
	public boolean equals(Object obj) {
		
		String tsName=getFQN();
		
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CEMEntity other = (CEMEntity) obj;

		if (!tsName.equals(other.getFQN()))
			return false;
		return true;
	}
	
	/**
	 * This method provides deep cloning capabilities via serialization and subsequent deserialization
	 * Child entities should call this common implementation (via super.deep()) and add any specific
	 * functionality in overridden method.
	 * @return
	 * @throws Exception
	 */
    public CEMEntity deepCopy() throws Exception
    {
        //Serialization of object
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bos);
        out.writeObject(this);
 
        //De-serialization of object
        ByteArrayInputStream bis = new   ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream in = new ObjectInputStream(bis);
        CEMEntity copied = (CEMEntity) in.readObject();
 
        return copied;
    }	
	
	
}
