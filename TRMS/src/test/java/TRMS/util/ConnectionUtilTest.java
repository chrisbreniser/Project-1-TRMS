package TRMS.util;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ConnectionUtilTest {

	private ConnectionUtil connectionUtil = new ConnectionUtil();

	@Test
	public void createConnectionTest() throws SQLException {
		Connection conn = connectionUtil.createConnection();
		conn.close();
	}
}
