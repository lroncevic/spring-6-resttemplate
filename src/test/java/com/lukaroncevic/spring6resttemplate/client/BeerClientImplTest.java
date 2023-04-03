package com.lukaroncevic.spring6resttemplate.client;

import com.lukaroncevic.spring6resttemplate.model.BeerDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BeerClientImplTest {

    @Autowired
    BeerClientImpl beerClient;

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