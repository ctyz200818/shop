<%--
  Created by IntelliJ IDEA.
  User: brian
  Date: 2017/1/18
  Time: 9:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<table border="0" width="100%">
    <s:iterator value="list" var="oi">
    <tr>
        <td><img width="40" height="45" src="${pageContext.request.contextPath}/<s:property value="#oi.product.image"/> "/> </td>
        <td><s:property value="#oi.product.pname"/> </td>
        <td><s:property value="#oi.count"/> </td>
        <td><s:property value="#oi.subtotal"/> </td>
    </tr>
    </s:iterator>
</table>
