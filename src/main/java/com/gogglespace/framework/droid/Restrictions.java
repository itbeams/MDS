package com.gogglespace.framework.droid;

import com.gogglespace.framework.droid.Restriction.LogicalOperator;

/**
 * Droid Persistence Framework
 * - restrictions expression
 * Created by Shahid Nawaz on 4/20/2015
 * @version 1.2
 * Added Logical operators support
 */
public class Restrictions {

	private Restrictions(){
		// Restrict outside instance creation
	}
	
	//------------------------------------- EQUAL FILTER ----------------------
	/**
	 * Equal filter restriction
	 * @param fieldName - field name restriction/filter to be applied on
	 * @param value - filter value
	 * @return Restriction
	 * @see Restriction
	 * */
    public static Restriction eq(String fieldName, long value) {
        return eq(fieldName, Long.toString(value));
    }
    
	/**
	 * Equal filter restriction
	 * @param fieldName - field name restriction/filter to be applied on
	 * @param value - filter value
	 * @param logicalOperator - logic operator @see Restriction.LogicalOperator
	 * @return Restriction
	 * @see Restriction
	 * */
    public static Restriction eq(String fieldName, long value, LogicalOperator logicOperator) {
        return eq(fieldName, Long.toString(value), logicOperator);
    }
    
	/**
	 * Equal filter restriction
	 * @param fieldName - field name restriction/filter to be applied on
	 * @param value - filter value
	 * @return Restriction
	 * @see Restriction
	 * */
    public static Restriction eq(String fieldName, boolean value) {
        return getRestriction(fieldName, Restriction.Operator.EQ, Boolean.toString(value));
    }
    
	/**
	 * Equal filter restriction
	 * @param fieldName - field name restriction/filter to be applied on
	 * @param value - filter value
	 * @param logicalOperator - logic operator @see Restriction.LogicalOperator
	 * @return Restriction
	 * @see Restriction
	 * */
    public static Restriction eq(String fieldName, boolean value, LogicalOperator logicOperator) {
        return getRestriction(fieldName, Restriction.Operator.EQ, Boolean.toString(value), logicOperator);
    }
    
	/**
	 * Equal filter restriction
	 * @param fieldName - field name restriction/filter to be applied on
	 * @param value - filter value
	 * @return Restriction
	 * @see Restriction
	 * */
    public static Restriction eq(String fieldName, String value) {
        return getRestriction(fieldName, Restriction.Operator.EQ, value);
    }
    
	/**
	 * Equal filter restriction
	 * @param fieldName - field name restriction/filter to be applied on
	 * @param value - filter value
	 * @param logicalOperator - logic operator @see Restriction.LogicalOperator
	 * @return Restriction
	 * @see Restriction
	 * */
    public static Restriction eq(String fieldName, String value, LogicalOperator logicOperator) {
        return getRestriction(fieldName, Restriction.Operator.EQ, value, logicOperator);
    }
    
    //------------------------------------- NOT EQUAL FILTER ------------------
	/**
	 * Not equal filter restriction
	 * @param fieldName - field name restriction/filter to be applied on
	 * @param value - filter value
	 * @return Restriction
	 * @see Restriction
	 * */
    public static Restriction ne(String fieldName, long value) {
        return ne(fieldName, Long.toString(value));
    }
    
	/**
	 * Not equal filter restriction
	 * @param fieldName - field name restriction/filter to be applied on
	 * @param value - filter value
	 * @param logicalOperator - logic operator @see Restriction.LogicalOperator
	 * @return Restriction
	 * @see Restriction
	 * */
    public static Restriction ne(String fieldName, long value, LogicalOperator logicOperator) {
        return ne(fieldName, Long.toString(value), logicOperator);
    }
    
	/**
	 * Not equal filter restriction
	 * @param fieldName - field name restriction/filter to be applied on
	 * @param value - filter value
	 * @return Restriction
	 * @see Restriction
	 * */
    public static Restriction ne(String fieldName, String value) {
        return getRestriction(fieldName, Restriction.Operator.NE, value);
    }
    
