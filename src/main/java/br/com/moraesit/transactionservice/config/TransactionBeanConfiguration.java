package br.com.moraesit.transactionservice.config;

import br.com.moraesit.transactionservice.validator.TransactionValidation;
import br.com.moraesit.transactionservice.validator.impl.EmptyTransactionValidationBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnJava;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.system.JavaVersion;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransactionBeanConfiguration {

    @Bean
    @ConditionalOnMissingBean
    @Conditional(TransactionEnableNewerThanJavaFourTeen.class)
    public TransactionValidation emptyTransactionNewerThanJavaSevenTeenValidation() {
        return new EmptyTransactionValidationBean();
    }

    @ConditionalOnJava(range = ConditionalOnJava.Range.OLDER_THAN, value = JavaVersion.FOURTEEN)
    @Bean
    @ConditionalOnMissingBean
    @Conditional(TransactionEnableNewerThanJavaFourTeen.class)
    public TransactionValidation emptyTransactionJavaOlderThanFourTeenValidation() {
        return new EmptyTransactionValidationBean();
    }
}
