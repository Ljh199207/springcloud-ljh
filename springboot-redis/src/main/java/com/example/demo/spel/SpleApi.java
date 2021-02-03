package com.example.demo.spel;

import com.example.demo.spel.bean.Inventor;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.GregorianCalendar;

public class SpleApi {

/*
    public static void main(String[] args) {
        //expression();
        //javabean();
        beanName();
    }*/


    public static void expression() {
        ExpressionParser expressionParser = new SpelExpressionParser();
        Expression expression = expressionParser.parseExpression("'Hello World'");
        String s = (String) expression.getValue();

        //使用方法
        expression = expressionParser.parseExpression("'Hello World'.split('')");
        String[] s1 = (String[]) expression.getValue();
        for (String s2 : s1) {
            System.out.println(s2);
        }
    }


    public static void javabean() {
        ExpressionParser expressionParser = new SpelExpressionParser();
        Expression expression = expressionParser.parseExpression("'Hello World'.bytes");
  /*      byte[] bytes = (byte[]) expression.getValue();
        for (byte s : bytes) {
            System.out.println(s);
        }
        //公共方法也可以被访问到
        expression = expressionParser.parseExpression("'Hello World'.bytes.length");
        int length = (int) expression.getValue();
        System.out.println(length);*/

        //String的构造函数
        expression = expressionParser.parseExpression("new String('Hello World').toUpperCase()");
        String s = expression.getValue(String.class);
        System.out.println(s);

    }


    public static void beanName() {
        GregorianCalendar c = new GregorianCalendar();
        c.set(1856, 7, 9);
        Inventor tesla = new Inventor("Nikola Tesla", c.getTime(), "Serbian");
        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression("name");
        EvaluationContext context = new StandardEvaluationContext(tesla);
        String name = (String) expression.getValue(context);
        System.out.println(name);
    }

}
