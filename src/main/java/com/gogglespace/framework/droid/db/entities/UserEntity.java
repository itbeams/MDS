package com.gogglespace.framework.droid.db.entities;

import com.gogglespace.framework.droid.EntityAnnotation;
import com.gogglespace.framework.droid.EntityAttributeAnnotation;
import com.gogglespace.framework.droid.FieldType;

/**
 * Entity contains user login inf
 * Created by Shahid Nawaz on 12/2/2015.
 * @version 1.0
 */
@EntityAnnotation(tableName = "users", cipherEntity=true, cacheEntityInfo=true, comments = "Entity for user ")
public class UserEntity extends BaseEntity { 

    @EntityAttributeAnnotation(fieldName = "userName", required = true, fieldType = FieldType.STRING)
    private String userName;

    @EntityAttributeAnnotation(fieldName = "userPasswd", required = true, fieldType = FieldType.STRING)
    private String userPassword;

    @EntityAttributeAnnotation(fieldName = "role", required = false, fieldType = FieldType.STRING)
    private String role;

    @EntityAttributeAnnotation(fieldName = "org", required = true, fieldType = FieldType.STRING)
    private String org;
    
    @EntityAttributeAnnotation(fieldName = "active", required = true, fieldType = FieldType.BOOLEAN)
    private boolean active;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
