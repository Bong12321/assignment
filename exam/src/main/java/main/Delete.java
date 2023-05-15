package main;

import configs.AppCtx;
import models.emp.EmpDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Delete {
    public static void main(String[] args){
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);

        EmpDao empDao = ctx.getBean(EmpDao.class);
        // no(EMPNO)가 2인 데이터를 삭제
        empDao.delete(2);

        ctx.close();
    }
}
