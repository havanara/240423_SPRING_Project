<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../layout/header.jsp" />

<div class="container-md">
	<h1>Board List Page</h1>
 	<span>총 게시글 수 : ${ph.totalCount }</span>
	
	<!-- 검색 라인 -->
	<!-- post 처리하지 않는 이유 : getmapping 하나에 한번에 처리하기 위해서(controller) -->
	<!-- post 처리 해도 안되는건 아니지만 일을 두번해야함 -->
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
				<th>순번</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
				<th>첨부파일수</th>
			</tr>
		</thead>
		<tbody class="table-group-divider">
		<c:forEach items="${list }" var="bvo">
			<tr>
				<td>${bvo.bno }</td>
				<td><a href="/board/detail?bno=${bvo.bno }">${bvo.title } (${bvo.cmtQty })</a></td>
				<td>${bvo.writer }</td>
				<td>${bvo.regDate }</td>
				<td>${bvo.readCount }</td>
				<td>${bvo.hasFile }</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
	<!-- 페이지네이션 라인 -->
	<!-- 이전, 다음 버튼 없애고 싶으면 c:if 처리 -->
	<!-- 이전, 다음 버튼 비활성화 시키고 싶으면 li class에 disabled 처리 -->
 	<nav aria-label="Page navigation example">
		<ul class="pagination justify-content-center">
				<li class="page-item ${ph.prev eq false ? 'disabled' : ''}">
				<!-- href="/board/list?pageNo=${ph.startPage-1 } -->
				<!-- ex)현재 11페이지인경우 이전 버튼을 누르면 10page로 이동 -->
					<a class="page-link" href="/board/list?pageNo=${ph.startPage-1 }&qty=${ph.pgvo.qty}
					&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}">이전</a>
				</li>
<%-- 			<c:if test="${ph.prev }">
			</c:if> --%>
			
			<c:forEach begin="${ph.startPage }" end="${ph.endPage }" var="i">
				<li class="page-item ${ph.pgvo.pageNo eq i? 'active' : ''}"><a class="page-link " href="/board/list?pageNo=${i }&qty=${ph.pgvo.qty}
				&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}">${i }</a></li>
			</c:forEach>
			
				<li class="page-item ${ph.next eq false ? 'disabled' : ''}">
					<a class="page-link" href="/board/list?pageNo=${ph.endPage+1 }&qty=${ph.pgvo.qty}
					&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}">다음</a>
				</li>
<%-- 			<c:if test="${ph.next }">
			</c:if> --%>
		</ul>
	</nav>
</div>

<jsp:include page="../layout/footer.jsp" />