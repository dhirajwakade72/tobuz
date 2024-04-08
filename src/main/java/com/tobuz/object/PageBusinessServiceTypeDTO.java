package com.tobuz.object;

import java.util.List;

public class PageBusinessServiceTypeDTO {
    List<BusinessServiceTypeDTO> businessServiceTypeDTOS;
    int totalPages;
    int currentPage;
    int size;

    public PageBusinessServiceTypeDTO(List<BusinessServiceTypeDTO> businessServiceTypeDTOS, int totalPages, int currentPage, int size) {
        this.businessServiceTypeDTOS = businessServiceTypeDTOS;
        this.totalPages = totalPages;
        this.currentPage = currentPage;
        this.size = size;
    }

    public PageBusinessServiceTypeDTO() {
    }

    public List<BusinessServiceTypeDTO> getBusinessServiceTypeDTOS() {
        return businessServiceTypeDTOS;
    }

    public void setBusinessServiceTypeDTOS(List<BusinessServiceTypeDTO> businessServiceTypeDTOS) {
        this.businessServiceTypeDTOS = businessServiceTypeDTOS;
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
