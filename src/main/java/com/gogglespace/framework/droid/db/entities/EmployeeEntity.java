package com.gogglespace.framework.droid.db.entities;

import com.gogglespace.framework.droid.EntityAnnotation;
import com.gogglespace.framework.droid.EntityAttributeAnnotation;
import com.gogglespace.framework.droid.FieldType;

/**
 * Entity class 
 * Created by Shahid Nawaz on 4/10/2015.
 * @version 1.0
 */
@EntityAnnotation(tableName = "employees", cacheEntityInfo=true, comments = "employee entity")
public class EmployeeEntity extends BaseEntity {

    @EntityAttributeAnnotation(fieldName = "name", required = true, fieldType = FieldType.STRING)
    private String name;

    @EntityAttributeAnnotation(fieldName = "age", required = true, fieldType = FieldType.LONG)
    private long age;


    @EntityAttributeAnnotation(fieldName = "address", required = true, fieldType = FieldType.STRING)
    private String address;


    @EntityAttributeAnnotation(fieldName = "active", required = true, fieldType = FieldType.BOOLEAN)
    private boolean active;


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public long getAge() {
		return age;
	}


	public void setAge(long age) {
		this.age = age;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}
   
}
