/**
 * 
 */
const SPEED = 100;
const contextPath = document.body.dataset.contextPath;
class SessionTimer2{
	
	constructor(timeout, element) {
		this.timeout = timeout;
		this.element = element;
		this.element.sessionTimer = this;
		this.init();
	}
	
	createMessageArea() {
		let source = `
			<div style='display:none;'>
			세션 연장할텨?
			<button class = "control" data-ts-state="true">예</button>
			<button class = "control">아니오</button>
			</div>
		`;
		this.msgArea = new DOMParser().parseFromString(source, 'text/html').body.children[0];
		this.msgArea.addEventListener("click", function(e) {
			if (e.target.dataset.tsState) {
				this.reset();
			}
			this.msgArea.style.display = "none";
		}.bind(this));
		this.element.after(this.msgArea);
	}

	init() {
		fetch("",{method:"head"});
		this.timer = this.timeout;
		this.element.innerHTML = this.timeout;
		this.createMessageArea();

		let obj = this;
		this.timeoutId = setTimeout(function() {
			obj.msgArea.style.display = "block";
		}, (this.timeout - 60) * SPEED);

		this.intervalId = setInterval(() => {
			if (obj.timer > 0) {
				obj.timer--;
			}
			else {
				obj.destroy();
			}
			obj.element.innerHTML = obj.timer;
		}, SPEED);
	}

	destroy() {
		clearInterval(this.intervalId);
		clearTimeout(this.timeoutId);
		//document.body.removeChild(this.msgArea);
		this.msgArea.remove();
		
	}

	reset(){
		this.destroy();
		this.init();
	}
}

const SessionTimer = function(timeout, element) {
	this.timeout = timeout;
	this.element = element;
	this.element.sessionTimer = this;

	this.createMessageArea = function() {
		let source = `
			<div style='display:none;'>
			세션 연장할텨?
			<button class = "control" data-ts-state="true">예</button>
			<button class = "control">아니오</button>
			</div>
		`;
		this.msgArea = new DOMParser().parseFromString(source, 'text/html').body.children[0];
		this.msgArea.addEventListener("click", function(e) {
			if (e.target.dataset.tsState) {
				this.reset();
			}
			this.msgArea.style.display = "none";
		}.bind(this));
		this.element.after(this.msgArea);
	}

	this.init = function() {
		fetch("",{method:"head"});
		this.timer = this.timeout;
		this.element.innerHTML = this.timeout;
		this.createMessageArea();

		let obj = this;
		this.timeoutId = setTimeout(function() {
			obj.msgArea.style.display = "block";
		}, (this.timeout - 60) * SPEED);

		this.intervalId = setInterval(() => {
			if (obj.timer > 0) {
				obj.timer--;
			}
			else {
				obj.destroy();
			}
			obj.element.innerHTML = obj.timer;
		}, SPEED);
	}

	this.destroy = function() {
		clearInterval(this.intervalId);
		clearTimeout(this.timeoutId);
		//document.body.removeChild(this.msgArea);
		this.msgArea.remove();
		
	}

	this.reset = function(){
		this.destroy();
		this.init();
	}
	this.init();
}

document.querySelectorAll("[data-ts-timeout]").forEach(element => {
	let timeout = element.dataset['tsTimeout'];

	console.log(timeout);
	new SessionTimer2(timeout, element);
})

stopBtn.addEventListener("click", e => {
	document.querySelectorAll("[data-ts-timeout]").forEach(element => {
		element.sessionTimer.destroy();
	})
})