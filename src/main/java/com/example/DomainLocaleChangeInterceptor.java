package com.example;

//import java.util.Locale;
////import java.util.Map;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import org.springframework.util.StringUtils;
////import org.springframework.web.servlet.HandlerMapping;
//import org.springframework.web.servlet.LocaleResolver;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//import org.springframework.web.servlet.support.RequestContextUtils;

// ドメインによるロケールの切り替えクラス
public class DomainLocaleChangeInterceptor {

//public class DomainLocaleChangeInterceptor extends HandlerInterceptorAdapter {
//
//      public static final String DEFAULT_PARAM_NAME = "locale";
//      private String paramName = DEFAULT_PARAM_NAME;
//      /**
//       * Controllerの@RequestMappingで指定するパラメータ名を返します
//       * @param paramName
//       */
//      public void setParamName(String paramName) {
//        this.paramName = paramName;
//      }
//      /**
//       * Controllerの@RequestMappingで指定するパラメータ名を設定します
//       * @param paramName
//       */
//      public String getParamName() {
//        return this.paramName;
//      }
//      @Override
//      public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException {
//        Locale locale = extractLocale(request);
//        if(locale != null) {
//          LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
//          if (localeResolver != null) {
//            localeResolver.setLocale(request, response, locale);
//          }
//        }
//        return true;
//      }
//      /**
//       * パスからロケールを返します
//       * パスにない場合、デフォルトのロケールを返します
//       * @param request
//       * @return
//       */
//      private Locale extractLocale(HttpServletRequest request) {
//        String locale = extractLocaleString(request);
//        if(StringUtils.hasText(locale)) {
//          return StringUtils.parseLocaleString(locale);
//        }
//        return request.getLocale();
//      }
//      /**
//       * ドメインからロケール部分の文字列を取り出す
//       * @param request
//       * @return
//       */
//      private String extractLocaleString(HttpServletRequest request) {
//        String locale = "ja"; // TODO
//        // リクエストヘッダのHostからlocaleを判別 例：en.example.ne.jp
//        String requestHost = request.getHeader("Host");
//        // ドメインの言語をlocaleの型に変換(enum)
//        
//        // ドメインのパターンはapplication.propertiesで定義
//        // TODO
//        
//          // URLのパスからロケール部分の文字列を取り出す
////        @SuppressWarnings("unchecked")
////        Map templateVariables = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
////        String locale = null;
////        if(templateVariables != null) {
////          locale = (String)templateVariables.get(getParamName());
////        }
//        return locale;
//      }
}
