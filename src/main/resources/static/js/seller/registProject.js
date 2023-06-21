let faqListCount = 1;
let productCount = 1;


function removeItemButton(funcName){
    let $div = $("<div>").attr("id","subItem");
    let $button = $("<button>").attr("id","productSub")
                                .addClass("sub-item")
                                .attr("onclick",`${funcName}()`);
    $button.append($("<img>").attr("src","/static/image/subitem.png")
                                .attr("width","40px")
                                .attr("height","40px"));
    $div.append($button);
    return $("<div>").append($div).html();
}

function fqaLoadDummy() {
    let data = [
        {title : "1", body:"2"},
        {title : "타이틀2", body:"내용물 222"},
        {title : "타이틀3", body:"내용물 333"},
        {title : "타이틀4", body:"내용물 444"},
        {title : "타이틀5", body:"내용물 555"},
    ];

    fqaLoadList(data);
}

function faqAddSubItem(faqListCount) {
    const elementText = removeItemButton("faqItemRemove");
    $(`#faqSubIndex${faqListCount-1}`).addClass("add-item-faq-col-2")
                                        .append(elementText);
}

function faqItemRemove(){
    faqListCount--;

    if(faqListCount > 2){
        $(`#faqIndex${faqListCount}`).remove();
        faqAddSubItem(faqListCount);
    }else {
        $(`#faqIndex${faqListCount}`).remove();
    }
}

function fqaLoadList(data) {
    data.forEach(item=>{
        let text = `
        <div id="faqIndex${faqListCount}">
            <div>
                <div id="faqSubIndex${faqListCount}" >
                    <div>
                        <div>
                            <label class="sub-title" for="title${faqListCount}">제목</label>
                            <input class="input-box w-100" id="title${faqListCount}" name="title${faqListCount}" value="${item.title}"/>
                        </div>
                        <div class="mt-4">
                            <label class="sub-title" for="body${faqListCount}">내용</label>
                            <textarea class="input-box w-100" rows="2" style="resize: none; height: 80px !important;"  id="body${faqListCount}" name="body" >${item.body}</textarea>
                        </div>
                    </div>
                </div>
                <div>
                    <hr class="line">
                </div>
            </div>
        </div>`
        $('#faqList').append(text);
        faqListCount++;
    })
    faqAddSubItem(faqListCount);
}

$("#fqaAdd").click(function (){
    let titleVal = $(`#title${faqListCount-1}`).val();
    let bodyVal = $(`#body${faqListCount-1}`).val();

    if(titleVal === "" || bodyVal === ""){
        alert("입력 되지 않은 필드가 존재합니다.");
        return;
    }

    $('#subItem').remove();
    $(`#faqIndex${faqListCount-1}`).children().children().prop("class","");

    let text = `
        <div id="faqIndex${faqListCount}">
            <div>
                <div id="faqSubIndex${faqListCount}" class="add-item-faq-col-2">
                    <div>
                        <div>
                            <label class="sub-title" for="title${faqListCount}">제목</label>
                            <input class="input-box w-100" id="title${faqListCount}" name="title${faqListCount}"/>
                        </div>
                        <div class="mt-4">
                            <label class="sub-title" for="body${faqListCount}" >내용</label>
                            <textarea class="input-box w-100" rows="2" style="resize: none; height: 80px !important;"  id="body${faqListCount}" name="body"></textarea>
                        </div>
                    </div>
                    ${removeItemButton("faqItemRemove")}
                </div>
                <div>
                    <hr class="line">
                </div>
            </div>
        </div>`

    $('#faqList').append(text);
    faqListCount++;
});

