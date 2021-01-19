$(document).ready(function() {
    $('#collapse1').hide();
    $('#collapse2').hide();
    $('#collapse3').hide();
    $('#collapse1').slideToggle("slow", "linear", function() {
        $('#collapse2').slideToggle("slow", "linear", function() {
            $('#collapse3').slideToggle("slow", "linear");
        });
    });
    setTimeout(function() {
        $(".alert").slideUp(900);
    }, 10000);

    var urlEndPoint = "http://localhost:8080/subscribe";
    var eventSource = new EventSource(urlEndPoint);
    console.log("Działa subskrybcja");
    eventSource.addEventListener("newAlert", function(event) {
        var articleData = JSON.parse(event.data);
        addBlock(articleData.title, articleData.text, articleData.date, articleData.iconTypeName, articleData.iconTypeBack, articleData.id);
    });
    eventSource.addEventListener("counter", function(event) {
        var eventData = JSON.parse(event.data);
        changeCounter(eventData.counter);
    });
    eventSource.addEventListener("error", function(event) {
        console.log("Error :" + event.currentTarget.readyState);
        if (event.currentTarget.readyState == EventSource.CLOSED) {} else {
            eventSource.close();
        }
    });
    window.onbeforeunload = function() {
        eventSource.close();
    }
    console.log("ready!");

});

function addBlock(title, text, date, iconTypeName, iconTypeBack, id) {
    var a = document.createElement("a");
    var divMR = document.createElement("div");
    var divIC = document.createElement("div");
    var div = document.createElement("div");
    var divSM = document.createElement("div");
    var i = document.createElement("i");
    var span = document.createElement("span");

    a.setAttribute('class', "dropdown-item d-flex align-items-center ");
    a.setAttribute('href', "#");
    divMR.setAttribute('class', "mr-3");
    divIC.setAttribute('class', "icon-circle " + iconTypeBack);
    divSM.setAttribute('class', "small text-gray-500");
    i.setAttribute('class', "fas " + iconTypeName + " text-white");
    span.setAttribute('class', "font-weight-bold");
    span.innerHTML = title;
    divSM.innerHTML = date;
    divIC.appendChild(i);
    divMR.appendChild(divIC);
    a.appendChild(divMR);
    div.appendChild(divSM);
    div.appendChild(span);
    a.appendChild(div);
    a.setAttribute('id', id);
    var list = document.getElementById("pack");
    list.insertBefore(a, list.childNodes[0]);
    var myEl = document.getElementById(id);
    myEl.addEventListener('click', function() {
        //alert('Hello world');
        //console.log("Kliknieto o id: " + id);
        updateViewed(id);
        myEl.remove();
    }, false);
}

function changeCounter(counter) {
    var counterHTML = document.getElementById("counter");
    counterHTML.innerHTML = counter;
    if (counter >= 1) counterHTML.innerHTML = counter;
    else counter = "";
}

function updateViewed(id) {
    $.ajax({
        type: "POST",
        url: "/updateViewed",
        data: {
            id: id
        },
        timeout: 100000,
        success: function() {
            console.log("SUCCESS: ");
        },
        error: function(e) {
            console.log("ERROR");
        },
        done: function(e) {
            console.log("DONE");
        }
    });
}


(function() {
    'use strict';
    window.addEventListener('load', function() {
        var forms = document.getElementsByClassName('needs-validation');
        var validation = Array.prototype.filter.call(forms, function(form) {
            form.addEventListener('submit', function(event) {
                if (form.checkValidity() === false) {
                    event.preventDefault();
                    event.stopPropagation();
                }
                form.classList.add('was-validated');
            }, false);
        });
    }, false);
})();

function myFun() {
    var tempTitle = document.getElementById("tytul").value;
    var tempText = document.getElementById("tresc").value;

    var tempIcon;
    var radios = document.getElementsByName('ikona');
    for (var i = 0, length = radios.length; i < length; i++) {
        if (radios[i].checked) {
            // do whatever you want with the checked radio
            tempIcon = radios[i].value;

            // only one radio can be logically checked, don't check the rest
            break;
        }
    }
    $.ajax({
        type: "POST",
        url: "/dispatchEvent",
        data: {
            title: tempTitle,
            text: tempText,
            icon: tempIcon
        },
        timeout: 100000,
        success: function() {
            console.log("SUCCESS: ");
        },
        error: function(e) {
            console.log("ERROR");
        },
        done: function(e) {
            console.log("DONE");
        }
    });
}

function isBlank(str) {
    return (!str || /^\s*$/.test(str));
}