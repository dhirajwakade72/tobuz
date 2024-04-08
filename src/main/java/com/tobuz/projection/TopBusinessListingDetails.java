package com.tobuz.projection;

public interface TopBusinessListingDetails {
    String getFilePath();
    String getTitle();
    String getListingDescription();
    Double getTotalBusinessSalePrice();
    String getSuggestedTitle();
    Long getBusinessId();
    Boolean getAddedToFavourites();
}