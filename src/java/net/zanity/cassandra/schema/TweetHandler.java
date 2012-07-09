package net.zanity.cassandra.schema;

import me.prettyprint.cassandra.serializers.StringSerializer;
import me.prettyprint.hector.api.Cluster;
import me.prettyprint.hector.api.Keyspace;
import me.prettyprint.hector.api.beans.HColumn;
import me.prettyprint.hector.api.exceptions.HectorException;
import me.prettyprint.hector.api.factory.HFactory;
import me.prettyprint.hector.api.mutation.Mutator;
import me.prettyprint.hector.api.query.ColumnQuery;
import me.prettyprint.hector.api.query.QueryResult;

/**
 *
 * @author Lyuben Todorov
 */
public class TweetHandler
{
    private Cluster cluster;
    private Keyspace tweetKeyspace;
    private static StringSerializer stringSerializer = StringSerializer.get();
    
    public void addTweet()
    {
        try
        {
            cluster = SchemaStore.getCluster();
            tweetKeyspace = SchemaStore.getKeyspace(cluster);
            
            Mutator<String> mutator = HFactory.createMutator(tweetKeyspace, StringSerializer.get());
            for (int i = 0; i < 4; i++)
            mutator.insert("ILIKEPIE!!!!!" + i,                                // row key
                           SchemaStore.getTweetColumnFamily(),             // column family
                           HFactory.createStringColumn("first", "John"));  // column:value pair
            
                            //CF    //KS            //Column pair
//            ColumnQuery<String, String, String> columnQuery = HFactory.createStringColumnQuery(tweetKeyspace);
//            columnQuery.setColumnFamily(SchemaStore.getKeyspaceName()).setKey("jsmith").setName("first");
            
            //execute query, adds data to cassandra store
//            QueryResult<HColumn<String, String>> result = columnQuery.execute();
            
//            System.out.println("Read HColumn from cassandra: " + result.get());            
//            System.out.println("Verify on CLI with:  get Keyspace1.Standard1['jsmith'] ");
        }
        catch(Exception ex)
        {
            //Handle the exception
            System.err.println("Error adding tweet: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public static void main(String [] args)
    {
        TweetHandler tweety = new TweetHandler();
        
            tweety.addTweet();
    }
}
