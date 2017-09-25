package com.gogglespace.framework.droid.db.schema;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.gogglespace.framework.droid.DbRepositoryHelper;
import com.gogglespace.framework.droid.EntityAttributeAnnotation;
import com.gogglespace.framework.droid.FieldInfo;
import com.gogglespace.framework.droid.db.entities.EmployeeEntity;
import com.gogglespace.framework.droid.db.entities.UserEntity;
import com.gogglespace.framework.droid.utils.StringUtils;


/**
 * DB schema helper using droid persistence framework
 * @author Shahid Nawaz on 2/5/2015.
 * @version 1.0
 */
@SuppressWarnings("unchecked")
public class SchemaHelper {
    public static final String REGULAR_DATABASE_NAME = "regularDb.db";
    public static final int REGULAR_DATABASE_VERSION = 1;

    public static final String SECURE_DATABASE_NAME = "secureDb.db";
    public static final int SECURE_DATABASE_VERSION = 1;

    /**
     * Return list of entity instances
     * @return List
     * */
    @SuppressWarnings("rawtypes")
    private static List getSchemaEntities () {
    	List appEntities = new ArrayList();

        // Users
        appEntities.add(new UserEntity()); // User role entity

        // Ref data services
        appEntities.add(new EmployeeEntity()); // Locations / Facilities

        return appEntities;
    }

    /**
     * Returns list of entities schema
     * @param schemaType
     * @see SCHEMA_TYPE
     * @return List<String>
     * */
    public static List<String> getSchema(SCHEMA_TYPE schemaType, boolean secureEntities) {
		@SuppressWarnings("rawtypes")
		List entities = getSchemaEntities();
		List<String> tableSchemaList = new ArrayList<>();

		for (Object obj : entities) {
			String schema = "";
			if (schemaType == SCHEMA_TYPE.CREATE_TABLES) {
				schema = getCreateSchema(obj, secureEntities);
			} else if (schemaType == SCHEMA_TYPE.DROP_TABLES) {
				schema = getDropTableSchema(obj, secureEntities);
			}

			// as there is a possibility of schema as empty based on the passed flag i.e. secureEntities
			if (StringUtils.hasValue(schema)) {
				tableSchemaList.add(schema);
			}
		}
		return tableSchemaList;
    }

    private static <T> String getCreateSchema(T clazz, boolean secureOnly){
        StringBuilder builder = new StringBuilder(CREATE_SCHEMA.CREATE_TABLE_STATEMENT.toString());
        DbRepositoryHelper helper = new DbRepositoryHelper();
        DbRepositoryHelper.EntityDetails entityDetails =  helper.getEntityDetailsOnly(clazz);
        // No schema needs to be created for none secure entities in secure database and vice versa
        if (secureOnly && !entityDetails.isCipherEntity()) {
        	return "";
        }
        else if (!secureOnly && entityDetails.isCipherEntity()) {
        	return "";
        }

        // table name
        builder.append(entityDetails.getTableName());

        // parenthesis
        builder.append(CREATE_SCHEMA.START_PARENTHESIS.toString());
        List<FieldInfo> fields = entityDetails.getFields();

        int loopIndex = 0;
        int numOfFields = fields.size();
        for(FieldInfo field : fields) {
            builder.append(field.getColumnName());
            if (field.isPrimaryKey()) {
                builder.append(CREATE_SCHEMA.PRIMARY_KEY_DATA_TYPE.toString());
            }
            else {
                builder.append(getDbColumnType(field.getFieldType().name()));
            }

            if (loopIndex < (numOfFields-1)) {
                builder.append(CREATE_SCHEMA.COMMA.toString());
            }
            loopIndex++;
        }

        builder.append(CREATE_SCHEMA.END_PARENTHESIS.toString());
        builder.append(CREATE_SCHEMA.SEMI_COLON.toString());
        return builder.toString();
    }

    /**
     * Create drop table schema for passed entity
     *
     * @param clazz - entity to be dropped
     * @param secureOnly - flag to get drop schema for secure and none secure db
     *
     * @return String - drop table schema
     * */
    private static <T> String getDropTableSchema(T clazz, boolean secureOnly){
        StringBuilder builder = new StringBuilder(DROP_SCHEMA.DROP_TABLE_STATEMENT.toString());
        DbRepositoryHelper helper = new DbRepositoryHelper();
        DbRepositoryHelper.EntityDetails entityDetails =  helper.getEntityDetailsOnly(clazz);
        if (secureOnly && !entityDetails.isCipherEntity()){
        	return "";
        }
        else if (!secureOnly && entityDetails.isCipherEntity()) {
        	return "";
        }
        else {
	        // table name
	        builder.append(entityDetails.getTableName());
	        return builder.toString();
        }
    }


    /**
     * Get SQLite equivalent data type
     *
     * @param columnType - Entity defined column type for persistence
     * @see EntityAttributeAnnotation
     *
     * @return String - return SQLite mapped data type for column
     *
     * */
    private static String getDbColumnType(String columnType) {
        String dbColumnType;
        switch (columnType.toUpperCase(Locale.ENGLISH)) {
            case "STRING" :
                dbColumnType = CREATE_SCHEMA.STRING_DATA_TYPE.toString();
                break;
            case "INTEGER" :
                dbColumnType = CREATE_SCHEMA.INTEGER_DATA_TYPE.toString();
                break;
            case "LONG" :
                dbColumnType = CREATE_SCHEMA.INTEGER_DATA_TYPE.toString();
                break;
            case "BOOLEAN" :
                dbColumnType = CREATE_SCHEMA.INTEGER_DATA_TYPE.toString();
                break;
            case "INT" :
                dbColumnType = CREATE_SCHEMA.INTEGER_DATA_TYPE.toString();
                break;
            default:
                dbColumnType = CREATE_SCHEMA.STRING_DATA_TYPE.toString();
                break;
        }

        return dbColumnType;
    }

    /**
     * Create schema information
     * */
    private enum CREATE_SCHEMA {
        CREATE_TABLE_STATEMENT {
            @Override
            public String toString() {
                return "CREATE TABLE IF NOT EXISTS ";
            }

        },
        START_PARENTHESIS {
            @Override
            public String toString() {
                return " (";
            }
        },
        END_PARENTHESIS {
            @Override
            public String toString() {
                return " )";
            }
        },
        COMMA {
            @Override
            public String toString() {
                return ", ";
            }
        },
        SEMI_COLON {
            @Override
            public String toString() {
                return ";";
            }
        },
        PRIMARY_KEY_DATA_TYPE {
            @Override
            public String toString() {
                return " INTEGER PRIMARY KEY AUTOINCREMENT ";
            }
        },
        STRING_DATA_TYPE {
            @Override
            public String toString() {
                return " TEXT ";
            }
        },
        BOOLEAN_DATA_TYPE {
            @Override
            public String toString() {
                return " INTEGER "; // 0/1 values
            }
        },
        INTEGER_DATA_TYPE {
            @Override
            public String toString() {
                return " INTEGER "; // 0/1 values
            }
        }
    }

    /**
     * Drop table schema
     * */
    private enum DROP_SCHEMA {
        DROP_TABLE_STATEMENT {
            @Override
            public String toString() {
                return "DROP TABLE IF EXISTS ";
            }
        }
    }

    public enum SCHEMA_TYPE {
        CREATE_TABLES, DROP_TABLES
    }

}
