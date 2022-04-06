package ua.ozzy.apiback.bitrix;

public class DealDto {

    private String title;

    private String typeId;

    private String stageId;

    private Long contactId;

    private String opened;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public String getOpened() {
        return opened;
    }

    public void setOpened(String opened) {
        this.opened = opened;
    }

    public static class Builder {

        private static final String DEFAULT_STAGE_ID = "NEW";
        private static final String DEFAULT_TYPE_ID = "SALE";

        private final DealDto dealDto = new DealDto();

        public Builder title(String title) {
            dealDto.title = title;
            return this;
        }

        public Builder defaultStage() {
            return stageId(DEFAULT_STAGE_ID);
        }

        public Builder stageId(String stageId) {
            dealDto.stageId = stageId;
            return this;
        }

        public Builder contactId(Long contactId) {
            dealDto.contactId = contactId;
            return this;
        }

        public DealDto build() {
            dealDto.opened = BitrixConstants.DEFAULT_OPENED;
            dealDto.typeId = DEFAULT_TYPE_ID;
            return dealDto;
        }

    }

}
