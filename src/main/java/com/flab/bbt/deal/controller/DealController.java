package com.flab.bbt.deal.controller;

import com.flab.bbt.common.CommonResponse;
import com.flab.bbt.common.SessionConst;
import com.flab.bbt.deal.domain.Deal;
import com.flab.bbt.deal.request.DealRequest;
import com.flab.bbt.deal.service.DealService;
import com.flab.bbt.product.domain.Product;
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
import org.springframework.web.bind.annotation.PutMapping;
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
        Product product = productService.findProductById(dealRequest.getProductId());
        Deal deal = dealService.createDeal(dealRequest.converToEntity(product.getId()));

        return CommonResponse.success(deal);
    }

    @GetMapping("/{id}")
    public CommonResponse getDeal(@PathVariable long id) {
        Deal deal = dealService.findDealById(id);
        return CommonResponse.success(deal);
    }

    @PatchMapping("/{dealId}")
    public CommonResponse participateDeal(@PathVariable Long dealId) {
        // in progress
        dealService.incrementParticipantCount(dealId);
        return CommonResponse.success();
    }


}
