$(document).ready(function() {
    setTimeout(function() {
        $(".alert").slideUp(900);
    }, 10000);
    $('#collapse1').hide();
        $('#collapse2').hide();
        $('#collapse3').hide();
        $('#collapse1').slideToggle("slow", "linear", function() {
            $('#collapse2').slideToggle("slow", "linear", function() {
                $('#collapse3').slideToggle("slow", "linear");
            });
        });
    $('#phone-number').mask('000-000-000');
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
    eventSource.addEventListener("wykresKolowyData", function(event) {
            var eventData = JSON.parse(event.data);
            console.log("Odebrałem dane wykresKolowyData :");
            console.log("osobowe :" + eventData.osobowe);
            console.log("ciezarowe :" + eventData.ciezarowe);
            console.log("inne :" + eventData.inne);
            myPie(eventData.osobowe, eventData.ciezarowe,eventData.inne );
    });
    eventSource.addEventListener("wykresLiniowyData1", function(event) {
            var eventData = JSON.parse(event.data);
            myChart1(eventData.keyt, eventData.valuet);
    });
    eventSource.addEventListener("wykresLiniowyData2", function(event) {
            var eventData = JSON.parse(event.data);
            myChart2(eventData.keyt, eventData.valuet);
    });
    window.onbeforeunload = function() {
        eventSource.close();
    }
    window.addEventListener('load', function() {
        // Fetch all the forms we want to apply custom Bootstrap validation styles to
        var forms = document.getElementsByClassName('needs-validation');
        // Loop over them and prevent submission
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
    $('#select').on('change', function()
    {
        var selected = $('#select').val();
        console.log("selected :" + selected);
        $('#mybutton').attr("href", "/item/info/"+selected);
    });
    $('#poleIlosc').on('change', function()
        {
            var selected = $('#poleIlosc').val();
            console.log("poleIlosc :" + selected);
            $('#addMoreItemBut').attr("href", "/item/info/"+selected);


        });
});
function readURL(input) {
    console.log("READURL");
    var myImg = document.getElementById('imageResult');
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            $('#imageResult').attr('src', e.target.result);
            if(myImg && myImg.style) {
                myImg.style.height = '200px';
                myImg.style.width = '200px';
            }

        };

        reader.readAsDataURL(input.files[0]);

    }

}




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

function myPie(osobowe, ciezarowe, inne) {
Chart.defaults.global.defaultFontFamily = 'Nunito', '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#858796';
var ctx = document.getElementById("myPieChart");
    var myPieChart = new Chart(ctx, {
      type: 'doughnut',
      data: {
        labels: ["Osobowe", "Ciezarowe", "Inne"],
        datasets: [{
          data: [osobowe, ciezarowe,inne],
          backgroundColor: ['#4e73df', '#1cc88a', '#36b9cc'],
          hoverBackgroundColor: ['#2e59d9', '#17a673', '#2c9faf'],
          hoverBorderColor: "rgba(234, 236, 244, 1)",
        }],
      },
      options: {
        maintainAspectRatio: false,
        tooltips: {
          backgroundColor: "rgb(255,255,255)",
          bodyFontColor: "#858796",
          borderColor: '#dddfeb',
          borderWidth: 1,
          xPadding: 15,
          yPadding: 15,
          displayColors: false,
          caretPadding: 10,
        },
        legend: {
          display: false
        },
        cutoutPercentage: 80,
      },
    });

}

function number_format(number, decimals, dec_point, thousands_sep) {
  // *     example: number_format(1234.56, 2, ',', ' ');
  // *     return: '1 234,56'
  number = (number + '').replace(',', '').replace(' ', '');
  var n = !isFinite(+number) ? 0 : +number,
    prec = !isFinite(+decimals) ? 0 : Math.abs(decimals),
    sep = (typeof thousands_sep === 'undefined') ? ',' : thousands_sep,
    dec = (typeof dec_point === 'undefined') ? '.' : dec_point,
    s = '',
    toFixedFix = function(n, prec) {
      var k = Math.pow(10, prec);
      return '' + Math.round(n * k) / k;
    };
  // Fix for IE parseFloat(0.55).toFixed(0) = 0;
  s = (prec ? toFixedFix(n, prec) : '' + Math.round(n)).split('.');
  if (s[0].length > 3) {
    s[0] = s[0].replace(/\B(?=(?:\d{3})+(?!\d))/g, sep);
  }
  if ((s[1] || '').length < prec) {
    s[1] = s[1] || '';
    s[1] += new Array(prec - s[1].length + 1).join('0');
  }
  return s.join(dec);
}

