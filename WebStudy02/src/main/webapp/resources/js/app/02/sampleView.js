/**
 * 비동기 요청을 1초마다 발생시켜서 서버의 시간을 출력하시오. 
 */

setInterval(()=>{
	clientArea.innerHTML = Date.now();
	fetch("../now")
	.then(resp=>resp.text())
	.then(n=>serverArea.innerHTML=n)
}, 1000);




