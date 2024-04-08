package com.tobuz.object;

import java.io.Serializable;
import java.util.List;

public class PageBrokerListDto implements Serializable {

    List<BrokerListingDTO> brokerListingDTOS;
    int totalPages;
    int currentPage;
    int size;


    public PageBrokerListDto() {
    }

    public PageBrokerListDto(List<BrokerListingDTO> brokerListingDTOS, int totalPages, int currentPage, int size) {
        this.brokerListingDTOS = brokerListingDTOS;
        this.totalPages = totalPages;
        this.currentPage = currentPage;
        this.size = size;
    }

    public List<BrokerListingDTO> getBrokerListingDTOS() {
        return brokerListingDTOS;
    }

    public void setBrokerListingDTOS(List<BrokerListingDTO> brokerListingDTOS) {
        this.brokerListingDTOS = brokerListingDTOS;
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
