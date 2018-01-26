package com.catechnologies.apm.cem.cemdbreconciliation;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import java.util.List;
import java.util.Scanner;
import com.catechnologies.apm.cem.cemdbreconciliation.util.*;
import com.catechnologies.apm.cem.cemdbreconciliation.exceptions.CLIValidationException;
import com.catechnologies.apm.cem.cemdbreconciliation.jaxb.adapters.CEMBusinessApplicationAdapter;
import com.catechnologies.apm.cem.cemdbreconciliation.jaxb.adapters.CEMBusinessServiceAdapter;
import com.catechnologies.apm.cem.cemdbreconciliation.jaxb.adapters.CEMBusinessTransactionAdapter;
import com.catechnologies.apm.cem.cemdbreconciliation.jpa.entity.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.persistence.EntityManager;

/**
 * Startup class of the whole application. It processes user input (from command line)
 * and initiates respective action. Four use cases are provided
 * Help   - displays help
 * Export - enables to export data from source database
 * Merge  - enables to import data exported in previous step into target database. Also "dry run" is supported 
 *          when source and target data will be merged into XML file instead of target database
 * Delta  - displays entities that not defined any longer in target database as opposed to source database  
 * @author bezad01
 *
 */
public class App {

    static final Logger log = LogManager.getLogger(App.class.getName());
    
	public static void main(String[] args) {
		
		CommandLineParser parser = new DefaultParser();
		CommandLine cmdLine = null;
		
		log.info("Processing command line input...");
		
		
		try {
			cmdLine = parser.parse(CLIFactory.getCLIDefinition(), args);
		} catch (ParseException e) {
			log.error("Error when parsing command line input "+e.getMessage());
			CLIFactory.printHelp();
			System.exit(0);
		}
		
		
		if(cmdLine.hasOption(CLIFactory.CLI_OPT_HELP)){
			processHelp();
		}else if(cmdLine.hasOption(CLIFactory.CLI_OPT_EXPORT)){
			processExport(cmdLine);
		}else if(cmdLine.hasOption(CLIFactory.CLI_OPT_MERGE)){
			processMerge(cmdLine);
		}else if(cmdLine.hasOption(CLIFactory.CLI_OPT_DELTA)){
			processDelta(cmdLine);
		}else{
			log.warn("Unknown command provided");
			CLIFactory.printHelp();
		}
		
	}
	
	/**
	 * Prints out help of the application.
	 */
	private static void processHelp(){
		CLIFactory.printHelp();
	}

