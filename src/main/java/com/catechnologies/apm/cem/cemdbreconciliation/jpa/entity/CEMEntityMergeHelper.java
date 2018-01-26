package com.catechnologies.apm.cem.cemdbreconciliation.jpa.entity;

import java.util.List;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class holds common merging logic. 
 * 
 * @author bezad01
 *
 * @param <T0> - represents type of parent object that is holding list of objects to be merged (objects to be merged are of type T1)
 * @param <T1> - represents list of objects (that belong to parent object of type T0) that should be merged
 */
public class CEMEntityMergeHelper<T0 extends CEMEntity, T1 extends CEMEntity> {

	
	static final Logger log = LogManager.getLogger(CEMEntityMergeHelper.class.getName());
	
	/**
	 * Compares lists of source and target business transactions and returns list of target transactions that are not defined in source list.
	 * @param sourceBTList
	 * @param targetBTList
	 * @return
	 */
	public static List<CEMBusinessTransaction> btDeltaComparison(List<CEMBusinessTransaction> sourceBTList, List<CEMBusinessTransaction> targetBTList) {
		
		List<CEMBusinessTransaction> btToDelete =  new ArrayList<CEMBusinessTransaction>();
		
		for(int i=0;i<targetBTList.size();i++){
			
			CEMBusinessTransaction iteratedItem = targetBTList.get(i);
			if(!sourceBTList.contains(iteratedItem)) btToDelete.add(iteratedItem);
			
		}
		
		return btToDelete;
		
	}
	
	/**
	 * This method merges two lists according to following rules:
	 * 
	 * Items contained in target list and not contained in source list are deleted from target list
	 * Items contained in source list and not contained in target list are inserted into target list
	 * Items contained in both lists are merged (i.e. their attributes are copied from source to target). If they contain
	 * child list of other elements (derived from CEMEntity class) they are merged using same algorithm
	 * 
	 * @param targetParent - parent of target list, e.g. target business transaction
	 * @param source - source list, e.g. list of source transactions
	 * @param target - target list, e.g. list of target transactions
	 * @throws Exception 
	 */
	public void mergeChildEntityLists(T0 targetParent, List<T1> source, List<T1> target) throws Exception{
		
		log.entry(source, target);

		
		List<T1> cemTargetEntitiesToDelete = new ArrayList<T1>(); 
		
		if(target != null){
			
			for(int i=0;i<target.size();i++){
				T1 iteratedItem = target.get(i);
				
				//if source list does not include iterated target item we need to delete this item from target
				//here we will just mark it for deletion and then delete in next cycle
				if(!source.contains(iteratedItem)){
					cemTargetEntitiesToDelete.add(iteratedItem);
					
					log.trace("Item added into cemTargetEntitiesToDelete "+ iteratedItem);
					
				}	

			}

			//delete all target component identifications marked for deletion
			for(int i=0;i<cemTargetEntitiesToDelete.size();i++){
				
				if(cemTargetEntitiesToDelete.get(i) instanceof CEMTransactionComponentIdentification){
					log.trace("removing CEMTransactionComponentIdentification "+cemTargetEntitiesToDelete.get(i));
					target.remove(cemTargetEntitiesToDelete.get(i));// will be physically deleted from DB when annotated by @org.eclipse.persistence.annotations.PrivateOwned
				}else{
					log.trace("performing soft delete");
					cemTargetEntitiesToDelete.get(i).setTsSoftDelete(Boolean.TRUE);	
				}
				
				
				
				
			}
			
			log.trace("Entities to delete were marked to soft delete.");
			
		}
		
		if(source != null) {

			//insert items that do not exist in target and exist in source
			//update items that exist in both source and target but differ
			for(int i=0;i<source.size();i++){
				T1 iteratedItem = source.get(i);
				
				log.trace("Iterated source item " + iteratedItem);
				
				if(target.contains(iteratedItem))/*if source component identification item exists in target as well, let's merge them*/{
					T1 targetItem = target.get(target.indexOf(iteratedItem));
					
					if(targetItem.getTsSoftDelete()!=null)/*technical condition only so that unit test does not have to set this attribute everywhere, in real database value should be always filled in*/
						if(targetItem.getTsSoftDelete().booleanValue() == true) continue;//we do not work with entities deleted in previous step!!!
					
					iteratedItem.copyTo(targetItem);
					log.trace("Iterated source item merged to target item", targetItem);
				}else/*if source component identification item DOES NOT exists in target we must create it here*/{
					
					@SuppressWarnings("unchecked")
					T1 clonedItem = (T1)iteratedItem.deepCopy();
					
					if(targetParent!=null)
						clonedItem.addToTargetParent(targetParent);
					
					log.trace("Iterated source item added to target items");
				}
					
			}		
			
		}
		
		
		log.exit(target);
	}

}
