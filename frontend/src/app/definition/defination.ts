export class Defination {

    public static readonly HOST="http://103.204.131.33:8080/tobuz/api";    
    //public static readonly HOST="http://localhost:8080/tobuz/api";
    public static readonly RECNET_BUSINESS_LISTING="/get_recent_business_listing";
    public static readonly GET_BUSINESS_LISTING_WITH_PAGE="/get_business_listing";
    public static readonly GET_BUSINESS_LISTING_BY_TYPE_WITH_PAGE="/get_business_listing_with_type";
    public static readonly GET_BUSINESS_LISTING_BY_CATEGORIES_WITH_PAGE="/get/business_listing_by_categories";
    public static readonly GET_SEARCH_WITH_PAGE="/business/search";
    public static readonly GET_SEARCH_ADVERT_WITH_PAGE="/advert/search";
    public static readonly GET_SEARCH_SERVICE_WITH_PAGE="/business_service/search";
    public static readonly GET_BUSINESS_SERVICE_BY_ID="/business_service";
    public static readonly GET_COUNTRY_LIST="/get/list/countries"; 
    public static readonly GET_STATE_BY_COUNTRY_ID="/get/state";    
    public static readonly GET_CATEGORIES="/get/all/categies";    
    public static readonly GET_BUSINESS_SERVICE_TYPE="/get/all/business_service_type";
    public static readonly POST_FILE_UPLOAD="/upload";
    public static readonly GET_EMAIL_EXIST="/user/check_mail_exist";
    public static readonly GET_USER_MENU="/user/menu";
    public static readonly POST_LOGIN_USER="/user/login";
    public static readonly POST_REGISTER_USER="/user/register";
    public static readonly POST_FORGOT_PASSWORD="/user/forgotPassword";
    public static readonly POST_SEND_OPT="/user/sendOpt";
    public static readonly POST_SUMBIT_SELLER_CONTACT="/business/saveContact";
    public static readonly POST_SUMBIT_BUSINESS_CONTACT="/business/saveBusinessContact";
    public static readonly PSOT_BROKER_SEARCH="/broker/search";
    public static readonly POST_ADD_TESTIMONIAL="/business/addTestimonial";
    public static readonly POST_ADD_FAVORITE_LISTING="/business/manageFavouriteBusiness";
    public static readonly POST_ADD_LISTING="/business/addListing";
    public static readonly GET_ALL_MY_LISTING="/get/my-listing";
    public static readonly DRAFT_LISTING_STATUS="DRAFTED";
    public static readonly UNDERVIEW_LISTING_STATUS="UNDER_REVIEW";

    public static readonly BUSINESS_TYPE="BUSINESS";
    public static readonly ADVERT_TYPE="ADVERTS";

    public static readonly MOBILE_MIN_LENGTH=10;
    public static readonly MOBILE_MAX_LENGTH=14;    

}
