import java.sql.Connection;
import java.sql.SQLException;

public abstract class DAO<T> {
    protected Connection connect;

    public DAO(Connection connect) {
        this.connect = connect;
    }

    public abstract T find(int id) throws SQLException;

    public abstract boolean create(T object);

    public abstract boolean update(T object);

    public abstract boolean delete(T object);


}

