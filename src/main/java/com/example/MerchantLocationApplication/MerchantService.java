package com.example.MerchantLocationApplication;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class MerchantService {

    private final MerchantRepository merchantRepository;

    public List<Merchant> getAllMerchants() {
        return merchantRepository.findAll();
    }

    public Merchant addNewMerchant(Merchant merchant) throws Exception {
        return merchantRepository.insert(merchant);
    }

    public void deleteMerchant(String id) throws Exception {
        if(!existsById(id)){
            throw new Exception("Merchant with this ID does not exist!");
        }
        else {
            merchantRepository.deleteById(id);
        }
    }

    public boolean existsById(String id){
        return merchantRepository.existsById(id);
    }

    public Merchant updateMerchant(String id, Merchant merchant) {
        return merchantRepository.save(merchant);
    }

    public Optional<Merchant> findById(String id) throws Exception {
        Optional<Merchant> merchant = merchantRepository.findById(id);
        if(merchant.isEmpty()){
            throw new Exception("No merchant with this id are in the database.");
        }
        else{
            return merchant;
        }
    }
}
