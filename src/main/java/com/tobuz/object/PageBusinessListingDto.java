package com.tobuz.object;

import java.util.List;

public class PageBusinessListingDto {
    List<BusinessListingDTO> businessListingDTOList;
    int totalPages;
    int currentPage;
    int size;

    public PageBusinessListingDto() {
    }

    public PageBusinessListingDto(List<BusinessListingDTO> businessListingDTOS, int totalPages, int currentPage, int size) {
        this.businessListingDTOList = businessListingDTOS;
        this.totalPages = totalPages;
        this.currentPage = currentPage;
        this.size = size;
    }

    public List<BusinessListingDTO> getBusinessListingDTOS() {
        return businessListingDTOList;
    }

    public void setBusinessListingDTOS(List<BusinessListingDTO> businessListingDTOS) {
        this.businessListingDTOList = businessListingDTOS;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
