<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../layout/header.jsp" />

<div class="container-md">
	<h1>Board List Page</h1>
	<span>총 게시글 수 : ${ph.totalCount }</span>
	
	<!-- 검색 라인 -->
	<form action="/board/list" method="get" class="row g-3">
		<div class="input-group mb-3" style="width: 500px;">
			<select class="form-select" name="type" aria-label="Default select example">
			<!-- == 처리해도 되고 eq 처리해도 됨 둘다 같으면 이라는 같은 의미 -->
			  <option ${ph.pgvo.type == null? 'selected' : ''} selected>Choose...</option>
			  <option value="t" ${ph.pgvo.type eq 't'? 'selected' : ''}>title</option>
			  <option value="w" ${ph.pgvo.type eq 'w'? 'selected' : ''}>writer</option>
			  <option value="c" ${ph.pgvo.type eq 'c'? 'selected' : ''}>content</option>
			  <option value="tc" ${ph.pgvo.type eq 'tc'? 'selected' : ''}>title&content</option>
			  <option value="wc" ${ph.pgvo.type eq 'wc'? 'selected' : ''}>writer&content</option>
			  <option value="tw" ${ph.pgvo.type eq 'tw'? 'selected' : ''}>title&writer</option>
			  <option value="twc" ${ph.pgvo.type eq 'twc'? 'selected' : ''}>all</option>
			</select>
			<input class="form-control" name="keyword" type="text" value="${ph.pgvo.keyword }"
			placeholder="search" aria-label="default input example">
			<input type="hidden" name="pageNo" value="1">
			<input type="hidden" name="qty" value="10">
			<button type="submit" class="btn btn-success">검색</button>
		</div>
	</form>
	
	<table class="table table-hover">
		<thead>
			<tr>
				<th>#</th>
				<th>title</th>
				<th>writer</th>
				<th>reg_date</th>
				<th>read_count</th>
			</tr>
		</thead>
		<tbody class="table-group-divider">
		<c:forEach items="${list }" var="bvo">
			<tr>
				<td>${bvo.bno }</td>
				<td><a href="/board/detail?bno=${bvo.bno }">${bvo.title }</a></td>
				<td>${bvo.writer }</td>
				<td>${bvo.reg_date }</td>
				<td>${bvo.read_count }</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
	<!-- 페이지네이션 라인 -->
	<nav aria-label="Page navigation example">
		<ul class="pagination justify-content-center">
			<c:if test="${ph.prev }">
				<li class="page-item">
					<a class="page-link" href="/board/list?pageNo=${ph.startPage-1 }&qty=${ph.pgvo.qty}
					&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}">이전</a>
				</li>
			</c:if>
			
			<c:forEach begin="${ph.startPage }" end="${ph.endPage }" var="i">
				<li class="page-item"><a class="page-link" href="/board/list?pageNo=${i }&qty=${ph.pgvo.qty}
				&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}">${i }</a></li>
			</c:forEach>
			
			<c:if test="${ph.next }">
				<li class="page-item">
					<a class="page-link" href="/board/list?pageNo=${ph.endPage+1 }&qty=${ph.pgvo.qty}
					&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}">다음</a>
				</li>
			</c:if>
		</ul>
	</nav>
</div>

<jsp:include page="../layout/footer.jsp" />