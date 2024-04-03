/**
 * 
 */
document.addEventListener("DOMContentLoaded", ()=>{
   // json data 를 수신하고, csr 방식으로 html 소스 생성
   fetch("", {
      headers : {
         accept : "application/json"
      }
   }).then(resp=>resp.json())
   .then(({headers, propsName, list}) => {
      console.log(headers, propsName, list);
      let trTag = `<tr>${headers.map(cn=>`<th>${cn}</th>`).join("")}</tr>`
      let trTags = "";
      for(let map of list){
         trTags += `<tr>${propsName.map(pn=>`<td>${map[pn]}</td>`).join("")}</tr>`;
      }
      window['head-area'].innerHTML = trTag;
      window['body-area'].innerHTML = trTags;
   }).catch(err=>console.error(err));
});