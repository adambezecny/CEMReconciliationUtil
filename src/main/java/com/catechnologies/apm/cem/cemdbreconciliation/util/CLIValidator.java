package com.catechnologies.apm.cem.cemdbreconciliation.util;

import org.apache.commons.cli.*;

import com.catechnologies.apm.cem.cemdbreconciliation.exceptions.CLIValidationException;

/**
 * class responsible for validating user input. 
 * @author bezad01
 *
 */
public class CLIValidator {

	/**
	 * validates user input when exporting data from source database
	 * @param cmdLine
	 * @throws CLIValidationException
	 */
	public static void validateExport(CommandLine cmdLine) throws CLIValidationException {	
		
		if(!cmdLine.hasOption(CLIFactory.CLI_OPT_ENTITY_TYPE))
			throw new CLIValidationException("Please specify entity type to export using --"+CLIFactory.CLI_OPT_ENTITY_TYPE+" switch.", 
					                         CLIValidationException.INVALID_REASON_NO_ENTITY_TYPE);
		
		
		if(
			!cmdLine.getOptionValue(CLIFactory.CLI_OPT_ENTITY_TYPE).equals(CLIFactory.CLI_OPT_EXPORT_ENTITY_ALLOWED_TYPE_BA) &&
			!cmdLine.getOptionValue(CLIFactory.CLI_OPT_ENTITY_TYPE).equals(CLIFactory.CLI_OPT_EXPORT_ENTITY_ALLOWED_TYPE_BS) &&
			!cmdLine.getOptionValue(CLIFactory.CLI_OPT_ENTITY_TYPE).equals(CLIFactory.CLI_OPT_EXPORT_ENTITY_ALLOWED_TYPE_BT)
		  )
			throw new CLIValidationException("Please specify correct entity type in --"+CLIFactory.CLI_OPT_ENTITY_TYPE+" switch.", 
                    CLIValidationException.INVALID_REASON_WRONG_ENTITY_TYPE);
			
		
		if( !cmdLine.hasOption(CLIFactory.CLI_OPT_BA_NAME) && !cmdLine.hasOption(CLIFactory.CLI_OPT_BS_NAME) &&!cmdLine.hasOption(CLIFactory.CLI_OPT_BT_NAME) )
			throw new CLIValidationException("Please specify either business application (-ban xx), business service (-ban xx -bs yy) or business transaction (-ban xx -bs yy -bt zz) to export.", 
                    CLIValidationException.INVALID_REASON_NO_ENTITY_NAME);

		if(cmdLine.hasOption(CLIFactory.CLI_OPT_BT_NAME) && ( !cmdLine.hasOption(CLIFactory.CLI_OPT_BA_NAME) || !cmdLine.hasOption(CLIFactory.CLI_OPT_BS_NAME)  ))	
			throw new CLIValidationException("When exporting business transaction business application and business service names(-ban, -bsn) must be also specified.", 
                    CLIValidationException.INVALID_REASON_MISSING_BA_OR_BS_NAME);
		
		
		if(cmdLine.hasOption(CLIFactory.CLI_OPT_BS_NAME) && !cmdLine.hasOption(CLIFactory.CLI_OPT_BA_NAME))
			throw new CLIValidationException("When exporting business service business application name(-ban) must be also specified.", 
                    CLIValidationException.INVALID_REASON_MISSING_BANAME);
			
		
		if(!cmdLine.hasOption(CLIFactory.CLI_OPT_EXPORT_FILE))
			throw new CLIValidationException("Please specify export file --"+CLIFactory.CLI_OPT_EXPORT_FILE+" switch.", 
                    CLIValidationException.INVALID_REASON_NO_EXPORT_FILE);
			
	}
	
	/**
	 * validates user input when doing merge (i.e. merging deserialised XML data with either target database or into target XML file when --dry-run option is provided)
	 * @param cmdLine
	 * @throws CLIValidationException
	 */
	public static void validateMerge(CommandLine cmdLine) throws CLIValidationException{
		
		if(!cmdLine.hasOption(CLIFactory.CLI_OPT_EXPORT_FILE))
			throw new CLIValidationException("Please specify export file --"+CLIFactory.CLI_OPT_EXPORT_FILE+" switch.", 
                    CLIValidationException.INVALID_REASON_NO_EXPORT_FILE);

	}
	
	/**
	 * validates user input when doing delta delete (e.g. deleting all business transactions from target DB that are no longer defined in source DB)
	 * @param cmdLine
	 * @throws CLIValidationException
	 */
	public static void validateDelta(CommandLine cmdLine) throws CLIValidationException{
		
		if( !cmdLine.hasOption(CLIFactory.CLI_OPT_BA_NAME) || !cmdLine.hasOption(CLIFactory.CLI_OPT_BS_NAME) )	
			throw new CLIValidationException("When doing delta delete of business transactions both business application and business service names(-ban, -bsn) must be specified.", 
                    CLIValidationException.INVALID_REASON_MISSING_BA_OR_BS_NAME);
		
	}
	
}
