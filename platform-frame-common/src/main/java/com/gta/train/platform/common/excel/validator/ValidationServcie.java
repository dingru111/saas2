package com.gta.train.platform.common.excel.validator;

import java.util.Set;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gta.train.platform.common.excel.validator.exception.ValidationException;
 
//@Component
public class ValidationServcie {/*
    
    *//**
     * 使用hibernate的注解来进行验证
     * 
     *//*
	@Autowired
    private   Validator validator;

   
    *//**
     * 功能描述: <br>
     * 〈注解验证参数〉
     *
     * @param obj
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     *//*
    public   <T> void validate(T obj) {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(obj);
        // 抛出检验异常
        if (constraintViolations.size() > 0) {
            throw new ValidationException (String.format("参数校验失败:%s", constraintViolations.iterator().next().getMessage()));
        }
    }
*/}
