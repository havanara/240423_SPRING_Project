<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  	<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
  	
<jsp:include page="../layout/header.jsp" />

<div class="container-md">
<h1>User Modify Page</h1>
<form action="/user/modify" method="post">
	<div class="mb-3">
	  <label for="n" class="form-label">nickName</label>
	  <input type="text" class="form-control" name="nickName" id="n" value="${ses.nickName }" readonly="readonly">
	</div>
	<div class="mb-3">
	  <label for="e" class="form-label">E-Mail</label>
	  <input type="email" class="form-control" name="email" id="e" value="${ses.email }" placeholder="example@test.com...">
	</div>
	<div class="mb-3">
	  <label for="p" class="form-label">PassWord</label>
	  <input type="password" class="form-control" name="pwd" id="p" placeholder="PassWord...">
	</div>
	<div class="mb-3">
	  <label for="r" class="form-label">RegDate</label>
	  <input type="text" class="form-control" name="regDate" id="r" value="${ses.regDate }" readonly="readonly">
	</div>
	<div class="mb-3">
	  <label for="l" class="form-label">LastLogin</label>
	  <input type="text" class="form-control" name="lastLogin" id="l" value="${ses.lastLogin }" readonly="readonly">
	</div>

	<button type="submit" class="btn btn-primary">수정</button>
	<a href="#"><button type="button" class="btn btn-secondary">회원탈퇴</button></a>
</form>

</div>

<jsp:include page="../layout/footer.jsp" />