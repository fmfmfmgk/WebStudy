//1. form을 셀렉팅
//2. form에 대한 이벤트 핸들러 (sumit)
//3. 기본액션 취소 프리밴트디폴트
//4. fetch를 이용해 비동기요청
//5. 라인 해더 바디(method = post) content-type
//6. 응답 데이터 확인 및 div태그에 값 넣기 (innerHTML)
/// WebStudy01/src/main/webapp/WEB-INF/views/mbti/mbtiForm.jsp

console.log(window['mbti-form']);
window['mbti-form'].addEventListener("submit",(e)=>{
	e.preventDefault();
	
	let form = e.target;
	
	let url = form.action;
	let method = form.method;
	let headers = {
		"content-type" : form.enctype,
		"Accept" : "text/html"
	}
	let fd = new FormData(form);
	let queryString = new URLSearchParams(fd).toString();
	let body = queryString;
	
	fetch(url,{
		method : method,
		headers : headers,
		body : body
	})
	.then(resp=>{
		return resp.text();
	})
	.then(html=>{
		//let newDocument = new DOMParser().parseFromString(html, headers.Accept);
		//let element = newDocument.querySelector("pre");
		//window['mbti-Area'].append = element;
		window['mbti-Area'].innerHTML = html;
	})
	.catch(()=>{
		
	});
})



































