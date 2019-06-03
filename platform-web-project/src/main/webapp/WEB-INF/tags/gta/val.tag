<%@ tag language='java' pageEncoding='UTF-8'%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ attribute name="value" type="java.lang.String" required="true"
	description="El表达式"%>
<c:choose>
	<c:when test="${empty value}">${value}</c:when>
	<c:when test="${value eq null}"> ${value}</c:when>
	<c:when test="${value==''}">${value}</c:when>
	<c:otherwise>
		<xmp 
		style="display:inline;">${value}</xmp>
	</c:otherwise>
</c:choose>