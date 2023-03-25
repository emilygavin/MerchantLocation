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
    public Optional<Merchant> findMerchantById(@PathVariable String id) throws Exception {
        return merchantService.findById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Merchant registerNewMerchant (@RequestBody Merchant merchant) throws Exception {
        return merchantService.addNewMerchant(merchant);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteMerchant(@PathVariable String id) throws Exception {
        merchantService.deleteMerchant(id);
    }

    @PutMapping(value = "/update/{id}")
    public Merchant updateMerchant(@PathVariable String id, @RequestBody Merchant merchant) throws Exception {
        return merchantService.updateMerchant(id, merchant);
    }
}
