package com.flab.bbt.deal.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flab.bbt.AbstractContainerBaseTest;
import com.flab.bbt.ConfigureTestProfile;
import com.flab.bbt.deal.domain.Deal;
import com.flab.bbt.deal.domain.DealStatus;
import com.flab.bbt.deal.request.DealRequest;
import com.flab.bbt.deal.service.DealService;
import com.flab.bbt.product.domain.PriceTable;
import com.flab.bbt.product.domain.Product;
import com.flab.bbt.product.service.ProductService;
import java.time.LocalDateTime;
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
class DealControllerTest extends AbstractContainerBaseTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductService productService;

    @MockBean
    private DealService dealService;

    @Test
    @DisplayName("deal이 생성되면 200 ok를 내려준다.")
    void createDealSuccessTest() throws Exception {
        // given
        PriceTable priceTable = getPriceTable();
        Deal deal = getDeal();

        when(productService.findPriceTableByProductId(anyLong())).thenReturn(priceTable);
        when(dealService.createDeal(priceTable.convertToDealEntity())).thenReturn(deal);

        DealRequest dealRequest = DealRequest.builder()
            .productId(priceTable.getProductId())
            .build();

        String content = objectMapper.writeValueAsString(dealRequest);

        // when
        mockMvc.perform(post("/deals")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            // then
            .andExpect(status().isOk());
    }

    @Test
    @DisplayName("deal id로 해당하는 deal을 조회한다.")
    void getDealSuccessTest() throws Exception {
        // given
        when(dealService.findDealById(anyLong())).thenReturn(getDeal());

        // when
        mockMvc.perform(get("/deals/1")
                .accept(MediaType.APPLICATION_JSON))
            // then
            .andExpect(status().isOk())
            .andExpect(jsonPath("data.productId", is(1)))
            .andExpect(jsonPath("data.groupSize", is(2)))
            .andExpect(jsonPath("data.discountPrice", is(1000)))
            .andExpect(jsonPath("data.status", is("CREATED")))
            .andExpect(jsonPath("data.participantCount", is(0)))
            .andExpect(jsonPath("data.isPrivate", is(false)));
    }

    private Product getProduct() {
        return Product.builder()
            .id(1L)
            .name("test")
            .skuCode("testSku")
            .imgUrl("testUrl")
            .build();
    }

    private PriceTable getPriceTable() {
        return PriceTable.builder()
            .id(1L)
            .dealCapacity(3)
            .priceDiscount(8000)
            .priceSale(10000)
            .dealValidPeriodInDays(1)
            .productId(1L)
            .isDealPrivate(false)
            .build();
    }

    private Deal getDeal() {
        return Deal.builder()
            .id(1L)
            .productId(1L)
            .groupSize(2)
            .discountPrice(1000)
            .status(DealStatus.CREATED)
            .participantCount(0)
            .createdAt(LocalDateTime.now().minusHours(22))
            .expiredAt(LocalDateTime.now().plusHours(2))
            .closedAt(LocalDateTime.now().minusHours(2))
            .isPrivate(false)
            .build();
    }
}