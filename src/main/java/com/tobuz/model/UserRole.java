package com.tobuz.model;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;


public final class  UserRole {
	
	public static String BROKER = "BROKER";
	public static String BUYER = "BUYER";
	public static String BUSINESS_SERVICE = "BUSINESS_SERVICE";
	public static String FRANCHISOR = "FRANCHISOR";
	public static String SELLER = "SELLER";
	public static String TOBUZ_ADMIN = "TOBUZ_ADMIN";
	public static String TOBUZ_SUB_ADMIN = "TOBUZ_SUB_ADMIN";
	public	static String TOBUZ_ACCOUNTANT = "TOBUZ_ACCOUNTANT";
	
	
	
	
	

	
	
	
	/*
	 * public String capitalize(){ return
	 * WordUtils.capitalizeFully(toString().replaceAll("_", " ")); }
	 * 
	 * public static Map<String, String> options() { final LinkedHashMap<String,
	 * String> vals = new LinkedHashMap<String, String>(); for (final UserRole val :
	 * UserRole.values()) { vals.put(val.toString(),
	 * StringUtils.capitalize(val.toString())); } return vals; }
	 */
	/*
	 * public static Map<String, String> getTobuzUserOptions() { final
	 * LinkedHashMap<String, String> vals = new LinkedHashMap<String, String>();
	 * vals.put(UserRole.BROKER.toString(), UserRole.BROKER.capitalize());
	 * vals.put(UserRole.BUYER.toString(), UserRole.BUYER.capitalize());
	 * vals.put(UserRole.BUSINESS_SERVICE.toString(),
	 * UserRole.BUSINESS_SERVICE.capitalize());
	 * vals.put(UserRole.FRANCHISOR.toString(), UserRole.FRANCHISOR.capitalize());
	 * vals.put(UserRole.SELLER.toString(), UserRole.SELLER.capitalize()); return
	 * vals; }
	 * 
	 * public static Map<String, String> getTobuzSubAdminOptions() { final
	 * LinkedHashMap<String, String> vals = new LinkedHashMap<String, String>();
	 * vals.put(UserRole.TOBUZ_SUB_ADMIN.toString(),
	 * UserRole.TOBUZ_SUB_ADMIN.capitalize());
	 * vals.put(UserRole.TOBUZ_ACCOUNTANT.toString(),
	 * UserRole.TOBUZ_ACCOUNTANT.capitalize()); return vals; }
	 * 
	 * public static List<UserRole> getTobuzSubAdminRoles() { List<UserRole> vals =
	 * new ArrayList<UserRole>(); vals.add(UserRole.TOBUZ_SUB_ADMIN);
	 * vals.add(UserRole.TOBUZ_ACCOUNTANT); vals.add(UserRole.TOBUZ_ADMIN); return
	 * vals; }
	 */
	

}
