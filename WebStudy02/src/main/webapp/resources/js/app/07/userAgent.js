/**
 * 
 */

window['ua-btn'].addEventListener("click", e=>{
	let agent = window.navigator.userAgent;
	const BrowserInfo = {
		EDG: "엣지",
	    CHROME:"크롬",
	    WHALE:"웨일",
	    OTHERS:"기타",
		findBrowserName:function(agent){
			agent = agent.toUpperCase();
			let browserName = this.OTHERS;
			for(let prop in this){
				if(agent.indexOf(prop) >=0){
					browserName = this[prop];
					break;
				}
			}
			return browserName;
		}
	}
	BrowserInfo['SAFARI'] = "사파리";
	
	let brName = BrowserInfo.findBrowserName(agent);
	msgArea.innerHTML = brName;
});











//let aTags = document.querySelectorAll("a.asyncA");
document.addEventListener("click",e=>{
	if(!e.target.classList.contains("asyncA")) return false;
	e.preventDefault();
	let aTag = e.target;
	
	let url = aTag.href;
	
	let method = aTag.dataset.method ?? "get";
	let headers = {
		"accept" : "text/html"
	}
	
	let options = {
		method : method,
		headers : headers
	}
	
	fetch(url,options)
	.then(resp =>{
		if(resp.ok){
			return resp.text();
		}else{
			throw new Error(`처리 실패 상태코드 : ${resp.status}`);
		}
	})
	.then(html=>{
		msgArea.innerHTML = html;
	})
	.catch()
});


//버튼을 클릭했을때 스크립트 코드 
let btn = document.getElementById("btn");
btn.addEventListener("click",function(){
	agent = navigator.userAgent.toUpperCase();
	alert(agent);
	
	let browserName;
	
	if (agent.includes("EDG")) {
		browserName = "엣지";
	}else if (agent.includes("WHALE")) {
		browserName = "웨일";
	}else if (agent.includes("CHROME")) {
		browserName = "크롬";
	}else if (agent.includes("SAFARI")) {
		browserName = "사파리";
	}else {
		browserName = "기타";
	}
	
	message = `당신의 브라우저는 ${browserName}입니다.`;
	
	let div = document.getElementById("msgArea");
	
	div.innerText = message;
	
})





