function myChart1(keyt, valuet) {
    var ctx = document.getElementById("myAreaChart1");
    var myLineChart = new Chart(ctx, {
      type: 'line',
      data: {
        labels: keyt,
        datasets: [{
          label: "Wpływy",
          lineTension: 0.3,
          backgroundColor: "rgba(78, 115, 223, 0.05)",
          borderColor: "rgba(78, 115, 223, 1)",
          pointRadius: 3,
          pointBackgroundColor: "rgba(78, 115, 223, 1)",
          pointBorderColor: "rgba(78, 115, 223, 1)",
          pointHoverRadius: 3,
          pointHoverBackgroundColor: "rgba(78, 115, 223, 1)",
          pointHoverBorderColor: "rgba(78, 115, 223, 1)",
          pointHitRadius: 10,
          pointBorderWidth: 2,
          data: valuet,
        }],
      },
      options: {
        maintainAspectRatio: false,
        layout: {
          padding: {
            left: 10,
            right: 25,
            top: 25,
            bottom: 0
          }
        },
        scales: {
          xAxes: [{
            time: {
              unit: 'date'
            },
            gridLines: {
              display: false,
              drawBorder: false
            },
            ticks: {
              maxTicksLimit: 7
            }
          }],
          yAxes: [{
            ticks: {
              maxTicksLimit: 5,
              padding: 10,
              // Include a dollar sign in the ticks
              callback: function(value, index, values) {
                return  number_format(value)+' zł';
              }
            },
            gridLines: {
              color: "rgb(234, 236, 244)",
              zeroLineColor: "rgb(234, 236, 244)",
              drawBorder: false,
              borderDash: [2],
              zeroLineBorderDash: [2]
            }
          }],
        },
        legend: {
          display: false
        },
        tooltips: {
          backgroundColor: "rgb(255,255,255)",
          bodyFontColor: "#858796",
          titleMarginBottom: 10,
          titleFontColor: '#6e707e',
          titleFontSize: 14,
          borderColor: '#dddfeb',
          borderWidth: 1,
          xPadding: 15,
          yPadding: 15,
          displayColors: false,
          intersect: false,
          mode: 'index',
          caretPadding: 10,
          callbacks: {
            label: function(tooltipItem, chart) {
              var datasetLabel = chart.datasets[tooltipItem.datasetIndex].label || '';
              return datasetLabel + ': ' + number_format(tooltipItem.yLabel)+' zł';
            }
          }
        }
      }
    });


}
function myChart2(keyt, valuet) {
    var ctx = document.getElementById("myAreaChart2");
    var myLineChart = new Chart(ctx, {
      type: 'line',
      data: {
        labels: keyt,
        datasets: [{
          label: "Wpływy",
          lineTension: 0.3,
          backgroundColor: "rgba(78, 115, 223, 0.05)",
          borderColor: "rgba(78, 115, 223, 1)",
          pointRadius: 3,
          pointBackgroundColor: "rgba(78, 115, 223, 1)",
          pointBorderColor: "rgba(78, 115, 223, 1)",
          pointHoverRadius: 3,
          pointHoverBackgroundColor: "rgba(78, 115, 223, 1)",
          pointHoverBorderColor: "rgba(78, 115, 223, 1)",
          pointHitRadius: 10,
          pointBorderWidth: 2,
          data: valuet,
        }],
      },
      options: {
        maintainAspectRatio: false,
        layout: {
          padding: {
            left: 10,
            right: 10,
            top: 10,
            bottom: 0
          }
        },
        scales: {
          xAxes: [{
            time: {
              unit: 'date'
            },
            gridLines: {
              display: false,
              drawBorder: false
            },
            ticks: {
              maxTicksLimit: 7
            }
          }],
          yAxes: [{
            ticks: {
              maxTicksLimit: 5,
              padding: 10,
              // Include a dollar sign in the ticks
              callback: function(value, index, values) {
                return  number_format(value)+' zł';
              }
            },
            gridLines: {
              color: "rgb(234, 236, 244)",
              zeroLineColor: "rgb(234, 236, 244)",
              drawBorder: false,
              borderDash: [2],
              zeroLineBorderDash: [2]
            }
          }],
        },
        legend: {
          display: false
        },
        tooltips: {
          backgroundColor: "rgb(255,255,255)",
          bodyFontColor: "#858796",
          titleMarginBottom: 10,
          titleFontColor: '#6e707e',
          titleFontSize: 14,
          borderColor: '#dddfeb',
          borderWidth: 1,
          xPadding: 15,
          yPadding: 15,
          displayColors: false,
          intersect: false,
          mode: 'index',
          caretPadding: 10,
          callbacks: {
            label: function(tooltipItem, chart) {
              var datasetLabel = chart.datasets[tooltipItem.datasetIndex].label || '';
              return datasetLabel + ': ' + number_format(tooltipItem.yLabel)+' zł';
            }
          }
        }
      }
    });


}