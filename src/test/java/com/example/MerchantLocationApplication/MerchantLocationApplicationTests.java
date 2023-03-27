package com.example.MerchantLocationApplication;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class MerchantLocationApplicationTests {

	@Autowired
	MerchantController merchantController;

//	@Test
//	public void testFindAllMerchants(){
//		List<Merchant> merchants = merchantController.fetchAllMerchants();
//
//		assertThat(merchants).isEqualTo(merchantController.fetchAllMerchants());
//	}

	@Test
	public void testCreateNewMerchantLatitudeTooBigError() throws Exception {
		Merchant merchant = new Merchant();
		Coordinates coordinates = new Coordinates(55.0, -6.0);

		merchant.setMerchantName("Testing123");
		merchant.setMerchantId(11);
		merchant.setCoordinates(coordinates);

		Exception exception = assertThrows(Exception.class, () -> {
			merchantController.registerNewMerchant(merchant);
		});
		String expectedMessage = "Latitude too big! (should be between 51.37 and 54.4902)";
		String actualMessage = exception.getMessage();
		assertEquals(expectedMessage, actualMessage);
	}

    @Test
    public void testCreateNewMerchantLatitudeTooSmallError() throws Exception {
        Merchant merchant = new Merchant();
        Coordinates coordinates = new Coordinates(50.0, -6.0);

        merchant.setMerchantName("Testing123");
        merchant.setMerchantId(11);
        merchant.setCoordinates(coordinates);

        Exception exception = assertThrows(Exception.class, () -> {
            merchantController.registerNewMerchant(merchant);
        });
        String expectedMessage = "Latitude too small! (should be between 51.37 and 54.4902)";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testCreateNewMerchantLongitudeTooBigError() throws Exception {
        Merchant merchant = new Merchant();
        Coordinates coordinates = new Coordinates(53.0, -4.0);

        merchant.setMerchantName("Testing123");
        merchant.setMerchantId(11);
        merchant.setCoordinates(coordinates);

        Exception exception = assertThrows(Exception.class, () -> {
            merchantController.registerNewMerchant(merchant);
        });
        String expectedMessage = "Invalid Longitude: should be between -5.42 and -10.70";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testCreateNewMerchantLongitudeTooSmallError() throws Exception {
        Merchant merchant = new Merchant();
        Coordinates coordinates = new Coordinates(53.0, -11.0);

        merchant.setMerchantName("Testing123");
        merchant.setMerchantId(11);
        merchant.setCoordinates(coordinates);

        Exception exception = assertThrows(Exception.class, () -> {
            merchantController.registerNewMerchant(merchant);
        });
        String expectedMessage = "Invalid Longitude: should be between -5.42 and -10.70";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testDeleteMerchantError() throws Exception {
        Merchant merchant = new Merchant();

        merchant.setId("thisIdDoesNotExist");

        Exception exception = assertThrows(Exception.class, () -> {
            merchantController.deleteMerchant(merchant.getId());
        });
        String expectedMessage = "Merchant with this ID does not exist!";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }
}