	/**
	 * Not equal filter restriction
	 * @param fieldName - field name restriction/filter to be applied on
	 * @param value - filter value
	 * @param logicalOperator - logic operator @see Restriction.LogicalOperator
	 * @return Restriction
	 * @see Restriction
	 * */
    public static Restriction ne(String fieldName, String value, LogicalOperator logicOperator) {
        return getRestriction(fieldName, Restriction.Operator.NE, value, logicOperator);
    }

    //------------------------------------- LESS THAN FILTER ------------------
	/**
	 * Less than filter restriction
	 * @param fieldName - field name restriction/filter to be applied on
	 * @param value - filter value
	 * @return Restriction
	 * @see Restriction
	 * */
    public static Restriction lt(String fieldName, long value){
        return lt(fieldName, Long.toString(value));
    }
    
	/**
	 * Less than filter restriction
	 * @param fieldName - field name restriction/filter to be applied on
	 * @param value - filter value
	 * @param logicalOperator - logic operator @see Restriction.LogicalOperator
	 * @return Restriction
	 * @see Restriction
	 * */
    public static Restriction lt(String fieldName, long value, LogicalOperator logicOperator){
        return lt(fieldName, Long.toString(value), logicOperator);
    }
    
	/**
	 * Less than filter restriction
	 * @param fieldName - field name restriction/filter to be applied on
	 * @param value - filter value
	 * @return Restriction
	 * @see Restriction
	 * */
    public static Restriction lt(String fieldName, String value) {
        return getRestriction(fieldName, Restriction.Operator.LT, value);
    }
    
	/**
	 * Less than filter restriction
	 * @param fieldName - field name restriction/filter to be applied on
	 * @param value - filter value
	 * @param logicalOperator - logic operator @see Restriction.LogicalOperator
	 * @return Restriction
	 * @see Restriction
	 * */
    public static Restriction lt(String fieldName, String value, LogicalOperator logicOperator) {
        return getRestriction(fieldName, Restriction.Operator.LT, value, logicOperator);
    }
    
    //------------------------------------- LESS THAN EQUAL FILTER ------------
	/**
	 * Less than equal filter restriction
	 * @param fieldName - field name restriction/filter to be applied on
	 * @param value - filter value
	 * @return Restriction
	 * @see Restriction
	 * */
    public static Restriction lte(String fieldName, long value) {
        return lte(fieldName, Long.toString(value));
    }
    
	/**
	 * Less than equal filter restriction
	 * @param fieldName - field name restriction/filter to be applied on
	 * @param value - filter value
	 * @param logicalOperator - logic operator @see Restriction.LogicalOperator
	 * @return Restriction
	 * @see Restriction
	 * */
    public static Restriction lte(String fieldName, long value, LogicalOperator logicOperator) {
        return lte(fieldName, Long.toString(value), logicOperator);
    }
    
	/**
	 * Less than equal filter restriction
	 * @param fieldName - field name restriction/filter to be applied on
	 * @param value - filter value
	 * @return Restriction
	 * @see Restriction
	 * */
    public static Restriction lte(String fieldName, String value) {
        return getRestriction(fieldName, Restriction.Operator.LE, value);
    }
    
	/**
	 * Less than equal filter restriction
	 * @param fieldName - field name restriction/filter to be applied on
	 * @param value - filter value
	 * @param logicalOperator - logic operator @see Restriction.LogicalOperator
	 * @return Restriction
	 * @see Restriction
	 * */
    public static Restriction lte(String fieldName, String value, LogicalOperator logicOperator) {
        return getRestriction(fieldName, Restriction.Operator.LE, value, logicOperator);
    }

