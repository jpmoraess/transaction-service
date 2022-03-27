package br.com.moraesit.transactionservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.system.JavaVersion;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.stereotype.Component;

@Component
public class TransactionEnableNewerThanJavaFourTeen implements Condition {

    @Value("${transaction.validation.enabled:true}")
    private  boolean isTransactionEnabled;

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return !isTransactionEnabled && JavaVersion.getJavaVersion().isEqualOrNewerThan(JavaVersion.FOURTEEN);
    }
}
