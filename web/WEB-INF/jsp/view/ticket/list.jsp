<%--@elvariable id="ticketDatabase" type="java.util.Map<Integer, com.hung.le.site.Ticket>" --%>
<template:basic htmlTitle="Tickets" bodyTitle="Tickets">
	<c:choose>
		<c:when test="${fn:length(ticketDatabase) == 0 }">
			<i>There are no tickets in the system.</i>
		</c:when>
		<c:otherwise>
			<c:forEach items="${ticketDatabase}" var="entry">
				Ticket ${entry.key }:
				<a href="<c:url value="/ticket/view/${entry.key}" />">
				<c:out value="${formatDate:abbreviateString(entry.value.subject, 60)}"/>
				</a><br />
				<c:out value="${entry.value.customerName }" /> created ticket
				<wrox:formatDate value="${entry.value.dateCreated}" type="both"
                                 timeStyle="short" dateStyle="medium" /><br />
                <br />
			</c:forEach>
		</c:otherwise>
	</c:choose>
</template:basic>