    //------------------------------------- GREATER THAN FILTER ---------------
	/**
	 * Greater than filter restriction
	 * @param fieldName - field name restriction/filter to be applied on
	 * @param value - filter value
	 * @return Restriction
	 * @see Restriction
	 * */
    public static Restriction gt(String fieldName, long value) {
        return gt(fieldName, Long.toString(value));
    }
    
	/**
	 * Greater than filter restriction
	 * @param fieldName - field name restriction/filter to be applied on
	 * @param value - filter value
	 * @param logicalOperator - logic operator @see Restriction.LogicalOperator
	 * @return Restriction
	 * @see Restriction
	 * */
    public static Restriction gt(String fieldName, long value, LogicalOperator logicOperator) {
        return gt(fieldName, Long.toString(value), logicOperator);
    }
    
	/**
	 * Greater than filter restriction
	 * @param fieldName - field name restriction/filter to be applied on
	 * @param value - filter value
	 * @return Restriction
	 * @see Restriction
	 * */
    public static Restriction gt(String fieldName, String value) {
        return getRestriction(fieldName, Restriction.Operator.GT, value);
    }
    
	/**
	 * Greater than filter restriction
	 * @param fieldName - field name restriction/filter to be applied on
	 * @param value - filter value
	 * @param logicalOperator - logic operator @see Restriction.LogicalOperator
	 * @return Restriction
	 * @see Restriction
	 * */
    public static Restriction gt(String fieldName, String value, LogicalOperator logicOperator) {
        return getRestriction(fieldName, Restriction.Operator.GT, value, logicOperator);
    }
    
    //------------------------------------- GREATER THAN EQUAL FILTER ---------
	/**
	 * Greater than equal filter restriction
	 * @param fieldName - field name restriction/filter to be applied on
	 * @param value - filter value
	 * @return Restriction
	 * @see Restriction
	 * */
    public static Restriction gte(String fieldName, long value){
        return gte(fieldName, Long.toString(value));
    }
    
	/**
	 * Greater than equal filter restriction
	 * @param fieldName - field name restriction/filter to be applied on
	 * @param value - filter value
	 * @param logicalOperator - logic operator @see Restriction.LogicalOperator
	 * @return Restriction
	 * @see Restriction
	 * */
    public static Restriction gte(String fieldName, long value, LogicalOperator logicOperator){
        return gte(fieldName, Long.toString(value), logicOperator);
    }
    
	/**
	 * Greater than equal filter restriction
	 * @param fieldName - field name restriction/filter to be applied on
	 * @param value - filter value
	 * @return Restriction
	 * @see Restriction
	 * */
    public static Restriction gte(String fieldName, String value) {
        return getRestriction(fieldName, Restriction.Operator.GE, value);
    }
    
	/**
	 * Greater than equal filter restriction
	 * @param fieldName - field name restriction/filter to be applied on
	 * @param value - filter value
	 * @param logicalOperator - logic operator @see Restriction.LogicalOperator
	 * @return Restriction
	 * @see Restriction
	 * */
    public static Restriction gte(String fieldName, String value, LogicalOperator logicOperator) {
        return getRestriction(fieldName, Restriction.Operator.GE, value, logicOperator);
    }
    
    //------------------------------------- LIKE FILTER -----------------------
	/**
	 * Like filter restriction
	 * @param fieldName - field name restriction/filter to be applied on
	 * @param value - filter value
	 * @return Restriction
	 * @see Restriction
	 * */
    public static Restriction like(String fieldName, String value) {
        return getRestriction(fieldName, Restriction.Operator.LIKE, value);
    }
    
	/**
	 * Like filter restriction
	 * @param fieldName - field name restriction/filter to be applied on
	 * @param value - filter value
	 * @param logicalOperator - logic operator @see Restriction.LogicalOperator
	 * @return Restriction
	 * @see Restriction
	 * */
    public static Restriction like(String fieldName, String value, LogicalOperator logicOperator) {
        return getRestriction(fieldName, Restriction.Operator.LIKE, value, logicOperator);
    }
    
