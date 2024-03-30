import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeptDAO extends DAO {

    public DeptDAO(Connection connect) {
        super(connect);
    }

    @Override
    public Dept find(int id) throws SQLException {
        PreparedStatement pstmt = connect.prepareStatement("SELECT * FROM Dept WHERE deptno = ?");
        pstmt.setInt(1, id);
        ResultSet result = pstmt.executeQuery();
        result.next();
        int deptno = result.getInt("deptno");
        String dname = result.getString("dname");
        String loc = result.getString("loc");
        return new Dept(deptno, dname, loc);
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
