package com.main;

import org.apache.ibatis.executor.statement.PreparedStatementHandler;
import org.apache.ibatis.executor.statement.SimpleStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.Configuration;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Properties;

@Intercepts({
        @Signature(type = StatementHandler.class, method = "parameterize", args = Statement.class)
})
public class CustomPlugin2 implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("CustomPlugin2:intercept:" + invocation.getMethod());
        Object target = invocation.getTarget();
//        MetaObject metaObject = SystemMetaObject.forObject(target);
//        Integer id = (Integer)metaObject.getValue("parameterHandler.parameterObject");
//        System.out.println("id:" + id);
//        metaObject.setValue("parameterHandler.parameterObject", 2);

        MetaObject metaObject = SystemMetaObject.forObject(target);
        StatementHandler statementHandler = (StatementHandler)metaObject.getValue("delegate");
        // 实际StatementHandler类型是预处理或普通，则执行
        if (statementHandler instanceof PreparedStatementHandler) {
//            获取参数对象，用反射设置值
//            Object company = metaObject.getValue("parameterHandler.parameterObject");

            // 获取参数对象，用Mybatis的API设置值
            Configuration configuration = (Configuration) metaObject.getValue("delegate.configuration");
            MetaObject metaObject1 = configuration.newMetaObject(metaObject.getValue("parameterHandler.parameterObject"));
            SqlCommandType sqlCommandType = (SqlCommandType) metaObject.getValue("parameterHandler.mappedStatement.sqlCommandType");
            if (sqlCommandType == SqlCommandType.INSERT) {
                metaObject1.setValue("name", "aa");
            } else if (sqlCommandType == SqlCommandType.UPDATE) {
                metaObject1.setValue("name", "bb");
            }
        }

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
