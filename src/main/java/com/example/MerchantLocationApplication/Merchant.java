package com.example.MerchantLocationApplication;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Merchant{
    @Id
    private String id;
    private Coordinates coordinates;
    @Indexed(unique = true)
    private int merchantId;
    private String merchantName;

    public Merchant() {
    }

    public Merchant(Coordinates coordinates, int merchantId, String merchantName) {
        this.coordinates = coordinates;
        this.merchantId = merchantId;
        this.merchantName = merchantName;
    }
}


