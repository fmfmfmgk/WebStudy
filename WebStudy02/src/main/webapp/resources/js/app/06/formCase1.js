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
		let options = {
			headers:{
				"Aeccpt" : "application/json"
			}
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
			let parser = new DOMParser();
			let newDoc =  parser.parseFromString(html,'text/html');
			let h4Element = newDoc.querySelector("h4");
			document.body.append(h4Element);
		})
		.catch((err)=>{
			console.log(err);
		})
})





























