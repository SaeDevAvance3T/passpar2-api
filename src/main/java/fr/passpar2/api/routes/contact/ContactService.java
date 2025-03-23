package fr.passpar2.api.routes.contact;

import fr.passpar2.api.routes.contact.dto.ContactRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public ContactDao createContact(String firstName, String lastName, String phone) {
        ContactDao contact = new ContactDao();
        contact.setFirstName(firstName);
        contact.setLastName(lastName);
        contact.setPhone(phone);

        return contactRepository.save(contact);
    }

    public ContactDao updateContactById(Integer id, ContactRequestDto request) {
        ContactDao contactToUpdate = getContactById(id);
        contactToUpdate.setFirstName(request.getFirstName());
        contactToUpdate.setLastName(request.getLastName());
        contactToUpdate.setPhone(request.getPhone());

        return contactRepository.save(contactToUpdate);
    }

    public List<ContactDao> getAllContacts() {
        return contactRepository.findAll();
    }

    public ContactDao getContactById(int id) {
        return contactRepository.getById(id);
    }

    public void deleteContact(ContactDao contact) {
        contactRepository.delete(contact);
    }

    public void saveContact(ContactDao contact) {
        contactRepository.save(contact);
    }
}
