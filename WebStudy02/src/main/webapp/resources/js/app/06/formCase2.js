/**
 * 
 */
// case1 : a 태그의 요청을 비동기로
let aTags = document.querySelectorAll("a");
//console.log(aTags);
aTags.forEach(v=>{
	v.addEventListener("click",e=>{
		e.preventDefault();
		let url = e.target.href;
		let p5 = v.dataset.p5;
		let options = {
			method:"post",
			headers:{
				"Aeccpt" : "application/json",
				"content-type" : "application/x-www-form-urlencoded"
			},
			body : "p5="+p5
		};
		fetch(url,options)
			.then(resp=>{
				if(resp.ok){
					return resp.json;
				}else{
					throw new Error(`요청 처리 실패, 상태코드 : ${resp.status}`);
				}
			})
			.then(obj=>{
				console.log(obj);
			})
			.catch(err=>console.log(err))
	})
})


//case2 : form 의 전송 이벤트를 비동기로
let forms = document.forms;
forms[0].addEventListener("submit",e=>{
	e.preventDefault();
	let form = e.target;
	
	//request line
	let url = form.action;
	let method = form.method;
	
	//request header
	let headers = {
		"content-type" : form.enctype,
		"accept" : "text/html" 
	}
	URLSearchParams()
	//request body
	let formData = new FormData(form);
	console.log(new URLSearchParams(formData).toString());
	let body = new URLSearchParams(formData).toString();
	
	let options = {
		method : method,
		headers : headers
	}
	
	if(method == "get"){
		url = `${url}?${body}`;
	}else{
		options.body = body;
	}
	
	fetch(url,options)
		.then((resp)=>{
			if(resp.ok){
				return resp.text();
			}else{
				throw new Error("에러 발생, 상태코드 : ${resp.status}");
			}
		})
		.then((html)=>{
			//document.body.append(html);
			//document.body.innerHTML = document.body.innerHTML + html;
			//resultArea.innerHTML = html; //(그다지 좋은 방식은 아님)
		})
		.catch((err)=>{
			console.log(err);
		})
})





























