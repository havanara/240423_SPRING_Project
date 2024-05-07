<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../layout/header.jsp" />

<div class="container-md">
<h1>Member Modify Page</h1>
<form action="/member/modify" method="post">
	<div class="mb-3">
	  <label for="i" class="form-label">ID</label>
	  <input type="text" class="form-control" name="id" id="i" value="${ses.id }" readonly="readonly" placeholder="ID...">
	</div>
	<div class="mb-3">
	  <label for="i" class="form-label">Reg_Date</label>
	  <input type="text" class="form-control" name="reg_date" id="i" value="${ses.reg_date }" readonly="readonly" placeholder="ID...">
	</div>
	<div class="mb-3">
	  <label for="i" class="form-label">Last_Login</label>
	  <input type="text" class="form-control" name="last_login" id="i" value="${ses.last_login }" readonly="readonly" placeholder="ID...">
	</div>
	<div class="mb-3">
	  <label for="p" class="form-label">PassWord</label>
	  <input type="password" class="form-control" name="pw" id="p" placeholder="PassWord...">
	</div>
	<div class="mb-3">
	  <label for="n" class="form-label">Name</label>
	  <input type="text" class="form-control" name="name" id="n" value="${ses.name }" placeholder="Name...">
	</div>
	<div class="mb-3">
	  <label for="e" class="form-label">E-Mail</label>
	  <input type="email" class="form-control" name="email" id="e" value="${ses.email }" placeholder="example@test.com...">
	</div>
	<div class="mb-3">
	  <label for="h" class="form-label">Home</label>
	  <input type="text" class="form-control" name="home" id="h" value="${ses.home }" placeholder="Home...">
	</div>
	<div class="mb-3">
	  <label for="a" class="form-label">Age</label>
	  <input type="text" class="form-control" name="age" id="a" value="${ses.age }" placeholder="Age...">
	</div>

	<button type="submit" class="btn btn-primary">수정</button>
	<a href="/member/delete"><button type="button" class="btn btn-secondary">회원탈퇴</button></a>
</form>

</div>

<jsp:include page="../layout/footer.jsp" />