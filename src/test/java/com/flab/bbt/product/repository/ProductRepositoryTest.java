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

    private Product savedProduct;

    @BeforeEach
    public void setUp() {
        Product product = buildProduct();
        savedProduct = productRepository.save(product);
    }

    @AfterEach
    public void clean() {
        productRepository.delete(savedProduct.getId());
    }

    @Test
    @DisplayName("저장소에 성공적으로 저장된다.")
    void saveSuccessTest() {
        // then
        assertThat(savedProduct.getId()).isNotNull();
    }

    @Test
    @DisplayName("product id로 성공적으로 조회된다.")
    void findByIdSuccessTest() {
        // when
        Optional<Product> foundProduct = productRepository.findById(savedProduct.getId());

        // then
        assertThat(foundProduct.get().getId()).isEqualTo(savedProduct.getId());
    }

    @Test
    @DisplayName("skuCode로 성공적으로 조회된다.")
    void findBySkuCodeSuccessTest() {
        // when
        Optional<Product> foundProduct = productRepository.findBySkuCode("testSku");

        // then
        assertThat(foundProduct.get().getSkuCode()).isEqualTo("testSku");
    }

    @Test
    @DisplayName("price table이 저장소에 성공적으로 저장된다.")
    void savePriceTableSuccessTest() {
        // when
        PriceTable savedPriceTable = productRepository.savePriceTable(buildPriceTable());

        // then
        assertThat(savedPriceTable.getId()).isEqualTo(1L);
    }

    private Product buildProduct() {
        return Product.builder()
            .name("test")
            .skuCode("testSku")
            .imgUrl("testUrl")
            .priceSale(2000)
            .priceDiscount(1000)
            .discountRate(50)
            .build();
    }

    private PriceTable buildPriceTable() {
        return PriceTable.builder()
            .id(1L)
            .productId(savedProduct.getId())
            .groupSize(2)
            .discountPrice(1000)
            .targetPeriod(1)
            .build();
    }
}