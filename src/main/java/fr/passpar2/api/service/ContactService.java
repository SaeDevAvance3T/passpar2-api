package fr.passpar2.api.service;

import fr.passpar2.api.entity.ContactDao;
import fr.passpar2.api.repository.IContactRepository;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    private final IContactRepository contactRepository;

    public ContactService(IContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public ContactDao createContact(ContactDao contact) {
        return contactRepository.save(contact);
    }
}
