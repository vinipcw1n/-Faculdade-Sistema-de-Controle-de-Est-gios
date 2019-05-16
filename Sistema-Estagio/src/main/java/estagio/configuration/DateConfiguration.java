package estagio.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.number.NumberFormatAnnotationFormatterFactory;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;

@Configuration
public class DateConfiguration {

    @Bean
    public FormattingConversionService conversionService() {

        DefaultFormattingConversionService cs = new DefaultFormattingConversionService(false);

        cs.addFormatterForFieldAnnotation(new NumberFormatAnnotationFormatterFactory());

        DateFormatterRegistrar reg = new DateFormatterRegistrar();
        reg.setFormatter(new DateFormatter("dd/MM/yyyy"));
        reg.registerFormatters(cs);

        return cs;
    }
}
