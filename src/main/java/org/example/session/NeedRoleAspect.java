package org.example.session;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class NeedRoleAspect {

    final Session session;

    public NeedRoleAspect(Session session) {
        this.session = session;
    }

    @Around("@annotation(NeedCashierRole)")
    public Object proceedIfCashier(ProceedingJoinPoint joinPoint) throws Throwable {
        if (session.cashier()) {
            return joinPoint.proceed();
        }
        throw new NoAccessException("Cashier");
    }

    @Around("@annotation(NeedAdminRole)")
    public Object proceedIfAdmin(ProceedingJoinPoint joinPoint) throws Throwable {
        if (session.admin()) {
            return joinPoint.proceed();
        }
        throw new NoAccessException("Admin");
    }

}
