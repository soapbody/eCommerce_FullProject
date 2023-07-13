package com.Mateus_Ulrich.eCommerce_FullProject.util;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
    public class ValidDateFormatValidator implements ConstraintValidator<ValidDateFormat, Date> {
        private String pattern;

        @Override
        public void initialize(ValidDateFormat constraintAnnotation) {
            this.pattern = constraintAnnotation.pattern();
        }

        @Override
        public boolean isValid(Date value, ConstraintValidatorContext context) {
            if (value == null)  {
                return false; // Permite valores nulos, se necessário
            }

            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            sdf.setLenient(false);

            try {
                String dateString = sdf.format(value);
                Date parsedDate = sdf.parse(dateString);
                return parsedDate.equals(value);
            } catch (ParseException e) {
                return false;
            }
        }
    }


