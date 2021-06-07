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

    @Around("@annotation(NeedModerRole)")
    public Object proceedIfModer(ProceedingJoinPoint joinPoint) throws Throwable {
        if (session.isModer()) {
            return joinPoint.proceed();
        }
        throw new NoAccessException("Moder");
    }

    @Around("@annotation(NeedAdminRole)")
    public Object proceedIfAdmin(ProceedingJoinPoint joinPoint) throws Throwable {
        if (session.isAdmin()) {
            return joinPoint.proceed();
        }
        throw new NoAccessException("Admin");
    }

}
