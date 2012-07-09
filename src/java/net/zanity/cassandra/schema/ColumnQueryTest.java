package net.zanity.cassandra.schema;

import me.prettyprint.cassandra.serializers.StringSerializer;
import me.prettyprint.hector.api.Cluster;
import me.prettyprint.hector.api.ConsistencyLevelPolicy;
import me.prettyprint.hector.api.Keyspace;
import me.prettyprint.hector.api.beans.ColumnSlice;
import me.prettyprint.hector.api.factory.HFactory;
import me.prettyprint.hector.api.query.QueryResult;
import me.prettyprint.hector.api.query.SliceQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Lyuben Todorov
 */
public class ColumnQueryTest
{
    static final Logger log = LoggerFactory.getLogger(Logger.class);
    
    public String connectionTest()
    {
        Cluster c; //V2
        try 
        {
                c= null;//CassandraHosts.getCluster();
                ConsistencyLevelPolicy mcl = null; //new MyConsistancyLevel();
                Keyspace ks = HFactory.createKeyspace("securemail", c);  //V2
                ks.setConsistencyLevelPolicy(mcl);
                StringSerializer se = StringSerializer.get();
                SliceQuery<String, String, String> q = HFactory.createSliceQuery(ks, se, se, se);
                q.setColumnFamily("UserTweets")
                .setKey("test")
                .setRange("", "", false, 100);
                QueryResult<ColumnSlice<String, String>> r = q.execute();
                return "Connection Success";
        } 
        catch (Exception et)
        {
                System.out.println("Can't Connect to Cassandra. Check she is OK?");
                return "Connection Failed - " + et;
        }
    }
}
