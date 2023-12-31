$("#retry-body").on("input",function (){
    $(this).siblings("label").children("span").text(this.value.length)
});

$("#qa-answer").on("input",function (){
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
        }
    })
}

function chart1(data) {
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
            labels: [...Object.keys(data)],
            datasets: [{
                label: '판매 비율',
                data: [...Object.values(data)],
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
            labels: [...Object.keys(data)],
            datasets: [{
                label: '# of Votes',
                data: [...Object.values(data)],
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
            chart2(success);
        },
        error : function (e){
        }
    })


}