package fr.passpar2.api.contact.dto;

import fr.passpar2.api.contact.ContactDao;
import fr.passpar2.api.response.IFullResponse;

public class ContactDto extends ContactBaseDto implements IFullResponse<ContactBaseDto> {

    public ContactDto() {
        super();
    }

    public ContactDto(ContactDao model) {
        super(model);
    }

}