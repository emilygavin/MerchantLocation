package com.example.MerchantLocationApplication;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class MerchantService {

    private final MerchantRepository merchantRepository;

    public List<Merchant> getAllMerchants() {
        return merchantRepository.findAll();
    }

    /** Get a list of the nearest merchants by latitude and longitude.
     * Uses the Comparator import to compare the received values to the values within the array. */
    public List<Merchant> getAllMerchantsByNearestLocation(double latitude, double longitude) {
        List<Merchant> merchants = merchantRepository.findAll();
        merchants.sort(Comparator.comparing(m -> distance(latitude, longitude, m.getLatitude(), m.getLongitude())));
        return merchants;
    }

    /** Uses the Haversine formula to calculate the smallest distance from the received console values
     * to the values that are within the object array. Returns a distance values in km. */
    private double distance(double lat1, double lon1, double lat2, double lon2) {
        double radius = 6378; // earth radius
        double lat = Math.toRadians(lat2 - lat1);
        double lon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(lat / 2) * Math.sin(lat / 2) + Math.cos(Math.toRadians(lat1)) *
                Math.cos(Math.toRadians(lat2)) * Math.sin(lon / 2) * Math.sin(lon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return radius * c;
    }

    public Merchant addNewMerchant(Merchant merchant) throws Exception {
        return merchantRepository.insert(merchant);
    }

    /** Uses error checking to ensure the Merchant Exists by ID before deletion. */
    public void deleteMerchant(String id) throws Exception {
        if(!existsById(id)){
            throw new Exception("Merchant with this ID does not exist!");
        }
        else {
            merchantRepository.deleteById(id);
        }
    }

    /** Boolean check for ID */
    public boolean existsById(String id){
        return merchantRepository.existsById(id);
    }

    public Merchant updateMerchant(String id, Merchant merchant) {
        return merchantRepository.save(merchant);
    }

    /** Uses the Optional<> Class to find if a merchant exists before returning an object. */
    public Optional<Merchant> findById(String id) throws Exception {
        Optional<Merchant> merchant = merchantRepository.findById(id);
        if(merchant.isEmpty()){
            throw new Exception("No merchants with this ID are in the database!");
        }
        else{
            return merchant;
        }
    }
}
