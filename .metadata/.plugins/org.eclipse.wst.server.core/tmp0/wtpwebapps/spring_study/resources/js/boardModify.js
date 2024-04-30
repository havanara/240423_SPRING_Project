console.log("boardModify.js in");

document.addEventListener('click',(e)=>{
    if(e.target.classList.contains('file-x')){
        let uuid = e.target.dataset.uuid;
        console.log(uuid);
        removeFileToServer(uuid).then(result =>{
            if(result == 1){
                alert('파일이 삭제되었습니다.');
                e.target.closest('li').remove(); //나를 포함하고 있는 li를 지워줘
            }
        })
    }
});

//비동기 메서드 맵핑 방법 : post등록, get조회, put수정, delete삭제
async function removeFileToServer(uuid){
    try {
        const url = "/board/"+uuid;
        const config = {
            method : 'delete' //맵핑 방법
        }
        const resp = await fetch(url, config);
        const result = resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}