package com.courtcanva.ccfranchise.validators;

import com.courtcanva.ccfranchise.annotations.AustralianNumber;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
public class AustralianNumberValidator implements ConstraintValidator<AustralianNumber, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext ctx) {

        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();

        try {
            Phonenumber.PhoneNumber phoneNumber = phoneNumberUtil.parse(value, "AU");

            return phoneNumberUtil.isValidNumberForRegion(phoneNumber, "AU");
        } catch (NumberParseException ex) {
            log.error(ex.getMessage());

            return false;
        }
    }
}
