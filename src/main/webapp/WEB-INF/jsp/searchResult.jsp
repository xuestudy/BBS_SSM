<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<base href="${pageContext.request.contextPath }/" />
<title>Java论坛搜索结果</title>
<meta http-equiv="content-type" content="text/html; charset=utf8">
<link rel="stylesheet" type="text/css" href="images/style.css"
	title="Integrated Styles">
<script language="JavaScript" type="text/javascript"
	src="images/global.js"></script>
<link rel="alternate" type="application/rss+xml" title="RSS"
	href="http://bbs.chinajavaworld.com/rss/rssmessages.jspa?forumID=20">
<script language="JavaScript" type="text/javascript"
	src="images/common.js"></script>
</head>

<body style="margin: 0 15px 0 15px;">
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tbody>
			<tr>
				<td width="40%"><img src="images/header-stretch.gif" alt=""
					border="0" height="57" width="100%"></td>
				<td width="1%"><img src="images/header-right.gif" alt=""
					height="57" border="0"></td>
			</tr>
		</tbody>
	</table>
	<br>
	<div id="jive-forumpage">
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tbody>
				<tr valign="top">
					<td width="98%">
						<p class="jive-breadcrumbs">
							<!-- 退出按钮 -->
							<a style="color: blue; float: right;" href="exit">退出</a>
							<!-- 回到主页 -->
							<a style="color: blue; float: right; margin-right: 10px;"
								href="article/flat">回到主页</a>
						</p>
						<p class="jive-breadcrumbs">论坛: Java语言交流</p>
						<p class="jive-description">
						<h2 style="color: red; text-align: center;">搜索结果</h2>
					</td>
				</tr>
			</tbody>
		</table>
		<br>
		<table border="0" cellpadding="3" cellspacing="0" width="100%">
			<tbody>
				<tr valign="top">
					<td><span class="nobreak">  第${page.getPageNum() }页
							 共${page.getPages() }页 <span class="jive-paginator"> [
								<c:choose>
									<c:when test="${list.size() == 0 }">
										<a href="javascript:void(0);">首页</a>
										<a href="javascript:void(0);">上一页</a>
										<a href="javascript:void(0);">下一页</a>
										<a href="javascript:void(0);">尾页</a>
									</c:when>
									<c:otherwise>
							           [
							          <a href="article/search?pageNum=${1 }&title=${title}">首页</a>
							          <c:choose>
							          	<c:when test="${page.getPageNum() == 1 }">
							          		<a href="article/search?pageNum=${page.getPageNum() }&title=${title}">上一页</a>
							          	</c:when>
							          	<c:otherwise>
							          		<a href="article/search?pageNum=${page.getPageNum() - 1 }&title=${title}">上一页</a>
							          	</c:otherwise>
							          </c:choose>
							          <c:choose>
							          	<c:when test="${page.getPageNum() == page.getPages() }">
							          		<a href="article/search?pageNum=${page.getPageNum() }&title=${title}">下一页</a>
							          	</c:when>
							          	<c:otherwise>
							          		<a href="article/search?pageNum=${page.getPageNum() + 1 }&title=${title}">下一页</a>
							          	</c:otherwise>
							          </c:choose>
							          <a href="article/search?pageNum=${page.getPages() }&title=${title}">尾页</a>
									</c:otherwise>
								</c:choose> ]
						</span>
					</span></td>
				</tr>
			</tbody>
		</table>
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tbody>
				<tr valign="top">
					<td width="99%">
						<div class="jive-thread-list">
							<div class="jive-table">
								<table summary="List of threads" cellpadding="0" cellspacing="0"
									width="100%">
									<thead>
										<tr>
											<th class="jive-author"></th>
											<th class="jive-author">操作</th>
											<th class="jive-author">主题</th>
											<th class="jive-author"><nobr> 作者 &nbsp; </nobr></th>
											<th class="jive-view-count"><nobr> 浏览 &nbsp; </nobr></th>
											<th class="jive-msg-count" nowrap="nowrap">回复</th>
											<th class="jive-last" nowrap="nowrap">发布时间</th>
										</tr>
									</thead>
									<!-- 开始 输入空串或者如果没有找到记录，则显示没有记录 -->
									<c:choose>
										<c:when test="${list.size() } == 0">
											<tbody>
												<tr class="">
													<td class="jive-first" nowrap="nowrap" colspan="7"
														width="100%"><font style="color: red;">没有搜索到相关信息</font>
													</td>
												</tr>
											</tbody>
										</c:when>
										<c:otherwise>
											<!-- 如果记录不为空，则显示信息 -->
											<c:forEach items="${list }" var="ar" varStatus="statuss">
												<tbody>
													<tr class="">
														<td class="jive-first" nowrap="nowrap" width="1%">
															<div class="jive-bullet">
																<img src="images/read-16x16.gif" alt="已读" border="0"
																	height="16" width="16">
																<!-- div-->
															</div>
														</td>

														<td nowrap="nowrap" width="1%">
															<!-- 更新 --> <a
															href="modifyFlat?id=${ar.getId() }&title=${ar.getTitle()}&cont=${ar.getCont()}">更新</a>
															<!-- 删除 --> <a
															href="deleteFlat?rootid=${ar.getRootid() }">删除</a>
														</td>

														<td class="jive-thread-name" width="95%"><a
															id="jive-thread-1"
															href="articleDetailFlat?id=${ar.getId()}">${ar.getTitle() }</a>
														</td>
														<td class="jive-author" nowrap="nowrap" width="1%"><span
															class=""> <a
																href="http://bbs.chinajavaworld.com/profile.jspa?userID=226030">bjsxt</a>
														</span></td>
														<td class="jive-view-count" width="1%">10000</td>
														<td class="jive-msg-count" width="1%">0</td>
														<td class="jive-last" nowrap="nowrap" width="1%"><fmt:formatDate
																value="${ar.getPdate() }" pattern="yyyy-MM-dd HH:mm:ss" /><br>
															<div class="jive-last-post">
																<br> by: <a
																	href="http://bbs.chinajavaworld.com/thread.jspa?messageID=780182#780182"
																	title="jingjiangjun" style="">黄智学</a>
															</div></td>
													</tr>
												</tbody>
											</c:forEach>
										</c:otherwise>
									</c:choose>
									<!-- end -->

								</table>
							</div>
						</div>
						<div class="jive-legend"></div>
					</td>
				</tr>
			</tbody>
		</table>
		<br> <br>
	</div>
</body>

</html>