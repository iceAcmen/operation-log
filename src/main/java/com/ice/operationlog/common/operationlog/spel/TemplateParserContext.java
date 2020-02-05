package com.ice.operationlog.common.operationlog.spel;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class TemplateParserContext extends StandardEvaluationContext implements ParserContext {

    @Override
    public String getExpressionPrefix() {
        return "#{";
    }

    @Override
    public String getExpressionSuffix() {
        return "}";
    }

    @Override
    public boolean isTemplate() {
        return true;
    }
}