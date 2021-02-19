import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;

public class JavaElasticConnector {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RestHighLevelClient client = new RestHighLevelClient(
				RestClient.builder(new HttpHost("localhost", 9200, "http")));
		
		SearchRequest searchRequest = new SearchRequest();
	    searchRequest.indices("tesla_employees");
	    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
	    searchSourceBuilder.query(QueryBuilders.matchAllQuery());
	    searchRequest.source(searchSourceBuilder);
	    Map<String, Object> map=null;
	     
	    try {
	        SearchResponse searchResponse = null;
	        searchResponse =client.search(searchRequest, RequestOptions.DEFAULT);
	        if (searchResponse.getHits().getTotalHits().value > 0) {
	            SearchHit[] searchHit = searchResponse.getHits().getHits();
	            for (SearchHit hit : searchHit) {
	                map = hit.getSourceAsMap();
	                  System.out.println("map:"+Arrays.toString(map.entrySet().toArray()));
	                    
	                
	            }
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	}

}
