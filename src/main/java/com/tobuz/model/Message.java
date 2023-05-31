package com.tobuz.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Message extends BaseEntity {

	@ManyToOne
	private AppUser fromUser;

	@ManyToOne
	private Role fromUserRole;

	@ManyToOne
	private AppUser toAppUser;

	@ManyToOne
	private Role toUserRole;

	@Column(columnDefinition = "TEXT")
	private String subject;

	@Column(columnDefinition = "TEXT")
	private String bodytext;

	private Boolean isReceiverVerified = Boolean.FALSE;

	@ManyToOne
	private BusinessListing businessListing;

	@ManyToOne
	private BusinessAdvert businessAdvert;

	@OneToOne
	private FileEntity attachment;

	private Date messageSentOn;

	private String fromUserName;

	private String fromUserDialingCode;

	private String fromUserMobileNumber;

	private String fromUserEmail;

	public String getFromUserEmail() {
		return fromUserEmail;
	}

	public void setFromUserEmail(String fromUserEmail) {
		this.fromUserEmail = fromUserEmail;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public String getFromUserDialingCode() {
		return fromUserDialingCode;
	}

	public void setFromUserDialingCode(String fromUserDialingCode) {
		this.fromUserDialingCode = fromUserDialingCode;
	}

	public String getFromUserMobileNumber() {
		return fromUserMobileNumber;
	}

	public void setFromUserMobileNumber(String fromUserMobileNumber) {
		this.fromUserMobileNumber = fromUserMobileNumber;
	}

	public AppUser getFromUser() {
		return fromUser;
	}

	public BusinessListing getBusinessListing() {
		return businessListing;
	}

	public FileEntity getAttachment() {
		return attachment;
	}

	public void setAttachment(FileEntity attachment) {
		this.attachment = attachment;
	}

	public void setBusinessListing(BusinessListing businessListing) {
		this.businessListing = businessListing;
	}

	public Date getMessageSentOn() {
		return messageSentOn;
	}

	public void setMessageSentOn(Date messageSentOn) {
		this.messageSentOn = messageSentOn;
	}

	public void setFromUser(AppUser fromUser) {
		this.fromUser = fromUser;
	}

	public Role getFromUserRole() {
		return fromUserRole;
	}

	public void setFromUserRole(Role fromUserRole) {
		this.fromUserRole = fromUserRole;
	}

	public AppUser getToAppUser() {
		return toAppUser;
	}

	public void setToAppUser(AppUser toAppUser) {
		this.toAppUser = toAppUser;
	}

	public Role getToUserRole() {
		return toUserRole;
	}

	public void setToUserRole(Role toUserRole) {
		this.toUserRole = toUserRole;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBodytext() {
		return bodytext;
	}

	public void setBodytext(String bodytext) {
		this.bodytext = bodytext;
	}

	public Boolean getIsReceiverVerified() {
		return isReceiverVerified;
	}

	public BusinessAdvert getBusinessAdvert() {
		return businessAdvert;
	}

	public void setBusinessAdvert(BusinessAdvert businessAdvert) {
		this.businessAdvert = businessAdvert;
	}

	public void setIsReceiverVerified(Boolean isReceiverVerified) {
		this.isReceiverVerified = isReceiverVerified;
	}
	/*
	 * public static Message createMessage(MessageBean messageBean) { // TODO
	 * Auto-generated method stub //Logger.info("1"); try { Message message = new
	 * Message(); message.setIsActive(Boolean.TRUE);
	 * message.setBodytext(messageBean.getBodytext()); message.setMessageSentOn(new
	 * Date()); if(messageBean.getFromUserId() != null) {
	 * message.setFromUser(AppUser.find.byId(messageBean.getFromUserId())); }
	 * if(messageBean.getFromUserRoleId() != null) {
	 * message.setFromUserRole(Role.find.byId(messageBean.getFromUserRoleId())); }
	 * message.setToAppUser(AppUser.find.byId(messageBean.getToAppUserId()));
	 * message.setToUserRole(Role.find.byId(messageBean.getToUserRoleId()));
	 * message.setFromUserName(messageBean.getFromUserName());
	 * message.setFromUserDialingCode(messageBean.getFromUserDialingCode());
	 * message.setFromUserMobileNumber(messageBean.getFromUserMobileNumber());
	 * message.setFromUserEmail(messageBean.getFromEmail());
	 * if(messageBean.getImageId() != null && messageBean.getImageId() != 0) {
	 * message.setAttachment(FileEntity.find.byId(messageBean.getImageId())); }
	 * Boolean isMessageValid = false; if(messageBean.getBusinessAdvertId() != null
	 * && messageBean.getBusinessAdvertId() != 0) { isMessageValid = true;
	 * message.setBusinessAdvert(BusinessAdvert.find.byId(messageBean.
	 * getBusinessAdvertId())); }else if(messageBean.getBusinessListingId() != null
	 * && messageBean.getBusinessListingId() != 0) { isMessageValid = true;
	 * message.setBusinessListing(BusinessListing.find.byId(messageBean.
	 * getBusinessListingId())); } //Logger.info(isMessageValid+"   ");
	 * if(isMessageValid) { message.save(); //Logger.info("message saved"); return
	 * message; } }catch (Exception e) { // TODO: handle exception
	 * e.printStackTrace(); }
	 * 
	 * return null; }
	 * 
	 * public static List<Message> getAppUserMessageList(AppUser appUser,Role role){
	 * Set<Message> messageSet =
	 * Message.find.where().or(Expr.and(Expr.eq("fromUserRole",
	 * role),Expr.eq("fromUser", appUser)),Expr.and(Expr.eq("toUserRole",
	 * role),Expr.eq("toAppUser",
	 * appUser))).orderBy().desc("messageSentOn").findSet(); return new
	 * ArrayList<>(messageSet); }
	 * 
	 * public static List<Message> getAppUserMessageList(AppUser appUser,Role
	 * role,BusinessAdvert businessAdvert){ Set<Message> messageSet =
	 * Message.find.where().or(Expr.and(Expr.eq("fromUserRole",
	 * role),Expr.eq("fromUser", appUser)),Expr.and(Expr.eq("toUserRole",
	 * role),Expr.eq("toAppUser", appUser))) .eq("businessAdvert", businessAdvert)
	 * .orderBy().desc("messageSentOn").findSet(); return new
	 * ArrayList<>(messageSet); }
	 * 
	 * public static List<Message> getAppUserMessageList(AppUser appUser,Role
	 * role,BusinessListing businessListing){ Set<Message> messageSet =
	 * Message.find.where().or(Expr.and(Expr.eq("fromUserRole",
	 * role),Expr.eq("fromUser", appUser)),Expr.and(Expr.eq("toUserRole",
	 * role),Expr.eq("toAppUser", appUser))) .eq("businessListing", businessListing)
	 * .orderBy().desc("messageSentOn").findSet(); return new
	 * ArrayList<>(messageSet); }
	 * 
	 * public static List<Message> getAppUserMessageList(AppUser appUser,Role
	 * role,BusinessAdvert businessAdvert,BusinessListing businessListing){
	 * ExpressionList<Message> messageQuery =
	 * Message.find.where().or(Expr.and(Expr.eq("fromUserRole",
	 * role),Expr.eq("fromUser", appUser)),Expr.and(Expr.eq("toUserRole",
	 * role),Expr.eq("toAppUser", appUser))); if(businessListing != null) {
	 * messageQuery.eq("businessListing", businessListing); } if(businessAdvert !=
	 * null) { messageQuery.eq("businessAdvert", businessAdvert); } Set<Message>
	 * messageSet = messageQuery.orderBy().desc("messageSentOn").findSet(); return
	 * new ArrayList<>(messageSet); }
	 * 
	 * public static List<Message> getAppUserMessageOrderByDateAscList(AppUser
	 * appUser,Role role,BusinessAdvert businessAdvert,BusinessListing
	 * businessListing){ ExpressionList<Message> messageQuery =
	 * Message.find.where().or(Expr.and(Expr.eq("fromUserRole",
	 * role),Expr.eq("fromUser", appUser)),Expr.and(Expr.eq("toUserRole",
	 * role),Expr.eq("toAppUser", appUser))); if(businessListing != null) {
	 * messageQuery.eq("businessListing", businessListing); } if(businessAdvert !=
	 * null) { messageQuery.eq("businessAdvert", businessAdvert); } Set<Message>
	 * messageSet = messageQuery.orderBy().asc("messageSentOn").findSet(); return
	 * new ArrayList<>(messageSet); }
	 * 
	 * public static List<Message> getAppUserMessageOrderByDateAscList(AppUser
	 * appUser,Role role,AppUser toUser,Role toRole,BusinessAdvert
	 * businessAdvert,BusinessListing businessListing, String email){
	 * ExpressionList<Message> messageQuery =
	 * Message.find.where().or(Expr.and(Expr.eq("fromUserRole",
	 * role),Expr.eq("fromUser", appUser)),Expr.and(Expr.eq("toUserRole",
	 * role),Expr.eq("toAppUser", appUser))); if(toUser != null && toRole != null) {
	 * messageQuery.or(Expr.and(Expr.eq("fromUserRole", toRole),Expr.eq("fromUser",
	 * toUser)),Expr.and(Expr.eq("toUserRole", toRole),Expr.eq("toAppUser",
	 * toUser))); }else { messageQuery.or(Expr.isNull("fromUserRole"),
	 * Expr.isNull("toUserRole")); } if(businessListing != null) {
	 * messageQuery.eq("businessListing", businessListing); } if(businessAdvert !=
	 * null) { messageQuery.eq("businessAdvert", businessAdvert); }if(email != null)
	 * { messageQuery.eq("fromUserEmail", email); } Set<Message> messageSet =
	 * messageQuery.orderBy().asc("messageSentOn").findSet(); return new
	 * ArrayList<>(messageSet); }
	 * 
	 * public static List<Message> getAppUserDistinctMessageList(AppUser
	 * appUser,Role role){ Set<Message> messageSet =
	 * Message.find.fetch("businessListing").setDistinct(Boolean.TRUE).findSet();
	 * return new ArrayList<>(messageSet); }
	 * 
	 * public static List<Message> getUnreadMessages(AppUser appUser,Role
	 * role,BusinessAdvert businessAdvert,BusinessListing businessListing){
	 * ExpressionList<Message> messageQuery = Message.find.where().eq("isActive",
	 * Boolean.TRUE).eq("toUserRole", appUser.getRole()).eq("toAppUser",
	 * appUser).eq("isReceiverVerified", Boolean.FALSE); if(businessAdvert != null)
	 * { messageQuery.eq("businessAdvert", businessAdvert); }else if(businessListing
	 * != null) { messageQuery.eq("businessListing",businessListing); } return
	 * messageQuery.findList(); }
	 * 
	 * public static List<Message> getAllMessagesBySearchKey(SearchBean searchBean){
	 * ExpressionList<Message> messageQuery = Message.find.where().eq("isActive",
	 * Boolean.TRUE);
	 * 
	 * List<String> countryIsoCodeList = searchBean.countryIsoCodeList;
	 * 
	 * if(countryIsoCodeList != null && !countryIsoCodeList.isEmpty()){
	 * countryIsoCodeList.stream().forEach(countryCode->{ Country countryByIsoCode =
	 * Country.getCountryByIsoCode(countryCode);
	 * messageQuery.or(Expr.eq("fromUser.mobileDerivedCountry.id",countryByIsoCode.
	 * getId()),Expr.eq("fromUser.mobileDerivedCountry.id",
	 * countryByIsoCode.getId())); }); } return messageQuery.findList(); } public
	 * static List<Message> getAllConatctToSellerUnregisteredUser(SearchBean
	 * searchBean) { getting all active users messages ExpressionList<Message>
	 * msgQuery = Message.find.where().eq("isActive", Boolean.TRUE);
	 * if(searchBean!=null && StringUtils.isNotEmpty(searchBean.fromDate)){
	 * msgQuery.ge("messageSentOn", DateUtil.getFormatedDate(searchBean.fromDate));
	 * } if(searchBean!=null && StringUtils.isNotEmpty(searchBean.fromDate)){
	 * msgQuery.le("messageSentOn",
	 * DateUtil.getEndDate(DateUtil.getFormatedDate(searchBean.toDate))); }
	 * 
	 * return msgQuery.findList(); }
	 */

}
