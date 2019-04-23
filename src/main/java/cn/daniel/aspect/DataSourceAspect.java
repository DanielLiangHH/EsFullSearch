package cn.daniel.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import cn.daniel.interfaze.MultiDataSource;
import cn.daniel.utils.DataSourceUtil;

@Aspect
@Order(3)
@Component
public class DataSourceAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    // 切点
    @Pointcut("execution(* cn.daniel.service..*(..)))")
    public void pointcut() {
        logger.debug("切换数据源");
    }

    @Before("pointcut()")
    private void before(JoinPoint joinPoint) {
        Object target = joinPoint.getTarget();
        String method = joinPoint.getSignature().getName();
        Class<?> classz = target.getClass();
        Class<?>[] parameterTypes = ((MethodSignature) joinPoint.getSignature()).getMethod().getParameterTypes();
        try {
            Method m = classz.getMethod(method, parameterTypes);
            if (m != null && m.isAnnotationPresent(MultiDataSource.class)) {
                MultiDataSource data = m.getAnnotation(MultiDataSource.class);
                if (!data.value().equals(DataSourceUtil.getDataSource())) {
                    DataSourceUtil.putDataSource(data.value());
                    logger.info("使用数据库:" + data.value());
                } else {
                    logger.debug("使用数据库:" + data.value());
                }
            } else {
                logger.debug("不切换数据源");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
