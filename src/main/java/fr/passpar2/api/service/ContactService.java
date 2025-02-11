package fr.passpar2.api.service;

import fr.passpar2.api.entity.ContactDao;
import fr.passpar2.api.entity.CustomerDao;
import fr.passpar2.api.repository.IContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    private final IContactRepository contactRepository;

    public ContactService(IContactRepository contactRepository) {
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
