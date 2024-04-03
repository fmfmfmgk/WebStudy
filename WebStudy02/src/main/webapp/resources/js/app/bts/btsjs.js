/**
 * 
 */
//1.change이벤트 발생
console.log(document.btsForm.member);
let sec = document.btsForm;
sec.addEventListener("submit",(e)=>{
//2.기존 submit이벤트 중지
	e.preventDefault();
	let form = e.target
//3. 비동기로 교체

//4.
	let url = form.action; 
	let method = form.method;
	let headers = {
		"content-type" : form.enctype,
		"accept" : "text/html"
	}
//	name=value&name=value
	let formData = new FormData(form);
	let body = new URLSearchParams(formData).toString(); 	

	
	fetch(url,{
		method : method,
		headers : headers,
		body : body
	})
	.then(resp=>{
		return resp.text();
	})
	.then(txt=>{
		window['result-area'].innerHTML = txt;
	})
	.catch(e=>{
		console.error(e);
	})
})
