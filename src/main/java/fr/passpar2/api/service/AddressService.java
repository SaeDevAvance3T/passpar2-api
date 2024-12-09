package fr.passpar2.api.service;

import fr.passpar2.api.entity.AddressDao;
import fr.passpar2.api.repository.IAddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    private final IAddressRepository addressRepository;

    public AddressService(IAddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<AddressDao> getAllAddresses() {
        return addressRepository.findAll();
    }

    public AddressDao getAddressById(int id) {
        Optional<AddressDao> addressOptional = addressRepository.findById(id);
        return addressOptional.orElseThrow(() -> new IllegalArgumentException("Adresse non trouvée avec l'ID: " + id));
    }

    public AddressDao createAddress(AddressDao address) {
        return addressRepository.save(address);
    }

    public AddressDao updateAddress(int id, AddressDao updatedAddress) {
        AddressDao existingAddress = getAddressById(id);

        existingAddress.setStreet(updatedAddress.getStreet());
        existingAddress.setCity(updatedAddress.getCity());
        existingAddress.setPostalCode(updatedAddress.getPostalCode());
        existingAddress.setCountry(updatedAddress.getCountry());
        existingAddress.setComplement(updatedAddress.getComplement());

        return addressRepository.save(existingAddress);
    }

    public void deleteAddress(int id) {
        AddressDao address = getAddressById(id);
        addressRepository.delete(address);
    }
}
