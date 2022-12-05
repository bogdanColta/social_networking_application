function addMessage() {
    $.ajax({
        url: `net/chat/messages`,
        type: "GET",
        async: false,
        success: function (response) {
            console.log(response);
            let res1 = JSON.stringify(response)
            const res = JSON.parse(res1);
            let aside = document.getElementById("aside1");
            aside.innerHTML = null;
            for (let item of Object.keys(res)) {
                let div1 = document.createElement("div");
                div1.className = "first-part";
                div1.innerHTML = res[item]["username"];
                let div2 = document.createElement("div");
                div2.className = "second-part";
                div2.innerHTML = res[item]["message"];
                let div3 = document.createElement("div");
                div3.className = "third-part";
                div3.innerHTML = res[item]["time"];
                let div = document.createElement("div");
                div.className = "group-rom";
                div.appendChild(div1);
                div.appendChild(div2);
                div.appendChild(div3);
                aside.appendChild(div);
            }
        }
    });
    setTimeout(addMessage, 1000);
}

let input_field = document.getElementById("textInput");

function send() {
    $.ajax({
        url: `net/chat/message`,
        type: "POST",
        contentType: "application/json",
        data: `{"username": "", 
                "time": "",
                "message": "${input_field.value}"}`,
        async: false,
        success: function () {
            input_field.value = null;
        },
    });
}

addMessage();