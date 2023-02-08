package com.flab.bbt.deal.controller;

import com.flab.bbt.aop.LoginCheck;
import com.flab.bbt.common.CommonResponse;
import com.flab.bbt.common.SessionConst;
import com.flab.bbt.deal.domain.Deal;
import com.flab.bbt.deal.domain.Participant;
import com.flab.bbt.deal.request.DealRequest;
import com.flab.bbt.deal.service.DealService;
import com.flab.bbt.product.domain.PriceTable;
import com.flab.bbt.product.service.ProductService;
import com.flab.bbt.user.domain.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/deals")
public class DealController {

    private final DealService dealService;
    private final ProductService productService;

    @PostMapping()
    public CommonResponse createDeal(@Valid @RequestBody DealRequest dealRequest) {
        PriceTable priceTable = productService.findPriceTableByProductId(
            dealRequest.getProductId());
        Deal deal = dealService.createDeal(priceTable.convertToDealEntity());
        return CommonResponse.success(deal);
    }

    @GetMapping("/{id}")
    public CommonResponse getDeal(@PathVariable long id) {
        Deal deal = dealService.findDealById(id);
        return CommonResponse.success(deal);
    }

    @LoginCheck
    @PatchMapping("/{dealId}/participate")
    public CommonResponse participateDeal(@PathVariable Long dealId,
        HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute(SessionConst.COOKIE_SESSION_ID);

        // TODO("deal에 이미 참여한 유저라면 참여 불가능하도록 처리")

        Deal updatedDeal = dealService.participateDeal(dealId,
            Participant.toParticipant(dealId, user.getId()));
        return CommonResponse.success(updatedDeal);
    }
}
