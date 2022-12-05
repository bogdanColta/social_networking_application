let images = [];

function getImages(){
    $.ajax({
        url: `net/gallery/images`,
        type: "GET",
        async: false,
        success: function (response) {
            images = response;
        }
    });

    let carousel = document.getElementsByClassName("carousel-inner")[0];

    for (let index in images) {
        let name = images[index]["name"] + "  " + images[index]["time"];
        let encoding = images[index]["encoding"];

        let item = document.createElement("div");
        item.classList.add("carousel-item");
        if (index == 0) {
            item.classList.add("active");
        }

        let image = new Image();
        image.src = encoding;
        image.setAttribute("style", "max-height: 73vh;");
        image.classList.add("d-block");
        image.classList.add("w-100");

        let div = document.createElement("div");
        div.classList.add("carousel-caption");
        div.classList.add("d-none");
        div.classList.add("d-md-block");
        let h5 = document.createElement("h5");
        h5.innerHTML = name;
        div.appendChild(h5);

        item.appendChild(image);
        item.appendChild(div);
        carousel.appendChild(item);
    }
}

getImages();

function encodeImageFileAsURL() {
    let file = document.getElementById("customFile").files[0];
    let reader = new FileReader();
    reader.onloadend = function () {
        sendImage(reader.result);
    }
    reader.readAsDataURL(file);
}

function sendImage(photo) {
    let input_field = document.getElementById("customFile");
    $.ajax({
        url: `net/gallery/image`,
        type: "POST",
        contentType: "application/json",
        data: `{"name": "",
                "encoding": "${photo}",
                "time": ""}`,
        async: false,
        success: function () {
            input_field.value = null;
        },
    });
}