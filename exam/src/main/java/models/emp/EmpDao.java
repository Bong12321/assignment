package models.emp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EmpDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // EMP2 테이블은 DBEAVER에서 생성(CREATE TABLE EMP2();)
    // EMP2 테이블(사원번호, 사원이름, 직종)에 (2, 사원2, STAFF) 추가
    public void insert(){
        String sql = "INSERT INTO EMP2 (EMPNO, ENAME, JOB) VALUES (?, ?, ?)";
        int affectedRows = jdbcTemplate.update(sql, 2, "사원2", "STAFF");
        System.out.println(affectedRows); // 몇 개가 실행됐나 보여주기
    }

    // EMP2 테이블에 EMPNO가 no인 데이터 수정
    public long insert(Emp emp){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO EMP2(EMPNO, ENAME, JOB) VALUES(EMP2_SEQ.nextval, ? ,?)";
        jdbcTemplate.update(con -> {
            PreparedStatement pstmt = con.prepareStatement(sql, new String[]{"EMPNO"});
            pstmt.setString(1, emp.getENAME());
            pstmt.setString(2, emp.getJOB());

            return pstmt;
        }, keyHolder);

        Number key = keyHolder.getKey();
        long EMPNO = key.longValue();

        return EMPNO;
    }

    // EMP2 테이블에 EMPNO가 no인 데이터 삭제
    public void delete(int no){
        String sql = "DELETE FROM EMP2 WHERE EMPNO = ?";
        int affectedRows = jdbcTemplate.update(sql, no);
        System.out.println(affectedRows);// 몇 개가 실행됐나 보여주기
    }

    // EMP2 테이블 전체 조회
    public long getTotal(){
        String sql = "SELECT COUNT(*) FROM EMP";
        long count = jdbcTemplate.queryForObject(sql, Long.class);

        return count;
    }

    private Emp mapper(ResultSet rs, int i) throws SQLException{
        Emp emp = new Emp();
        emp.setEMPNO(rs.getLong("EMPNO"));
        emp.setENAME(rs.getString("ENAME"));
        emp.setJOB(rs.getString("JOB"));

        return emp;
    }

    // EMP2 테이블 조회
    public List<Emp> gets(){
        String sql = "SELECT * FROM EMP";
        List<Emp> emps = jdbcTemplate.query(sql, this::mapper);// 람다식

        return emps;
    }

}
