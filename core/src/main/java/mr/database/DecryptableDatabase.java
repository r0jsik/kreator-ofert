package mr.database;


public interface DecryptableDatabase extends Database
{
	void decrypt(String password);
}