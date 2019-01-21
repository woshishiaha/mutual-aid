<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<!-- saved from url=(0041)http://taizhou.19lou.com/forum-827-1.html -->
<HTML><HEAD><META content="IE=7.0000" http-equiv="X-UA-Compatible">
<TITLE>邻帮网</TITLE>
<META charset=gb2312>

<LINK rel=stylesheet href="_files2/list-min.css">

<LINK rel=stylesheet href="_files2/auto_dealer.css">



<META name=GENERATOR content="MSHTML 8.00.7601.18210">

</HEAD>
<BODY>







<UL class=ad-320x90></UL>
<DIV id=nav class="layout link0" itemprop="breadcrumb">
当前位置：<A href="index.action">返回首页</A> <I>&gt;&nbsp;</I>
<c:if test="${bankuai.id==1}">交流区</c:if>
<c:if test="${bankuai.id==2}">互助区</c:if>
<I>&nbsp;</I>


</DIV>


<DIV id=list-wrap class=layout  >
<DIV id=main-wrap>
<UL class=quickpost-mod>
<c:if test="${bankuai.id==1}">
  <LI>
  <A class=J_login  href="indexmethod!fatie.action?bid=${bankuai.id }&leixing=1" ><EM>
  <IMG alt=发表帖子 src="_files2/01.gif" width=42 height=36> <SPAN>发表帖子</SPAN> </EM></A>
  </LI>
</c:if>  

<c:if test="${bankuai.id==2}">
  <LI>
  <A class=J_login  href="indexmethod!fatie.action?bid=${bankuai.id }&leixing=1" ><EM>
  <IMG alt=我要求助 src="_files2/01.gif" width=42 height=36> <SPAN>我要求助</SPAN> </EM></A>
  </LI>
</c:if>  
  
  
  </UL>

<DIV class=clearall>
<DIV class="post-reward-tips fl"></DIV>
<DIV class="page-mod fr">
${pagerinfo }
</DIV>

</DIV>

<DIV class=list-tab>
<DIV class="J_fixedScroll list-tab-nav ">


<DIV align="right">
<form action="indexmethod!bankuailist.action?id=${bankuai.id }" method="post" >
标题：<INPUT type="text" name="title"  value="${title }"/>
<INPUT type="submit" value="本版搜索"  />
</form>
</DIV>

</DIV>

<DIV class=list-tab-bd>
<FORM id=J_threadForm method=post name=manageForm><INPUT value=827 type=hidden 
name=fid> 
<TABLE class="list-data  ">
  <THEAD>
  <TR>
    <TH class="data-orderby link6" colSpan=2>
    <A href="indexmethod!bankuailist.action?id=${bankuai.id }&paixu=1" ttname="bbs_list_newpost">按最新发表</A> <I class=pipe>|</I> 
    <A href="indexmethod!bankuailist.action?id=${bankuai.id }&paixu=2"  ttname="bbs_list_mostreply">按最后回复</A><I class=pipe>|</I> 
    <A href="indexmethod!bankuailist.action?id=${bankuai.id }&paixu=3"  ttname="bbs_list_mostreply">按最多回帖</A> 
      
      </TH>
    
    <TH class=author>作者</TH>
    <TH class=num>回复/查看</TH>
    <TH class=lastpost>最后回复时间</TH></TR></THEAD>
  
  <c:forEach items="${list}"  var="bean2" varStatus="v">
  <TBODY>
  <c:if test="${v.index%2==0}">
  <TR>
  </c:if>
   <c:if test="${v.index%2==1}">
   <TR class=gray>
   </c:if>
    <TD class=icon>
    <c:if test="${bean2.leixing=='精华'}">
     <IMG src="uploadfile/4.gif"> 
    </c:if>
     <c:if test="${bean2.leixing=='帖子'}">
     <IMG src="uploadfile/1.gif"> 
    </c:if>
     <c:if test="${bean2.leixing=='求助'}">
     <IMG src="uploadfile/3.gif"> 
    </c:if>
     <c:if test="${bean2.leixing=='活动'}">
     <IMG src="uploadfile/2.gif"> 
    </c:if>
   
    </TD>
    <TH class=title>
      <DIV class=subject>
      <A style="COLOR: #008000; FONT-WEIGHT: bold"  href="indexmethod!tiezi.action?tid=${bean2.id }" >
      <SPAN >${bean2.title }</SPAN> </A>
      
      </DIV></TH>
    
    <TD class=author>
    <A href="#">
    <SPAN >${bean2.user.truename }</SPAN></A> <BR>
    <SPAN class=color9 >${fn:substring(bean2.createtime,0, 10)}</SPAN> </TD>
    
    <TD class="num numeral"><EM itemprop="回复数">${bean2.huifushu }</EM> 
    <SPAN itemprop="查看数">${bean2.dianjishu }</SPAN> </TD>
    
    <TD class=lastpost><EM>
    <A href="#" rel=nofollow>
    <SPAN 
      class=numeral itemprop="最后回复时间">${fn:substring(bean2.huifutime,0, 19)}</SPAN> </TD></TR>
      </TBODY>
    </c:forEach>  
      
      
  
      
      
      
  
      
       
      
      
      
  
      
 
  
  


</TABLE></FORM></DIV></DIV>


<DIV class="page-mod fr">

${pagerinfo }
</DIV>

</DIV>


<DIV id=side-wrap itemtype=" http://Schema.org/Forum" itemscope>
<DIV class=list-board>
<DL class=list-board-hd>
  <DT class=board-img>
  </DT>
  <DD>
  <br/>
  <H1>
 <SPAN >${bankuai.bankuaiming }</SPAN>
  </H1>
  </DD>
  </DL>


<UL class="board-count color9">
  <LI>总帖子数<EM >${tiezicount }</EM> </LI>
  <LI>总回复数<EM >${huifucount }</EM> </LI></UL></DIV>

<P class=tag-top></P>









</DIV>

<DIV class=go-top><A class=back-to-top href="#"><SPAN 
class=arrow></SPAN>
<P>回顶部</P></A>
</DIV>

</BODY></HTML>

