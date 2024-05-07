<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<jsp:include page="../layout/header.jsp" />

<div class="container-md">
<h1>Board Modify Page</h1>
<c:set value="${bdto.bvo }" var="bvo"></c:set>
<form action="/board/modify" method="post" enctype="multipart/form-data">
	<div class="mb-3">
	  <label for="n" class="form-label">bno</label>
	  <input type="text" class="form-control" name="bno" id="n" value="${bvo.bno }" readonly="readonly" placeholder="Bno...">
	</div>
	<div class="mb-3">
	  <label for="t" class="form-label">title</label>
	  <input type="text" class="form-control" name="title" id="t" value="${bvo.title }" placeholder="Title...">
	</div>
	<div class="mb-3">
	  <label for="w" class="form-label">writer</label>
	  <input type="text" class="form-control" name="writer" id="w" value="${bvo.writer }" readonly="readonly" placeholder="Writer...">
	</div>
	<div class="mb-3">
	  <label for="r" class="form-label">regDatd</label>
	  <input type="text" class="form-control" name="reg_date" id="r" value="${bvo.regDate }" readonly="readonly" placeholder="Reg_date...">
	</div>
	<div class="mb-3">
	  <label for="c" class="form-label">content</label>
	  <textarea class="form-control" name="content" id="c" aria-label="With textarea">${bvo.content }</textarea>
	</div>
		
	<!-- 파일 표시 -->
	<c:set value="${bdto.flist }" var="flist"></c:set> 
	<div class="mb-3">
		<ul class="list-group list-group-flush">
		<!-- 파일 개수만큼 li를 반복하여 파일 표시 타입이 1인 경우만 표시 -->
		<!-- li 안에 div를 넣고 거기에 img 표시 -->
		<!-- div를 넣고 파일이름, 작성일, span 넣어 size 표기 -->
			<c:forEach items="${flist }" var="fvo">
	  			<li class="list-group-item">
	  				<c:choose>
	  					<c:when test="${fvo.fileType > 0}">
							<div>
								<img alt="" src="/up/${fvo.saveDir }/${fvo.uuid}_th_${fvo.fileName}">
							</div>
	  					</c:when>
	  					<c:otherwise>
	  						<div>
	  							<svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-file-earmark-zip-fill" viewBox="0 0 16 16">
									<path d="M5.5 9.438V8.5h1v.938a1 1 0 0 0 .03.243l.4 1.598-.93.62-.93-.62.4-1.598a1 1 0 0 0 .03-.243"/>
									<path d="M9.293 0H4a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h8a2 2 0 0 0 2-2V4.707A1 1 0 0 0 13.707 4L10 .293A1 1 0 0 0 9.293 0M9.5 3.5v-2l3 3h-2a1 1 0 0 1-1-1m-4-.5V2h-1V1H6v1h1v1H6v1h1v1H6v1h1v1H5.5V6h-1V5h1V4h-1V3zm0 4.5h1a1 1 0 0 1 1 1v.938l.4 1.599a1 1 0 0 1-.416 1.074l-.93.62a1 1 0 0 1-1.109 0l-.93-.62a1 1 0 0 1-.415-1.074l.4-1.599V8.5a1 1 0 0 1 1-1"/>
								</svg>
	  						</div>
	  					</c:otherwise>
	  				</c:choose>
	  				<div>
	  					<!-- 파일이름, 작성일, size -->
	  					<div>${fvo.fileName }</div>
	  					${fvo.regDate }
	  					<span class="badge rounded-pill text-bg-dark">${fvo.fileSize }Byte</span>
	  					<button type="button" data-uuid="${fvo.uuid }" class="btn btn-outline-danger btn-sm file-x" >X</button>
	  				</div>
				</li>
			</c:forEach>
		</ul>
	</div>
	<br> <hr>
	
	<!-- 파일 입력 라인 추가 -->
	<div class="mb-3">
	  <label for="file" class="form-label">files...</label>
	  <!-- multiple -> input 한개에 여러개의 값을 넣을 수 있음 -->
	  <input type="file" class="form-control" name="files" id="file" multiple="multiple" style="display: none"><br>
	  <button type="button" class="btn btn-outline-success" id="trigger">FileUpload...</button>
	</div>
	
	<!-- 파일 목록 표시라인 -->
	<div class="mb-3" id="fileZone"></div>
	
	<button type="submit" class="btn btn-warning" id="regBtn">수정</button>
	<a href="/board/list"><button type="button" class="btn btn-primary">list</button></a>
</form>
</div>

<script type="text/javascript" src="/re/js/boardModify.js"></script>
<script type="text/javascript" src="/re/js/boardRegister.js"></script>

<jsp:include page="../layout/footer.jsp" />

</body>
</html>