package com.tobuz.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class  BusinessListingStatus {
	

public final static	String PUBLISHED  = "PUBLISHED";

public final static	String UNDER_REVIEW  = "UNDER_REVIEW";
	
public final static	String APPROVED  = "APPROVED";

public final static	String REJECTED  = "REJECTED";

public final static	String SOLD  = "SOLD";

public final static	String COMPLETED  = "COMPLETED";
public final static	String DRAFTED  = "DRAFTED";
public final static	String EXPIRED  = "EXPIRED";
	
	/*
	 * public String capitalize(){
	 * if(this.equals(BusinessListingStatus.UNDER_REVIEW)) { return
	 * WordUtils.capitalizeFully("Pending Approval"); } return
	 * WordUtils.capitalizeFully(toString().replaceAll("_", " ")); }
	 */
	
	public static List<String> getListingNotAvailableStatusList(){
		List<String> statusList = new ArrayList<String>();
		statusList.add(BusinessListingStatus.UNDER_REVIEW);
		statusList.add(BusinessListingStatus.REJECTED);
	//	statusList.add(BusinessListingStatus.SOLD);
		statusList.add(BusinessListingStatus.DRAFTED);
		//statusList.add(BusinessListingStatus.EXPIRED);
		return statusList;
	}
	
	public static List<String> getListingContactNotAvailableStatusList(){
		List<String> statusList = new ArrayList<String>();
		statusList.add(BusinessListingStatus.UNDER_REVIEW);
		statusList.add(BusinessListingStatus.REJECTED);
		statusList.add(BusinessListingStatus.SOLD);
		statusList.add(BusinessListingStatus.DRAFTED);
		statusList.add(BusinessListingStatus.EXPIRED);
		return statusList;
	}
	
	public static String getStatusColor(BusinessListingStatus businessListingStatus) {
		Map<String,String> statusColorMap = new HashMap<String,String>();
		//statusColorMap.put(BusinessListingStatus.PUBLISHED, value);
		statusColorMap.put(BusinessListingStatus.UNDER_REVIEW, "#d58512");
		statusColorMap.put(BusinessListingStatus.APPROVED, "#398439");
		statusColorMap.put(BusinessListingStatus.REJECTED, "#c9302c");
		statusColorMap.put(BusinessListingStatus.SOLD, "#204d74");
	//	statusColorMap.put(BusinessListingStatus.COMPLETED, value);
		statusColorMap.put(BusinessListingStatus.DRAFTED, "#269abc");
		statusColorMap.put(BusinessListingStatus.EXPIRED, "#dddddd");
		return statusColorMap.get(businessListingStatus);
	}
	public static List<String> getListingStatusForALLPRCount(){
		List<String> statusList = new ArrayList<String>();
		statusList.add(BusinessListingStatus.UNDER_REVIEW);
		statusList.add(BusinessListingStatus.PUBLISHED);
		return statusList;
	}

}
