package com.example.MerchantLocationApplication;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/merchants")
@AllArgsConstructor
public class MerchantController {

    private MerchantService merchantService;

    @GetMapping
    public List<Merchant> fetchAllMerchants(){
        return merchantService.getAllMerchants();
    }

    @GetMapping(value = "/findById/{id}")
    public Optional<Merchant> fetchById(@PathVariable String id) throws Exception {
        return merchantService.findById(id);
    }

    /** Creating a new Merchant Object. Accepts Merchant JSON body */
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Merchant registerNewMerchant (@RequestBody Merchant merchant) throws Exception {
        return merchantService.addNewMerchant(merchant);
    }

    /** Deleting a Merchant by path variable ID */
    @DeleteMapping(value = "/delete/{id}")
    public void deleteMerchant(@PathVariable String id) throws Exception {
        merchantService.deleteMerchant(id);
    }

    /** Update a Merchant by path variable ID */
    @PutMapping(value = "/update/{id}")
    public Merchant updateMerchant(@PathVariable String id, @RequestBody Merchant merchant) throws Exception {
        return merchantService.updateMerchant(id, merchant);
    }

    /** Get a list of the nearest merchants by latitude and longitude */
    @GetMapping(value = "/location")
    public List<Merchant> fetchAllMerchantsByNearestLocation(@RequestParam(required = true) Double latitude,
                                                             @RequestParam(required = true) Double longitude){
        return merchantService.getAllMerchantsByNearestLocation(latitude, longitude);
    }
}
