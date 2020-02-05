package com.ice.operationlog.spel;

import org.junit.Test;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.Assert;

import java.util.Objects;
import java.util.Properties;

public class SpelTest {

    @Test
    public void helloWorld(){
        SpelExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression("('Hello ' + 'World').concat(#end)");
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setVariable("end", "!");
        Object value = expression.getValue(context);
        Assert.isTrue("Hello World!".equals(value), value.toString());
    }

    @Test
    public void testBeanExpression() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext();
        ctx.refresh();
        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setBeanResolver(new BeanFactoryResolver(ctx));
        Properties result1 = parser.parseExpression("@systemProperties").getValue(context, Properties.class);
        System.out.println(result1);
        Assert.isTrue(Objects.equals(System.getProperties(), result1), result1.toString());
    }
}
