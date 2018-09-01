package armyBase.sd.request;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;

import org.apache.http.impl.client.HttpClients;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import armyBase.sd.dto.SoldierOperationDTO;


public class SoldierOperationRequest {
		private ObjectMapper mapper = new ObjectMapper();
		

		public List<SoldierOperationDTO> getAll()
		{
			String request = "http://localhost:8080/soldierOp/getAll";
			
			List<SoldierOperationDTO> list = new ArrayList<SoldierOperationDTO>();
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
						list = mapper.readValue(output,  new TypeReference<List<SoldierOperationDTO>>(){});
						
					}
	
					httpClient.close();

				  } catch (ClientProtocolException e) {
				
					e.printStackTrace();

				  } catch (IOException e) {
				
					e.printStackTrace();
				  }
	         
		
				return list;
			
			}
		

		public SoldierOperationDTO getById(Long id)
		{  
			String request = "http://localhost:8080/soldierOp/getById?id="+id.toString();
		
			SoldierOperationDTO obj = new SoldierOperationDTO();
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
					obj = mapper.readValue(output,  new TypeReference<SoldierOperationDTO>(){});
					httpClient.close();
				}	
	        	
	        	
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
			
			return obj;
	    }
		
	
	  public void save(SoldierOperationDTO obj) throws ClientProtocolException, IOException {
		  
		  	String url = "http://localhost:8080/soldierOp/add";
		  
		    CloseableHttpClient client = HttpClients.createDefault();
		    HttpPut httpPut = new HttpPut(url);
		 
		    String json = null;
			try {
				json = mapper.writeValueAsString(obj);
				System.out.println(json);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		    StringEntity entity = null;
			try {
				entity = new StringEntity(json);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		    httpPut.setEntity(entity);
		    httpPut.setHeader("Accept", "application/json");
		    httpPut.setHeader("Content-type", "application/json");
		 
		    CloseableHttpResponse response = client.execute(httpPut);
		    if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
				   + response.getStatusLine().getStatusCode());
			}
		    client.close();  


	  }
	

	public void update(SoldierOperationDTO obj) throws ClientProtocolException, IOException {

		 
	  String url = "http://localhost:8080/soldierOp/update?";
		   
		  CloseableHttpClient httpClient = HttpClients.createDefault();
	      
	      
		  HttpPost httpPost = new HttpPost(url);
	      
	      try {
				String json = mapper.writeValueAsString(obj);
				StringEntity entity = new StringEntity(json);
			    httpPost.setEntity(entity);
				httpPost.setHeader("Accept", "application/json");
				httpPost.setHeader("Content-type", "application/json");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
	      
	        CloseableHttpResponse response = httpClient.execute(httpPost);
	        if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
				   + response.getStatusLine().getStatusCode());
			}
		    httpClient.close();  
			 
		  }
	  
	  public void deleteById(Long id) throws ClientProtocolException, IOException
	  {
		  
	      String url = "http://localhost:8080/soldier/deleteById?id=" + id;
	
	      HttpDelete deleteRequest = new HttpDelete(url);
	      
	      CloseableHttpClient httpClient = HttpClients.createDefault();
	
		HttpResponse response = httpClient.execute(deleteRequest);
			
			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
				   + response.getStatusLine().getStatusCode());
			}

		
  
	  }
	  
}
