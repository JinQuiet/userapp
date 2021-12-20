package com.teamred.filter;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamred.json.Payload;
import com.teamred.validator.ValidationChain;
import com.teamred.validator.ValidationResult;
import com.teamred.validator.Validator;
import com.teamred.validator.impl.web.ContentTypeValidator;
import com.teamred.validator.impl.web.PathValidator;
import com.teamred.validator.impl.web.RequestMethodValidator;
import com.teamred.web.mapping.PathParamToRegexPool;
import com.teamred.web.mapping.RequestMapping;
import com.teamred.web.mapping.RequestMappingPool;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;

import jakarta.servlet.http.HttpServletResponse;

import static com.teamred.web.util.WebUtils.*;

public class RootFilter extends HttpFilter {

	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("====Entered RootFilter=====");
		
		request.getServletPath();


		//real path to the resource we're trying to get
		String path = request.getRequestURI().substring(request.getContextPath().length());
		//if it's a context root it goes to "/actions/index/"
		//in other cases it goes to provided path


		//check for the correct request mapping
		//if NOT - generate responce code and send it back

		String pathInfo = getCleanPath(path);

		String requestMethod = request.getMethod();
		String contentType = request.getContentType();

        ValidationChain<String> pathValidationChain = new ValidationChain<String>();

		List<RequestMapping> pathMapping = null;

			if (pathInfo != null){
            Validator<String> pathValidator = new PathValidator("request.path", RequestMappingPool.getRequestMapping());
                pathValidationChain.nextLink(pathInfo, pathValidator);
                    pathMapping 
                    = getMappingForRequestPart(RequestMappingPool.getRequestMapping(), 
                    pathInfo,
                    (rm) -> pathInfo!=null && pathInfo.matches(pathParamToRegex(PathParamToRegexPool.getPathParamToRegexMapping(), rm.getPath())));
			}

			if ((pathMapping != null) && (pathMapping.size()>0)){
            Validator<String> requestMethodValidator = new RequestMethodValidator("request.method", pathMapping);
                pathValidationChain.nextLink(requestMethod, requestMethodValidator);
                    pathMapping
                    = getMappingForRequestPart(pathMapping, 
                    requestMethod,
                    (rm) -> requestMethod != null && requestMethod.equals(rm.getRequestMethod()));
			}					

			if ((pathMapping != null) && (pathMapping.size()>0)){			
            Validator<String> contentTypeValidator = new ContentTypeValidator("request.contentType", pathMapping);
                pathValidationChain.nextLink(contentType, contentTypeValidator);
                    pathMapping
                    = getMappingForRequestPart(pathMapping, 
                    contentType,
                    (rm) -> contentType!=null && contentType.equals(rm.getContentType()));                    
			}

        ValidationResult mappingMatch = pathValidationChain.resolve();

		//if YES is correct - call the servler at /actions with the appropriate action
		if (mappingMatch.isValid()) {
			chain.doFilter(request, response);
		} else {
			//if there is no maping for the request send back 404

			Payload<String> payload = new Payload<String>(null, pathValidationChain.getErrorMessages());

			System.out.println("chainResolution :: " + mappingMatch.isValid());
			System.out.println("chainErrors :: " + pathValidationChain.getErrorMessages());			

			try {
				ObjectMapper mapper = new ObjectMapper();				
				String json = mapper.writeValueAsString(payload);
				
				System.out.println("ResultingJSONstring = " + json);

				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(json);	

			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}			
		}		

		System.out.println("====Left RootFilter");		
	}
}