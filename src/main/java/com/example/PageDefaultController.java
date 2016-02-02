package com.example;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.io.Resource;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.context.request.RequestAttributes;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.servlet.HandlerMapping;
//import org.webjars.WebJarAssetLocator;
//
//@Controller
public class PageDefaultController {

//  @ResponseBody
//  @RequestMapping(value="/**", method=RequestMethod.GET)
//  public ResponseEntity<Resource> locateWebjarAsset(WebRequest request) {
//      try {
//        WebJarAssetLocator assetLocator = new WebJarAssetLocator();
//          String mvcPrefix = "/sample/";
//          String mvcPath = (String) request.getAttribute(
//                  HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE, RequestAttributes.SCOPE_REQUEST);
//          System.out.println("@@@@@@@@@@@@@ mvcPath:" + mvcPath);
//          String webjar = "dummy";
//          String fullPath = assetLocator.getFullPath(webjar, 
//                  mvcPath.substring(mvcPrefix.length()));
//          ClassPathResource res = new ClassPathResource(fullPath);
//          long lastModified = res.lastModified();
//          if ((lastModified > 0) && request.checkNotModified(lastModified)) {
//              return null;
//          }
//          return new ResponseEntity<Resource>(res, HttpStatus.OK);
//      } catch (Exception e) {
//          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//      }
//  }
}
