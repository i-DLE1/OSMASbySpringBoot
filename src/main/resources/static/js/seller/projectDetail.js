$("#retry-body").on("input",function (){
    $(this).siblings("label").children("span").text(this.value.length)
});

$("#qa-answer").on("input",function (){
    console.log($(this).siblings("label")[0])
    $(this).siblings("label").children("span").text(this.value.length)
})


function qaAnswerSubmit(submitType) {
    const content = $("#qa-answer").val();
    const queryStr = new URLSearchParams(location.search);
    $.ajax({
        url : `/seller/projectDetail/qaAnswer?no=${queryStr.get('no')}&submitType=${submitType}`,
        type : "post",
        contentType : "application/json; charset=utf-8;",
        data : content,
        success : function (success) {
            window.close()
        },
        error : function (error) {
            console.log(error)
        }
    })
}

function projectRetrySubmit(){
    const content = $("#retry-body").val();
    const no = new URLSearchParams(location.search).get('no');
    $.ajax({
        url : `/seller/projectDetail/retry?no=${no}`,
        type : "post",
        data : content,
        contentType: "application/json; charset=utf-8",
        success : function (success){
            window.close();
        },
        error : function (error){
            console.log(error);
        }
    })
}

function chart1(data) {
    console.log(data)
    const ctx = document.getElementById('myChart');
    new Chart(ctx, {
        type: 'pie',
        animation: {
            onComplete: () => {
                delayed = true;
            },
            delay: (context) => {
                let delay = 0;
                if (context.type === 'data' && context.mode === 'default' && !delayed) {
                    delay = context.dataIndex * 300 + context.datasetIndex * 100;
                }
                return delay;
            },
        },
        data: {
            labels: [Object.keys(data)],
            datasets: [{
                label: '판매 비율',
                data: [Object.values(data)],
                borderWidth: 1
            }]
        },
        options: {
            responsive: true,
            plugins: {
                legend: {
                    position: 'top',
                },
                title: {
                    display: true,
                    text: '상품별 판매 비중',
                    font: {
                        size: 20,
                    },
                },

            }
        },
    });
}

function chart2(data) {
    const ctx2 = document.getElementById('myChart2');
    new Chart(ctx2, {
        type: 'polarArea',
        animation: {
            onComplete: () => {
                delayed = true;
            },
            delay: (context) => {
                let delay = 0;
                if (context.type === 'data' && context.mode === 'default' && !delayed) {
                    delay = context.dataIndex * 300 + context.datasetIndex * 100;
                }
                return delay;
            },
        },
        data: {
            labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
            datasets: [{
                label: '# of Votes',
                data: [12, 19, 3, 5, 2, 3],
                borderWidth: 1
            }]
        },
        options: {
            responsive: true,
            plugins: {
                legend: {
                    position: 'top',
                },
                title: {
                    display: true,
                    text: '상품별 판매 비중',
                    font: {
                        size: 20,
                    },
                }
            }
        },
    });
}

function chart3(data){
    const ctx3 = document.getElementById('myChart3');
    new Chart(ctx3, {
        type: 'bar',
        animation: {
            onComplete: () => {
                delayed = true;
            },
            delay: (context) => {
                let delay = 0;
                if (context.type === 'data' && context.mode === 'default' && !delayed) {
                    delay = context.dataIndex * 300 + context.datasetIndex * 100;
                }
                return delay;
            },
        },
        data: {
            labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
            datasets: [{
                label: '# of Votes',
                data: [12, 19, 3, 5, 2, 3],
                borderWidth: 1
            }]
        },
        options: {
            responsive: true,
            plugins: {
                legend: {
                    position: 'top',
                },
                title: {
                    display: true,
                    text: '상품별 판매 비중',
                    font: {
                        size: 20,
                    },
                }
            }
        },
    });
}

let data ={};
function getData() {
    const no = new URLSearchParams(location.search).get('no')

    return $.ajax({
        url : `/seller/projectDetail/productStatistics?no=${no}`,
        success : function (success){
            chart1(success);
        },
        error : function (e){
            console.log(e)
        }
    })


}