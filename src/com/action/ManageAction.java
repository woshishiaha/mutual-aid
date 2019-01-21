package com.action;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.dao.BankuaiDao;
import com.dao.CainaDao;
import com.dao.HuifuDao;
import com.dao.TieziDao;
import com.dao.UserDao;
import com.dao.ZixunDao;
import com.model.Caina;
import com.model.Huifu;
import com.model.Tiezi;
import com.model.User;
import com.model.Zixun;
import com.opensymphony.xwork2.ActionSupport;
import com.util.Pager;
import com.util.Util;

public class ManageAction extends ActionSupport {

	private static final long serialVersionUID = -4304509122548259589L;

	private UserDao userDao;

	private String url = "./";

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}





//	登入请求
	public String login() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = userDao.selectBean(" where username = '" + username
				+ "' and password= '" + password + "' and role= "+1 +" and deletestatus=0 ");
		if (user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("manage", user);
			this.setUrl("manage/index.jsp");
			return "redirect";
		} else {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("gbk");
			response.setContentType("text/html; charset=gbk");
			response
			.getWriter()
			.print(
					"<script language=javascript>alert('用户名或者密码错误');window.location.href='login.jsp';</script>");
		}
		return null;
	}
//	用户退出
	public String loginout() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		session.removeAttribute("manage");
		this.setUrl("login.jsp");
		return SUCCESS;
	}
//	跳转到修改密码页面
	public String changepwd() {
		this.setUrl("user/user.jsp");
		return SUCCESS;
	}
//	修改密码操作
	public void changepwd2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User u = (User)session.getAttribute("manage");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		User bean = userDao.selectBean(" where username= '"+u.getUsername()+"' and password= '"+password1+"' and deletestatus=0");
		if(bean!=null){
			bean.setPassword(password2);
			userDao.updateBean(bean);
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
			response
			.getWriter()
			.print(
					"<script language=javascript>alert('修改成功');window.location.href='method!changepwd.action';</script>");
		}else{
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
			response
			.getWriter()
			.print(
					"<script language=javascript>alert('原密码错误');window.location.href='method!changepwd.action';</script>");
		}
	}



	private BankuaiDao bankuaiDao;

	public BankuaiDao getBankuaiDao() {
		return bankuaiDao;
	}

	public void setBankuaiDao(BankuaiDao bankuaiDao) {
		this.bankuaiDao = bankuaiDao;
	}







	//跳转到修改密码页面
	public String changepwd3() {
		this.setUrl("user/user.jsp");
		return SUCCESS;
	}
