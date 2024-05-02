console.log("comment js in")

//cmtAddBtn 버튼 클릭 시 bno, writer, content를 비동기로 DB에 넣기

document.getElementById('cmtAddBtn').addEventListener('click',()=>{
    // value : input, checkbox, option...
    // innertext : div, span...
    const cmtText = document.getElementById('cmtText').value;
    const cmtWriter = document.getElementById('cmtWriter').innerText;

    if(cmtText == '' || cmtText == null){
        alert("댓글을 입력해주세요.");
        document.getElementById('cmtText').focus();
        return false;
    }else{
        let cmtData = {
            bno : bnoVal,
            writer : cmtWriter,
            content : cmtText
        }
        console.log(cmtData);

        postCommentToServer(cmtData).then(result => {
            console.log(result);
            if(result == '1'){
                alert("댓글이 등록되었습니다.");
                document.getElementById('cmtText').value = "";
                //화면에 뿌리기
                spreadCommentList(bnoVal);
            }
        });
    }
});

async function postCommentToServer(cmtData){
    try {
        const url = "/comment/post";
        const config = {
            method : "post",
            headers : {
                'content-type' : 'application/json; charset=utf-8'
            },
            body : JSON.stringify(cmtData)
        };

        const resp = await fetch(url,config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    } 
}

// <!-- 댓글 출력 라인 -->	
// <ul class="list-group list-group-flush" id="cmtListArea">
//       <li class="list-group-item">
//            <div class="input-group mb-3">
//                <div class="fw-bold">Writer</div>
//                content
//            </div>
//            <span class="badge rounded-pill text-bg-warning">regDate</span>
//       </li>
// </ul>

//댓글 뿌리기
async function getCommentListFromServer(bno, page){
    try {
        const resp = await fetch("/comment/"+bno+"/"+page);
        const result = await resp.json();
        return result;
    } catch (error) {
        console.log(error);
    }
}
// async function getCommentListFromServer(bno){
//     try {
//         const resp = await fetch("/comment/"+bno);
//         const result = await resp.json();
//         return result;
//     } catch (error) {
//         console.log(error);
//     }
// }
function spreadCommentList(bno, page=1){
    getCommentListFromServer(bno, page).then(result => {
        console.log(result);
        //댓글 뿌리기
        const ul = document.getElementById('cmtListArea');
        if(result.cmtList.length > 0){
            if(page == 1){
                ul.innerHTML = '';
            }
            for(let i=0; i<result.cmtList.length; i++){ //for(let cvo of result) 처리해도 됨
                let add = `<ul class="list-group list-group-flush" id="cmtListArea">`;
                add += `<li class="list-group-item">`;
                add += `<div class="input-group mb-3"> no. ${result.cmtList[i].cno} | `;
                add += `<div class="fw-bold">${result.cmtList[i].writer }</div><br>`;
                add += `${result.cmtList[i].content }`;
                add += `</div>`;
                add += `<span class="badge rounded-pill text-bg-warning">${result.cmtList[i].regDate}</span>`;
                //수정, 삭제 버튼
                add += `<button type="button" class="btn btn-outline-warning btn-sm mod" data-bs-toggle="modal" data-bs-target="#myModal">수정</button>`;
                add += `<button type="button" class="btn btn-outline-danger btn-sm del">삭제</button>`;
                add += `</li> </ul>`;
                ul.innerHTML += add;
            }
            //더보기 버튼 코드
            let moreBtn = document.getElementById('moreBtn');
            console.log(moreBtn);
            
            //moreBtn이 표시되는 조건
        }else{
            ul.innerHTML = `<li class="list-group-item">댓글이 없습니다.</li>`;
        }
    });
}

document.addEventListener('click',(e)=>{
    if(e.target.id == 'moreBtn'){
        let page = parseInt(e.target.dataset.page);
        spreadCommentList(bnoVal,page);
    }
});


// function spreadCommentList(bno){
//     getCommentListFromServer(bno).then(result => {
//         console.log(result);
//         const ul = document.getElementById('cmtListArea');
//         if(result.length > 0){
//             ul.innerHTML = "";
//             for(let i=0; i<result.length; i++){ //for(let cvo of result) 처리해도 됨
//                 let add = `<ul class="list-group list-group-flush" id="cmtListArea">`;
//                 add += `<li class="list-group-item">`;
//                 add += `<div class="input-group mb-3"> no. ${result[i].cno} / `;
//                 add += `<div class="fw-bold">${result[i].writer }</div><br>`;
//                 add += `${result[i].content }`;
//                 add += `</div>`;
//                 add += `<span class="badge rounded-pill text-bg-warning">${result[i].regDate}</span>`;
//                 //수정, 삭제 버튼
//                 add += `<button type="button" class="btn btn-outline-warning btn-sm mod" data-bs-toggle="modal" data-bs-target="#myModal">수정</button>`;
//                 add += `<button type="button" class="btn btn-outline-danger btn-sm del">삭제</button>`;
//                 add += `</li> </ul>`;
//                 ul.innerHTML += add;
//             }
//         }else{
//             ul.innerHTML = `<li class="list-group-item">댓글이 없습니다.</li>`;
//         }
//     });
// }