    //------------------------------------- IN FILTER -------------------------
	/**
	 * In filter restriction
	 * @param fieldName - field name restriction/filter to be applied on
	 * @param value - filter value
	 * @return Restriction
	 * @see Restriction
	 * */
    public static Restriction in(String fieldName, String... value) {
    	StringBuilder builder = new StringBuilder("");
    	for (String val : value) {
    		if (builder.length() > 0) 
    			builder.append(",");
    		builder.append(val);
    	}
        return getRestriction(fieldName, Restriction.Operator.IN, builder.toString());
    }

	/**
	 * In filter restriction
	 * @param fieldName - field name restriction/filter to be applied on
	 * @param value - filter value
	 * @return Restriction
	 * @see Restriction
	 * */
    public static Restriction in(String fieldName, long... value) {
    	StringBuilder builder = new StringBuilder("");
    	for (long val : value) {
    		if (builder.length() > 0) 
    			builder.append(",");
    		builder.append(val);
    	}
        return getRestriction(fieldName, Restriction.Operator.IN, builder.toString());
    }
    
	/**
	 * In filter restriction
	 * @param fieldName - field name restriction/filter to be applied on
	 * @param value - filter value
	 * @return Restriction
	 * @see Restriction
	 * */
    public static Restriction in(String fieldName, int... value) {
       	StringBuilder builder = new StringBuilder("");
    	for (long val : value) {
    		if (builder.length() > 0) 
    			builder.append(",");
    		builder.append(val);
    	}
        return getRestriction(fieldName, Restriction.Operator.IN, builder.toString());
    }
    
	/**
	 * In filter restriction
	 * @param fieldName - field name restriction/filter to be applied on
	 * @param value - filter value
	 * @param logicalOperator - logic operator @see Restriction.LogicalOperator
	 * @return Restriction
	 * @see Restriction
	 * */
    public static Restriction in(LogicalOperator logicalOperator, String fieldName, String... value) {
    	StringBuilder builder = new StringBuilder("");
    	for (String val : value) {
    		if (builder.length() > 0) 
    			builder.append(",");
    		builder.append(val);
    	}
        return getRestriction(fieldName, Restriction.Operator.IN, builder.toString(), logicalOperator);
    }
    
	/**
	 * In filter restriction
	 * @param fieldName - field name restriction/filter to be applied on
	 * @param value - filter value
	 * @param logicalOperator - logic operator @see Restriction.LogicalOperator
	 * @return Restriction
	 * @see Restriction
	 * */
    public static Restriction in(LogicalOperator logicalOperator, String fieldName, long... value) {
    	StringBuilder builder = new StringBuilder("");
    	for (long val : value) {
    		if (builder.length() > 0) 
    			builder.append(",");
    		builder.append(val);
    	}
        return getRestriction(fieldName, Restriction.Operator.IN, builder.toString(), logicalOperator);
    }
    
    /**
     * Create Restriction instance 
     * @param field - filter field
     * @param operator - operator i.e. <, >, = etc. @see Restriction.Operator
     * @param value - value for filter field
     * @return Restriction - restriction
     * @see Restriction, Restriction.Operator, Restriction.LogicalOperator
     * */
    private static Restriction getRestriction(String field, Restriction.Operator operator, String value) {
        return new Restriction(field, operator, value);
    }
    
    /**
     * Create Restriction instance 
     * @param field - filter field
     * @param operator - operator i.e. <, >, = etc. @see Restriction.Operator
     * @param value - value for filter field
     * @param logicalOperator - logical operator i.e. AND, OR, NOT @see Restriction.LogicalOperator
     * @return Restriction - restriction
     * @see Restriction, Restriction.Operator, Restriction.LogicalOperator
     * */
    private static Restriction getRestriction(String field, Restriction.Operator operator, String value, LogicalOperator logicalOperator) {
        return new Restriction(field, operator, value, logicalOperator);
    }
}
