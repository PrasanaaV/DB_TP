import java.sql.*;

public class EmpDAO extends DAO{

    public EmpDAO(Connection connect) {
        super(connect);
    }

    // Recursion - Q11

    @Override
    public Emp find(int id) throws SQLException {
        PreparedStatement pstmt = connect.prepareStatement("SELECT mgr FROM Emp WHERE empno = ?");
        pstmt.setInt(1, id);
        ResultSet result = pstmt.executeQuery();
        result.next();
        int mgr = result.getInt("mgr");
        pstmt = connect.prepareStatement("SELECT * FROM emp WHERE empno = ?");
        pstmt.setInt(1, mgr);
        result = pstmt.executeQuery();
        result.next();
        if (result.next()) {
            mgr = result.getInt("mgr");
            if (mgr != 0) {
                return fromResultSet(result);
            }
        }
        return null;
    }

    public Emp fromResultSet(ResultSet result) throws SQLException {
        Long empno = result.getLong("empno");
        String ename = result.getString("ename");
        String efirst = result.getString("efirst");
        String job = result.getString("job");
        int mgr = result.getInt("mgr");
        Emp empMGR = find(mgr);
        java.sql.Date hiredate = result.getDate("hiredate");
        int sal = result.getInt("sal");
        int comm = result.getInt("comm");
        int tel = result.getInt("tel");
        int deptno = result.getInt("deptno");
        DeptDAO deptDao = new DeptDAO(connect);
        Dept department = deptDao.find(deptno);
        return new Emp(empno, ename, efirst, job, empMGR, hiredate, sal, comm, tel, department);
    }

    @Override
    public boolean create(Object object) {
        return false;
    }

    @Override
    public boolean update(Object object) {
        return false;
    }

    @Override
    public boolean delete(Object object) {
        return false;
    }


}
