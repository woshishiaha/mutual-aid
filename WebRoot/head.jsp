<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 头部 -->
<HEADER class="head layout">
<DIV class=head-hd align="center">

<span style="font-size: 40px;font-weight: bold;color: #339900;">
邻帮网


</span>


</DIV>


<DIV class=head-bd>
<NAV class=menu>
<a href="index.action">
<STRONG>首页</STRONG> 
</a>
<A href="indexmethod!bankuailist.action?id=1" rel=nofollow>交流区</A> 
<A href="indexmethod!bankuailist.action?id=2" rel=nofollow>互助区</A> 

<A href="method!user.action" >个人中心</A> 


<c:if test="${user!=null}">
<A href="indexmethod!loginout.action">用户退出</A> 
<span style="font-size: 20px;">
欢迎${user.truename }使用本网站
</span>
</c:if>

</NAV>




</DIV>
</HEADER>