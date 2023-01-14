package com.flab.bbt.deal.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.flab.bbt.AbstractContainerBaseTest;
import com.flab.bbt.ConfigureTestProfile;
import com.flab.bbt.deal.domain.Deal;
import com.flab.bbt.deal.domain.DealStatus;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@ConfigureTestProfile
class DealRepositoryTest extends AbstractContainerBaseTest {

    @Autowired
    private DealRepository dealRepository;

    private Deal savedDeal;

    @BeforeEach
    public void setUp() {
        savedDeal = dealRepository.save(buildDeal());
    }

    @Test
    @DisplayName("저장소에 성공적으로 저장된다.")
    void saveSuccessTest() {
        // then
        assertThat(savedDeal.getId()).isNotNull();
    }

    @Test
    @DisplayName("deal id로 성공적으로 조회된다.")
    void findByIdSuccessTest() {
        // when
        Optional<Deal> foundDeal = dealRepository.findById(savedDeal.getId());

        // then
        assertThat(foundDeal.get().getId()).isEqualTo(savedDeal.getId());
    }

    private Deal buildDeal() {
        return Deal.builder()
            .productId(1L)
            .groupSize(2)
            .discountPrice(1000)
            .status(DealStatus.CREATED)
            .participantCount(0)
            .isPrivate(false)
            .build();
    }
}