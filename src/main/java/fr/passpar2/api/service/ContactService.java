package fr.passpar2.api.service;

import fr.passpar2.api.entity.ContactDao;
import fr.passpar2.api.repository.IContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    private final IContactRepository contactRepository;

    public ContactService(IContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public ContactDao createContact(ContactDao contact) {
        return contactRepository.save(contact);
    }

    public List<ContactDao> getAllContacts() {
        return contactRepository.findAll();
    }

    public List<ContactDao> getContactsByCustomerId(int id) {
        return contactRepository.findAllByCustomerId(id);
    }

    public void deleteContact(ContactDao contact) {
        contactRepository.delete(contact);
    }
}
