package main;

import configs.AppCtx;
import models.emp.Emp;
import models.emp.EmpDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//ENAME을 직원, JOB을 스테프로 수정
public class Correction {
    public static void main(String[] args){
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);

        EmpDao empDao = ctx.getBean(EmpDao.class);
        Emp emp= new Emp();
        emp.setENAME("직원");
        emp.setJOB("STAFF");
        long EMPNO = empDao.insert(emp);
        System.out.println(EMPNO);
    }
}
