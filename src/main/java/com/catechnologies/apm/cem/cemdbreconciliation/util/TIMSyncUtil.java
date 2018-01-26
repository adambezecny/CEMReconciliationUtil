package com.catechnologies.apm.cem.cemdbreconciliation.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;



/**
 * Utility class used to synchronize TIM monitors
 * @author bezad01
 *
 */
public class TIMSyncUtil {

	
	static final Logger log = LogManager.getLogger(TIMSyncUtil.class.getName());
	
	private final static String EM_URL_BASE_KEY   = "enterpisemanager.url.prefix";
	private final static String EM_ADMIN_USER_KEY = "enterpisemanager.admin.user";
	private final static String EM_ADMIN_PWD_KEY  = "enterpisemanager.admin.pwd";
	
	
	
	private String emURLBase = null;//http://bezad01w7.ca.com:8081
	private String emUser = null;
	private String emPwd = null;
	
    private BasicCookieStore cookieStore;
    private CloseableHttpClient httpclient;
    private HttpClientContext localContext;
	
	
    public TIMSyncUtil(){
    	
        cookieStore = new BasicCookieStore();
        httpclient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
        localContext = HttpClientContext.create();
        localContext.setCookieStore(cookieStore);
        emURLBase = ResourceUtil.getApplicationProperty(EM_URL_BASE_KEY);
        emUser = ResourceUtil.getApplicationProperty(EM_ADMIN_USER_KEY);
        emPwd = ResourceUtil.getApplicationProperty(EM_ADMIN_PWD_KEY);

    }
	
    
    private void displayCookies()   {
       
       log.trace("CookieStore:");
       List<Cookie> cookies = cookieStore.getCookies();
     
       if (cookies.isEmpty()){
    	   log.trace("None");
       }else {
    	   for (int i = 0; i < cookies.size(); i++)  
    		   log.trace("- " + cookies.get(i).toString());                
       } 
       
    }
    
    
    private String getCookie() {
      List<Cookie> cookies = cookieStore.getCookies();
      String cookie="";
      
      for (int i = 0; i < cookies.size(); i++)  { 
        if( i > 0 ) cookie = cookie+ ";";
        cookie = cookie + cookies.get(i).getName() + "=" + cookies.get(i).getValue();
      }
      return cookie;
      
    }
    
    
    private void displayHeaders(String htype ,Header[] h) {
        
    	log.trace(htype + " headers:");
    	
        for(int i=0; i<h.length; i++) 
        	log.trace("  " + h[i].getName() +"="+h[i].getValue());
    }
  
    /**
     * internal method used to connect to CEM UI via HTTP POST
     */
    private void connect() {
     
    	CloseableHttpResponse response1 = null;
    	CloseableHttpResponse response2 = null;
     	
    	try {
		      HttpGet httpget = new HttpGet(emURLBase+"/wily/cem/tess/app/login.html");
		      log.trace("URL: " + httpget.toString() );
		      httpget.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		      httpget.setHeader("User-Agent","Mozilla/5.0");
		      httpget.setHeader("Accept-Language","en-US,en;q=0.5");
		      displayHeaders("Request", httpget.getAllHeaders());
		
		      
		      response1 = httpclient.execute(httpget,localContext);
		      log.trace("Response: " + response1.getStatusLine());
		      displayHeaders("Response", response1.getAllHeaders());
		      displayCookies();     

		      
		       HttpEntity entity = response1.getEntity();
		       //System.out.println( EntityUtils.toString(entity));
		       EntityUtils.consume(entity);
		      
		      HttpUriRequest login = RequestBuilder.post()
		                             .setUri(new URI(emURLBase+"/wily/cem/tess/app/j_acegi_security_check"))
		                             .addParameter("j_username", this.emUser)
		                             .addParameter("loginForm:loginId_userName_clientSide", "")
		                             .addParameter("j_password", this.emPwd)
		                             .addParameter("loginForm:loginId_loginButton", "Log In")
		                             .build();
      
		      log.trace("URL: " + login.toString() );
		      login.setHeader("Cookie", getCookie());
		      displayHeaders("Request", login.getAllHeaders());
		      response2 = httpclient.execute(login, localContext);
		      log.trace("Response: " + response2.getStatusLine());
		      displayHeaders("Response", response2.getAllHeaders());
		      displayCookies();       

		      
		       HttpEntity entity2 = response2.getEntity();       
		       EntityUtils.consume(entity2);
      
        }catch(Exception e){
          log.trace("Error in connect() " + e);
        }finally{
	    	try {response1.close();response2.close();} catch (IOException ee) {log.trace("Error in connect.finally{} " + ee);}
        }
   
    }
    
    
   @SuppressWarnings("unused")
   private void getOverview(){
	   
	   CloseableHttpResponse response = null;
	   
	    try {
	      HttpGet httpget = new HttpGet(emURLBase+"/wily/cem/tess/app/admin/overview.html?pId=1");
	      log.trace("URL: " + httpget.toString() );
	      httpget.setHeader("Cookie", getCookie());
	      displayHeaders("Request", httpget.getAllHeaders());
	      
	      response = httpclient.execute(httpget, localContext);
	      log.trace("Response: " + response.getStatusLine());
	      displayHeaders("Response", response.getAllHeaders());
	      displayCookies();     
	
	       HttpEntity entity = response.getEntity();
	       //System.out.println( EntityUtils.toString(entity));
	       EntityUtils.consume(entity);
	    
	    } catch(Exception e){
	    	log.trace("Error in getOverview()" + e);
	    }finally{
	    	try {response.close();} catch (IOException ee) {log.trace("Error in getOverview.finally{} " + ee);}
	    }
    
   }

   /**
    * Internal method used to synchronize monitors
    */
   private void _synchronizeMonitors(){
	   
	   CloseableHttpResponse response = null;
	   
	    try {
	      
	      HttpUriRequest login = RequestBuilder.post()
                  .setUri(new URI(emURLBase+"/wily/cem/tess/app/admin/monitorList.html"))
                  .addParameter("synchronizeMonitors", "Synchronize All Monitors")//synchronizeMonitors=Synchronize+All+Monitors&focusSave=
                  .addParameter("focusSave=", "")
                  .build();

			log.trace("URL: " + login.toString() );
			login.setHeader("Cookie", getCookie());
			displayHeaders("Request", login.getAllHeaders());
			response = httpclient.execute(login, localContext);
			log.trace("Response: " + response.getStatusLine());
			displayHeaders("Response", response.getAllHeaders());
			displayCookies();       
	      
		    HttpEntity entity = response.getEntity();
		    EntityUtils.consume(entity);
	      
	    }catch(Exception e){
	        log.trace("Error in synchronizeMonitors()" + e);
	    }finally{
	    	try {response.close();} catch (IOException ee) {log.trace("Error in synchronizeMonitors.finally{} " + ee);}
	    }
   
  }
   
   /**
    * Utility method used to synchronize TIMs. Internally it connects to Enterprise Manager via HTTP POST request and sends another
    * POST request to synchronize TIM. This method is basically doing same processing as human user would via CA CEM GUI. 
    */
   public static void synchronizeMonitors(){
	    TIMSyncUtil util = new TIMSyncUtil();
		util.connect();
		util._synchronizeMonitors();
   }
    
	public static void main (String[] args) throws Exception{
		TIMSyncUtil.synchronizeMonitors();
	}
	
}
