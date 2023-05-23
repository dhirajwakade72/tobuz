package com.tobuz.model;


public class PaymentConstant {
	
	
	/**
	 * payment gateway parameters - KEYS
	 * 
	 */

	public static final String IVP_METHOD_KEY = "ivp_method";
	public static final String IVP_STORE_KEY = "ivp_store";
	public static final String IVP_AUTH_KEY = "ivp_authkey";
	public static final String IVP_TRANSATION_TYPE_KEY = "ivp_trantype";
	public static final String IVP_TRANSACTION_CLASS_KEY = "ivp_tranclass";
	public static final String IVP_DESC_KEY = "ivp_desc";
	public static final String IVP_CART_KEY = "ivp_cart";
	public static final String IVP_CURRENCY_KEY = "ivp_currency";
	public static final String IVP_AMOUNT_KEY = "ivp_amount";
	public static final String IVP_TEST_KEY = "ivp_test";
	public static final String RETURN_AUTH_KEY = "return_auth";
	public static final String RETURN_DECLIINED_KEY = "return_decl";
	public static final String RETURN_CANCEL_KEY = "return_can";
	public static final String BILL_TITLE_KEY = "bill_title";
	public static final String BILL_FIRST_NAME_KEY = "bill_fname";
	public static final String BILL_SNAME_KEY = "bill_sname";
	public static final String BILL_EMAIL_KEY = "bill_email";
	public static final String BILL_ADDRESS_KEY = "bill_addr1";
	public static final String BILL_CITY_KEY = "bill_city";
	public static final String BILL_COUNTRY_KEY = "bill_country";

	public static final String ORDER_REF_KEY = "order_ref";

	public static final String TRANSACTION_REF_KEY = "tran_ref";
	
	public static final String SALE_PAYMENT = "sale";
	public static final String AUTH_PAYMENT = "auth";

	public static final String WEB_HOST ="https://localhost:9000";
	/**
	 * Unique Customer ID Optional. If set, the customer may be given the option
	 * to store their card for future use if the transaction is authorised, and
	 * will be presented with a list of stored cards (if any) at the start of a
	 * transaction.
	 */

	public static final String BILL_CUSTOMER_REF_KEY = "bill_custref";
	

	/**
	 * Telr store id for Jugaad value of IVP_STORE_KEY
	 */

	public static final String TELR_STORE_ID = "18993";

	/**
	 * Currency Type for Telr
	 */

	public static final String CURRENCY = "AED";

	/**
	 * Transaction Class for Payment
	 * 
	 */
	public static final String IVP_TRANS_CLASS_CONT = "cont";

	/**
	 * Payment Type create - to generate New Transaction check - to check the
	 * existing transaction status Value of IVP_METHOD_KEY
	 */

	public static final String IVP_METHOD_CREATE = "create";
	public static final String IVP_METHOD_CHECK = "check";

	/**
	 * Transaction Status Value
	 */
	public static final String TRANSACTION_AUTHORISED = "Authorised";

	/**
	 * get the protocol and authentication keys from application.conf file
	 * 
	 */
	/*
	 * public static Configuration configuration =
	 * Play.application().configuration();
	 * 
	 * //public static final String PROTOCOL =
	 * configuration.getString("payment.protocol"); // returns public static final
	 * String IVP_TEST = configuration.getString("payment.protocol");// 0 for live
	 * public static final String PAYMENT_TEST_AUTH_KEY =
	 * configuration.getString("payment.authkey").trim(); public static final String
	 * PAYMENT_PROD_AUTH_KEY =
	 * configuration.getString("payment.prod_authkey").trim(); public static final
	 * String HOST = configuration.getString("server.url").trim(); public static
	 * final String EMAIL = configuration.getString("server.email").trim(); public
	 * static final String SMS = configuration.getString("server.sms").trim();
	 * public static final String GOOGLE_API_KEY =
	 * configuration.getString("tobuz.google.key"); public static final String
	 * GOOGLE_SHORT_URL_API_KEY =
	 * configuration.getString("tobuz.google.shortUrlkey"); // public static final
	 * String HOST = "http://localhost:9000";
	 * 
	 * 
	 * public static String getPaymentDescription(AppUser appUser, TobuzPackage
	 * tobuzPackage,BusinessListing businessListing) { String paymentDescription =
	 * "Tobuz Payment"; if (tobuzPackage != null) { paymentDescription = "Tobuz | "+
	 * tobuzPackage.getTobuzPackageType()+" Package sale by the user "+appUser.
	 * getName()+" and "+appUser.getMobileNumber(); } if (businessListing != null) {
	 * paymentDescription =
	 * "Tobuz | distress sale payment for the listing "+businessListing.getTitle()
	 * +" by the user "+appUser.getName()+" and "+appUser.getMobileNumber(); }
	 * 
	 * return paymentDescription; }
	 * 
	 * 
	 * public static String getPaymentDescription(Payment payment,String
	 * paymentType) { String paymentDescription = "Tobuz Payment"; if
	 * (StringUtils.ignoreCase(paymentType, "new_package") &&
	 * payment.getTobuzPackage() != null) { paymentDescription = "Tobuz | "+
	 * payment.getTobuzPackage().getTobuzPackageType()+" Package sale by the user "
	 * +payment.getAppUser().getName()+" and "+payment.getAppUser().getMobileNumber(
	 * ); } if (StringUtils.ignoreCase(paymentType, "renew_package") &&
	 * payment.getTobuzPackage() != null && payment.getBusinessListing() != null) {
	 * paymentDescription =
	 * "Tobuz | Renew "+payment.getTobuzPackage().getTobuzPackageType()
	 * +" package for the listing "+payment.getBusinessListing().getListingId()
	 * +" - "+payment.getBusinessListing().getTitle()+" by the user "+payment.
	 * getAppUser().getName()+" and "+payment.getAppUser().getMobileNumber(); }
	 * 
	 * if (StringUtils.ignoreCase(paymentType, "renew_package") &&
	 * payment.getTobuzPackage() != null && payment.getBusinessAdvert() != null) {
	 * paymentDescription =
	 * "Tobuz | Renew "+payment.getTobuzPackage().getTobuzPackageType()
	 * +" package for the advert "+payment.getBusinessAdvert().getAdvertId()+" - "
	 * +payment.getBusinessAdvert().getTitle()+" by the user "+payment.getAppUser().
	 * getName()+" and "+payment.getAppUser().getMobileNumber(); } if
	 * (StringUtils.ignoreCase(paymentType, "distress_sale") &&
	 * payment.getBusinessListing() != null) { paymentDescription =
	 * "Tobuz | Distress sale payment for the listing "+payment.getBusinessListing()
	 * .getListingId()+", "+payment.getBusinessListing().getTitle()+" by the user "
	 * +payment.getAppUser().getName()+" and "+payment.getAppUser().getMobileNumber(
	 * ); }
	 * 
	 * return paymentDescription; }
	 */
}
