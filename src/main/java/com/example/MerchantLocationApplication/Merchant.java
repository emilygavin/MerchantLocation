package com.example.MerchantLocationApplication;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Merchant {
    @Id
    private String id;
    private String latitude;
    private String longitude;
    @Indexed(unique = true)
    private int merchantId;
    private String merchantName;

    public Merchant(String latitude, String longitude, int merchantId, String merchantName) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.merchantId = merchantId;
        this.merchantName = merchantName;
    }
}


