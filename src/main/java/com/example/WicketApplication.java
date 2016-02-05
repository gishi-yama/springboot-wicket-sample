package com.example;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.ws.javax.WicketServerEndpointConfig;
import org.apache.wicket.resource.DynamicJQueryResourceReference;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.apache.wicket.util.crypt.CharEncoding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;
import org.wicketstuff.annotation.scan.AnnotatedMountScanner;

import com.example.page.FooPage;

@EnableWebSocket
@SpringBootApplication
public class WicketApplication extends WebApplication implements WebSocketConfigurer{

  @Autowired
  private ApplicationContext applicationContext;

  @Override
  public Class<? extends WebPage> getHomePage() {
    return FooPage.class;
  }

  public static void main(String[] args) {
    SpringApplication.run(WicketApplication.class, args);
  }

  @Override
  public void init() {
    super.init();
    getRequestCycleSettings().setResponseRequestEncoding(CharEncoding.UTF_8);
    getMarkupSettings().setDefaultMarkupEncoding(CharEncoding.UTF_8);
    getComponentInstantiationListeners().add(new SpringComponentInjector(this, applicationContext));
    new AnnotatedMountScanner().scanPackage("com.example.page").mount(this);
    getJavaScriptLibrarySettings().setJQueryReference(new DynamicJQueryResourceReference());
    mountPages();
  }

  public void mountPages() {
//    mountPage("/FooPage", FooPage.class);
  }

  @Override
  public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
    
  }
  
  @Bean
  public ServerEndpointExporter serverEndpointExporter() {
    return new ServerEndpointExporter();
  }

  @Bean
  public WicketServerEndpointConfig myWicketServerEndpointConfig() {
    return new WicketServerEndpointConfig();
  }

}
