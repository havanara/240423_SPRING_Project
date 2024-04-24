<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:include page="../layout/header.jsp" />

<div class="container-md">
<h1>Board Detail Page</h1>
	<div class="mb-3">
	  <label for="n" class="form-label">bno</label>
	  <input type="text" class="form-control" name="bno" id="n" value="${bvo.bno }" readonly="readonly" placeholder="Bno...">
	</div>
	<div class="mb-3">
	  <label for="t" class="form-label">title</label>
	  <input type="text" class="form-control" name="title" id="t" value="${bvo.title }" readonly="readonly" placeholder="Title...">
	</div>
	<div class="mb-3">
	  <label for="w" class="form-label">writer</label>
	  <input type="text" class="form-control" name="writer" id="w" value="${bvo.writer }" readonly="readonly" placeholder="Writer...">
	</div>
	<div class="mb-3">
	  <label for="r" class="form-label">reg_datd</label>
	  <input type="text" class="form-control" name="reg_date" id="r" value="${bvo.reg_date }" readonly="readonly" placeholder="Reg_date...">
	</div>
	<div class="mb-3">
	  <label for="c" class="form-label">content</label>
	  <textarea class="form-control" name="content" id="c" aria-label="With textarea" readonly="readonly">${bvo.content }</textarea>
	</div>
	<a href="/board/modify?bno=${bvo.bno }"><button type="button" class="btn btn-warning">수정</button></a>
	<a href="/board/remove?bno=${bvo.bno }"><button type="button" class="btn btn-danger">삭제</button></a>
	<a href="/board/list"><button type="button" class="btn btn-primary">list</button></a>
</div>

<jsp:include page="../layout/footer.jsp" />

</body>
</html>