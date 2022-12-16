package com.flab.bbt.common;

import static com.flab.bbt.common.PageConst.*;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Pageable {
    private final int page;
    private final int size;
}
