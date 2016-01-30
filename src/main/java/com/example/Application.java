package com.example;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
//import org.springframework.web.servlet.i18n.SessionLocaleResolver;

// 起動クラス
@SpringBootApplication
public class Application extends WebMvcAutoConfigurationAdapter{ 

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	// ロケールをサーバ（セッション）で保持する場合
	//@Bean
	//public SessionLocaleResolver localeResolver) {
	//  SessionLocaleResolver localeResolver = new SessionLocaleResolver();
	//  localeResolver.setDefaultLocale(Locale.JAPAN);
	//  return localeResolver;
	//}

    // ロケールをクライアント（Cookie）で保持する場合
    @Bean 
    public CookieLocaleResolver localeResolver() { 
            CookieLocaleResolver localeResolver = new CookieLocaleResolver(); 
            localeResolver.setCookieName("INQUIRY_LOCALE");
            localeResolver.setDefaultLocale(Locale.JAPAN); 
            return localeResolver; 
    }
    
    // URLパラメータによるロケール切り替えクラス
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
      LocaleChangeInterceptor i = new LocaleChangeInterceptor();
      i.setParamName("lang");
      return i;
    }
    
//    // URL(パス)によるロケール切り替えクラス
//    @Bean
//    public LocaleChangeInterceptor localeChangeInterceptor() {
//      LocaleChangeInterceptor i = new LocaleChangeInterceptor();
//      i.setParamName("locale");
//      return i;
//    }
	
	// インターセプターの登録
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	  registry.addInterceptor(localeChangeInterceptor());
	}
	
	
}
