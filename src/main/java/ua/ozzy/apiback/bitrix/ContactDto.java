package ua.ozzy.apiback.bitrix;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ContactDto {

    private String name;

    private String secondName;

    private String lastName;

    private String opened;

    private String typeId;

    private String sourceId;

    private List<PhoneDto> phone = new ArrayList<>();

    public String getName() {
        return name;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getOpened() {
        return opened;
    }

    public String getTypeId() {
        return typeId;
    }

    public String getSourceId() {
        return sourceId;
    }

    public List<PhoneDto> getPhone() {
        return phone;
    }

    public static class Builder {

        private static final String DEFAULT_TYPE_ID = "CLIENT";
        private static final String DEFAULT_SOURCE_ID = "SELF";

        private final ContactDto contactDto = new ContactDto();

        public Builder firstName(String name) {
            contactDto.name = name;
            return this;
        }

        public Builder middleName(String middleName) {
            contactDto.secondName = middleName;
            return this;
        }

        public Builder lastName(String lastName) {
            contactDto.lastName = lastName;
            return this;
        }

        public Builder phoneNumber(String phoneNum) {
            PhoneDto mobPhone = PhoneDto.newMobilePhone(phoneNum);
            contactDto.phone = Collections.singletonList(mobPhone);
            return this;
        }

        public ContactDto build() {
            contactDto.opened = BitrixConstants.DEFAULT_OPENED;
            contactDto.typeId = DEFAULT_TYPE_ID;
            contactDto.sourceId = DEFAULT_SOURCE_ID;
            return contactDto;
        }

    }

}
