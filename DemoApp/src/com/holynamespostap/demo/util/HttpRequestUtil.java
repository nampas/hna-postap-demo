package com.holynamespostap.demo.util;

import javax.servlet.http.HttpServletRequest;

public class HttpRequestUtil {

	public static String extractUsernameFromRequest(HttpServletRequest request) {
		String requestUrl = request.getRequestURL().toString();
		if(requestUrl == null) return null;

		// Cut out the query parameters
		int queryStart = requestUrl.lastIndexOf("?");
		if(queryStart >= 0) {
			requestUrl = requestUrl.substring(0, queryStart);
		}

		// Cut out the path preceding the username (we want the the last
		// path segment)
		int lastSegmentStart = requestUrl.lastIndexOf("/");
		if(lastSegmentStart >= 0) {
			requestUrl = requestUrl.substring(lastSegmentStart + 1,
												requestUrl.length());
		}

		return requestUrl;
	}
}