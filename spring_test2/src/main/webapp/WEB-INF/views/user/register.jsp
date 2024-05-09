<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<jsp:include page="../layout/header.jsp" />

<div class="container-md">
<h1>User Join Page</h1>
<form action="/user/register" method="post">

	<div class="mb-3">
	  <label for="e" id="emailLabel" class="form-label">e-mail</label>
	  <input type="email" class="form-control" name="email" id="e" placeholder="example@test.com...">	  
	  <button type="submit" id="doubleCheck" class="btn btn-success">중복확인</button>
	
	</div>
	<div class="mb-3">
	  <label for="p" class="form-label">passWord</label>
	  <input type="password" class="form-control" name="pwd" id="p" placeholder="passWord...">
	</div>
	<div class="mb-3">
	  <label for="n" class="form-label">nickName</label>
	  <input type="text" class="form-control" name="nickName" id="n" placeholder="nickName...">
	</div>

	<button type="submit" class="btn btn-primary">JOIN</button>
</form>

</div>

<script type="text/javascript" src="resources/js/userRegister.js">

</script>

<jsp:include page="../layout/footer.jsp" />