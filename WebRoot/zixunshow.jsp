<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<!-- saved from url=(0069)http://taizhou.19lou.com/forum-827-thread-164231376836350284-1-1.html -->
<HTML><HEAD><META content="IE=7.0000" http-equiv="X-UA-Compatible">
<TITLE>邻帮网</TITLE>
<META charset=gb2312>


<LINK rel=stylesheet href="_files3/view-min.css">
<LINK rel=stylesheet href="_files3/viewqqb.css">
<LINK rel=stylesheet href="_files3/view_baipin.css">


<META name=GENERATOR content="MSHTML 8.00.7601.18210">

</HEAD>
<BODY>





<DIV style="POSITION: relative" id=view-wrap class="layout&#13;&#10;has-bigshow" >



<DIV class=view-crumbs>
<DIV id=nav class="layout link0" itemprop="breadcrumb">当前位置：
<A href="index.action">首页</A> <I>&gt;&nbsp;</I>
 <I>&gt;&nbsp;</I><EM>查看社区资讯</EM> <I>&nbsp;</I>
</DIV></DIV>



<DIV id=view-hd align="center">
<H1 class="fl f14 link1">

<A ><SPAN >${bean.ztitle }</SPAN> </A></H1>

  </DIV>
  
  <div>
  <br/>
  
   ${bean.zcontent }  

</div>


<div>
  <br/>
  
   ${bean.ctime }  

</div>




</BODY></HTML>