//	修改密码操作
	public void changepwd4() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User u = (User)session.getAttribute("user");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		User bean = userDao.selectBean(" where username= '"+u.getUsername()+"' and password= '"+password1+"' and deletestatus=0");
		if(bean!=null){
			bean.setPassword(password2);
			userDao.updateBean(bean);
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
			response
			.getWriter()
			.print(
					"<script language=javascript>alert('修改成功');window.location.href='method!changepwd3.action';</script>");
		}else{
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
			response
			.getWriter()
			.print(
					"<script language=javascript>alert('原密码错误');window.location.href='method!changepwd3.action';</script>");
		}
	}


	//跳转到个人信息中心页面
	public String useradd() {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("url", "method!useradd2.action");
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");

		User bean = userDao.selectBean(" where id= "+user.getId());
		request.setAttribute("bean", bean);
		request.setAttribute("title", "个人信息中心");
		this.setUrl("user/useradd.jsp");
		return SUCCESS;
	}



	private File uploadfile;






	public File getUploadfile() {
		return uploadfile;
	}

	public void setUploadfile(File uploadfile) {
		this.uploadfile = uploadfile;
	}

	//个人信息中心操作
	public void useradd2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String jianjie = request.getParameter("jianjie");
		String lianxifangshi = request.getParameter("lianxifangshi");
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");

		User bean = userDao.selectBean(" where id= "+user.getId());
		bean.setJianjie(jianjie);
		bean.setLianxifangshi(lianxifangshi);
		if(uploadfile!=null){
			String savaPath = ServletActionContext.getServletContext().getRealPath("/")+ "/uploadfile/";
			String time = new java.text.SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()).toString();
			String userimgPath = time+".jpg";
			File imageFile = new File(savaPath + userimgPath);
			Util.copyFile(uploadfile, imageFile);
			bean.setTouxiang(userimgPath);
		}
		userDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
		.getWriter()
		.print(
				"<script language=javascript>alert('操作成功');window.location.href='method!useradd.action';</script>");
	}

	private TieziDao tieziDao;


	public TieziDao getTieziDao() {
		return tieziDao;
	}

	public void setTieziDao(TieziDao tieziDao) {
		this.tieziDao = tieziDao;
	}

	//帖子列表
	public String tiezilist() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String title = request.getParameter("title");


		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (title != null && !"".equals(title)) {

			sb.append("title like '%" + title + "%'");
			sb.append(" and ");
			request.setAttribute("title", title);
		}




		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");

		sb.append("  deletestatus=0  and user.id="+user.getId()+"  and bankuai.id=1 order by id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = tieziDao.selectBeanCount(where.replaceAll(" order by id desc ", ""));
		request.setAttribute("list", tieziDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!tiezilist.action", "共有" + total + "条记录"));
		request.setAttribute("url", "method!tiezilist.action");
		request.setAttribute("url2", "method!tiezi");
		request.setAttribute("titletitle", "帖子管理");
		this.setUrl("tiezi/tiezilist.jsp");
		return SUCCESS;

	}


	//删除帖子操作
	public void tiezidelete() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Tiezi bean = tieziDao.selectBean(" where id= "
				+ request.getParameter("id"));
		bean.setDeletestatus(1);
		tieziDao.updateBean(bean);

		User u = userDao.selectBean(" where id= "+bean.getUser().getId());
		u.setFatieshu(u.getFatieshu()-1);
		userDao.updateBean(u);

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
		.getWriter()
		.print(
				"<script language=javascript>alert('操作成功');window.location.href='method!tiezilist.action';</script>");
	}

	//跳转到查看帖子页面
	public String tieziupdate3() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Tiezi bean = tieziDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("title", "帖子查看");
		this.setUrl("tiezi/tieziupdate3.jsp");
		return SUCCESS;
	}


	private HuifuDao huifuDao;


	public HuifuDao getHuifuDao() {
		return huifuDao;
	}

	public void setHuifuDao(HuifuDao huifuDao) {
		this.huifuDao = huifuDao;
	}

	//回复列表
	public String huifulist() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String title = request.getParameter("title");


		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (title != null && !"".equals(title)) {

			sb.append(" tiezi.title like '%" + title + "%'");
			sb.append(" and ");
			request.setAttribute("title", title);
		}




		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");

		sb.append("  deletestatus=0  and tiezi.user.id="+user.getId()+"  and tiezi.bankuai=1  order by id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = huifuDao.selectBeanCount(where.replaceAll(" order by id desc ", ""));
		request.setAttribute("list", huifuDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!huifulist.action", "共有" + total + "条记录"));
		request.setAttribute("url", "method!huifulist.action");
		request.setAttribute("url2", "method!huifu");
		request.setAttribute("titletitle", "回复管理");
		this.setUrl("huifu/huifulist.jsp");
		return SUCCESS;

	}


	//删除回复操作
	public void huifudelete() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Huifu bean = huifuDao.selectBean(" where id= "
				+ request.getParameter("id"));
		bean.setDeletestatus(1);
		huifuDao.updateBean(bean);

		User u = userDao.selectBean(" where id= "+bean.getUser().getId());
		u.setHuifushu(u.getHuifushu()-1);
		userDao.updateBean(u);
		Tiezi t = tieziDao.selectBean(" where id= "+bean.getTiezi().getId());
		t.setHuifushu(t.getHuifushu()-1);
		tieziDao.updateBean(t);

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
		.getWriter()
		.print(
				"<script language=javascript>alert('操作成功');window.location.href='method!huifulist.action';</script>");
	}

	//跳转到查看回复页面
	public String huifuupdate3() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Huifu bean = huifuDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("title", "回复查看");
		this.setUrl("huifu/huifuupdate3.jsp");
		return SUCCESS;
	}





	//用户空间中心
	public String kongjian() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String id = request.getParameter("id");


		
		this.setUrl("kongjian/index.jsp?id="+id);
		return "success";
	}








	//注册用户列表
	public String userlist() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String username = request.getParameter("username");


		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (username != null && !"".equals(username)) {

			sb.append("username like '%" + username + "%'");
			sb.append(" and ");
			request.setAttribute("username", username);
		}




		sb.append("  role!=1 order by id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = userDao.selectBeanCount(where.replaceAll(" order by id desc ", ""));
		request.setAttribute("list", userDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!userlist.action", "共有" + total + "条记录"));
		request.setAttribute("url", "method!userlist.action");
		request.setAttribute("url2", "method!user");
		request.setAttribute("title", "注册用户管理");
		this.setUrl("user/userlist.jsp");
		return SUCCESS;

	}



//	锁定注册用户操作
	public void userdelete() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		User bean = userDao.selectBean(" where id= "
				+ request.getParameter("id"));
		bean.setDeletestatus(1);
		userDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
		.getWriter()
		.print(
				"<script language=javascript>alert('操作成功');window.location.href='method!userlist.action';</script>");
	}

	//解锁注册用户操作
	public void userdelete2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		User bean = userDao.selectBean(" where id= "
				+ request.getParameter("id"));
		bean.setDeletestatus(0);
		userDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
		.getWriter()
		.print(
				"<script language=javascript>alert('操作成功');window.location.href='method!userlist.action';</script>");
	}




	private ZixunDao zixunDao;

	public ZixunDao getZixunDao() {
		return zixunDao;
	}

	public void setZixunDao(ZixunDao zixunDao) {
		this.zixunDao = zixunDao;
	}

	//社区资讯列表
	public String zixunlist() {
		HttpServletRequest request = ServletActionContext.getRequest();

		String ztitle = request.getParameter("ztitle");


		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (ztitle != null && !"".equals(ztitle)) {

			sb.append("ztitle like '%" + ztitle + "%'");
			sb.append(" and ");
			request.setAttribute("ztitle", ztitle);
		}




		sb.append("  deletestatus=0 order by id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = zixunDao.selectBeanCount(where.replaceAll(" order by id desc ", ""));
		request.setAttribute("list", zixunDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!zixunlist.action", "共有" + total + "条记录"));
		request.setAttribute("url", "method!zixunlist.action");
		request.setAttribute("url2", "method!zixun");
		request.setAttribute("title", "社区资讯管理");
		this.setUrl("zixun/zixunlist.jsp");
		return SUCCESS;

	}
//	跳转到添加社区资讯页面
	public String zixunadd() {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("url", "method!zixunadd2.action");
		request.setAttribute("title", "社区资讯添加");
		this.setUrl("zixun/zixunadd.jsp");
		return SUCCESS;
	}


//	添加社区资讯操作
	public void zixunadd2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();

		String zcontent = request.getParameter("zcontent");
		String ztitle = request.getParameter("ztitle");

		Zixun bean = new Zixun();


		bean.setCtime(Util.getTime());
		bean.setZcontent(zcontent);
		bean.setZtitle(ztitle);


		zixunDao.insertBean(bean);


		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
		.getWriter()
		.print(
				"<script language=javascript>alert('操作成功');window.location.href='method!zixunlist.action';</script>");


	}
//	跳转到更新社区资讯页面
	public String zixunupdate() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Zixun bean = zixunDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("url", "method!zixunupdate2.action?id="+bean.getId());
		request.setAttribute("title", "社区资讯修改");
		this.setUrl("zixun/zixunupdate.jsp");
		return SUCCESS;
	}
