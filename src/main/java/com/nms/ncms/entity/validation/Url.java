/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights reserved.
 */
package com.nms.ncms.entity.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;

@Pattern.List({
    @Pattern(
            regexp = "^(?:ftp|http|https):\\/\\/(?:[\\w\\.\\-\\+]+:{0,1}[\\w\\.\\-\\+]*@)?(?:[a-z0-9\\-\\.]+)(?::[0-9]+)?(?:\\/|\\/(?:[\\w#!:\\.\\?\\+=&%@!\\-\\/\\(\\)]+)|\\?(?:[\\w#!:\\.\\?\\+=&%@!\\-\\/\\(\\)]+))?$",
            message = "{com.nms.vmm.eip.util.validation.url}"
    )
})
@Constraint(validatedBy = {})
@Documented
@Target({ElementType.METHOD,
    ElementType.FIELD,
    ElementType.ANNOTATION_TYPE,
    ElementType.CONSTRUCTOR,
    ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Url {

    String message() default "{com.nms.vmm.eip.util.validation.url}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({ElementType.METHOD,
        ElementType.FIELD,
        ElementType.ANNOTATION_TYPE,
        ElementType.CONSTRUCTOR,
        ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {

        Url[] value();
    }
}
