package com.itheima.validation;

import com.itheima.anno.State;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


//<给那个注解提供校验规则，校验的数据类型>
public class StateValidation implements ConstraintValidator<State, String> {
    //value,将来要校验的数据
    //return:如果false校验不通过，true：校验通过
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        //提供校验规则
        if (value == null) {
            return false;
        }
        if(value.equals("已发布")||value.equals("草稿")){
            return true;
        }
        return false;
    }
}
