console.log("boardRegister.js in")

document.getElementById('trigger').addEventListener('click',()=>{
    document.getElementById('file').click();
});

//정규 표현식을 사용하여 파일의 형식을 제한
//실행파일 막기(exe, bat, sh, mis, dll, jar)
//파일 사이즈 체크(max 20mb 사이즈보다 크면 막기)
//이미지 파일만 올리기(jpg, jpeg, gif, png, bmp)

const regExp = new RegExp("\.(exe|sh|bat|mis|dll|jar)$");
const regExpImg = new RegExp("\.(jpg|jpeg|gif|png|bmp)$");
const maxSize = 1024*1024*20;

//Validation : 규칙 설정
//return 0(o) 또는 1(x)로 리턴

function fileValidation(name, fileSize){
    let fileName = name.toLowerCase(); //파일 이름을 전부 소문자로 변경
    if(regExp.test(fileName)){ //파일 확장자에 실행파일 확장자가 있다면 0 리턴
        return 0; //실행파일인지 아닌지 체크, 맞으면 0 리턴
    }else if(fileSize > maxSize){
        return 0;
    }else if(!regExpImg.test(fileName)){ //이미지 파일이 아니면 0 리턴
        return 0;
    }else{
        return 1;
    }
}

//첨부 파일에 따라 등록이 가능한지 체크
document.addEventListener('change',(e)=>{
    console.log(e.target);
    if(e.target.id === 'file'){
        //여러개의 파일이 배열로 들어옴
        const fileObject = document.getElementById('file').files;
        console.log(fileObject);

        //한번 true가 된 disabled(비활성화)는 혼자서 다시 false로 돌아올 수 없어서 바꿔줘야 함
        //그래서 새로 들어오면 버튼을 활성화 시키기
        document.getElementById('regBtn').disabled = false;

        let div = document.getElementById('fileZone');
        div.innerHTML = ''; //기존에 등록한 파일이 있다면 지우기

        let ul = `<ul class="list-group">`;
        //각각의 파일을 fileValidation에 의해 리턴 여부를 체크
        //모든 파일의 return의 값이 1이어야 가능
        //*(곱하기)로 isOK를 처리하여 모든 파일이 1인지 체크
        //하나라도 0이 들어가면 fail
        let isOK = 1;
        for(let file of fileObject){ //배열은 for of 객체는 for in
            let validResult = fileValidation(file.name, file.size); //하나의 파일에 대한 결과
            isOK *= validResult; //가능한지 안한지 여부를 먼저 체크
            ul+=`<li class="list-group-item">`;
            ul+=`<div>${validResult ? '업로드 가능' : '업로드 불가능'}</div> ${file.name}`;
            ul+=`<span class="badge text-bg-${validResult ? 'success' : 'danger'}">${file.size}</span>`;
            ul+=`</li>`;
        }
        ul+=`</ul>`;
        div.innerHTML = ul;
        //업로드가 불가능한 파일이 1개라도 있다면 파일 등록 버튼 비활성화
        if(isOK == 0){
            document.getElementById('regBtn').disabled = true; //비활성화 처리
        }
    }
});

// <ul class="list-group">
//  <li class="list-group-item"><div>업로드 가능</div>파일명
//      <span class="badge text-bg-success">파일 사이즈</span>
//  </li>
//  <li class="list-group-item"><div>업로드 불가능</div>파일명
//      <span class="badge text-bg-danger">파일 사이즈</span>
//  </li>
// </ul>