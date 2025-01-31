package com.nitin.searchingnpagination.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nitin.searchingnpagination.services.SearchService;
import com.nitin.searchingnpagination.util.ExceptionJsonInfo;
import com.nitin.searchingnpagination.util.JsonUtil;

@RestController
@RequestMapping("/search/")
@CrossOrigin("*")
public class SearchEngineController {
	
	private static final Logger LOGGER = LogManager.getLogger(SearchEngineController.class);
	
	@Autowired
	SearchService searchService; 
	
	@GetMapping("searchbykey")
    public ResponseEntity<Object> getSeachingData(@RequestParam(value = "skeyword",required = false) String skeyword)
    {
    	try {	
			return new ResponseEntity<>(
					JsonUtil.convertJavaObjectToJson(searchService.searchbykeyword(skeyword)
							), HttpStatus.OK);
		} 
		catch (RuntimeException runex) 
		{
			LOGGER.error(runex.getMessage(), runex);
			return new ResponseEntity<>(new ExceptionJsonInfo(runex.getMessage(), HttpStatus.NOT_ACCEPTABLE), HttpStatus.NOT_ACCEPTABLE);
		}
		catch (Exception exception) {
			LOGGER.error(exception.getMessage(), exception);
			return new ResponseEntity<>(new ExceptionJsonInfo(exception), HttpStatus.INTERNAL_SERVER_ERROR);
			}
    }
    
    @GetMapping("searchbyenter")
    public ResponseEntity<Object> getSeachingEnter(
    		@RequestParam(value = "skeyword",required = false) String skeyword,
    		@RequestParam Integer page,
    		@RequestParam Integer size
    		)
    {
    	try {	
			return new ResponseEntity<>(
					JsonUtil.convertJavaObjectToJson(searchService.searchbyEnter(skeyword,page,size)
							), HttpStatus.OK);
		} 
		catch (RuntimeException runex) 
		{
			LOGGER.error(runex.getMessage(), runex);
			return new ResponseEntity<>(new ExceptionJsonInfo(runex.getMessage(), HttpStatus.NOT_ACCEPTABLE), HttpStatus.NOT_ACCEPTABLE);
		}
		catch (Exception exception) {
			LOGGER.error(exception.getMessage(), exception);
			return new ResponseEntity<>(new ExceptionJsonInfo(exception), HttpStatus.INTERNAL_SERVER_ERROR);
			}
    }

}
