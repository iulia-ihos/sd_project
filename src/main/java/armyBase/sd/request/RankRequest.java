package armyBase.sd.request;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;

import org.apache.http.impl.client.CloseableHttpClient;

import org.apache.http.impl.client.HttpClients;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import armyBase.sd.dto.RankDTO;






public class RankRequest {
		private ObjectMapper mapper = new ObjectMapper();
		

		public List<RankDTO> getAll()
		{
			String request = "http://localhost:8080/rank/getAll";
			
			List<RankDTO> list = new ArrayList<RankDTO>();
			try {
				  CloseableHttpClient httpClient = HttpClients.createDefault();
				
		           HttpGet getRequest = new HttpGet(request);
				

					HttpResponse response = httpClient.execute(getRequest);

					if (response.getStatusLine().getStatusCode() != 200) {
						throw new RuntimeException("Failed : HTTP error code : "
						   + response.getStatusLine().getStatusCode());
					}

					BufferedReader br = new BufferedReader(
			                         new InputStreamReader((response.getEntity().getContent())));

					String output = null;
				
					while ((output = br.readLine()) != null) {
						list = mapper.readValue(output,  new TypeReference<List<RankDTO>>(){});
						
					}
	
					httpClient.close();

				  } catch (ClientProtocolException e) {
				
					e.printStackTrace();

				  } catch (IOException e) {
				
					e.printStackTrace();
				  }
	         
		
				return list;
			
			}
		

		public RankDTO getById(Long id)
		{  
			String request = "http://localhost:8080/rank/getById?id="+id.toString();
		
			RankDTO obj = new RankDTO();
			try {
				CloseableHttpClient httpClient = HttpClients.createDefault();
	        	
	        	HttpGet getRequest = new HttpGet(request
						);
	        	
				HttpResponse response = httpClient.execute(getRequest);

				if (response.getStatusLine().getStatusCode() != 200) {
					throw new RuntimeException("Failed : HTTP error code : "
					   + response.getStatusLine().getStatusCode());
				}

				BufferedReader br = new BufferedReader(
		                         new InputStreamReader((response.getEntity().getContent())));

				String output = null;
				
				System.out.println("Output from Server .... \n");
				while ((output = br.readLine()) != null) {
					obj = mapper.readValue(output,  new TypeReference<RankDTO>(){});
					httpClient.close();
				}	
	        	
	        	
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
			
			return obj;
	    }
		
}