//	更新社区资讯操作
	public void zixunupdate2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();

		String zcontent = request.getParameter("zcontent");
		String ztitle = request.getParameter("ztitle");

		Zixun bean = zixunDao.selectBean(" where id= "
				+ request.getParameter("id"));

		bean.setZcontent(zcontent);
		bean.setZtitle(ztitle);

		zixunDao.updateBean(bean);

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
		.getWriter()
		.print(
				"<script language=javascript>alert('操作成功');window.location.href='method!zixunlist.action';</script>");
	}
//	删除社区资讯操作
	public void zixundelete() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Zixun bean = zixunDao.selectBean(" where id= "
				+ request.getParameter("id"));
		bean.setDeletestatus(1);
		zixunDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
		.getWriter()
		.print(
				"<script language=javascript>alert('操作成功');window.location.href='method!zixunlist.action';</script>");
	}

	//跳转到查看社区资讯页面
	public String zixunupdate3() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Zixun bean = zixunDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("title", "社区资讯查看");
		this.setUrl("zixun/zixunupdate3.jsp");
		return SUCCESS;
	}


	//用户管理中心
	public String user() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user==null){
			response.getWriter().print(
			"<script language=javascript>alert('请先登录');window.location.href='login.jsp';</script>");
			return null;
		}




		this.setUrl("user/index.jsp");
		return "redirect";
	}





	//帖子列表
	public String tiezilist2() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String title = request.getParameter("title");


		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (title != null && !"".equals(title)) {

			sb.append("title like '%" + title + "%'");
			sb.append(" and ");
			request.setAttribute("title", title);
		}




		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");

		sb.append("  deletestatus=0  and user.id="+user.getId()+"  and bankuai.id=2 order by id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = tieziDao.selectBeanCount(where.replaceAll(" order by id desc ", ""));
		request.setAttribute("list", tieziDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!tiezilist2.action", "共有" + total + "条记录"));
		request.setAttribute("url", "method!tiezilist2.action");
		request.setAttribute("url2", "method!tiezi");
		request.setAttribute("titletitle", "帖子管理");
		this.setUrl("tiezi/tiezilist2.jsp");
		return SUCCESS;

	}


	//删除帖子操作
	public void tiezidelete10() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Tiezi bean = tieziDao.selectBean(" where id= "
				+ request.getParameter("id"));
		bean.setDeletestatus(1);
		tieziDao.updateBean(bean);

		User u = userDao.selectBean(" where id= "+bean.getUser().getId());
		u.setFatieshu(u.getFatieshu()-1);
		userDao.updateBean(u);

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
		.getWriter()
		.print(
				"<script language=javascript>alert('操作成功');window.location.href='method!tiezilist2.action';</script>");
	}

	//跳转到查看帖子页面
	public String tieziupdate30() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Tiezi bean = tieziDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("title", "帖子查看");
		this.setUrl("tiezi/tieziupdate30.jsp");
		return SUCCESS;
	}




	//回复列表
	public String huifulist2() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String title = request.getParameter("title");


		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (title != null && !"".equals(title)) {

			sb.append(" tiezi.title like '%" + title + "%'");
			sb.append(" and ");
			request.setAttribute("title", title);
		}




		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");

		sb.append("  deletestatus=0  and tiezi.user.id="+user.getId()+"  and tiezi.bankuai=2  order by id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = huifuDao.selectBeanCount(where.replaceAll(" order by id desc ", ""));
		request.setAttribute("list", huifuDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!huifulist2.action", "共有" + total + "条记录"));
		request.setAttribute("url", "method!huifulist2.action");
		request.setAttribute("url2", "method!huifu");
		request.setAttribute("titletitle", "回复管理");
		this.setUrl("huifu/huifulist2.jsp");
		return SUCCESS;

	}


	//删除回复操作
	public void huifudelete10() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Huifu bean = huifuDao.selectBean(" where id= "
				+ request.getParameter("id"));
		bean.setDeletestatus(1);
		huifuDao.updateBean(bean);

		User u = userDao.selectBean(" where id= "+bean.getUser().getId());
		u.setHuifushu(u.getHuifushu()-1);
		userDao.updateBean(u);
		Tiezi t = tieziDao.selectBean(" where id= "+bean.getTiezi().getId());
		t.setHuifushu(t.getHuifushu()-1);
		tieziDao.updateBean(t);

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
		.getWriter()
		.print(
				"<script language=javascript>alert('操作成功');window.location.href='method!huifulist2.action';</script>");
	}

	//跳转到查看回复页面
	public String huifuupdate30() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Huifu bean = huifuDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("title", "回复查看");
		this.setUrl("huifu/huifuupdate30.jsp");
		return SUCCESS;
	}


	private CainaDao cainaDao;

	public CainaDao getCainaDao() {
		return cainaDao;
	}

	public void setCainaDao(CainaDao cainaDao) {
		this.cainaDao = cainaDao;
	}


	//确认采纳操作
	public void cainadelete() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");

		String huifuid = request.getParameter("huifuid");


		Huifu huifu = huifuDao.selectBean(" where id= "+huifuid);
		
		if(huifu.getUser().getId()==huifu.getTiezi().getUser().getId()){
			response.getWriter().print("<script language=javascript>alert('不能采纳自己发的回复');window.location.href='method!cainalist.action';</script>");

			return;
			
		}
		

		Caina bean = cainaDao.selectBean(" where huifu.tiezi.id= "+huifu.getTiezi().getId()  );

		if(bean!=null){

			response.getWriter().print("<script language=javascript>alert('您的求助已经采纳了回复，不可重复采纳');window.location.href='method!cainalist.action';</script>");

			return;
		}

		bean = new Caina();

		bean.setCtime(Util.getTime());
		bean.setHuifu(huifu);
		bean.setStatus1("未确认");
		bean.setStatus2("未确认");

		cainaDao.insertBean(bean);


		response.getWriter().print("<script language=javascript>alert('操作成功');window.location.href='method!cainalist.action';</script>");


	}


	//采纳列表
	public String cainalist() {
		HttpServletRequest request = ServletActionContext.getRequest();

		String title = request.getParameter("title");


		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (title != null && !"".equals(title)) {

			sb.append(" huifu.tiezi.title like '%" + title + "%'");
			sb.append(" and ");
			request.setAttribute("title", title);
		}


		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");

		sb.append("  deletestatus=0 and huifu.tiezi.user.id="+user.getId()+" order by id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = cainaDao.selectBeanCount(where.replaceAll(" order by id desc ", ""));
		request.setAttribute("list", cainaDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!cainalist.action", "共有" + total + "条记录"));
		request.setAttribute("url", "method!cainalist.action");
		request.setAttribute("url2", "method!caina");
		request.setAttribute("titletitle", "我的采纳");
		this.setUrl("caina/cainalist.jsp");
		return SUCCESS;

	}


	//求助者确认操作
	public void cainadelete2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");



		Caina bean = cainaDao.selectBean(" where id="+request.getParameter("id")  );


		bean.setStatus1("确认完成");

		cainaDao.updateBean(bean);




		response.getWriter().print("<script language=javascript>alert('操作成功');window.location.href='method!cainalist.action';</script>");


	}




	//跳转到求助者评价页面
	public String cainaupdate() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Caina bean = cainaDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("url", "method!cainaupdate2.action?id="+bean.getId());
		request.setAttribute("title", "求助者评价");
		this.setUrl("caina/cainaupdate.jsp");
		return SUCCESS;
	}

	//求助者评价操作
	public void cainaupdate2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();

		String pj1 = request.getParameter("pj1");

		Caina bean = cainaDao.selectBean(" where id= "
				+ request.getParameter("id"));

		bean.setPj1(pj1);

		cainaDao.updateBean(bean);

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
		.getWriter()
		.print(
				"<script language=javascript>alert('操作成功');window.location.href='method!cainalist.action';</script>");
	}



	//采纳列表
	public String cainalist2() {
		HttpServletRequest request = ServletActionContext.getRequest();

		String title = request.getParameter("title");


		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (title != null && !"".equals(title)) {

			sb.append(" huifu.tiezi.title like '%" + title + "%'");
			sb.append(" and ");
			request.setAttribute("title", title);
		}


		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");

		sb.append("  deletestatus=0 and huifu.user.id="+user.getId()+" order by id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = cainaDao.selectBeanCount(where.replaceAll(" order by id desc ", ""));
		request.setAttribute("list", cainaDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!cainalist2.action", "共有" + total + "条记录"));
		request.setAttribute("url", "method!cainalist2.action");
		request.setAttribute("url2", "method!caina");
		request.setAttribute("titletitle", "我的被采纳");
		this.setUrl("caina/cainalist2.jsp");
		return SUCCESS;

	}


	//帮助者确认操作
	public void cainadelete3() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");



		Caina bean = cainaDao.selectBean(" where id="+request.getParameter("id")  );


		bean.setStatus2("确认完成");

		cainaDao.updateBean(bean);




		response.getWriter().print("<script language=javascript>alert('操作成功');window.location.href='method!cainalist2.action';</script>");


	}




	//跳转到帮助者评价页面
	public String cainaupdate3() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Caina bean = cainaDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("url", "method!cainaupdate4.action?id="+bean.getId());
		request.setAttribute("title", "帮助者评价");
		this.setUrl("caina/cainaupdate3.jsp");
		return SUCCESS;
	}

	//帮助者评价操作
	public void cainaupdate4() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();

		String pj2 = request.getParameter("pj2");

		Caina bean = cainaDao.selectBean(" where id= "
				+ request.getParameter("id"));

		bean.setPj2(pj2);

		cainaDao.updateBean(bean);

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
		.getWriter()
		.print(
				"<script language=javascript>alert('操作成功');window.location.href='method!cainalist2.action';</script>");
	}

	
	
	//跳转到查看详情页面
	public String cainaupdate5() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Caina bean = cainaDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("title", "查看详情");
		this.setUrl("caina/cainaupdate5.jsp");
		return SUCCESS;
	}

	
	
	//
	public String cainalist3() {
		HttpServletRequest request = ServletActionContext.getRequest();

		String uid = request.getParameter("uid");


		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

	



		sb.append("  deletestatus=0 and huifu.tiezi.user.id="+uid+" order by id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = cainaDao.selectBeanCount(where.replaceAll(" order by id desc ", ""));
		request.setAttribute("list", cainaDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!cainalist3.action", "共有" + total + "条记录"));
		request.setAttribute("url", "method!cainalist3.action");
		request.setAttribute("url2", "method!caina");
		request.setAttribute("titletitle", "用户评价");
		this.setUrl("kongjian/caina/cainalist3.jsp");
		return SUCCESS;

	}
	
	
}
