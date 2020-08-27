package mr.database.table;


import java.sql.ResultSetMetaData;
import java.sql.SQLException;


@FunctionalInterface
public interface SelectionConsumer
{
	void accept(ResultSetMetaData metaData, int columnCount) throws SQLException;
}