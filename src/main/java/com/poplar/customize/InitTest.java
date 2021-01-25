package com.poplar.customize;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Select;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Create BY poplar ON 2020/11/18
 * 测试通过动态代理为接口创建对象
 * sql解析
 */

interface UserInterface {

    @Select("select * from user where id=#{id and name=#{name}")
    Object getUserById(Integer id, String name);

}

@Slf4j
public class InitTest {

    public static void main(String[] args) {
        UserInterface userProxy = (UserInterface) Proxy.newProxyInstance(InitTest.class.getClassLoader(), new Class<?>[]{UserInterface.class}, (proxy, method, args1) -> {
            Select annotation = method.getAnnotation(Select.class);
            if (annotation != null) {
                String value = annotation.value()[0];
                //构建参数
                log.info("参数={}", Arrays.asList(args1));//[1, 猫花]
                log.info("sql={}", value);
                Map<String, Object> map = buildParameters(method, args1);
                String sql = parseSql(value, map);
                System.out.println(sql);
            }
            return null;
        });
        userProxy.getUserById(1, "猫花");
    }

    //解析sql
    private static String parseSql(String sql, Map<String, Object> map) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < sql.length(); i++) {
            char charAt = sql.charAt(i);
            if (charAt == '#') {
                int nextIndex = i + 1;
                char c = sql.charAt(nextIndex);
                if (c != '{') {
                    throw new RuntimeException(String.format("此处的sql应该是#{sql=%s\nindex=%d\n", builder.toString(), nextIndex));
                }
                StringBuilder b = new StringBuilder();
                i = parsedSqlParams(sql, nextIndex, b);
                String paramName = b.toString();
                Object paramValue = map.get(paramName);
                if (paramValue == null) {
                    throw new RuntimeException(String.format("找不到参数值paramName=%s", paramName));
                }
                builder.append(paramValue.toString());
                continue;
            }
            builder.append(charAt);
        }
        return builder.toString();
    }

    private static int parsedSqlParams(String sql, int nextIndex, StringBuilder b) {
        nextIndex++;
        for (; nextIndex < sql.length(); nextIndex++) {
            char c = sql.charAt(nextIndex);
            if (c != '}') {
                b.append(c);
                continue;
            }
            if (c == '}') {
                return nextIndex;
            }
        }
        throw new RuntimeException(String.format("这里应该为sql}=%s\n index=%d\n", b.toString(), nextIndex));
    }

    //构建参数
    private static Map<String, Object> buildParameters(Method method, Object[] args) {
        //如果有多线程操作需求，可以换成CurrentHashMap
        Map<String, Object> paramsMap = new HashMap<>();
        Parameter[] parameters = method.getParameters();
        //循环可以换成Stream并行流操作
        for (int i = 0; i < parameters.length; i++) {
            paramsMap.put(parameters[i].getName(), args[i]);
        }
        return paramsMap;
    }
}
