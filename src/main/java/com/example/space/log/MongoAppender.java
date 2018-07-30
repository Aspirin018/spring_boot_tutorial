package com.example.space.log;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;
import org.springframework.context.annotation.Configuration;


/**
 * 使用log4j实现http请求日志入mongodb
 * log4j提供的输出器实现自Appender接口，要自定义appender输出到MongoDB，只需要继承AppenderSkeleton类，并实现几个方法即可完成。
 * @author liyu
 * @date 18-7-28
 */
@Configuration //注意一定要加注解
public class MongoAppender extends AppenderSkeleton {
    private MongoClient mongoClient; //mongodb的连接客户端
    private MongoDatabase mongoDatabase; //记录日志的数据库
    private MongoCollection<BasicDBObject> logsCollection; //记录日志的集合

    /**
     * 在log4j.properties中配置
     */
    private String connectionUrl;
    private String databaseName;
    private String collectionName;


    @Override
    protected void append(LoggingEvent loggingEvent) {
        if(mongoDatabase == null){
            //根据log4j.properties中的配置创建mongodb连接
            MongoClientURI mongoClientURI = new MongoClientURI(connectionUrl);
            mongoClient = new MongoClient(mongoClientURI);
            mongoDatabase = mongoClient.getDatabase(databaseName);
            logsCollection = mongoDatabase.getCollection(collectionName, BasicDBObject.class);
        }
        //LoggingEvent提供getMessage()函数来获取日志消息
        logsCollection.insertOne((BasicDBObject) loggingEvent.getMessage());
    }

    @Override
    public void close() {
        if(mongoClient != null){
            mongoClient.close();
        }
    }

    @Override
    public boolean requiresLayout() {
        return false;
    }

    public String getConnectionUrl() {
        return connectionUrl;
    }

    public void setConnectionUrl(String connectionUrl) {
        this.connectionUrl = connectionUrl;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public MongoClient getMongoClient() {
        return mongoClient;
    }

    public void setMongoClient(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    public MongoDatabase getMongoDatabase() {
        return mongoDatabase;
    }

    public void setMongoDatabase(MongoDatabase mongoDatabase) {
        this.mongoDatabase = mongoDatabase;
    }

    public MongoCollection<BasicDBObject> getLogsCollection() {
        return logsCollection;
    }

    public void setLogsCollection(MongoCollection<BasicDBObject> logsCollection) {
        this.logsCollection = logsCollection;
    }
}
