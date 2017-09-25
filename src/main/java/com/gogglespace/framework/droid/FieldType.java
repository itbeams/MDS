package com.gogglespace.framework.droid;

/**
 * An enumeration holds field types
 *
 * Created by Shahid Nawaz on 2/4/2015.
 * @version 1.0
 */
public enum FieldType {

    STRING {
    	@Override
        public String toString() {
            return "String";
        }
    },

    BOOLEAN {
    	@Override
        public String toString() {
            return "Boolean";
        }
    },

    LONG {
    	@Override
        public String toString() {
            return "long";
        }
    },


    INT {
    	@Override
        public String toString() {
            return "int";
        }
    },

    DATE {
    	@Override
        public String toString() {
            return "Date";
        }
    }
}
