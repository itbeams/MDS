package com.gogglespace.framework.droid.utils;

public class AppConstants {

	public static final String ACCUTE_APP_PREFRENCE 	= "ACCUTE_APP_PREFRENCE";


	public static final String AD_LOGIN_TYPE = "ADLogin";

	public static final int WAIT_TIMEOUT = 70000;

	public static final String SLOW_INTERNET_CONNECTION = "SLOW_INTERNET_CONNECTION";

	public static final String SERVER_NOT_RESPONDING = "SERVER_NOT_RESPONDING";
	public static final String USER_NAME = "USER_NAME";

	public static final String URL_SOURCE = "URL_SOURCE";

	public static final String LOGIN = "LOGIN";
	public static final String LOGOUT = "LOGOUT";

	public static final String REGISTRATION = "REGISTRATION";

	public static final String SELECTED_MESSAGE = "SELECTED_MESSAGE";

	public static final String DESTINATION_SCREEN = "DESTINATION_SCREEN";

	public static final String COMPOSE_SCREEN = "COMPOSE_SCREEN";

	public static final String DETAIL_SCREEN = "DETAIL_SCREEN";

	public static final String REPLY_SCREEN = "REPLY_SCREEN";

	public static final String SELECTED_CONTACTS = "SELECTED_CONTACTS";

	public static final String TREATMENT_LIST_ITEM_SUMMARY = "TREATMENT_LIST_ITEM_SUMMARY";

	public static final String NOTIFICATION_MESSAGES = "NOTIFICATION_MESSAGES";

	public static final String PASSWORD_WARNING_MESSAGE = "PWD_WARNING_MSG";

	public static final String BILLING_TREATMENT = "BILLING_TREATMENT";

	public static final String CREATED_PATIENT = "NEW_PATIENT";
	public static final String PATIENT = "PATIENT";

	public static final String TREATMENT = "TREATMENT";

	public static final String OPENED_TREATTMENT_ID = "OPENED_TREATTMENT_ID"; // current opened treatment id
	public static final String OPENED_TREATMENT_PATIENT = "OPENED_TREATMENT_PATIENT"; // current opened treatment patient

	// question base form and section to display form and section container info
	public static final String QUESTION_DISPLAY_CONTAINER_KEY = "QUESTION_CONTAINER";

	public static final int BARCODE_SCAN_REQUEST_CODE = 1001;// barcode request code
	public static final String SCANNED_BAR_CODE = "BARCODE";

	// Ramu is good with the hard coded CANCELLED TREATMENT FORM "Canceled Treatment"
	// Cancelled treatment form needs to be opened from popup instead of tab (it should not be displayed on the Tab)
	public static final String CANCELLED_TREATMENT_FORM_NAME = "Canceled Treatment";

	/**
	 * Ramu - is good with the hard coded BILLING TREATMENT FORM "Billing" rather than implementing configurable
	 * Billing form appears as a popup like cancel treatment form
	 * */
	public static final String BILLING_TREATMENT_FORM_NAME = "Billing";

	public static final String MULTI_ENTRY_MODEL 	  = "MULTI_ENTRY_MODEL";
	public static final String MULTI_ENTRY_FORM_TYPE  = "MULTI_ENTRY_FORM_TYPE";
	public static final String SECTION = "SECTION";
	public static final String T_MACHINE_FORM = "T_MACHINE_FORM";

	public static final String MULTI_ENTRY_MODEL_PAGE_INDEX = "VITAL_PAGE_INDEX";

	public static final int TREATMENT_SUMMARY_RESULT_CODE = 100;

	public static final int SESSION_TIME_OUT_ERROR_CODE = 401; // 602 was the session timeout error code

	public static final int PASSWORD_EXPIRED_ERROR_CODE = 604;

	public static final int ORG_SWITCH_RESULT_CODE		= 2000;//

	public static final int DATE_OF_BIRTH_NUMBER_OF_YEARS = 30;

	// Allows to export db for data analysis
	public static final boolean ALLOW_DB_EXPORT = true;

	//
	public static final int SCHEDULER_JOB_ID = 1000; // default job id for scheduler

	// Used as app constants
	public static final String BOOLEAN_YES			   = "Yes";
	public static final String BOOLEAN_NO			   = "No";

	private AppConstants(){
		// Outside object initialize restriction
	}
}
