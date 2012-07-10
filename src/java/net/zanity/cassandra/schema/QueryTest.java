package net.zanity.cassandra.schema;

import java.sql.ResultSet;
import java.util.Iterator;
import java.util.List;
import me.prettyprint.cassandra.serializers.StringSerializer;
import me.prettyprint.hector.api.Cluster;
import me.prettyprint.hector.api.Keyspace;
import me.prettyprint.hector.api.beans.HColumn;
import me.prettyprint.hector.api.beans.OrderedRows;
import me.prettyprint.hector.api.beans.Row;
import me.prettyprint.hector.api.beans.Rows;
import me.prettyprint.hector.api.exceptions.HectorException;
import me.prettyprint.hector.api.factory.HFactory;
import me.prettyprint.hector.api.mutation.MutationResult;
import me.prettyprint.hector.api.mutation.Mutator;
import me.prettyprint.hector.api.query.ColumnQuery;
import me.prettyprint.hector.api.query.QueryResult;
import me.prettyprint.hector.api.query.RangeSlicesQuery;

/**
 *
 * @author Lyuben Todorov
 */
public class QueryTest 
{
    private static StringSerializer stringSerializer = StringSerializer.get();
    private static final String _KEYSPACE = "JAVATest";
    private static final String _COLUMNFAMILY = "HECTORTest";
    
    public static void main(String[] args) 
    {
        Cluster cluster = null;
        Keyspace keyspaceOperator = null;
        try
        {
            cluster = HFactory.getOrCreateCluster("TestCluster", "localhost:9160");

            keyspaceOperator = HFactory.createKeyspace(_KEYSPACE, cluster);
        
        } catch (Exception e)
        {
            System.out.println("SMTHN went wrong!");
        }
        
        try 
        {
            Mutator<String> mutator = HFactory.createMutator(keyspaceOperator, stringSerializer);
            // add 10 rows
//            for (int i = 0; i < 10; i++) 
//            {             
//                mutator.addInsertion("fake_key_" + i, _COLUMNFAMILY, HFactory.createStringColumn("fake_column_0", "fake_value_0_" + i))
//                .addInsertion("fake_key_" + i, _COLUMNFAMILY, HFactory.createStringColumn("fake_column_1", "fake_value_1_" + i))
//                .addInsertion("fake_key_" + i, _COLUMNFAMILY, HFactory.createStringColumn("fake_column_2", "fake_value_2_" + i));            
//            } 
            
            MutationResult me = mutator.execute();
            System.out.println("MutationResult from 10 row insertion: " + me);
            
            RangeSlicesQuery<String, String, String> rangeSlicesQuery = 
                HFactory.createRangeSlicesQuery(keyspaceOperator, stringSerializer, stringSerializer, stringSerializer);
            rangeSlicesQuery.setColumnFamily(_COLUMNFAMILY);            
            rangeSlicesQuery.setKeys("", "");
            rangeSlicesQuery.setRange("John", "John", false, 3);
            
            rangeSlicesQuery.setRowCount(10);
            QueryResult<OrderedRows<String, String, String>> result = rangeSlicesQuery.execute();
            
//            Iterator it = result.get().iterator();
//            while(it.hasNext())
//            {
//                System.out.println(it.next().toString());
//            }
            result.get().getList();
            for(Row<String, String, String> rowData : result.get().getList())
            {
//                System.out.println(tempData.getColumnSlice().getColumns());
                for(HColumn<String, String> cellData : rowData.getColumnSlice().getColumns())
                {
                    System.out.print("name: " + cellData.getName() + 
                                       " value:" + cellData.getValue() + "\t");
                }
                System.out.println("");
            }
            
            ColumnQuery<String, String, String> columnQuery = HFactory.createStringColumnQuery(keyspaceOperator);
            columnQuery.setColumnFamily(_COLUMNFAMILY).setKey("fake_key_0").setName("fake_column_0");
            QueryResult<HColumn<String, String>> colResult = columnQuery.execute();
            System.out.println("Execution time: " + colResult.getExecutionTimeMicro());
            System.out.println("CassandraHost used: " + colResult.getHostUsed());
            System.out.println("Query Execute: " + colResult.getQuery());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        cluster.getConnectionManager().shutdown();
    }
}
