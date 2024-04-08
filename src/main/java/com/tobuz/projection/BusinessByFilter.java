package com.tobuz.projection;

public interface BusinessByFilter {
    String getFilePath();
    String getTitle();
    String getListingDescription();
    Double getPrice();
    String getSuggestedTitle();
    Long getId();

    Long getCountryId();

    String getStateId();
    String getCountryName();
    Boolean getAddedToFavourites();
}
