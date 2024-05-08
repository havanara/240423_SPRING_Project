<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  	<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
    
<jsp:include page="../layout/header.jsp" />

<h1>User list Page</h1>

<c:set value="${bdto.bvo }" var="bvo"></c:set>

<div class="row row-cols-1 row-cols-md-3">
 <c:forEach items="${list }" var="uvo">      
  <div class="col" style="max-width: 350px;">
    <div class="card h-100">
		<img src="/re/image/프로필 이미지.png" class="card-img-top" alt="프로필 없음">
     	<div class="card-body">
        <h5 class="card-title">${uvo.nickName }(${uvo.email})</h5>
        <p class="card-text">최근 접속일 : ${uvo.lastLogin}</p>
        <p class="card-text text-danger">
        (<c:forEach items="${uvo.authList }" var="authList">
        	${authList.auth }
        </c:forEach>)
        </p>
      	</div>
    </div>
   </div>
 </c:forEach>
</div>

<jsp:include page="../layout/footer.jsp" />

</body>
</html>