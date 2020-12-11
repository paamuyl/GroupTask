window.onload = function () {
    //獲取DOM元件
    var infoWindow = document.getElementById("infoWindow");
    var msg = document.getElementById("msg");    
    var messageDisplay = document.getElementById("messageDisplay");
 	var userid=document.getElementById("userid");
    var webSocket;
    var isConnectSuccess = false;
 	var sMessage= document.getElementById("sMessage");
    //設置WebSocket連接
     $(document).ready(function(){ 
     alert("連線中......");
     setWebSocket();
     });
     
    //Submit Form時送出訊息
     $('#msg').keypress(function(e) {
        if(e.which == 13) {
        sendMessage();
        msg.value = "";
        return false;
        }
    });
        
	sMessage.addEventListener("click", function() {
		sendMessage();
		msg.value = "";
        return false;
    });
    //使用webSocket擁有的function, send(), 送出訊息
    function sendMessage() {
        //檢查WebSocket連接狀態
        if (webSocket && isConnectSuccess) {
            var messageInfo = {
            	userid: userid.value,
                message: msg.value
            }
            webSocket.send(JSON.stringify(messageInfo));
        } else {
            infoWindow.innerHTML = "未登入";
        }
    }
 
    //設置WebSocket
    function setWebSocket() {
		alert("Start!!!");
        //開始WebSocket連線
        webSocket = new WebSocket('ws://10.10.1.143:8080/CakeShop1123chat/mywebsocket/'+userid.value);
        //以下開始偵測WebSocket的各種事件
         
        //onerror , 連線錯誤時觸發  
        webSocket.onerror = function (event) {
            loginBtn.disabled = false;
            userNameInput.disabled = false;
            infoWindow.innerHTML = "登入失敗";
        };
 
        //onopen , 連線成功時觸發
        webSocket.onopen = function (event) {
            isConnectSuccess = true;
            loginBtn.disabled = true;
            infoWindow.innerHTML = "登入成功";
           };
 
        //onmessage , 接收到來自Server的訊息時觸發
        webSocket.onmessage = function (event) {
            var messageObject = JSON.parse(event.data);
            messageDisplay.innerHTML += "" + messageObject.userid + " 12:24 PM <br><p>" + messageObject.message+"</p>";
       		
        };
    }
};
