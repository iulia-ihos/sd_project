package armyBase.sd.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;

import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;

import org.apache.http.impl.client.HttpClients;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import armyBase.sd.dto.UserDTO;

public class UserRequest {
	private ObjectMapper mapper = new ObjectMapper();

	public UserDTO getByEmail(String email) {
		String request = "http://localhost:8080/user/getByEmail?email=%s";

		String requestEnc = null;
		try {
			requestEnc = String.format(request, URLEncoder.encode(email, "UTF-8")

			);
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		UserDTO student = new UserDTO();
		try {
			CloseableHttpClient httpClient = HttpClients.createDefault();

			HttpGet getRequest = new HttpGet(requestEnc);

			HttpResponse response = httpClient.execute(getRequest);

			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

			String output = null;

			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				student = mapper.readValue(output, new TypeReference<UserDTO>() {
				});
				httpClient.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return student;
	}

	public UserDTO getById(Long id) {
		String request = "http://localhost:8080/user/getById?id=" + id.toString();

		UserDTO obj = new UserDTO();
		try {
			CloseableHttpClient httpClient = HttpClients.createDefault();

			HttpGet getRequest = new HttpGet(request);

			HttpResponse response = httpClient.execute(getRequest);

			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

			String output = null;

			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				obj = mapper.readValue(output, new TypeReference<UserDTO>() {
				});
				httpClient.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return obj;
	}

	public boolean login(String email, String passwd) {

		String request = "http://localhost:8080/user/login?email=%s&password=%s";

		String requestEnc = null;
		try {
			requestEnc = String.format(request, URLEncoder.encode(email, "UTF-8"), URLEncoder.encode(passwd, "UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		String success = "LOGIN SUCCESSFUL";

		CloseableHttpClient httpClient = HttpClients.createDefault();

		HttpGet getRequest = new HttpGet(requestEnc);

		HttpResponse response;
		try {
			response = httpClient.execute(getRequest);

			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

			String message = br.readLine();

			httpClient.close();

			if (message.equals(success))
				return true;
			return false;

		} catch (ClientProtocolException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return false;
	}

	public UserDTO register(UserDTO obj) throws IOException {
		String url = "http://localhost:8080/user/register";
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
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
		}
		client.close();
		return obj;
	}

	public UserDTO changePassword(String email, String password) throws ClientProtocolException, IOException {

		String url = "http://localhost:8080/user/changePassword?email=%s&password=%s";
		String request = null;
		try {
			request = String.format(url, URLEncoder.encode(email, "UTF-8"), URLEncoder.encode(password, "UTF-8"));
		} catch (UnsupportedEncodingException e1) {

			e1.printStackTrace();
		}
		System.out.println(request);

		UserDTO obj = new UserDTO();

		CloseableHttpClient httpClient = HttpClients.createDefault();

		HttpPut putRequest = new HttpPut(request);

		HttpResponse response = httpClient.execute(putRequest);

		if (response.getStatusLine().getStatusCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

		String output = null;

		while ((output = br.readLine()) != null) {
			obj = mapper.readValue(output, new TypeReference<UserDTO>() {
			});
			httpClient.close();
		}
		return obj;
	}

	public void deleteStudentById(Long studentId) throws ClientProtocolException, IOException {

		String url = "http://localhost:8080/Student/deleteStudentById?studentId=" + studentId;

		HttpDelete deleteRequest = new HttpDelete(url);

		CloseableHttpClient httpClient = HttpClients.createDefault();

		HttpResponse response = httpClient.execute(deleteRequest);

		if (response.getStatusLine().getStatusCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
		}

	}

}
