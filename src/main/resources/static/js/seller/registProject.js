let faqListCount = 1;
let productCount = 1;


function removeItemButton(funcName){
    let $div = $("<div>").attr("id","subItem");
    let $button = $("<button>").attr("id","productSub")
                                .addClass("sub-item")
                                .attr("onclick",`${funcName}()`);
    $button.append($("<img>").attr("src","/images/seller/regist/subitem.png")
                                .attr("width","40px")
                                .attr("height","40px"));
    $div.append($button);
    return $("<div>").append($div).html();
}

function fqaLoadDummy() {
    let data = [
        {title : "1", content:"2"},
        {title : "타이틀2", content:"내용물 222"},
        {title : "타이틀3", content:"내용물 333"},
        {title : "타이틀4", content:"내용물 444"},
        {title : "타이틀5", content:"내용물 555"},
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
                            <label class="sub-title" for="content${faqListCount}">내용</label>
                            <textarea class="input-box w-100" rows="2" style="resize: none; height: 80px !important;"  id="content${faqListCount}" name="content" >${item.content}</textarea>
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
    let contentVal = $(`#content${faqListCount-1}`).val();

    if(titleVal === "" || contentVal === ""){
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
                            <label class="sub-title" for="content${faqListCount}" >내용</label>
                            <textarea class="input-box w-100" rows="2" style="resize: none; height: 80px !important;"  id="content${faqListCount}" name="content"></textarea>
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
    let $maxQuantityVal = $(`#maxQuantity${productCount-1}`).val();
    let $priceVal = $(`#price${productCount-1}`).val();
    let $introductionVal = $(`#introduction${productCount-1}`).val();
    // let $shippingStartVal = $(`#shippingStart${productCount-1}`).val();
    // let $shippingFeeVal = $(`#shippingFee${productCount-1}`).val();

    if($nameVal === "" || $sizeVal === ""|| $maxQuantityVal === ""|| $priceVal === ""|| $introductionVal === ""){
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
                        
                        <div class="col-3">
                            <label for="money" class="sub-title">금액</label>
                            <input class="w-100 input-box" type="number" id="price${productCount}" name="price">
<!--                            <input class="w-100 input-box" type="number" id="price${productCount}" name="price${productCount}">-->
                        </div>
                    </div>
                    <div class="row" style="margin-top: 1em;">
                        <div class="col">
                            <label for="body" class="sub-title">설명</label>
                            <input class="w-100 input-box" type="text" id="introduction${productCount}" name="introduction">
<!--                            <input class="w-100 input-box" type="text" id="introduction${productCount}" name="introduction${productCount}">-->
                        </div>
                        <div class="col-2">
                            <label for="count" class="sub-title">수량</label>
                            <input class="w-100 input-box" type="number" id="maxQuantity${productCount}" name="maxQuantity">
<!--                            <input class="w-100 input-box" type="number" id="maxQuantity${productCount}" name="maxQuantity${productCount}">-->
                        </div>
<!--                        <div class="col-3">-->
<!--                            <label for="shippingStart" class="sub-title">배송시작</label>-->
<!--                            <input class="w-100 input-box" type="date" id="shippingStart${productCount}" name="shippingStart${productCount}">-->
<!--                        </div>-->
<!--                        <div class="col-3">-->
<!--                            <label for="shippingFee" class="sub-title">배송비</label>-->
<!--                            <input class="w-100 input-box" type="number" id="shippingFee${productCount}" name="shippingFee${productCount}">-->
<!--                        </div>-->
                    </div>
                </div>
                ${removeItemButton("productItemRemove")}
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
        {name : "상품명1", size:"150", maxQuantity : 1, price:10000, introduction: "상세내용1"},
        {name : "상품명2", size:"160", maxQuantity : 2, price:1000, introduction: "상세내용2"},
        {name : "상품명3", size:"200", maxQuantity : 3, price:100, introduction: "상세내용3"},
        {name : "상품명4", size:"70", maxQuantity : 4, price:1000, introduction: "상세내용4"},
        {name : "상품명5", size:"75", maxQuantity : 5, price:20000, introduction: "상세내용5"},
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
                            <input class="w-100 input-box" type="text" id="name${productCount}" name="name" value=${item.name}>
<!--                            <input class="w-100 input-box" type="text" id="name${productCount}" name="name${productCount}" value=${item.name}>-->
                        </div>
                        <div class="col-4">
                            <label class="sub-title" for="size">사이즈</label>
                            <input class="w-100 input-box" type="text" id="size${productCount}" name="size" value=${item.size}>
<!--                            <input class="w-100 input-box" type="text" id="size${productCount}" name="size${productCount}" value=${item.size}>-->
                        </div>
                        
                        <div class="col-3">
                            <label for="price" class="sub-title">금액</label>
                            <input class="w-100 input-box" type="number" id="price${productCount}" name="price" value=${item.price}>
<!--                            <input class="w-100 input-box" type="number" id="price${productCount}" name="price${productCount}" value=${item.price}>-->
                        </div>
                    </div>
                    <div class="row" style="margin-top: 1em;">
                        <div class="col">
                            <label for="introduction" class="sub-title">설명</label>
                            <input class="w-100 input-box" type="text" id="introduction${productCount}" name="introduction" value=${item.introduction}>
<!--                            <input class="w-100 input-box" type="text" id="introduction${productCount}" name="introduction${productCount}" value=${item.body}>-->
                        </div>
                        <div class="col-2">
                            <label for="maxQuantity" class="sub-title">수량</label>
                            <input class="w-100 input-box" type="number" id="maxQuantity${productCount}" name="maxQuantity" value=${item.maxQuantity}>
<!--                            <input class="w-100 input-box" type="number" id="maxQuantity${productCount}" name="maxQuantity${productCount}" value=${item.count}>-->
                        </div>
<!--                        <div class="col-3">-->
<!--                            <label for="shippingStart" class="sub-title">배송시작</label>-->
<!--                            <input class="w-100 input-box" type="date" id="shippingStart${productCount}" name="shippingStart${productCount}" value=${item.shippingStart}>-->
<!--                        </div>-->
<!--                        <div class="col-3">-->
<!--                            <label for="shippingFee" class="sub-title">배송비</label>-->
<!--                            <input class="w-100 input-box" type="number" id="shippingFee${productCount}" name="shippingFee${productCount}" value=${item.shppingFee}>-->
<!--                        </div>-->
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


// 타임리프 대체 예정
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

// 타임리프 대체 예정
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
            .append($(`<td>`).addClass( "col-1").text(item.count));
        $("#projectEndList").append($tr);
    })
}



function indexCheckConfirm() {
    let check1 = $("#check1").prop('checked');
    let check2 = $("#check2").prop('checked');
    $.ajax({
        url : '/seller/regist/project1',
        type : 'post',
        data : {check1, check2},
        success : function (data) {

            console.log(data)
            if(data === 'success'){
                location.href='/seller/regist/project2'
            }else {
                alert("필수 항목에 체크를 하지 않았습니다.");
            }
        },
        error : function (error) {
            console.log(error)
        }
    })
}




const suneditor = (minHeight, maxHeight) => {
    SUNEDITOR.create('content', {
        font:[
            'Noto Sans KR', 'Arial','Nanum Gothic',
        ],
        buttonList: [
            ['undo', 'redo'],
            ['font', 'fontSize', 'formatBlock', 'paragraphStyle', 'blockquote'],
            ['bold', 'underline', 'italic', 'strike', 'subscript', 'superscript'],
            ['fontColor', 'hiliteColor', 'textStyle'],
            ['image','link'],
            ['removeFormat'],
            ['outdent', 'indent'],
            ['align', 'horizontalRule', 'list', 'lineHeight'],
            ['fullScreen', 'showBlocks'],
        ],
        lang: SUNEDITOR_LANG['ko'],
        minHeight : minHeight,
        maxHeight : maxHeight,
        minWidth : 1120,
        maxWidth : 1120,
    })
}

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

function projectInitRegist(temporary) {
    let title = $("#title").val()
    let subTitle = $("#content").val()
    let startDate = $("#startDate").val()
    let endDate = $("#endDate").val()
    let targetAmount = $("#money").val()

    $.ajax({
        url : '/seller/regist/project2',
        contentType : 'application/json; charset=utf-8;',
        data : JSON.stringify({title,subTitle,startDate,endDate,targetAmount}),
        type : 'post',
        success : function (data){
            if(data === 'success'){
                if(temporary){
                    alert("임시저장이 완료 됐습니다.")
                }else {
                    location.href='/seller/regist/project3'
                }
            }else {
                alert("입력 되지 않은 필수 항목이 존재합니다.");
            }
        },
        error : function (error){
            console.log(error)
        }

    })
}



function registProject3() {
    let productListLength =$("#productList").children("div").length;
    let dataList = [];
    for(let i = 1 ; i < productListLength+1; i++){
        const name = $("#name"+i).val()
        const size = $("#size"+i).val()
        const price = $("#price"+i).val()
        const introduction = $("#introduction"+i).val()
        const maxQuantity = $("#maxQuantity"+i).val()

        if(name === '' || size === '' || price === '' || introduction === '' || maxQuantity === '') break;

        dataList = [...dataList, {name,size,price,introduction,maxQuantity}]
    }
    $.ajax({
        url : '/seller/regist/project3',
        type : 'post',
        contentType: "application/json; charset=utf-8",
        data : JSON.stringify(dataList),
        success : function (success) {
            console.log(success)
        },
        error: function (error){
            console.log(error)
        }
    })
}

function registProject4() {
    let content = $($("#suneditor_content").children("div")
        .children("div")[3]).children("div")
        .html();

    $.ajax({
        url : "/seller/regist/project4",
        type : "post",
        contentType : "application/json; charset=utf-8;",
        data : JSON.stringify({content}),
        success : function (success){
            console.log(success)
        },
        error : function (error){
            console.log(error)
        }
    })
}

function registProject5() {
    let faqListLength =$("#faqList").children("div").length;
    let dataList = [];

    for(let i = 1 ; i < faqListLength+1; i++){
        const title = $("#title"+i).val()
        const content = $("#content"+i).val()
        const registDate = new Date();

        if(title === '' || content === '' ) break;

        dataList = [...dataList, {title, content, registDate}]
    }
    console.log(dataList)
    $.ajax({
        url : '/seller/regist/project5',
        type : 'post',
        contentType: "application/json; charset=utf-8",
        data : JSON.stringify(dataList),
        success : function (success) {
            console.log(success)
        },
        error: function (error){
            console.log(error)
        }
    })

}
function registProject6(){
    let title = $("#title").val();
    let content = $($("#suneditor_content").children("div")
                                            .children("div")[3]).children("div")
                                                                .html();
    let registDate = new Date();
    let deleteYN = 'N'

    $.ajax({
        url : "/seller/regist/project6",
        type : "post",
        contentType : "application/json; charset=utf-8;",
        data : JSON.stringify({title, content, registDate, deleteYN}),
        success : function (success){
            location.href = '/seller/regist/project6'
        },
        error : function (error){
            console.log(error)
        }
    })
}