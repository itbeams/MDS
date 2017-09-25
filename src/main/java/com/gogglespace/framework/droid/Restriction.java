package com.gogglespace.framework.droid;

/**
 * Droid Persistence Framework
 * Criteria restriction
 * Created by Shahid Nawaz on 4/20/2015, 08/07/2015
 * @version 1.0
 * 
 * 08/07/2015 - Added support for logical operators other than AND only
 * @see LogicalOperator
 */
public class Restriction {
    private String fieldName;
    private Operator operator;
    private String value;
    
    private LogicalOperator logicalOperator;
    
    Restriction(String field, Operator op, String val) {
        this.fieldName = field;
        this.operator=op;
        this.value=val;
        this.logicalOperator = LogicalOperator.AND; // default 
    }

    Restriction(String field, Operator op, String val, LogicalOperator logicOperator) {
        this.fieldName = field;
        this.operator=op;
        this.value=val;
        this.logicalOperator = logicOperator;
    }
    
    public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public LogicalOperator getLogicalOperator() {
		return logicalOperator;
	}

	public void setLogicalOperator(LogicalOperator logicalOperator) {
		this.logicalOperator = logicalOperator;
	}



	public enum Operator {
        LT, GT, LE, GE, EQ,NE, LIKE, IN
    }
    
    public enum LogicalOperator {
        AND, OR, NOT
    }
}
