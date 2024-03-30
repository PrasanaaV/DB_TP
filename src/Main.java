import java.sql.SQLException;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) throws SQLException {

        Db.loadJdbcDriver();
        Connection connexion = Db.connectToDb();
        DeptDAO departmentDao = new DeptDAO(connexion);
        Dept dept20 = departmentDao.find(20);
        System.out.println(dept20.toString());
        /*if (connexion != null) {
            Db.displayDepartment(connexion);
            Db.moveDepartment(connexion,  7369, 30);
            Db.moveDepartmentPrepare(connexion,  7369, 30);
            Db.displayTable(connexion, "emp");
        }*/
        Db.closeDb(connexion);
    }
}