	/**
	 * Core method providing export functionality.
	 * @param cmdLine 
	 */
	private static void processExport(CommandLine cmdLine){
		
		try {
			CLIValidator.validateExport(cmdLine);
		} catch (CLIValidationException e) {
			log.error(e.getMessage());
			CLIFactory.printHelp();
			System.exit(0);
		}
		
		log.info("Initiating export...");
		log.info("Export entity type: "+cmdLine.getOptionValue(CLIFactory.CLI_OPT_ENTITY_TYPE));
		
		if(cmdLine.getOptionValue(CLIFactory.CLI_OPT_ENTITY_TYPE).equals(CLIFactory.CLI_OPT_EXPORT_ENTITY_ALLOWED_TYPE_BA))
			log.info("Business application name: "+cmdLine.getOptionValue(CLIFactory.CLI_OPT_BA_NAME));
		
		if(cmdLine.getOptionValue(CLIFactory.CLI_OPT_ENTITY_TYPE).equals(CLIFactory.CLI_OPT_EXPORT_ENTITY_ALLOWED_TYPE_BS)){
			log.info("Business application name: "+cmdLine.getOptionValue(CLIFactory.CLI_OPT_BA_NAME));
			log.info("Business service name: "+cmdLine.getOptionValue(CLIFactory.CLI_OPT_BS_NAME));
		}

		if(cmdLine.getOptionValue(CLIFactory.CLI_OPT_ENTITY_TYPE).equals(CLIFactory.CLI_OPT_EXPORT_ENTITY_ALLOWED_TYPE_BT)){
			log.info("Business application name: "+cmdLine.getOptionValue(CLIFactory.CLI_OPT_BA_NAME));
			log.info("Business service name: "+cmdLine.getOptionValue(CLIFactory.CLI_OPT_BS_NAME));
			log.info("Business transaction name: "+cmdLine.getOptionValue(CLIFactory.CLI_OPT_BT_NAME));
		}
		
		
		log.info("Export file: "+cmdLine.getOptionValue(CLIFactory.CLI_OPT_EXPORT_FILE));

		AppDAO appDAO = null;
		
		if(cmdLine.hasOption(CLIFactory.CLI_OPT_EXPORT_INVERSE)){
			log.warn("Exporting from target DB! XML file will be postfixed with \".target.xml\" literal.");
			appDAO = new AppDAO(JPAFactory.getInstance().getTargetEntityManager());
		}else{
			appDAO = new AppDAO(JPAFactory.getInstance().getSourceEntityManager());
		}	
			
		
		CEMBusinessApplication  cemBusinessApplication = null;
		CEMBusinessService 		cemBusinessService	   = null;
		CEMBusinessTransaction  cemBusinessTransaction = null;
		
		String xml = null;
		
		if(cmdLine.getOptionValue(CLIFactory.CLI_OPT_ENTITY_TYPE).equals(CLIFactory.CLI_OPT_EXPORT_ENTITY_ALLOWED_TYPE_BA)){

			cemBusinessApplication = appDAO.findCEMBusinessApplicationByName(cmdLine.getOptionValue(CLIFactory.CLI_OPT_BA_NAME));
			
			if(cemBusinessApplication == null){
				log.error("Business application with given name was not found!");
				System.exit(0);
			}
			
			xml = XMLSerializer.objectToXML(cemBusinessApplication, CEMBusinessApplication.class);
			
		}else if(cmdLine.getOptionValue(CLIFactory.CLI_OPT_ENTITY_TYPE).equals(CLIFactory.CLI_OPT_EXPORT_ENTITY_ALLOWED_TYPE_BS)){
			
			cemBusinessService = appDAO.findCEMBusinessServiceByName(cmdLine.getOptionValue(CLIFactory.CLI_OPT_BA_NAME), cmdLine.getOptionValue(CLIFactory.CLI_OPT_BS_NAME));
			
			if(cemBusinessService == null){
				log.error("Business service with given name was not found!");
				System.exit(0);
			}
			
			xml = XMLSerializer.objectToXML(cemBusinessService, CEMBusinessService.class);
			
		}else/*business transaction*/{
			
			cemBusinessTransaction = appDAO.findCEMBusinessTransactionByName(cmdLine.getOptionValue(CLIFactory.CLI_OPT_BA_NAME), cmdLine.getOptionValue(CLIFactory.CLI_OPT_BS_NAME), cmdLine.getOptionValue(CLIFactory.CLI_OPT_BT_NAME));
			
			if(cemBusinessTransaction == null){
				log.error("Business transaction with given name was not found!");
				System.exit(0);
			}
			
			xml = XMLSerializer.objectToXML(cemBusinessTransaction, CEMBusinessTransaction.class);

		}
		
		
		try{
			String fileName = (cmdLine.hasOption(CLIFactory.CLI_OPT_EXPORT_INVERSE) ? cmdLine.getOptionValue(CLIFactory.CLI_OPT_EXPORT_FILE)+".target.xml" : cmdLine.getOptionValue(CLIFactory.CLI_OPT_EXPORT_FILE)  );
			PrintWriter pw = new PrintWriter(fileName);
			pw.print(xml);
			pw.close();
		} catch (Exception e) {	
			log.error("Error when creating XML file " + e.getMessage());
			e.printStackTrace();
		}
		
		log.info("XML file successfully exported!");
	}

