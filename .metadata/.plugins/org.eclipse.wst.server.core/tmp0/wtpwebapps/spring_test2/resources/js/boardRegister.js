console.log("boardRegister js in")

//트리거 버튼 처리
document.getElementById('trigger').addEventListener('click',()=>{
    document.getElementById('file').click();
});

//실행 파일에 대한 정규 표현식 작성 / 파일 최대 사이즈
const regExp = new RegExp("\.(exe|sh|bat|dll|jar|msi)$");
const maxSize = 1024*1024*20;

function fileValidation(fileName, fileSize){
    if(regExp.test(fileName)){
        return 0;
    }else if(fileSize > maxSize){
        return 0;
    }else{
        return 1;
    }
}

document.addEventListener('change', (e)=>{
    if(e.target.id == 'file'){ //파일에 변화가 생겼다면
        //files는 input type="file"의 element에 저장된 file의 정보를 가져오는 property
        const fileObj = document.getElementById('file').files;
        console.log(fileObj);

        document.getElementById('regBtn').disabled = false; //한번 disabled이 true 처리된건 혼자 풀지 못해 처음에 false처리를 해야함

        //등록된 file의 정보를 fileZone에 기록
        let div = document.getElementById('fileZone');
        div.innerHTML = ''; //기존에 혹시나 있을 div에 추가되어있던 값 비우기

        // ul > li로 파일 목록 추가
        // <ul class="list-group list-group-flush">
        //     <li class="list-group-item">An item</li>
        // </ul>

        //여러개의 등록 파일이 모두 검증을 통과해야하기 때문에
        //isOK를 두어서 *(곱하기)로 각각 파일이 통과할때마다 연산을 실행 -> 통과 여부 확인
        let isOk = 1; //곱해야 하니까 0이면 안됨
        let ul = `<ul class="list-group list-group-flush">`;
        for(let file of fileObj){
            //개별 파일 검증 통과 결과
            let vaildResult = fileValidation(file.name, file.size);
            isOk *= vaildResult;
            ul += `<li class="list-group-item">`;
            ul += `<div class="mb-3">`;
            ul += `${vaildResult ? '<div class="fw-bold">업로드 가능</div>' : '<div class="fw-bold text-danger">업로드 불가능</div>'}`;
            ul += `${file.name}</div>`;
            ul += `<span class="badge rounded-pill text-bg${vaildResult ? 'success' : 'danger'}">${file.size}Bytes</span>`;
            ul += `</li>`;
        }
        ul += `</ul>`;
        div.innerHTML = ul;

        if(isOk == 0){ //isOK가 0이라는건 하나라도 파일이 검증을 통과하지 못했다는 의미(버튼 비활성화 처리)
            document.getElementById('regBtn').disabled = true; //한번 disabled이 true 처리된건 혼자 풀지 못해 처음에 false처리를 해야함
        }
    }
});