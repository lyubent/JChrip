package net.zanity.cassandra.schema;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
    
    public String displayTestData()
    {
        return "If this is displayed, i know how to use jstl... sort of :)";
    }
    
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
        }
        catch(Exception ex)
        {
            //Handle the exception
            System.err.println("Error adding tweet: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public List<String> getTweetsByUser()
    {
        List<String> tweetList = new ArrayList<String>();
        
        
        
        return tweetList;
    }
            
    public static void main(String [] args)
    {
        TweetHandler tweety = new TweetHandler();
        
            tweety.addTweet();
    }
}