	/**
	 * Core method providing merging functionality.
	 * @param cmdLine
	 */
	private static void processMerge(CommandLine cmdLine){
		
		CEMBusinessApplication cemBusinessApplicationSource = null;
		CEMBusinessService cemBusinessServiceSource = null; 
		CEMBusinessTransaction cemBusinessTransactionSource = null;
		
		CEMBusinessApplication cemBusinessApplicationTarget = null;
		CEMBusinessService cemBusinessServiceTarget = null; 
		CEMBusinessTransaction cemBusinessTransactionTarget = null;
		
		EntityManager emS = null;
		EntityManager emT = null;
		AppDAO appDAOSource = null;
		AppDAO appDAOTarget = null;
		
		
		try {
			CLIValidator.validateMerge(cmdLine);
		} catch (CLIValidationException e) {
			log.error(e.getMessage());
			CLIFactory.printHelp();
			System.exit(0);
		}
		
		log.info("Initiating merge...");
	
		String exportFile = cmdLine.getOptionValue(CLIFactory.CLI_OPT_EXPORT_FILE);
		boolean dryRun = cmdLine.hasOption(CLIFactory.CLI_OPT_IMPORT_DRY_RUN);
		String dryRunFile = null;
		if(dryRun) dryRunFile=cmdLine.getOptionValue(CLIFactory.CLI_OPT_IMPORT_DRY_RUN);
		
		String xml = null;
		String outputXML = null;
		
		try {
			xml = ResourceUtil.readFile(exportFile);
		} catch (IOException e) {
			log.error("Error when reading specified export file "+exportFile+" "+e.getMessage());
			e.printStackTrace();
			System.exit(0);
		}

		try{
			
			emS = JPAFactory.getInstance().getSourceEntityManager();
			emT = JPAFactory.getInstance().getTargetEntityManager();
			appDAOSource = new AppDAO(emS);
			appDAOTarget = new AppDAO(emT);
			
		
			String rootElementName = XMLSerializer.extractValue(xml, "name(/*)");
			
			if(!dryRun) emT.getTransaction().begin();

			
				if(rootElementName.equals("businessApplication")){
					 log.info("businessApplication detected in XML file.");
					 cemBusinessApplicationSource = (CEMBusinessApplication)XMLSerializer.xmlToObject(xml, CEMBusinessApplication.class);
					 CEMBusinessApplicationAdapter adapter = new CEMBusinessApplicationAdapter();
					 adapter.unmarshal(cemBusinessApplicationSource);
					 log.info("XML deserialization successfull.");
					
					 log.info("Retrieving target business application from database...");
					 cemBusinessApplicationTarget = appDAOTarget.findCEMBusinessApplicationByName(cemBusinessApplicationSource.getTsName());
					
					 if(cemBusinessApplicationTarget!=null){
						 log.info("Target business application retrieved from database.");
					 }else{
						 log.info("Unable to retrieve target business application from database. Exiting.");
						 JPAFactory.getInstance().closeEMF();
						 System.exit(0);
					 }
					 
					 
					 log.info("Merging source & target business application...");
					 cemBusinessApplicationSource.copyTo(cemBusinessApplicationTarget);
					 log.info("Merging source & target business application finished.");
					 
					 if(dryRun){
						 log.info("Storing merged business application to xml file...");
						 emT.getTransaction().begin();
						 emT.flush();//flush in order to see all generated sequence IDs etc correctly propagated into XML
						 outputXML = XMLSerializer.objectToXML(cemBusinessApplicationTarget, CEMBusinessApplication.class);
						 emT.getTransaction().rollback();//this is dry run only we always need to rollback
					 }else{
						 log.info("Storing merged business application to target database...");
						 emT.persist(cemBusinessApplicationTarget);
						 log.info("Merged business application stored to target database.");
					 }
					 
				}else if(rootElementName.equals("businessService")){
					 log.info("businessService detected in XML file.");
					 cemBusinessServiceSource = (CEMBusinessService)XMLSerializer.xmlToObject(xml, CEMBusinessService.class);
					 CEMBusinessServiceAdapter adapter = new CEMBusinessServiceAdapter();
					 adapter.unmarshal(cemBusinessServiceSource);//explicit call of unmarshalling adapter in order to set back pointers of child objects properly
					 log.info("XML deserialization successfull.");
					 
					 CEMBusinessService tmp = appDAOSource.findCEMBusinessServiceById(cemBusinessServiceSource);//temporary object used only to retrieve names of above lying business application which was not deserialized from XML
					 tmp.cutOffChildNodes();
					 tmp.getTsApp().cutOffChildNodes();
					 cemBusinessServiceSource.setTsApp(tmp.getTsApp());
					 
					
					 log.info("Retrieving target business service from database...");
					 cemBusinessServiceTarget = appDAOTarget.findCEMBusinessServiceByName(tmp.getTsApp().getTsName(), cemBusinessServiceSource.getTsName());
					 
					 if(cemBusinessServiceTarget!=null){
						 log.info("Target business service retrieved from database.");
					 }else{
						 log.info("Unable to retrieve target business service from database. Exiting.");
						 JPAFactory.getInstance().closeEMF();
						 System.exit(0);
					 }
					 
					 
					 
					 log.info("Merging source & target business service...");
					 cemBusinessServiceSource.copyTo(cemBusinessServiceTarget);
					 log.info("Merging source & target business service finished.");

					 if(dryRun){
						 log.info("Storing merged business service to xml file...");
						 emT.getTransaction().begin();
						 emT.flush();//flush in order to see all generated sequence IDs etc correctly propagated into XML
						 outputXML = XMLSerializer.objectToXML(cemBusinessServiceTarget, CEMBusinessService.class);
						 emT.getTransaction().rollback();//this is dry run only we always need to rollback
					 }else{
						 log.info("Storing merged business service to target database...");
						 emT.persist(cemBusinessServiceTarget);
						 log.info("Merged business service stored to target database.");
					 }
					
				}else if(rootElementName.equals("businessTransaction")){
					 
					 log.info("businessTransaction detected in XML file.");
					 cemBusinessTransactionSource = (CEMBusinessTransaction)XMLSerializer.xmlToObject(xml, CEMBusinessTransaction.class);
					 CEMBusinessTransactionAdapter adapter = new CEMBusinessTransactionAdapter();
					 adapter.unmarshal(cemBusinessTransactionSource);//explicit call of unmarshalling adapter in order to set back pointers of child objects properly
					 log.info("XML deserialization successfull.");
					 
					 CEMBusinessTransaction tmp = appDAOSource.findCEMBusinessTransactionById(cemBusinessTransactionSource);//temporary object used only to retrieve names of above lying business application and service which was not deserialized from XML
					 tmp.cutOffChildNodes();
					 tmp.getTsTranDefGroup().cutOffChildNodes();
					 tmp.getTsTranDefGroup().getTsApp().cutOffChildNodes();
					 cemBusinessTransactionSource.setTsTranDefGroup(tmp.getTsTranDefGroup());
					 cemBusinessTransactionSource.setTsApp(tmp.getTsApp());
					 
					 log.info("Retrieving target business transaction from database...");
					 cemBusinessTransactionTarget = appDAOTarget.findCEMBusinessTransactionByName(cemBusinessTransactionSource.getTsApp().getTsName(), cemBusinessTransactionSource.getTsTranDefGroup().getTsName(), cemBusinessTransactionSource.getTsName());
					 
					 if(cemBusinessTransactionTarget!=null){
						 log.info("Target business transaction retrieved from database.");
					 }else{
						 log.info("Unable to retrieve target business transaction from database. Exiting.");
						 JPAFactory.getInstance().closeEMF();
						 System.exit(0);
					 }
					 
					 
					 
					 log.info("Merging source & target business transaction...");
					 cemBusinessTransactionSource.copyTo(cemBusinessTransactionTarget);
					 log.info("Merging source & target business transaction finished.");
					 
					 if(dryRun){
						 log.info("Storing merged business transaction to xml file...");
						 emT.getTransaction().begin();
						 emT.flush();//flush in order to see all generated sequence IDs etc correctly propagated into XML
						 outputXML = XMLSerializer.objectToXML(cemBusinessTransactionTarget, CEMBusinessTransaction.class);
						 emT.getTransaction().rollback();//this is dry run only we always need to rollback
					 }else{
						 log.info("Storing merged business transaction to target database...");
						 emT.persist(cemBusinessTransactionTarget);
						 log.info("Merged business transaction stored to target database.");
					 }

				}else{
					log.error("unknown top element in provided XML file. Allowed values are businessApplication, businessService or businessTransaction.");
					JPAFactory.getInstance().closeEMF();
					System.exit(0);
				}
		
		
				if(dryRun){
					try {
						PrintWriter pw = new PrintWriter(dryRunFile);
						pw.print(outputXML);
						pw.close();
						log.info("XML file stored.");
					} catch (Exception e) {	
						log.error("Error when creating output XML file " + e.getMessage());
						e.printStackTrace();
						System.exit(0);
					}
					
				}else{
					emT.getTransaction().commit();
					log.info("DB changes committed!");
				}

				log.info("Performing TIM synchronization...");
				TIMSyncUtil.synchronizeMonitors();
				log.info("TIM synchronization completed!");
				
				
		}catch(Exception e){
			log.error("Error during processing "+e.toString());
			e.printStackTrace();	
			if(!dryRun){
				emT.getTransaction().rollback();
				log.error("DB transaction on target DB was rolled back");
				
			}

		}finally{
			JPAFactory.getInstance().closeEMF();
			System.exit(0);
		}	
		
	}
	
