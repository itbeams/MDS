package com.gogglespace.framework.droid;

import java.util.ArrayList;
import java.util.List;

import com.gogglespace.framework.droid.Restriction.LogicalOperator;
import com.gogglespace.framework.droid.Restriction.Operator;
import com.gogglespace.framework.droid.exceptions.CommonRuntimeException;
import com.gogglespace.framework.droid.utils.StringUtils;


/**
 * Repository helper for dynamic schema of persistence framework <br />
 * @author Shahid Nawaz on 2/4/2015.
 * @version 1.0
 */
public class DbRepositoryHelper {
    EntityDetails instance = null;
    public class EntityDetails {

        List<FieldInfo> fields;
        String tableName;
        String idField;
        String[] selectedColumnList;
        boolean cipherEntity;

        EntityDetails(List<FieldInfo> fields, String tableName, String idField, String[] selectedColumnList) {
            this.fields = fields;
            this.tableName = tableName;
            this.idField = idField;
            this.selectedColumnList = selectedColumnList;
        }
        EntityDetails(List<FieldInfo> fields, String tableName, String idField, String[] selectedColumnList, 
        		boolean cipher) {
        	this(fields, tableName, idField, selectedColumnList);
            this.cipherEntity = cipher;
        }
        public List<FieldInfo> getFields() {
            return fields;
        }

        public String getTableName() {
            return tableName;
        }

        public String getIDField() {
            return idField;
        }

        public String[] getSelectedColumnList() {
            return selectedColumnList;
        }
        
        public boolean isCipherEntity(){
        	return cipherEntity;
        }
    }

    public <T> EntityDetails getEntityDetailsOnly(T type) {
        if (instance == null) {
            List<FieldInfo> fieldList = AnnotationHelper.getFieldsInfo(type,
                    AnnotationHelper.FIELD_INFO.DEFINITION_ONLY);
            String tableName = null;
            String idField = null;
            String[] columns = new String[fieldList.size()];
            boolean cipher = false;
            int index = 0;
            for (FieldInfo f : fieldList) {
                if (tableName == null) {
                    tableName = f.getTableName();
                }
                columns[index++] = f.getColumnName();
                if (f.isPrimaryKey()) {
                    idField = f.getColumnName();
                }
                
                if (!cipher){
                	cipher = f.isCipher(); 
                }
            }
            
            instance = new EntityDetails(fieldList, tableName, idField, columns, cipher);
        }
        return instance;
    }



    public String getWhereClause(Criteria criteria) {
        String logicalOperator = "";
        StringBuilder whereClazz = new StringBuilder();
        if (criteria == null ) {
        	return null;
        }
        List<Restriction> restrictions = criteria.getRestrictions();
        if (!ObjectUtils.isNullOrEmpty(restrictions)){
            for (Restriction restriction : restrictions) {
                if (whereClazz.length() > 0) {
                	whereClazz.append(logicalOperator);
                }
                whereClazz.append(getOperatorTranslated(restriction, restriction.getOperator()));
                logicalOperator = getLogicalOperator(restriction.getLogicalOperator());
            }
        }
        return whereClazz.length()>0?whereClazz.toString():null;
    }
    
    /**
     * Get logical operator
     * NOTE: Default operator if not specified is AND
     * @param logicalOperator - logical operator
     * @return String - logical operator
     * @see Restriction.LogicalOperator
     * */
    private String getLogicalOperator(Restriction.LogicalOperator logicalOperator) {
    	final String andOperator 	= " AND ";
    	final String orOperator 	= " OR ";
    	final String notOperator 	= " NOT ";
    	
    	String operator;
    	if (logicalOperator == null) {
    		operator = andOperator;
    	}
    	else if ( logicalOperator == LogicalOperator.AND ) {
    		operator = andOperator;
    	}
    	else if ( logicalOperator == LogicalOperator.OR ) {
    		operator = orOperator;
    	}
    	else if ( logicalOperator == LogicalOperator.NOT ) {
    		operator = notOperator;
    	}
    	else {
    		operator = andOperator;
    	}
    	return operator;
    }
    
    private String getOperatorTranslated(Restriction restriction, Restriction.Operator operator) {
        switch (operator) {
            case EQ: 
            	return restriction.getFieldName() + " " + "=" +" " + "?";
            case NE:
            	return restriction.getFieldName() + " " + "!=" +" " + "?";
            case LT: 
            	return restriction.getFieldName() + " " + "<" +" " + "?";
            case LE: 
              	return restriction.getFieldName() + " " + "<=" +" " + "?";
            case GT:
              	return restriction.getFieldName() + " " + ">" +" " + "?";
            case GE: 
            	return restriction.getFieldName() + " " + ">=" +" " + "?";
            case LIKE: 
            	return restriction.getFieldName() + " " + "like" +" " + "?";
            case IN: 
            	return restriction.getFieldName() + " " + " in("+getInPlaceholders(restriction.getValue())+ ")";
            default : 
                return "";
        }
    }
    
    private String getInPlaceholders(String values) {
    	String tmpValues = StringUtils.getString(values);
    	String[] valArr = tmpValues.split(",");
    	int len = 0;
    	if (valArr != null) {
    		len = valArr.length;
    	}
        if (len < 1) {
            // It will lead to an invalid query anyway ..
            throw new CommonRuntimeException("No placeholders");
        } else {
            StringBuilder sb = new StringBuilder(len * 2 - 1);
            sb.append("?");
            for (int i = 1; i < len; i++) {
                sb.append(",?");
            }
            return sb.toString();
        }
    }

    /**
     * Modified for in clause arguments
     * - known limitation of having only one operational condition 
     * with IN clause
     * */
    public String[] getWhereArgs(Criteria criteria) {
        String[] args = null;
        if (criteria == null ) {
        	return args;
        }
        List<Restriction> restrictions = criteria.getRestrictions();
        List<String> argList = new ArrayList<>();
        if (restrictions != null && !restrictions.isEmpty()){
            for (Restriction restriction : restrictions) {
            	if (restriction.getOperator() == Operator.IN){
            		String[] inArgs = restriction.getValue().split(",");
            		if (inArgs != null && inArgs.length > 0) {
            			for(int i=0; i<inArgs.length; i++)
            				argList.add(inArgs[i]);
            		}
            	}
            	else {
            		argList.add(restriction.getValue());
            	}
            }
        }
    	if(!argList.isEmpty()) {
    		args = argList.toArray(new String[argList.size()]);
    	}
        return args;
    }

    private String[] getOrderFields(Criteria criteria) {
        String[] values = null;
        if (criteria == null ) {
        	return values;
        }
        List<String> fields = criteria.getOrderByFields();
        if (fields != null && !fields.isEmpty()){
            values =  fields.toArray(new String[fields.size()]);
        }
        return values;
    }

	public String getOrderByClause(Criteria criteria) {
        String[] fields = getOrderFields(criteria);
        if (criteria != null && fields != null && fields.length > 0 && criteria.getOrderBy() != null) {
            StringBuilder orderByClause = new StringBuilder();
            for(String field : fields) {
               if (orderByClause.length() > 0) {
                   orderByClause.append(", ");
                }
               orderByClause.append(field);
            }
            if (orderByClause.length() > 0) {
            	orderByClause.append(Criteria.OrderBy.ASC == criteria.getOrderBy() ? " asc":" desc");
            }
            return orderByClause.length()>0?orderByClause.toString():null;
        }
        else {
            return null;
        }
    }

  
}
