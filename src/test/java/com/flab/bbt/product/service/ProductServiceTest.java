package com.flab.bbt.product.service;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
//    private ProductRepository productRepository = new ProductRepositoryImpl();
//    private ProductService productService = new ProductService(productRepository);

    @InjectMocks
    private ProductService productService;
    @Mock
    private ProductRepositoryImpl productRepository;

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
    }

    @Test
    @DisplayName("제품 등록시 성공적으로 저장소에 저장된다.")
    void registerSuccessTest(){
        // given
        when(productRepository.findBySerialNum(anyString())).thenReturn(Optional.empty());
        when(productRepository.save(any(Product.class))).thenReturn(product);

        // when
        productService.register(product);

        // then
        verify(productRepository).save(product);
    }


    @Test
    @DisplayName("같은 SerialNum의 제품이 등록될 경우 제품 등록에 실패한다.")
    void registerProductWithDplicatedSerialNumTest(){
        // given
        when(productRepository.findBySerialNum(anyString())).thenReturn(Optional.ofNullable(product));

        // when
        CustomException e = assertThrows(CustomException.class,
                () -> productService.register(product));

        // then
        assertThat(e.getErrorCode().getMessage()).isEqualTo("이미 존재하는 상품입니다.");
    }

    @Test
    @DisplayName("id로 제품 조회시 해당하는 제품이 성공적으로 조회된다.")
    void findProductByIdSuccessTest() {
        // given
        Long test_id = 1L;
        when(productRepository.findById(test_id)).thenReturn(Optional.ofNullable(product));

        // when
        Product result = productService.findProductById(test_id);

        // then
        assertThat(result.getSerialNum()).isEqualTo("SN00001");
        verify(productRepository).findById(test_id);
    }

    @Test
    @DisplayName("존재하지 않는 id로 조회 시 PRODUCT_NOT_FOUND 예외가 발생한다.")
    void findProductByNonExistIdTest() {
        // given
        Long test_id = 1L;
        when(productRepository.findById(test_id)).thenReturn(Optional.empty());

        // when
        CustomException e = assertThrows(CustomException.class,
                () -> productService.findProductById(test_id));

        // then
        assertThat(e.getErrorCode().getMessage()).isEqualTo("상품을 찾지 못했습니다.");
    }

    @Test
    @DisplayName("제품 전체 조회시 전체 제품이 성공적으로 조회된다.")
    void findProductsTest() {
        // given
        when(productRepository.findAll()).thenReturn(getProductList());

        // when
        List<Product> products = productService.findProducts();

        // then
        assertThat(products.size()).isEqualTo(2);
    }

    private Product createAnotherProduct() {
        return Product.builder()
                .name("test2")
                .serialNum("SN00002")
                .imgUrl("url")
                .priceSale(20000)
                .priceDiscount(18000)
                .discountRate(10)
                .build();
    }

    private List<Product> getProductList() {
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        productList.add(createAnotherProduct());

        return productList;
    }
}