package com.example.MerchantLocationApplication;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MerchantRepository extends MongoRepository<Merchant, String> {
    //Merchant updateMyMerchant(String id, Merchant merchant);
}
