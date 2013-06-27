package com.lumata.common.testing.selenium;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:arcangelo.dipasquale@lumatagroup.com">Arcangelo Di Pasquale</a>
 * 
 */
public final class SeleniumCache {

	private static Map<String, Object> cache = new HashMap<String, Object>();
	
	private SeleniumCache() {}
	
	public static Object getCacheValue( Object key ) {
		return SeleniumCache.cache.get(key);		
	}
	
	public static void setCacheValue( String key, Object value ) {
		SeleniumCache.cache.put(key, value);		
	}
	
}
