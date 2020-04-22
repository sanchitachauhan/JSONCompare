/**
 * 
 */
package com.hcl.api.validator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.github.kvnxiao.jsonequals.JsonCompareResult;
import com.github.kvnxiao.jsonequals.JsonRoot;

/**
 * @author HCL
 *         <li>Read Property File Get Keys specific to Env Get List of APIs to
 *         be validated Get Map consists of Nodes as a value and API name as a
 *         key For each API validate response nodes and their status</li>
 */
public class apivalidator {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		apivalidator av=new apivalidator();
		//JsonRoot jsonRootA= new JsonRoot();
		String stgresponse= av.getAPIResponse("https://cdnapi-stg.manutd.com/api/v1/en/in/all/web/list/spotlight", "cU0OdnfQE971ts32rBdCN5gAtIybdJFz12OgGCQR");
		String prodresponse= av.getAPIResponse("https://cdnapi.manutd.com/api/v1/en/in/all/web/list/spotlight", "RfJZFw3Z5f1jJj40DF2dR5s6IuLOW8jq8W07XYUM");
		JsonRoot stgroot=JsonRoot.from(stgresponse);
		JsonRoot prodroot=JsonRoot.from(prodresponse);
		JsonCompareResult result=stgroot.compareTo(prodroot);
		System.out.println(result.getInequalityMessages());
	}

	private String getAPIResponse(String uri,String xapikey) throws IOException {

		StringBuffer cdnresponse = new StringBuffer();
		StringBuffer response = new StringBuffer();
		String cdninputLine = "";
		String inputLine = "";
		URL url = new URL(uri);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestProperty("Accept", "application/json");
		con.setRequestProperty("x-api-key",xapikey);
		con.setRequestProperty("Content-Type", "application/json");
		
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			while ((cdninputLine = in.readLine()) != null) {
				cdnresponse.append(cdninputLine);
			}
			
			return cdnresponse.toString();
	}

	
}
