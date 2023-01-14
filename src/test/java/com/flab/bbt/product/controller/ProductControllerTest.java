package com.flab.bbt.product.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flab.bbt.AbstractContainerBaseTest;
import com.flab.bbt.ConfigureTestProfile;
import com.flab.bbt.product.domain.Product;
import com.flab.bbt.product.request.PriceTableRequest;
import com.flab.bbt.product.request.ProductRequest;
import com.flab.bbt.product.service.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ConfigureTestProfile
class ProductControllerTest extends AbstractContainerBaseTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductService productService;

    @Test
    @DisplayName("product가 생성되면 200 ok를 내려준다.")
    void addProductSuccessTest() throws Exception {
        // given
        ProductRequest productRequest = ProductRequest.builder()
            .name("test")
            .skuCode("testSku")
            .imgUrl("/test")
            .priceSale(10000)
            .priceDiscount(8000)
            .discountRate(20)
            .build();

        String content = objectMapper.writeValueAsString(productRequest);

        // when
        mockMvc.perform(post("/products")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            // then
            .andExpect(status().isOk());
    }

    @Test
    @DisplayName("product의 priceTable이 생성되면 200 ok를 내려준다.")
    void createPriceTableSuccessTest() throws Exception {
        // given
        Product product = getProduct();

        when(productService.findProductById(anyLong())).thenReturn(product);

        PriceTableRequest priceTableRequest = PriceTableRequest.builder()
            .groupSize(2)
            .discountPrice(8000)
            .targetPeriod(1)
            .build();

        String content = objectMapper.writeValueAsString(priceTableRequest);

        // when
        mockMvc.perform(post("/products/1/price-tables")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            // then
            .andExpect(status().isOk());
    }

    private Product getProduct() {
        return Product.builder()
            .id(1L)
            .name("test")
            .skuCode("testSku")
            .imgUrl("testUrl")
            .priceSale(2000)
            .priceDiscount(1000)
            .discountRate(50)
            .build();
    }

}