package orderngo.basedados;

import java.sql.SQLException;

/**
 *
 * @author grupo1
 */
public interface DeletableInDatabase
{
    public void delete() throws SQLException;
}
