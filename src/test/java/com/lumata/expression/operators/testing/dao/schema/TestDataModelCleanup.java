package com.lumata.expression.operators.testing.dao.schema;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.database.MysqlUtils;
import com.lumata.common.testing.exceptions.DataModelException;
import com.lumata.common.testing.exceptions.EnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.model.SchemaMetadata;
import com.lumata.common.testing.system.Environment;

public class TestDataModelCleanup {

	private static final Logger logger = LoggerFactory.getLogger(TestDataModelCreation.class);

	private Environment env;
	private String tenant;
	private Mysql mysql;
	private SchemaMetadata originalSchemaMetadata;

	@Parameters({ "environment", "tenant" })
	@BeforeClass
	public void init(@Optional("E4O_VM") String environment, @Optional("tenant") String tenant) throws EnvironmentException {

		env = new Environment("input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE);
		this.tenant = tenant;
		this.mysql = new Mysql(env.getDataSource(tenant));
	}

	@BeforeClass
	public void setup() throws SQLException {
		logger.info("making database snapshot");
		this.originalSchemaMetadata = getSchemaMetadata();
	}

	@AfterClass
	public void cleanup() throws SQLException {
		logger.info("starting database restore");
		SchemaMetadata tmp = getSchemaMetadata();
		originalSchemaMetadata.tableDifference(tmp);
	}

	@Test
	public void changeDb() {
		logger.info("changing database ");
		String now = new Date().toString();
		String sql = "insert into sales_channels(channel_name) values ('test" + now + "')";
		mysql.execUpdate(sql);

	}

	private SchemaMetadata getSchemaMetadata() throws SQLException {
		SchemaMetadata schemaMetadata = new SchemaMetadata();
		ArrayList<String> schema = MysqlUtils.getSchema(mysql);
		for (String tableName : schema) {
			int recordCount = MysqlUtils.getTableSize(tableName, mysql);
			schemaMetadata.add(tableName, recordCount);
		}
		return schemaMetadata;
	}

}