$("#productAdd").click(function (){
    let $nameVal = $(`#name${productCount-1}`).val();
    let $sizeVal = $(`#size${productCount-1}`).val();
    let $countVal = $(`#count${productCount-1}`).val();
    let $moneyVal = $(`#money${productCount-1}`).val();
    let $bodyVal = $(`#body${productCount-1}`).val();
    let $shippingStartVal = $(`#shippingStart${productCount-1}`).val();
    let $shippingFeeVal = $(`#shippingFee${productCount-1}`).val();

    if($nameVal === "" || $sizeVal === ""|| $countVal === ""|| $moneyVal === ""|| $bodyVal === ""|| $shippingStartVal === ""|| $shippingFeeVal === ""){
        alert("입력 되지 않은 필드가 존재합니다.");
        return;
    }
    $("#subItem").remove();
    $(`#addItemProductIndex${productCount-1}`).children().prop("class","");
    let text=
        `<div id="addItemProductIndex${productCount}">
            <div id="productSubIndex${productCount}" class="add-item-product-col-2">
                <div id="item">
                    <div class="row" style="margin-top: 1em;">
                        <div class="col">
                            <label class="sub-title" for="name">상품명</label>
                            <input class="w-100 input-box" type="text" id="name${productCount}" name="name${productCount}">
                        </div>
                        <div class="col">
                            <label class="sub-title" for="size">사이즈</label>
                            <input class="w-100 input-box" type="text" id="size${productCount}" name="size${productCount}">
                        </div>
                        <div class="col-2">
                            <label for="count" class="sub-title">수량</label>
                            <input class="w-100 input-box" type="number" id="count${productCount}" name="count${productCount}">
                        </div>
                        <div class="col-3">
                            <label for="money" class="sub-title">금액</label>
                            <input class="w-100 input-box" type="number" id="money${productCount}" name="money${productCount}">
                        </div>
                    </div>
                    <div class="row" style="margin-top: 1em;">
                        <div class="col">
                            <label for="body" class="sub-title">설명</label>
                            <input class="w-100 input-box" type="text" id="body${productCount}" name="body${productCount}">
                        </div>
                        <div class="col-3">
                            <label for="shippingStart" class="sub-title">배송시작</label>
                            <input class="w-100 input-box" type="date" id="shippingStart${productCount}" name="shippingStart${productCount}">
                        </div>
                        <div class="col-3">
                            <label for="shippingFee" class="sub-title">배송비</label>
                            <input class="w-100 input-box" type="number" id="shippingFee${productCount}" name="shippingFee${productCount}">
                        </div>
                    </div>
                </div>
                ${removeItemButton("productItemRemove")};
            </div>
            <div>
                <hr class="line">
            </div>
        </div>`
    $("#productList").append(text);
    productCount++;
})

function newsListDummy() {
    let data = [
        {title:"title1", body:"body11111",startDate:"2023-01-01",endDate:"2032-10-10"},
        {title:"title2", body:"body22222",startDate:"2023-02-01",endDate:"2032-10-10"},
        {title:"title3", body:"body33333",startDate:"2023-03-01",endDate:"2032-10-10"},
        {title:"title4", body:"body44444",startDate:"2023-04-01",endDate:"2032-10-10"},
    ];
    newsList(data);
}

function productInfoListDummy() {
    let data = [
        {title : "어쩌구 저쩌구", size:"70", money:"200,000", count:12},
        {title : "어쩌구 저쩌구", size:"70", money:"200,000", count:12},
        {title : "어쩌구 저쩌구", size:"70", money:"200,000", count:12},
        {title : "어쩌구 저쩌구", size:"70", money:"200,000", count:12},
        {title : "어쩌구 저쩌구", size:"70", money:"200,000", count:12},
        {title : "어쩌구 저쩌구", size:"70", money:"200,000", count:12},
    ];
    productInfoList(data);
}

function productItemDummy() {
    let data = [
        {name : "상품명1", size:"150", count : 1, money:10000, body: "상세내용1", shippingStart : "2023-01-01", shppingFee: 3000},
        {name : "상품명2", size:"160", count : 2, money:1000, body: "상세내용2", shippingStart : "2023-02-01", shppingFee: 2500},
        {name : "상품명3", size:"200", count : 3, money:100, body: "상세내용3", shippingStart : "2023-03-01", shppingFee: 1000},
        {name : "상품명4", size:"70", count : 4, money:1000, body: "상세내용4", shippingStart : "2023-04-01", shppingFee: 0},
        {name : "상품명5", size:"75", count : 5, money:20000, body: "상세내용5", shippingStart : "2023-05-01", shppingFee: 100},
    ];
    productItemLoad(data);
}

function productItemRemove(){
    productCount--;
    if(productCount > 2){
        $(`#addItemProductIndex${productCount}`).remove();
        productAddSubItem(productCount);
    }else {
        $(`#addItemProductIndex${productCount}`).remove();
    }
}

function productAddSubItem(productCount) {
    const elementText = removeItemButton("productItemRemove");
    $(`#productSubIndex${productCount-1}`).addClass("add-item-product-col-2")
                                            .append(elementText);
}

