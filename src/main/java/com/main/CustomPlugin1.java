package com.main;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Statement;
import java.util.Properties;

@Intercepts({
        @Signature(type = StatementHandler.class, method = "parameterize", args = Statement.class)
})
public class CustomPlugin1 implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("CustomPlugin1:intercept:" + invocation.getMethod());
        Object target = invocation.getTarget();
        MetaObject metaObject = SystemMetaObject.forObject(target);
        Integer id = (Integer)metaObject.getValue("parameterHandler.parameterObject");
        System.out.println("id:" + id);
        metaObject.setValue("parameterHandler.parameterObject", 2);
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        System.out.println("CustomPlugin1:plugin");
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
