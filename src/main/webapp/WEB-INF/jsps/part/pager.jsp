<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
	要求：
		1.引入jstl标签库：
			-> < %@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		2.实现Pager.java
			-> import java.util.ArrayList;
			-> import java.util.List;
			-> 
			-> public class Pager<T> {
			-> 	/** 总记录数 */
			-> 	private long totalRecd;
			-> 	/** 起始记录 */
			-> 	private int startPageIdx;
			-> 	/** 结束记录 */
			-> 	private int endPageIdx;
			-> 	/** 当前第一页 */
			-> 	private int currPageIdx;
			-> 	/** 每页显示的记录数 */
			-> 	private int pageSize = 10;
			-> 	/** 总页数 */
			-> 	private int totalPageNum;
			-> 	/** 数据集合 */
			-> 	private List<T> entityList = new ArrayList<T>();
			-> 
			-> 	public Pager(long totalRecd, int currPageIdx, List<T> entityList, int pageSize) {
			-> 		this.totalRecd = totalRecd;
			-> 		this.currPageIdx = currPageIdx;
			-> 		this.pageSize = pageSize;
			-> 		this.entityList = entityList;
			-> 
			-> 		setOtherParams();
			-> 	}
			-> 
			-> 	public Pager(long totalRecd, int currPageIdx, List<T> entityList) {
			-> 		this.totalRecd = totalRecd;
			-> 		this.currPageIdx = currPageIdx;
			-> 		this.entityList = entityList;
			-> 
			-> 		setOtherParams();
			-> 	}
			-> 
			-> 	private void setOtherParams() {
			-> 		this.totalPageNum = (int) Math.ceil(1.0 * totalRecd / pageSize);
			-> 
			-> 		this.startPageIdx = this.currPageIdx - (this.pageSize / 2 - 1);
			-> 		this.endPageIdx = this.currPageIdx + this.pageSize / 2;
			-> 		if (this.startPageIdx < 1) {
			-> 			this.startPageIdx = 1;
			-> 			this.endPageIdx = pageSize;
			-> 			if (this.endPageIdx > this.totalPageNum) {
			-> 				this.endPageIdx = this.totalPageNum;
			-> 			}
			-> 		}
			-> 		if (this.endPageIdx > this.totalPageNum) {
			-> 			this.endPageIdx = this.totalPageNum;
			-> 			this.startPageIdx = endPageIdx - pageSize + 1;
			-> 			if (this.startPageIdx < 1) {
			-> 				this.startPageIdx = 1;
			-> 			}
			-> 		}
			-> 	}
			-> 
			-> 	public void setTotalRecd(int totalRecd) {
			-> 		this.totalRecd = totalRecd;
			-> 	}
			-> 
			-> 	public void setCurrPageIdx(int currPageIdx) {
			-> 		this.currPageIdx = currPageIdx;
			-> 	}
			-> 
			-> 	public void setEntityList(List<T> entityList) {
			-> 		this.entityList = entityList;
			-> 	}
			-> 
			-> 	public long getTotalRecd() {
			-> 		return totalRecd;
			-> 	}
			-> 
			-> 	public int getStartPageIdx() {
			-> 		return startPageIdx;
			-> 	}
			-> 
			-> 	public int getEndPageIdx() {
			-> 		return endPageIdx;
			-> 	}
			-> 
			-> 	public int getCurrPageIdx() {
			-> 		return currPageIdx;
			-> 	}
			-> 
			-> 	public int getPageSize() {
			-> 		return pageSize;
			-> 	}
			-> 
			-> 	public int getTotalPageNum() {
			-> 		return totalPageNum;
			-> 	}
			-> 
			-> 	public List<T> getEntityList() {
			-> 		return entityList;
			-> 	}
			-> }
		
		3.服务器端： request.setAttribute("pager", pager)
		4.jsp包含：
			-> 		<jsp:include page="/partial/pager.jsp">
			-> 			<jsp:param value="list.action?name=jack&" name="url"/>
			-> 			<jsp:param value="currPageIdx" name="pageIdxKeyWord"/>
			-> 		</jsp:include>
 --%>
<ul class="pagination pagination-sm">
	<!-- 上一页 -->
	<c:if test="${requestScope.pager.currPageIdx == 1 }">
		<li class="disabled">
			<span>&laquo;</span>
		</li>
	</c:if>
	<c:if test="${requestScope.pager.currPageIdx != 1 }">
		<li>
			<a href="${param.url }${param.pageIdxKeyWord }=${requestScope.pager.currPageIdx - 1}">&laquo;</a>	
		</li>
	</c:if>
	
	<!-- 页码 -->
	<c:forEach begin="${requestScope.pager.startPageIdx}" end="${requestScope.pager.endPageIdx}" step="1" var="idx">
		<c:if test="${idx == requestScope.pager.currPageIdx }">
			<li class="active">
				<a href="javascript:void(0);">${idx }</a>
			</li>
		</c:if>
		<c:if test="${idx != requestScope.pager.currPageIdx }">
			<li>
				<a href="${param.url }${param.pageIdxKeyWord }=${idx }">${idx }</a>
			</li>
		</c:if>
	</c:forEach>
	
	<!-- 下一页 -->
	<c:if test="${requestScope.pager.currPageIdx == requestScope.pager.totalPageNum}">
		<li class="disabled"><span>&raquo;</span></li>
	</c:if>
	<c:if test="${requestScope.pager.currPageIdx != requestScope.pager.totalPageNum}">
		<li>
			<a href="${param.url }${param.pageIdxKeyWord }=${requestScope.pager.currPageIdx + 1}">&raquo;</a>	
		</li>
	</c:if>
</ul>
<span>共${pager.totalPageNum }页,总计${pager.totalRecd }条记录</span>