	/**
	 * Core method providing delta functionality.
	 * @param cmdLine
	 */
	private static void processDelta(CommandLine cmdLine){
		
		try {
			CLIValidator.validateDelta(cmdLine);
		} catch (CLIValidationException e) {
			log.error(e.getMessage());
			CLIFactory.printHelp();
			System.exit(0);
		}
		
		log.info("Initiating business transaction delta delete...");
		
		
		EntityManager emS = JPAFactory.getInstance().getSourceEntityManager();
		EntityManager emT = JPAFactory.getInstance().getTargetEntityManager();
		
		AppDAO appDAOSource = new AppDAO(emS);
		AppDAO appDAOTarget = new AppDAO(emT);
		
		CEMBusinessService cemBusinessServiceSource = null;
		CEMBusinessService cemBusinessServiceTarget = null;
		
		cemBusinessServiceSource = appDAOSource.findCEMBusinessServiceByName(cmdLine.getOptionValue(CLIFactory.CLI_OPT_BA_NAME), cmdLine.getOptionValue(CLIFactory.CLI_OPT_BS_NAME));
		cemBusinessServiceTarget = appDAOTarget.findCEMBusinessServiceByName(cmdLine.getOptionValue(CLIFactory.CLI_OPT_BA_NAME), cmdLine.getOptionValue(CLIFactory.CLI_OPT_BS_NAME));
		
		List<CEMBusinessTransaction> btDelta = CEMEntityMergeHelper.btDeltaComparison(cemBusinessServiceSource.getTsTransets(), cemBusinessServiceTarget.getTsTransets());
		
		log.info("Business application name: "+cmdLine.getOptionValue(CLIFactory.CLI_OPT_BA_NAME));
		log.info("Business service name: "+cmdLine.getOptionValue(CLIFactory.CLI_OPT_BS_NAME));
		
		if(btDelta.size()==0){
			log.info("There are no differences in business transactions for given business application and business service.");
			JPAFactory.getInstance().closeEMF();
			System.exit(0);
		}else{
			log.info("Following business transactions defined in target DB for above defined business application and service are not defined in source DB any more:");	
		}		
		
		
		for(int i=0;i < btDelta.size();i++){
			log.info(btDelta.get(i).getTsName());
		}
		
		log.info("Do you want to delete these business transactions from target database[yes/no]? Default: no");
		Scanner scanner = new Scanner(System.in);
		String userReply = scanner.nextLine();
		
		if(userReply.equalsIgnoreCase("yes")){
			
			try{
				log.info("deleting...");
				
				emT.getTransaction().begin();
				
				for(int i=0;i < btDelta.size();i++){
					btDelta.get(i).setTsSoftDelete(Boolean.TRUE);
					emT.persist(btDelta.get(i));
				}	
				
				emT.getTransaction().commit();
				
				log.info("DB changes committed!");
				
				log.info("Performing TIM synchronization...");
				TIMSyncUtil.synchronizeMonitors();
				log.info("TIM synchronization completed!");
				
				
			}catch(Exception e){
				log.error("Error during business transaction deletion. Rollbacking DB transaction "+e.toString());
				emT.getTransaction().rollback();
				JPAFactory.getInstance().closeEMF();
				log.info("DB transaction rolled back!");
			}
			
			
			
		}else{
			log.info("Processing terminated, no business transactions deleted.");
			
		}
		
		scanner.close();
		JPAFactory.getInstance().closeEMF();
		System.exit(0);
		
		
	}
	
	
}
