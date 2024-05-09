<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
  	
<jsp:include page="../layout/header.jsp" />

<div class="container-md">
<h1>User Modify Page</h1>

<sec:authentication property="principal.uvo.email" var="authEmail"/>
<sec:authentication property="principal.uvo.nickName" var="authNick"/>
<sec:authentication property="principal.uvo.regDate" var="authReg"/>
<sec:authentication property="principal.uvo.lastLogin" var="authLast"/>

<form action="/user/modify" method="post">
<input type="hidden" name="email" value="${authEmail }">
	<div class="mb-3">
	  <label for="n" class="form-label">nickName</label>
	  <input type="text" class="form-control" name="nickName" id="n" value="${authNick }">
	</div>
	<div class="mb-3">
	  <label for="e" class="form-label">E-Mail</label>
	  <input type="email" class="form-control" name="email" id="e" value="${authEmail }" readonly="readonly">
	</div>
	<div class="mb-3">
	  <label for="p" class="form-label">PassWord</label>
	  <input type="password" class="form-control" name="pwd" id="p">
	</div>
	<div class="mb-3">
	  <label for="r" class="form-label">RegDate</label>
	  <input type="text" class="form-control" name="regDate" id="r" value="${authReg }" readonly="readonly">
	</div>
	<div class="mb-3">
	  <label for="l" class="form-label">LastLogin</label>
	  <input type="text" class="form-control" name="lastLogin" id="l" value="${authLast }" readonly="readonly">
	</div>

	<button type="submit" class="btn btn-primary">수정</button>
	<button type="button" class="btn btn-secondary" id="delBtn">회원탈퇴</button>
</form>

</div>

<script type="text/javascript">
document.getElementById('delBtn').addEventListener('click',()=>{
    let check = confirm("정말 탈퇴하시겠습니까?");
    //confirm창에서 yes 누르면 true / no 누르면 false
    console.log(check);
    if(check){
        location.href = "/user/remove";
    }
});
</script>

<jsp:include page="../layout/footer.jsp" />