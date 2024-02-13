package hello.proxy.cglib;

import hello.proxy.cglib.code.TimeMethodInterceptor;
import hello.proxy.common.service.ConcreteService;
import hello.proxy.common.service.ServiceImpl;
import hello.proxy.common.service.ServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;

@Slf4j
public class CglibTest {

    /**
     * 구체 클래스 프록시
     */
    @Test
    void cglib() {
        ConcreteService target = new ConcreteService();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ConcreteService.class);
        enhancer.setCallback(new TimeMethodInterceptor(target));
        ConcreteService proxy = (ConcreteService) enhancer.create();
        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());
        proxy.call();
    }

    /**
     * 인터페이스 프록시
     */
    @Test
    void cglib2() {
        ServiceInterface target = new ServiceImpl();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ServiceInterface.class);
        enhancer.setCallback(new TimeMethodInterceptor(target));
        ServiceInterface proxy = (ServiceInterface) enhancer.create();
        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());
        proxy.find();
    }
}
