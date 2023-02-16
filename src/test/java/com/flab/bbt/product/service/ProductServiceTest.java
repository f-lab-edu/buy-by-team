package com.flab.bbt.product.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.flab.bbt.exception.CustomException;
import com.flab.bbt.product.domain.PriceTable;
import com.flab.bbt.product.domain.Product;
import com.flab.bbt.product.repository.ProductRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    Long product_id = 1L;
    @InjectMocks
    private ProductService productService;
    @Mock
    private ProductRepository productRepository;

    Product product;

    @BeforeEach
    public void setUp() {
        product = Product.builder()
            .name("test")
            .skuCode("SN00001")
            .imgUrl("url")
            .build();
    }

    @Test
    @DisplayName("제품 등록시 성공적으로 저장소에 저장된다.")
    void registerSuccessTest() {
        // given
        when(productRepository.findBySkuCode(anyString())).thenReturn(Optional.empty());
        when(productRepository.save(any(Product.class))).thenReturn(product);

        // when
        productService.register(product);

        // then
        verify(productRepository).save(product);
    }


    @Test
    @DisplayName("같은 SerialNum의 제품이 등록될 경우 제품 등록에 실패한다.")
    void registerProductWithDplicatedSerialNumTest() {
        // given
        when(productRepository.findBySkuCode(anyString())).thenReturn(Optional.ofNullable(product));

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
        assertThat(result.getSkuCode()).isEqualTo("SN00001");
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
        when(productRepository.findListWithPagination(PageRequest.of(0, 10))).thenReturn(
            getProductList());

        // when
        List<Product> products = productService.findProducts(PageRequest.of(0, 10));

        // then
        assertThat(products.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("유효한 제품 ID가 주어졌을 때, 해당 제품으로 생성할 수 있는 팀 구매에 대한 정보(할인가격, 목표 인원, 마감 기한, 공개 여부)를 조회할 수 있다")
    void findPriceTableByProductIdTest() {
        // given
        when(productRepository.findPriceTableByProductId(product_id)).thenReturn(
            Optional.ofNullable(createPriceTable(product_id)));

        // when
        PriceTable priceTable = productService.findPriceTableByProductId(product_id);

        // then
        assertThat(priceTable.getDealCapacity()).isEqualTo(2);
    }

    @Test
    @DisplayName("제품 ID가 주어졌을 때, 해당 제품으로 생성할 수 있는 팀 구매에 대한 정보(할인가격, 목표 인원, 마감 기한, 공개 여부)를 조회할 수 있다")
    void findPriceTableByProductId_ExceptionTest() {
        // given
        when(productRepository.findPriceTableByProductId(product_id)).thenReturn(Optional.empty());

        // when
        CustomException e = assertThrows(CustomException.class,
            () -> productService.findPriceTableByProductId(product_id));

        // then
        assertThat(e.getErrorCode().getCode()).isEqualTo(8000);
    }

    private Product createAnotherProduct() {
        return Product.builder()
            .name("test2")
            .skuCode("SN00002")
            .imgUrl("url")
            .build();
    }

    private List<Product> getProductList() {
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        productList.add(createAnotherProduct());

        return productList;
    }

    private PriceTable createPriceTable(Long productId) {
        return PriceTable.builder()
            .productId(productId)
            .dealCapacity(2)
            .discountPrice(10000)
            .isDealPrivate(false)
            .dealValidPeriodInDays(1)
            .startDate(LocalDateTime.of(2023, 1, 29, 0, 0))
            .endDate(LocalDateTime.of(2023, 1, 30, 0, 0))
            .build();
    }
}