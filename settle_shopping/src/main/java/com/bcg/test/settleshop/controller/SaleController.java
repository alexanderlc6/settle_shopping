package com.bcg.test.settleshop.controller;

import com.bcg.test.settleshop.bean.dto.OrderRequestDto;
import com.bcg.test.settleshop.bean.vo.ResponseVO;
import com.bcg.test.settleshop.enums.SystemErrorCodeEnum;
import com.bcg.test.settleshop.service.SaleService;
import com.bcg.test.settleshop.utils.BeanValidators;
import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Map;

/**
 * Created by alexlu on 2019/6/13.
 */
@RestController
@RequestMapping("/sales")
public class SaleController extends BaseController {
    @Autowired
    SaleService saleService;

    Validator validator = Validation.byProvider(HibernateValidator.class).configure().buildValidatorFactory().getValidator();


    /**
     * Buy sku items with order dealing process
     * @param orderRequestDto
     * @return
     */
    @PostMapping("/buy")
    public ResponseVO buy(@RequestBody @Validated OrderRequestDto orderRequestDto){
        Map<String, String> constraintAttrs = BeanValidators.validate(validator, orderRequestDto);
        if (constraintAttrs != null && !constraintAttrs.isEmpty()) {
            return getFailureWithMap(SystemErrorCodeEnum.ILLEGAL_PARAMETER.getCode(), constraintAttrs);
        }

        return getFromData(saleService.buy(orderRequestDto));
    }
}
