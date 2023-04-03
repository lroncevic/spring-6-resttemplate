package com.lukaroncevic.spring6resttemplate.client;

import com.lukaroncevic.spring6resttemplate.model.BeerDTO;
import com.lukaroncevic.spring6resttemplate.model.BeerStyle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BeerClientImplTest {

    @Autowired
    BeerClientImpl beerClient;


    @Test
    void updateBeer() {

        BeerDTO newDto = BeerDTO.builder()
                .price(new BigDecimal("10.99"))
                .beerName("Mongo Bobs 2")
                .beerStyle(BeerStyle.IPA)
                .quantityOnHand(500)
                .upc("123456")
                .build();

        BeerDTO beerDTO = beerClient.createBeer(newDto);

        final String newName = "Mongo Bobs 3";
        beerDTO.setBeerName(newName);
        BeerDTO updatedBeer = beerClient.updateBeer(beerDTO);

        assertEquals(newName, updatedBeer.getBeerName());
    }

    @Test
    void createBeer() {

        BeerDTO newDto = BeerDTO.builder()
                .price(new BigDecimal("10.99"))
                .beerName("Mongo Bobs")
                .beerStyle(BeerStyle.IPA)
                .quantityOnHand(500)
                .upc("123456")
                .build();

        BeerDTO savedDto = beerClient.createBeer(newDto);
        assertNotNull(savedDto);
    }

    @Test
    void getBeerById() {

        Page<BeerDTO> beerDTOPage = beerClient.listBeers();

        BeerDTO dto = beerDTOPage.getContent().get(0);

        BeerDTO byId = beerClient.getBeerById(dto.getId());

        assertNotNull(byId);
    }

    @Test
    void listBeersNoBeerName() {

        beerClient.listBeers(null, null, null, null,null);

    }

    @Test
    void listBeers() {

        beerClient.listBeers("ALE", null, null, null, null);

    }
}