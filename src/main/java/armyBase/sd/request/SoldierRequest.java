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

import armyBase.sd.dto.SoldierDTO;


public class SoldierRequest {
		private ObjectMapper mapper = new ObjectMapper();
		

		public List<SoldierDTO> getAll()
		{
			String request = "http://localhost:8080/soldier/getAll";
			
			List<SoldierDTO> list = new ArrayList<SoldierDTO>();
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
						list = mapper.readValue(output,  new TypeReference<List<SoldierDTO>>(){});
						
					}
	
					httpClient.close();

				  } catch (ClientProtocolException e) {
				
					e.printStackTrace();

				  } catch (IOException e) {
				
					e.printStackTrace();
				  }
	         
		
				return list;
			
			}
		

		public SoldierDTO getById(Long id)
		{  
			String request = "http://localhost:8080/soldier/getById?id="+id.toString();
		
			SoldierDTO obj = new SoldierDTO();
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
					obj = mapper.readValue(output,  new TypeReference<SoldierDTO>(){});
					httpClient.close();
				}	
	        	
	        	
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
			
			return obj;
	    }
		
		public SoldierDTO getByIdUser(Long id)
		{  
			String request = "http://localhost:8080/soldier/getByIdUser?id="+id.toString();
		
			SoldierDTO obj = new SoldierDTO();
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
					obj = mapper.readValue(output,  new TypeReference<SoldierDTO>(){});
					httpClient.close();
				}	
	        	
	        	
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
			
			return obj;
	    }
		
		public SoldierDTO updateRole(Long idSoldier,Long idRole)
		{  
			String request = "http://localhost:8080/soldier/updateRole?idSoldier=idSoldier&idRole=idRole" ;
		
			SoldierDTO obj = new SoldierDTO();
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
					obj = mapper.readValue(output,  new TypeReference<SoldierDTO>(){});
					httpClient.close();
				}	
	        	
	        	
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
			
			return obj;
	    }
		
	
	  public void save(SoldierDTO obj) throws ClientProtocolException, IOException {
		  
		  	String url = "http://localhost:8080/soldier/add";
		  
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
	

	public void update(SoldierDTO obj) throws ClientProtocolException, IOException {

		 
	  String url = "http://localhost:8080/soldier/update";
		   
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
