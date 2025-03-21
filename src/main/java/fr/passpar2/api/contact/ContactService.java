package fr.passpar2.api.contact;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public ContactDao createContact(String firstName, String lastName, String phone) {
        ContactDao newContact = new ContactDao();
        newContact.setFirstName(firstName);
        newContact.setLastName(lastName);
        newContact.setPhone(phone);

        return contactRepository.save(newContact);
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
