package orderngo.basedados;

import java.sql.SQLException;

/**
 *
 * @author grupo1
 */
public interface SavableInDatabase
{
    public void save() throws SQLException;
}
