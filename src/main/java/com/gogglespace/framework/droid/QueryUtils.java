package com.gogglespace.framework.droid;

/**
 * Utility operations for query
 * @author Shahid Nawaz on 09/19/2017
 * @version 1.0
 * */
class QueryUtils {
	
	static final String ID_FIELD_WHERE_CLAUSE = " = ?";
	
	private QueryUtils () {
		// instance creation restriction
	}
	
	static String[] getSelectionArg(long id) {
		return new String[]{String.valueOf(id)};
	}
	
	static String getWhereClauseIdField(String idField) {
		return idField + ID_FIELD_WHERE_CLAUSE;
	}
}
