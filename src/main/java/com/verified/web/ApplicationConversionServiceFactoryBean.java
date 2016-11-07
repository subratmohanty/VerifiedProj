package com.verified.web;

import com.verified.UserRole;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;

@Configurable
/**
 * A central place to register application converters and formatters. 
 */
public class ApplicationConversionServiceFactoryBean extends FormattingConversionServiceFactoryBean {

	@Override
	protected void installFormatters(FormatterRegistry registry) {
		super.installFormatters(registry);
		// Register application converters and formatters
	}

	public Converter<UserRole, String> getUserRoleToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.verified.UserRole, java.lang.String>() {
            public String convert(UserRole userRole) {
                return new StringBuilder().append(userRole.getRoleName()).toString();
            }
        };
    }

	public Converter<Long, UserRole> getIdToUserRoleConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.verified.UserRole>() {
            public com.verified.UserRole convert(java.lang.Long id) {
                return UserRole.findUserRole(id);
            }
        };
    }

	public Converter<String, UserRole> getStringToUserRoleConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.verified.UserRole>() {
            public com.verified.UserRole convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), UserRole.class);
            }
        };
    }

	public void installLabelConverters(FormatterRegistry registry) {
        registry.addConverter(getUserRoleToStringConverter());
        registry.addConverter(getIdToUserRoleConverter());
        registry.addConverter(getStringToUserRoleConverter());
    }

	public void afterPropertiesSet() {
        super.afterPropertiesSet();
        installLabelConverters(getObject());
    }
}
