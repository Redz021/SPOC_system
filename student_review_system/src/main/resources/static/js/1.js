// 创建轮播图区域
// @{divarea}轮播图放入的地方
// @{【url，link】} url图片地址 link点击图片跳转的链接

function createBanners(divarea,options){
	var imagArea = document.createElement("div"); //图片区域
	var numArea = document.createElement("div"); //角标区域
	var curIndex = 1;//第几张图
	var changeTimer = null;//计时器
	var changeDuration = 3000; //3秒切换
	var timer = null;//动画计时器
	//1.生成区域显示图片
	initImage();


	//2.生成区域显示角标
	initNumbers();


	//3.设置区域状态 
	setstatus();

	//4.自动切换
	autochange();




	function initImage(){
		imagArea.style.width = "100%";
		imagArea.style.height = "100%";
		imagArea.style.display = "flex";
		imagArea.style.overflow = "hidden";
		for (let i = 0; i < options.length; i++) {
			var obj = options[i];
			var imag = document.createElement("img");
			imag.src = obj.url;
			imag.style.height = "100%";
			imag.style.width = "100%";
			imag.style.marginLeft = "0";
			imag.style.cursor = "pointer";
			imag.addEventListener("click",function(){
				location.href = obj.link;
			})
			imagArea.appendChild(imag);

		}
		imagArea.addEventListener("mouseenter", function(){
			clearInterval(changeTimer);
			changeTimer = null;
		});
		imagArea.addEventListener("mouseleave", function(){
			autochange();
		});
		divarea.appendChild(imagArea);
	}

	function initNumbers(){
		numArea.style.textAlign = "center";
		numArea.style.marginTop = "-20px";
		for (let i = 0; i < options.length; i++) {
			var sp = document.createElement("span");
			sp.style.width = "12px";
			sp.style.height = "12px";
			sp.style.display = "inline-block";
			sp.style.margin = "0 7px";
			sp.style.borderRadius = "50%";
			sp.style.backgroundColor = "lightgrey";
			sp.style.cursor = "pointer";
			sp.addEventListener("click", function(){
				curIndex = i;
				setstatus();
			})
			numArea.appendChild(sp);

		}
		divarea.appendChild(numArea);
	}


	function setstatus(){
		//设置圈的背景颜色
		for (var i = 0; i < numArea.children.length; i++) {
			if (i === curIndex) {
				//当前页

				numArea.children[i].style.background = "#22313f";
			}
			else{
				//没选择的页
				numArea.children[i].style.background = "lightgrey";
			}
		}
		//设置图片
		var start = parseInt(imagArea.children[0].style.marginLeft);
		var end = curIndex * -100;
		var dis = end - start;
		var duration = 500;
		var speed = dis / duration;
		if(timer){
			clearInterval(timer);
		}
		timer = setInterval(function(){
			start += speed * 20;
			imagArea.children[0].style.marginLeft = start + "%";
			if(Math.abs(end - start) < 1){
				imagArea.children[0].style.marginLeft = end + "%";
				clearInterval(timer);
			}
		},20)
		

	}



	//自动切换
	function autochange(){
		if (changeTimer) {
			return;
		}
		changeTimer = setInterval(function(){
			if (curIndex === options.length - 1) {
				curIndex = 0;
			}
			else{
				curIndex++;
			}
			setstatus();
		},changeDuration)
	}
}