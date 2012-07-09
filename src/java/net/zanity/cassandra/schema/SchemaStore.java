package net.zanity.cassandra.schema;

import me.prettyprint.hector.api.Cluster;
import me.prettyprint.hector.api.Keyspace;
import me.prettyprint.hector.api.exceptions.HectorException;
import me.prettyprint.hector.api.factory.HFactory;

/**
 *
 * @author Lyuben Todorov
 */
public class SchemaStore
{
    private static final String _CLUSTER = "TestCluster";
    private static final String _HOST = "localhost:9160";
    private static final String _TWEET_KEYSPACE = "JAVATest";
    
    public static String getKeyspaceName()
    {
        return "JAVATest";
    }
    
    public static Keyspace getKeyspace(Cluster cluster)
    {
        return HFactory.createKeyspace(_TWEET_KEYSPACE, cluster);
    }
    
    public static String getTweetColumnFamily()
    {
        return "HECTORTest";
    }
    
    public static Cluster getCluster() 
    {
        try{return HFactory.getOrCreateCluster(_CLUSTER, _HOST);}
        catch(HectorException hectorEx){hectorEx.printStackTrace();}
        catch(Exception ex){ex.printStackTrace();}
        
        return null;
    }
    
    public static void shutDownCluster(Cluster shutMeDown)
    {
        try{shutMeDown.getConnectionManager().shutdown();}
        catch(HectorException hectorEx){hectorEx.printStackTrace();}
        catch(Exception ex){ex.printStackTrace();}
    }
    
}
