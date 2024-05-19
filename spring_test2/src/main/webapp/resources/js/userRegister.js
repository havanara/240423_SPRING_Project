document.getElementById('doubleCheck').addEventListener('click',()=>{
    const userJoinEmail = document.getElementById('e').value;

	if (userArray.includes(userJoinEmail)) {
		alert("이미 사용중인 email 입니다.");
		return;
	} else {
		alert("사용 가능한 email 입니다.");
	}
});