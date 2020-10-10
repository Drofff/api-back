package ua.ozzy.apiback.dto;

public class CreateFeedbackRequestDto {

    private Short rate;

    private String comment;

    private CreateFeedbackRequestCustomerDto customer;

    public Short getRate() {
        return rate;
    }

    public void setRate(Short rate) {
        this.rate = rate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public CreateFeedbackRequestCustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CreateFeedbackRequestCustomerDto customer) {
        this.customer = customer;
    }

}
