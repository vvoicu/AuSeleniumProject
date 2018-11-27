package ecs.tools.connectors;

import java.net.UnknownHostException;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

import ecs.tools.ConfigUtils;

/**
 * Class Will be used to extract actualData from the Database. This data will be compared with the expectedData and used for validation.
 * Please note, there should only be READ ONLY operations on the MongoDB.
 * @author voicu.turcu
 *
 */
public class MongoConnector {

	protected static MongoClient mongoDBClient;
	protected static MongoDatabase workingDB;
	protected static DB db;

	private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

	private static String DB_MONGO_URL;
	private static String DB_MONGO_PORT;
	private static String DB_MONGO_DB;

	public MongoConnector() throws UnknownHostException {
		DB_MONGO_URL = ConfigUtils.getProperty("DB_MONGO_URL");
		DB_MONGO_PORT = ConfigUtils.getProperty("DB_MONGO_PORT");
		DB_MONGO_DB = ConfigUtils.getProperty("DB_MONGO_DB");

//		logger.info("Mongo URL: " + DB_MONGO_URL);
//		logger.info("Mongo PORT: " + DB_MONGO_PORT);
//		logger.info("Mongo DB: " + DB_MONGO_DB);

		mongoDBClient = new MongoClient(DB_MONGO_URL + ":" + DB_MONGO_PORT);
	}

	public void displayCollectionNames() throws NumberFormatException, UnknownHostException {
		workingDB = mongoDBClient.getDatabase(DB_MONGO_DB);
		MongoIterable<String> dbCollectionNames = workingDB.listCollectionNames();

		for (String nameNow : dbCollectionNames) {
			logger.info("Collection: " + nameNow);
			MongoCollection<Document> collectionNow = workingDB.getCollection(nameNow);
			displayCollectionDocuments(collectionNow);
		}
	}

	public MongoCollection<Document> getCollectionByName(String name)
			throws NumberFormatException, UnknownHostException {
		workingDB = mongoDBClient.getDatabase(DB_MONGO_DB);
		MongoCollection<Document> dbCollectionNames = workingDB.getCollection(name);
		displayCollectionDocuments(dbCollectionNames);
		return dbCollectionNames;
	}

	public static String displayCollectionDocuments(MongoCollection<Document> collection) {
		String result = "";
		for (Document document : collection.find()) {
			result += document.toJson() + "\n";
		}

		return result;
	}
}
