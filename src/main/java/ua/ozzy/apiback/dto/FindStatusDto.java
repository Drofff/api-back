package ua.ozzy.apiback.dto;

public class FindStatusDto {

    private String name;

    public FindStatusDto() {
    }

    public FindStatusDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
