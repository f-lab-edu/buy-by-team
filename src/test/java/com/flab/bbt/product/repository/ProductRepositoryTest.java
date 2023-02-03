package com.flab.bbt.product.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.flab.bbt.AbstractContainerBaseTest;
import com.flab.bbt.ConfigureTestProfile;
import com.flab.bbt.deal.domain.Deal;
import com.flab.bbt.product.domain.PriceTable;
import com.flab.bbt.product.domain.Product;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
@ConfigureTestProfile
class ProductRepositoryTest extends AbstractContainerBaseTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    @DisplayName("저장소에 성공적으로 저장된다.")
    void saveSuccessTest() {
        // given
        Product product = buildProductWithSkuCode("testSku1");
        Product savedProduct = productRepository.save(product);


        // then
        assertThat(savedProduct.getId()).isNotNull();
        assertThat(product.getName()).isEqualTo(savedProduct.getName());
        assertThat(product.getSkuCode()).isEqualTo(savedProduct.getSkuCode());
    }

    @Test
    @DisplayName("product id로 성공적으로 조회된다.")
    void findByIdSuccessTest() {
        // given
        Product savedProduct = productRepository.save(buildProductWithSkuCode("testSku2"));

        // when
        Optional<Product> foundProduct = productRepository.findById(savedProduct.getId());

        // then
        assertThat(foundProduct.get().getId()).isEqualTo(savedProduct.getId());
    }

    @Test
    @DisplayName("skuCode로 성공적으로 조회된다.")
    void findBySkuCodeSuccessTest() {
        // given
        String skuCode = "testSku3";
        Product savedProduct = productRepository.save(buildProductWithSkuCode(skuCode));

        // when
        Optional<Product> foundProduct = productRepository.findBySkuCode(skuCode);

        // then
        assertThat(foundProduct.get().getSkuCode()).isEqualTo(skuCode);
    }

    @Test
    @DisplayName("price table이 저장소에 성공적으로 저장된다.")
    void savePriceTableSuccessTest() {
        // given
        PriceTable priceTable = buildPriceTable();

        // when
        PriceTable savedPriceTable = productRepository.savePriceTable(buildPriceTable());

        // then
        assertThat(savedPriceTable.getId()).isNotNull();
        assertThat(priceTable.getProductId()).isEqualTo(savedPriceTable.getProductId());
        assertThat(priceTable.getDealCapacity()).isEqualTo(savedPriceTable.getDealCapacity());
        assertThat(priceTable.getDiscountPrice()).isEqualTo(savedPriceTable.getDiscountPrice());
        assertThat(priceTable.getDealValidPeriodInDays()).isEqualTo(savedPriceTable.getDealValidPeriodInDays());
    }

    private Product buildProductWithSkuCode(String skuCode) {
        return Product.builder()
            .name("test")
            .skuCode(skuCode)
            .imgUrl("/testUrl")
            .build();
    }

    private PriceTable buildPriceTable() {
        return PriceTable.builder()
            .productId(1L)
            .dealCapacity(2)
            .discountPrice(1000)
            .dealValidPeriodInDays(1)
            .build();
    }
}