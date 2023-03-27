package com.example.MerchantLocationApplication;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MerchantService {

    private final MerchantRepository merchantRepository;

    /**
     * Min/Max Latitude/Longitudes of Ireland
     */
    private double maxLatitude = 54.4902;
    private double minLatitude = 51.37;
    private double maxLongitude = -5.42;
    private double minLongitude = -10.70;

    public List<Merchant> getAllMerchants() {
        return merchantRepository.findAll();
    }

    /**
     * Get a list of the nearest merchants by latitude and longitude.
     * Uses the Comparator import to compare the received values to the values within the array.
     */
    public List<Merchant> getAllMerchantsByNearestLocation(double latitude, double longitude) {
        List<Merchant> merchants = merchantRepository.findAll();
        merchants.sort(Comparator.comparing(m -> distance(latitude, longitude, m.getCoordinates().getLatitude(), m.getCoordinates().getLongitude())));
        return merchants;
    }

    /**
     * Uses the Haversine formula to calculate the smallest distance from the received console values
     * to the values that are within the object array. Returns a distance values in km.
     */
    private double distance(double lat1, double lon1, double lat2, double lon2) {
        double radius = 6378; // earth radius in km
        double lat = Math.toRadians(lat2 - lat1);
        double lon = Math.toRadians(lon2 - lon1);
        /** Haversine Formula */
        double a = Math.sin(lat / 2) * Math.sin(lat / 2) + Math.cos(Math.toRadians(lat1)) *
                Math.cos(Math.toRadians(lat2)) * Math.sin(lon / 2) * Math.sin(lon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return radius * c;
    }

    public Merchant addNewMerchant(Merchant merchant) throws Exception {
        if (merchant.getCoordinates().getLatitude() < maxLatitude ){
            if (merchant.getCoordinates().getLatitude() > minLatitude){
                if (merchant.getCoordinates().getLongitude() > minLongitude){
                    if (merchant.getCoordinates().getLongitude() < maxLongitude) {
                        return merchantRepository.insert(merchant);
                    }
                    else { throw new Exception("Invalid Longitude: should be between -5.42° and -10.70°"); }
                }
                else{ throw new Exception("Invalid Longitude: should be between -5.42° and -10.70°"); }
            }
            else { throw new Exception("Latitude too small! (should be between 51.37° and 54.4902°)"); }
        }
        else{ throw new Exception("Latitude too big! (should be between 51.37° and 54.4902°)"); }
    }

    /** Uses error checking to ensure the Merchant Exists by ID before deletion. */
    public void deleteMerchant(String id) throws Exception {
        /** Boolean check for ID */
        if(!merchantRepository.existsById(id)){
            throw new Exception("Merchant with this ID does not exist!");
        }
        else {
            merchantRepository.deleteById(id);
        }
    }

    public Merchant updateMerchant(Merchant merchant) {
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
