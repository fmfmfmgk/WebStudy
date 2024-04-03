/**
 * 
 */

//contextPath를 가져오는 방법1
const cPath = document.body.dataset.contextPath;

//방법2 form태그의 action 이용
var forms = document.forms;
var url1 = forms[0].action;

const log = console.log;
document.querySelectorAll("li.folder>a").forEach(a=>{
	a.addEventListener("click",function(e){
		e.preventDefault();
		
		let type = a.dataset.click;
		let url = `${a.href}&type=${type}`;
		let method = "get";
		let headers = {
			"accept" : "application/json"
		}
		fetch(url,{
			method : method,
			headers : headers
		})
		.then(resp=>{
			return resp.json();
		})
		.then(outter=>{
			let jsonObj = outter.wrapperList;
			let txt = "<ul class='col-6'>";
			for(let file of jsonObj){
				txt += `<li data-name="${file.name}" id="${file.path}" class="${file.file? 'file' : 'folder'}">`;
				txt += `${file.name}</li>`;
			}
			txt+= "</ul>";
			
			/*window['right-area'].innerHTML = txt;*/
			document.querySelector(".card.col-6").innerHTML = txt;
		})
		.catch(e=>{
			console.log(e);
		})
	})
	a.addEventListener("dblclick",function(e){
		e.preventDefault();
		let type = a.dataset.dblclick;
		let url = `${a.href}&type=${type}`;
		window.location.href = url;
	})
});



document.querySelector("#right-area").addEventListener('click', e => {
	if(!e.target.classList.contains('file'))return false; 
		let url = `${cPath}/case2/fileInfo`;
		log(cPath);
		let method = "get";
		let headers = {
			"accept":"application/json"
		};
		let urlSearchParams = new URLSearchParams();
		let path = e.target.id;
		urlSearchParams.append("path",path)
		let queryString = urlSearchParams.toString();
		fetch(`${url}?${queryString}`,{
			method : method,
			headers : headers
		}).then(resp=>resp.json())
		.then(jsonObj=>{
			log(jsonObj.size);
			e.target.innerHTML = e.target.dataset.name + "," + jsonObj.size;
		})
		.catch(err=>console.error(err));
	});
