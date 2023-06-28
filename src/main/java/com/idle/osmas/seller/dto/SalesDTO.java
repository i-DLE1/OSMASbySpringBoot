package com.idle.osmas.seller.dto;

public class SalesDTO {

    private int no;
    private String title;
    private String content;
    private String registDate;
    private String startDate;
    private String endDate;
    private int targetAmount;
    private int currentAmount;
    private int views;
    private int refMemberNo;
    private int refCategoryNo;

    public SalesDTO() {
    }

    public SalesDTO(int no, String title, String content, String registDate, String startDate, String endDate, int targetAmount, int currentAmount, int views, int refMemberNo, int refCategoryNo) {
        this.no = no;
        this.title = title;
        this.content = content;
        this.registDate = registDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.targetAmount = targetAmount;
        this.currentAmount = currentAmount;
        this.views = views;
        this.refMemberNo = refMemberNo;
        this.refCategoryNo = refCategoryNo;
    }

    public int getNo() {
        return no;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getRegistDate() {
        return registDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public int getTargetAmount() {
        return targetAmount;
    }

    public int getCurrentAmount() {
        return currentAmount;
    }

    public int getViews() {
        return views;
    }

    public int getRefMemberNo() {
        return refMemberNo;
    }

    public int getRefCategoryNo() {
        return refCategoryNo;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setRegistDate(String registDate) {
        this.registDate = registDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setTargetAmount(int targetAmount) {
        this.targetAmount = targetAmount;
    }

    public void setCurrentAmount(int currentAmount) {
        this.currentAmount = currentAmount;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public void setRefMemberNo(int refMemberNo) {
        this.refMemberNo = refMemberNo;
    }

    public void setRefCategoryNo(int refCategoryNo) {
        this.refCategoryNo = refCategoryNo;
    }

    @Override
    public String toString() {
        return "SalseDTO{" +
                "no=" + no +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", registDate='" + registDate + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", targetAmount=" + targetAmount +
                ", currentAmount=" + currentAmount +
                ", views=" + views +
                ", refMemberNo=" + refMemberNo +
                ", refCategoryNo=" + refCategoryNo +
                '}';
    }
}
