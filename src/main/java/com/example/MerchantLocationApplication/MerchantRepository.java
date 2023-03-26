package com.example.MerchantLocationApplication;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MerchantRepository extends MongoRepository<Merchant, String> {
}
