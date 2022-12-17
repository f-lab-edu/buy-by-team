//package com.flab.bbt.common.config;
//
//import static com.flab.bbt.common.PageConst.*;
//import static java.lang.Math.min;
//import static org.springframework.util.StringUtils.hasText;
//
//import com.flab.bbt.common.Pageable;
//import com.flab.bbt.exception.CustomException;
//import com.flab.bbt.exception.ErrorCode;
//import org.springframework.core.MethodParameter;
//import org.springframework.web.bind.support.WebDataBinderFactory;
//import org.springframework.web.context.request.NativeWebRequest;
//import org.springframework.web.method.support.HandlerMethodArgumentResolver;
//import org.springframework.web.method.support.ModelAndViewContainer;
//
//public class PageableHandlerMethodResolver implements HandlerMethodArgumentResolver {
//
//    @Override
//    public boolean supportsParameter(MethodParameter parameter) {
//        return parameter.getParameterType().equals(Pageable.class);
//    }
//
//    @Override
//    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
//        NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
//        String pageStr = webRequest.getParameter(DEFAULT_PAGE_PARAM);
//        String sizeStr = webRequest.getParameter(DEFAULT_SIZE_PARAM);
//
//        int page = parseAndCheckUpperLimit(pageStr, Integer.MAX_VALUE, DEFAULT_PAGE_NUM);
//        int size = parseAndCheckUpperLimit(sizeStr, DEFAULT_MAX_SIZE, DEFAULT_SIZE_NUM);
//
//        page = adjustPage(page, size);
//
//        return new Pageable(page, size);
//    }
//
//    private int parseAndCheckUpperLimit(String param, int upperLimit, int defaultNum) {
//        if (hasText(param)) {
//            try {
//                int parsed = min(Integer.parseInt(param), upperLimit);
//                return parsed < 1 ? defaultNum : parsed;
//            } catch (NumberFormatException e) {
//                throw new CustomException(ErrorCode.INVALID_INPUT);
//            }
//        } else {
//            return defaultNum;
//        }
//    }
//
//    /*
//    파라미터로 넘어오는 page는 1부터 시작이나 query를 날릴 때에는 1을 뺀 0부터 가져온다.
//    ex) select * from products limit 0, 10 -> 0번째 부터 10개 가져오기
//    또한 1을 뺀 값에 size만큼 곱해줘야 한다. 만약 2페이지 10개 항목 가져오려면 (2-1)*10
//    ex) select * from products limit 10, 10
//     */
//    private int adjustPage(int page, int size) {
//        return (page-1)*size;
//    }
//}
