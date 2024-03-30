import java.sql.*;

public class Db {

    static void loadJdbcDriver() {
        /* Load JDBC Driver. */

        try {
            Class.forName( "org.postgresql.Driver" );
        } catch ( ClassNotFoundException e ) {
            e.getException();
        }
    }

    static Connection connectToDb() {
        String url = "jdbc:postgresql://localhost/p";
        String user = "p";
        String pass = "";
        Connection connexion;
        try {
            connexion = DriverManager.getConnection( url, user, pass );

            System.out.println("Bdd Connected");
            return connexion;
        } catch ( SQLException e ) {
            e.getErrorCode();
            return null;
        }
    }

    static void closeDb(Connection connexion) {
        if (connexion != null) {
            try {
                connexion.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // Exercice 1
    public static void displayDepartmentAndLocation(Connection connexion) throws SQLException {
        Statement statement = connexion.createStatement();
        ResultSet resultat = statement.
                executeQuery( "SELECT deptno, dname, loc FROM dept;" );

        while ( resultat.next() ) {
            int deptno = resultat.getInt( "deptno");
            String dname = resultat.getString( "dname" );

            System.out.println("Department " + deptno + " is for "
                    + dname + " and located in ? ");
        }
        resultat.close();
    }

    // Exercice 2
    public static void moveDepartment(Connection connexion, int empno, int newDeptno) throws SQLException {
        Statement statement = connexion.createStatement();
        int affectedEmp = statement.
                executeUpdate( "UPDATE emp SET deptno = ".concat(String.valueOf(newDeptno)) + "WHERE empno = ".concat(String.valueOf(empno)));
        if (affectedEmp > 0) {
            System.out.println("Update successful, " + affectedEmp + " rows affected.");
        } else {
            System.out.println("Update failed, no rows affected.");
        }
    }


    public static void displayTable(Connection connexion, String tableName) throws SQLException {
        Statement statement = connexion.createStatement();
        ResultSet resultat = statement.
                executeQuery( "SELECT * FROM".concat(tableName));
        while ( resultat.next() ) {
            System.out.println(resultat);
        }
    }

    public static void moveDepartmentPrepare(Connection connexion, int empno, int newDeptno) throws SQLException {
        PreparedStatement pstmt = connexion.prepareStatement("UPDATE emp SET deptno = ? WHERE empno = ?");
        pstmt.setInt(1, newDeptno);
        pstmt.setInt(2, empno);
        int affectedEmp = pstmt.executeUpdate();
        if (affectedEmp > 0) {
            System.out.println("Update successful, " + affectedEmp + " rows affected.");
        } else {
            System.out.println("Update failed, no rows affected.");
        }
        pstmt.close();
    }

    public static void displayTablePrepareNotFix(Connection connexion, String tableName) throws SQLException {

        PreparedStatement pstmt = connexion.prepareStatement("SELECT * FROM  ?");
        ResultSet result = pstmt.executeQuery();
        result.next();
        System.out.println(result);
    }

    //public static void displayTablePrepareFix(Connection connexion, String tableName) throws SQLException {
    //     String query = "SELECT * FROM ".concat(tableName);
    //     PreparedStatement pstmt = connexion.prepareStatement(query);
    //     ResultSet resultSet = pstmt.executeQuery();
    //     result.next();
    //     System.out.println(resultSet.getInt("empno"));
    // }

}
