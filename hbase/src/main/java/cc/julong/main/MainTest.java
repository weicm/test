package cc.julong.main;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.io.compress.Compression;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * Created by Think on 2016/7/6.
 */
public class MainTest {

	private static final String TABLE = "MINUTE_DDTJ";

	private static final String TABLE_NAME = "MY_TABLE_NAME_TOO";
	private static final String CF_DEFAULT = "DEFAULT_COLUMN_FAMILY";

	public static void createOrOverwrite(Admin admin, HTableDescriptor table) throws IOException {
		if (admin.tableExists(table.getTableName())) {
			admin.disableTable(table.getTableName());
			admin.deleteTable(table.getTableName());
		}
		admin.createTable(table);
	}

	public static void createSchemaTables(Configuration config) throws IOException {

			Connection connection = ConnectionFactory.createConnection(config);
			Admin admin = connection.getAdmin();
			HTableDescriptor table = new HTableDescriptor(TableName.valueOf(TABLE_NAME));
			table.addFamily(new HColumnDescriptor(CF_DEFAULT).setCompressionType(Compression.Algorithm.SNAPPY));

			System.out.print("Creating table. ");
			createOrOverwrite(admin, table);
			System.out.println(" Done.");
	}

	public static void modifySchema(Configuration config) throws IOException {

			Connection connection = ConnectionFactory.createConnection(config);
			Admin admin = connection.getAdmin();
			TableName tableName = TableName.valueOf(TABLE_NAME);
			if (admin.tableExists(tableName)) {
				System.out.println("Table does not exist.");
				System.exit(-1);
			}

			HTableDescriptor table = new HTableDescriptor(tableName);

			// Update existing table
			HColumnDescriptor newColumn = new HColumnDescriptor("NEWCF");
			newColumn.setCompactionCompressionType(Compression.Algorithm.GZ);
			newColumn.setMaxVersions(HConstants.ALL_VERSIONS);
			admin.addColumn(tableName, newColumn);

			// Update existing column family
			HColumnDescriptor existingColumn = new HColumnDescriptor(CF_DEFAULT);
			existingColumn.setCompactionCompressionType(Compression.Algorithm.GZ);
			existingColumn.setMaxVersions(HConstants.ALL_VERSIONS);
			table.modifyFamily(existingColumn);
			admin.modifyTable(tableName, table);

			// Disable an existing table
			admin.disableTable(tableName);

			// Delete an existing column family
			admin.deleteColumn(tableName, CF_DEFAULT.getBytes("UTF-8"));

			// Delete a table (Need to be disabled first)
			admin.deleteTable(tableName);
	}


	public static void main(String... args) throws IOException {
		//Add any necessary configuration files (hbase-site.xml, core-site.xml)
		/*config.addResource(new Path(System.getenv("HBASE_CONF_DIR"), "hbase-site.xml"));
		config.addResource(new Path(System.getenv("HADOOP_CONF_DIR"), "core-site.xml"));*/

		Configuration config = HBaseConfiguration.create();
		config.addResource("hbase-site.xml");
		config.addResource("core-site.xml");
		Connection connection = ConnectionFactory.createConnection(config);
		Admin admin = connection.getAdmin();
		TableName tableName = TableName.valueOf(TABLE);

		if (!admin.tableExists(tableName)) {
			System.out.println("Table does not exist.");
			System.exit(-1);
		}else {
			System.out.println("Table exist!");
		}


		Table table = connection.getTable(tableName);
		System.out.println(table.getClass());
		long r = table.incrementColumnValue(Bytes.toBytes("line-1"), Bytes.toBytes("F"), Bytes.toBytes("1"), 3);
		connection.close();
		System.out.println(r);

	}
}
