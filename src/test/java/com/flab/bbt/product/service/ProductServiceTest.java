package com.flab.bbt.product.service;

import com.flab.bbt.exception.CustomException;
import com.flab.bbt.product.domain.Product;
import com.flab.bbt.product.repository.ProductRepository;
import com.flab.bbt.product.repository.ProductRepositoryImpl;
import com.flab.bbt.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    private ProductRepository productRepository = new ProductRepositoryImpl();
    private ProductService productService = new ProductService(productRepository);

    Product product;
    Product product2;

    @BeforeEach
    public void setUp() {
        product = Product.builder()
                         .name("test")
                         .serialNum("SN00001")
                         .imgUrl("url")
                         .priceSale(10000)
                         .priceDiscount(9000)
                         .discountRate(10)
                         .build();

        product2 = Product.builder()
                .name("test2")
                .serialNum("SN00002")
                .imgUrl("url")
                .priceSale(20000)
                .priceDiscount(18000)
                .discountRate(10)
                .build();
    }

    @Test
    @DisplayName("제품 등록시 성공적으로 저장소에 저장된다.")
    void registerSuccessTest(){
        // given
        // when
        productService.register(product);

        // then
        assertThat(productRepository.findById(product.getId()).get()).isEqualTo(product);
    }

    @Test
    @DisplayName("같은 SerialNum의 제품이 등록될 경우 제품 등록에 실패한다.")
    void registerProductWithDplicatedSerialNumTest(){
        // given
        // when
        productService.register(product);

        CustomException e = assertThrows(CustomException.class,
                () -> productService.register(product));

        // then
        assertThat(e.getErrorCode().getMessage()).isEqualTo("이미 존재하는 상품입니다.");
    }

    @Test
    @DisplayName("id로 제품 조회시 해당하는 제품이 성공적으로 조회된다.")
    void findProductByIdSuccessTest() {
        // given
        productService.register(product);

        // when
        Product result = productService.findProductById(product.getId());

        // then
        assertThat(result.getId()).isEqualTo(product.getId());
        assertThat(result.getSerialNum()).isEqualTo(product.getSerialNum());
    }

    @Test
    @DisplayName("존재하지 않는 id로 조회 시 PRODUCT_NOT_FOUND 예외가 발생한다.")
    void findProductByNonExistIdTest() {
        // given
        productService.register(product);

        // when
        Product result = productService.findProductById(product.getId());
        CustomException e = assertThrows(CustomException.class,
                () -> productService.findProductById(100L));

        // then
        assertThat(e.getErrorCode().getMessage()).isEqualTo("상품을 찾지 못했습니다.");
    }

    @Test
    @DisplayName("제품 전체 조회시 전체 제품이 성공적으로 조회된다.")
    void findProductsTest() {
        // given
        productService.register(product);
        productService.register(product2);

        // when
        List<Product> products = productService.findProducts();

        // then
        assertThat(products.size()).isEqualTo(2);
    }


}