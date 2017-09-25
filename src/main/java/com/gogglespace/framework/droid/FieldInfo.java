package com.gogglespace.framework.droid;

/**
 * Class that hold attribute definition
 * Created by Shahid Nawaz on 2/4/2015.
 * Updated by Shahid Nawaz on 12/17/2015
 * - Added support for object cloning
 *
 * @version 1.0
 */
public class FieldInfo implements Cloneable {

    private String tableName;
    private boolean cipher;
    private String fieldName; // entity field name
    private String columnName; // actual column name as defined for database
    private boolean required;
    private int maxLength;
    private int minLength;
    private FieldType fieldType;
    private boolean primaryKey;
    private Object data; // field data value while inspecting object

    public FieldInfo() {
    	// No implementation required
    }

    public FieldInfo(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    
    public boolean isCipher() {
		return cipher;
	}

	public void setCipher(boolean cipher) {
		this.cipher = cipher;
	}

	public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public int getMinLength() {
        return minLength;
    }

    public void setMinLength(int minLength) {
        this.minLength = minLength;
    }

    public FieldType getFieldType() {
        return fieldType;
    }

    public void setFieldType(FieldType fieldType) {
        this.fieldType = fieldType;
    }

    public boolean isPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "FieldInfo{" +
                "tableName='" + tableName + '\'' +
                ", Cipher='" + cipher+"\'"+
                ", fieldName='" + fieldName + '\'' +
                ", columnName='" + columnName + '\'' +
                ", required=" + required +
                ", maxLength=" + maxLength +
                ", minLength=" + minLength +
                ", fieldType=" + fieldType +
                ", primaryKey=" + primaryKey +
                ", data=" + data +
                '}';
    }
    

}
