package com.artemiysaltsin.restaurants;

import com.artemiysaltsin.restaurants.model.EmailToken;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class EmailTokenValidationTest {

    private static Validator validator;

    static {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.usingContext().getValidator();
    }

    @Test
    public void testValid() {

        final EmailToken person = new EmailToken("23678.876@gmail2. com4", "fgdsf");
        Set<ConstraintViolation<EmailToken>> validates = validator.validate(person);
        if (validates.size() > 0)
            validates.stream().map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
        else
            System.out.println("Email valid!");

    }

}