function productItemLoad(data){
    data.forEach(item => {
        let text=
        `<div id="addItemProductIndex${productCount}">
            <div id="productSubIndex${productCount}">
                <div id="item">
                    <div class="row" style="margin-top: 1em;">
                        <div class="col">
                            <label class="sub-title" for="name">상품명</label>
                            <input class="w-100 input-box" type="text" id="name${productCount}" name="name${productCount}" value=${item.name}>
                        </div>
                        <div class="col">
                            <label class="sub-title" for="size">사이즈</label>
                            <input class="w-100 input-box" type="text" id="size${productCount}" name="size${productCount}" value=${item.size}>
                        </div>
                        <div class="col-2">
                            <label for="count" class="sub-title">수량</label>
                            <input class="w-100 input-box" type="number" id="count${productCount}" name="count${productCount}" value=${item.count}>
                        </div>
                        <div class="col-3">
                            <label for="money" class="sub-title">금액</label>
                            <input class="w-100 input-box" type="number" id="money${productCount}" name="money${productCount}" value=${item.money}>
                        </div>
                    </div>
                    <div class="row" style="margin-top: 1em;">
                        <div class="col">
                            <label for="body" class="sub-title">설명</label>
                            <input class="w-100 input-box" type="text" id="body${productCount}" name="body${productCount}" value=${item.body}>
                        </div>
                        <div class="col-3">
                            <label for="shippingStart" class="sub-title">배송시작</label>
                            <input class="w-100 input-box" type="date" id="shippingStart${productCount}" name="shippingStart${productCount}" value=${item.shippingStart}>
                        </div>
                        <div class="col-3">
                            <label for="shippingFee" class="sub-title">배송비</label>
                            <input class="w-100 input-box" type="number" id="shippingFee${productCount}" name="shippingFee${productCount}" value=${item.shppingFee}>
                        </div>
                    </div>
                </div>
                
            </div>
            <div>
                <hr class="line">
            </div>
        </div>`
        $("#productList").append(text);
        productCount++
    })
    productAddSubItem(productCount);
}


function newsList(data){
    data.forEach((item,index)=>{
        let $tr = $(`<tr>`);
        $tr.append($(`<th>`).text(index+1))
            .append($(`<td>`).text(item.title))
            .append($(`<td>`).text(item.body))
            .append($(`<td>`).text(item.startDate))
            .append($(`<td>`).text(item.endDate));
        $("#newsList").append($tr);
    });
}

function productInfoList(data) {
    data.forEach((item,index)=>{
        let $tr = $(`<tr>`);
        $tr.addClass("row")
            .append($(`<th>`).addClass( "col-1").text("상품명"))
            .append($(`<td>`).addClass( "col").text(item.title))
            .append($(`<th>`).addClass( "col-1").text("사이즈"))
            .append($(`<td>`).addClass( "col").text(item.size))
            .append($(`<th>`).addClass( "col-2").text("가격(원)"))
            .append($(`<td>`).addClass( "col").text(item.money))
            .append($(`<th>`).addClass( "col-1").text("수량"))
            .append($(`<td>`).addClass( "col-2").text(item.count));
        $("#projectEndList").append($tr);

    })
}

function newsRegist(){
    this.preventDefault();
    $("#newsList").html("");
    newsList();
}

function indexCheckConfirm() {
    if( !($("#check1").prop("checked") && $("#check2").prop("checked"))){
        alert("필수 항목에 체크를 하지 않았습니다.");
    }else{
        location.href="project2.html"
    }
}

// 보류
// function project1Confirm() {
//     const title = $("#title").val()
//     const body = $("#body").val()
//     const startDate = $("#startDate").val()
//     const endDate = $("#endDate").val()
//     const money = $("#money").val()
//
//     let startDateString = new Date(startDate).toLocaleString().slice(0,new Date(startDate))
//     let todayString = new Date().toLocaleString('ko-KR', { timeZone: 'Asia/Seoul' })
//     startDateString =  startDateString.slice(0, startDateString.lastIndexOf("."))
//     todayString =  todayString.slice(0, todayString.lastIndexOf("."))
//
//
//     if(title === "" || body === "" || startDate === "" || endDate === "" || money === ""){
//         alert("필수 항목을 입력하지 않았습니다. ")
//     }else{
//         if(money < 1000000){
//             alert("펀딩 목표 금액은 100만원 이하일 수 없습니다.")
//         }
//         if(startDateString === todayString){
//             alert("익일 부터 프로젝트를 시작할 수 있습니다. ")
//         }
//     }
// }


function project2LoadData() {

}

function project2LoadDate() {

}

function project3LoadData() {
    productItemDummy();
    // productItemLoad(data)
}

function project3Confirm() {

}

function project4LoadData() {
    newsListDummy();
    //newsList(data)
}

function project4Confirm() {

}
function project5LoadDate(){
    fqaLoadDummy(); // 더미데이터 로드
    //fqaLoadList(data)
}

function project5Confirm() {

}
function project6LoadData() {
    newsListDummy();
    //newsList(data)
}

function previewImage(ele) {
    let img = new FileReader();
    img.onload = function (e){
        $(ele).siblings("label").children().prop("src",e.target.result)
    };
    img.readAsDataURL(ele.files[0]);
}