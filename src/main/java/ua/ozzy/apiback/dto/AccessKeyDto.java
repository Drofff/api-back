package ua.ozzy.apiback.dto;

public class AccessKeyDto {

    private final String accessKey;

    public AccessKeyDto(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getAccessKey() {
        return accessKey;
    }

}
