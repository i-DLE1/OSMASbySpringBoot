let faqListCount = 1;
let productCount = 1;
let registProductList = [];
let registProductImgList = [];
let registFaqList = [];

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
                    <input id="faqNo${faqListCount}" value="${item.no}" hidden=/>
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
                        <input id="faqNo${faqListCount}" value="0" hidden/>
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
                    <input id="productNo${productCount}" hidden value="0"/>
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
                            <label for="status" class="sub-title">상품선택</label>
                            <select class="w-100 select-box" id="status${productCount}" name="status">
                                <option value="AVAILABLE" selected>선택가능</option>
                                <option value="NOT_AVAILABLE" >선택불가</option>
                            </select>
                        </div>
                        <div class="col-2">
                            <label for="count" class="sub-title">수량</label>
                            <input class="w-100 input-box" type="number" id="maxQuantity${productCount}" name="maxQuantity">
                        </div>
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
                    <input id="productNo${productCount}" hidden value="${item.no}"/>
                    <div class="row" style="margin-top: 1em;">
                        <div class="col">
                            <label class="sub-title" for="name">상품명</label>
                            <input class="w-100 input-box" type="text" id="name${productCount}" name="name" value=${item.name}>
                        </div>
                        <div class="col-4">
                            <label class="sub-title" for="size">사이즈</label>
                            <input class="w-100 input-box" type="text" id="size${productCount}" name="size" value=${item.size}>
                        </div>
                        
                        <div class="col-3">
                            <label for="price" class="sub-title">금액</label>
                            <input class="w-100 input-box" type="number" id="price${productCount}" name="price" value=${item.price}>
                        </div>
                    </div>
                    <div class="row" style="margin-top: 1em;">
                        <div class="col">
                            <label for="introduction" class="sub-title">설명</label>
                            <input class="w-100 input-box" type="text" id="introduction${productCount}" name="introduction" value=${item.introduction}>
                        </div>
                        <div class="col-2">
                            <label for="status" class="sub-title">상품선택</label>
                            <select class="w-100 select-box" id="status${productCount}" name="status">
                                <option value="AVAILABLE" ${item.status == 'AVAILABLE' ?  'selected' : ''}>선택가능</option>
                                <option value="NOT_AVAILABLE" ${item.status == 'NOT_AVAILABLE' ?  'selected' : ''} >선택불가</option>
                            </select>
                        </div>
                        <div class="col-2">
                            <label for="maxQuantity" class="sub-title">수량</label>
                            <input class="w-100 input-box" type="number" id="maxQuantity${productCount}" name="maxQuantity" value=${item.maxQuantity}>
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
    if (productCount > 2) {
        productAddSubItem(productCount);
    }
}

function subCategory(mainCategoryCode, data){
    $('#subCategory').html("")
    const $select = $('<select>').addClass('select-box').attr("id",'subCategoryCode').attr('name','subCategory')
    const $option = $('<option>').text('전체').val(mainCategoryCode);
    $select.append($option)

    data.forEach(e=>{
        const $option = $('<option>').text(e.name).val(e.no)
        $select.append($option)
    })
    $('#subCategory').append($select);
}

function getMainCategoryCode(){
    $("#mainCategory").bind("change",function (){
        const mainCategoryCode = $("#mainCategory").val()

        if(mainCategoryCode === ''){
            $('#subCategory').html("")
            return;
        }

        $.ajax({
            url : `/seller/regist/getSubCategory?mainCategoryCode=${mainCategoryCode}`,
            success : function (success) {
                subCategory(mainCategoryCode, success);
            },
            error : function (error){
                console.log(error);
            }
        })

    })
}
function subMainCategoryCode(){
    $("#subCategoryCode").bind("change",function () {
        const subCategoryCode = $("#subCategoryCode").val()
        console.log(subCategoryCode)
    })
}



// 타임리프 대체 예정
function newsList(data){
    data.forEach((item,index)=>{
        let $deleteBtn = $('<input>').addClass("cus-button")
            .attr("value","삭제")
            .attr("type","button")
            .attr("onclick",`deleteProjectNews(${item.no})`)
        let $td = $('<td>').append($deleteBtn)
        let $tr = $(`<tr>`).attr("onclick",`loadProjectFaq(${item.no})`);
        $tr.append($(`<th>`).text(index+1))
            .append($(`<td>`).text(item.title))
            .append($(`<td>`).text(item.content))
            .append($(`<td>`).text(item.registDate))
            .append($(`<td>`).text(item.modifyDate))
            .append($td);
        $("#newsList").append($tr);
    });
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

//temporary : boolean
function project3LoadData() {
    let no = new URLSearchParams(location.search).get('no')
    $.ajax({
        url : '/seller/regist/project3ProductGetdata'  + (no === null ?  '' : `?no=${no}`),
        type : "get",
        success : function (success) {
            console.log(success)
            let productList = [...success]
            registProductList = [...success];
            productItemLoad(productList)
        },
        error : function (error){
            console.log(error)
        }
    })

    $.ajax({
        url : '/seller/regist/project3ProductGetImg' + (no === null ?  '' : `?no=${no}`),
        type : "get",
        success : function (success) {
            if(success.length === 0 ) return;
            let productImgList = [...success]
            registProductImgList = [...success];
            loadProductImg(productImgList)
        },
        error : function (error){
            console.log(error)
        }
    })
}

function project5LoadData(){
    let no = new URLSearchParams(location.search).get('no')
    $.ajax({
        url : '/seller/regist/project5GetData' + (no === null ?  '' : `?no=${no}`),
        type : "get",
        success : function (success) {
            registFaqList = [...success];
            fqaLoadList(success);
        },
        error : function (error){
            console.log(error);
        }
    })


}
function project6LoadData() {
    let no = new URLSearchParams(location.search).get('no')
    $.ajax({
        url : "/seller/regist/project6GetData" + (no === null ?  '' : `?no=${no}`),
        success : function (succuess) {
            newsList(succuess)
        },
        error : function (error){
            console.log(error)
        }
    })

}

function previewImage(ele) {
    let img = new FileReader();
    img.onload = function (e){
        $(ele).siblings("label").children().prop("src",e.target.result)
    };
    img.readAsDataURL(ele.files[0]);
}

function loadProductImg(imgFileList){
    let rootUrl = location.origin+'/files/seller/project/';
    let legnth = imgFileList.length
    $("#present").prop("src",rootUrl+imgFileList[0]?.changeName)
    $("#thumbnail").prop("src",rootUrl+imgFileList[1]?.changeName)
    $("#imgfile0").prop("src",rootUrl+imgFileList[2]?.changeName)
    $("#imgfile1").prop("src",rootUrl+imgFileList[3]?.changeName)
    $("#imgfile2").prop("src",rootUrl+imgFileList[4]?.changeName)
}

function loadProjectFaq(no){
    $.ajax({
        url: `/seller/regist/projectNews?no=${no}`,
        success : function (success) {
            $("#title").val(success.title);
            $($("#suneditor_content").children("div")
                .children("div")[3]).children("div")
                .html("");
            $($("#suneditor_content").children("div")
                .children("div")[3]).children("div")
                .html(success.content);
            $("#newsSumbit").attr("onclick",`modifyProjectNews(${no})`).val("수정");
        } ,
        error : function (error) {
        }
    })
}

function deleteProjectNews(no){
    $.ajax({
        url : `/seller/regist/deleteProjectNews?no=${no}`,
        success : function (success){
            alert("정삭적으로 삭제되었습니다.")
            location.reload();
        },
        error : function (error){
            alert("삭제에 실패했습니다.")
        }
    })
}

function modifyProjectNews(no){
    let title = $("#title").val();
    let content = $($("#suneditor_content").children("div")
        .children("div")[3]).children("div")
        .html();
    
    $.ajax({
        url: `/seller/regist/modifyProjectNews`,
        type : 'post',
        contentType : 'application/json; charset=utf-8;',
        data : JSON.stringify({no, title, content}),
        success : function (success) {
            alert("수정이 완료됐습니다.")
            location.reload();
        },
        error : function (error) {
            alert("수정이 실패했습니다.")
        }
        
    })
}

function initProjectNews() {
    $("#title").val("");
    $($("#suneditor_content").children("div")
        .children("div")[3]).children("div")
        .html("");
}

function ajaxCsrfSet() {
    let token = $("meta[name='_csrf']").attr("content");
    let header = $("meta[name='_csrf_header']").attr("content");

    console.log(token)
    console.log(header)

    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
}


function indexCheckConfirm() {
    let no = new URLSearchParams(location.search).get('no')
    let check1 = $("#check1").prop('checked');
    let check2 = $("#check2").prop('checked');
    $.ajax({
        url : '/seller/regist/project1' + (no === null ?  '' : `?no=${no}`),
        type : 'post',
        data : {check1, check2},
        success : function (data) {
            if(data === 'success'){
                location.href='/seller/regist/project2' + (no === null ?  '' : `?no=${no}`);
            }else {
                alert("필수 항목에 체크를 하지 않았습니다.");
            }
        },
        error : function (error) {
            console.log(error)
        }
    })
}

function projectInitRegist(temporary) {
    let no = new URLSearchParams(location.search).get('no')
    let title = $("#title").val()
    let subTitle = $("#content").val()
    let startDate = $("#startDate").val()
    let endDate = $("#endDate").val()
    let targetAmount = $("#money").val()
    let subCategoryCode= $("#subCategoryCode").val()
    let category = {no : subCategoryCode};

    $.ajax({
        url : '/seller/regist/project2' + (no === null ?  '' : `?no=${no}`),
        contentType : 'application/json; charset=utf-8;',
        data : JSON.stringify({title,subTitle,startDate,endDate,targetAmount,category }),
        type : 'post',
        success : function (data){
            if(data === 'success'){
                if(temporary){
                    alert("임시저장이 완료 됐습니다.")
                }else {

                    location.href='/seller/regist/project3' + (no === null ?  '' : `?no=${no}`);
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

// temporary : boolean
function registProject3(temporary) {
    let presentFile = $("#present").attr("src")
    let thumbnailFile = $("#thumbnail").attr("src")

    if(presentFile === undefined || thumbnailFile === undefined){
        alert("대표이미지와 썸네일 이미지는 필수로 등록 해야합니다.")
        return;
    }

    let dataList = [];
    let formData = new FormData();
        formData.append("presentFile",presentFile)
        formData.append("thumbnailFile",thumbnailFile)
    let files = $(".file")
    let productListLength =$("#productList").children("div").length;
    let no = new URLSearchParams(location.search).get('no')

    for(let i = 0 ; i < files.length; i++ ){
        formData.append("fileList",files[i].files[0])// }
    }

    for(let i = 1 ; i < productListLength+1; i++){
        const no = $("#productNo"+i).val()
        const name = $("#name"+i).val()
        const size = $("#size"+i).val()
        const price = $("#price"+i).val()
        const introduction = $("#introduction"+i).val()
        const maxQuantity = $("#maxQuantity"+i).val()
        const status = $("#status"+i).val()

        if(name === '' || size === '' || price === '' || introduction === '' || maxQuantity === '') break;

        dataList = [...dataList, {no, name, size, price, introduction, maxQuantity, status}]
    }
    let data = {new : dataList, old : registProductList, oldImg : registProductImgList}
    formData.append("productList", new Blob([JSON.stringify(data)],{type:"application/json; charset=utf-8;"}));

    $.ajax({
        url : '/seller/regist/project3' + (no === null ?  '' : `?no=${no}`),
        type : 'post',
        contentType : false,
        processData : false,
        enctype : 'multipart/form-data',
        data : formData,
        success : function (success) {
            if(temporary){
                alert("임시저장이 완료 됐습니다.")
            }else {
                location.href='/seller/regist/project4' + (no === null ?  '' : `?no=${no}`);
            }
        },
        error: function (error){
            console.log(error)
        }
    })
}

function registProject4(temporary) {
    let no = new URLSearchParams(location.search).get('no')
    let content = $($("#suneditor_content").children("div")
        .children("div")[3]).children("div")
        .html();
    let projectNo =  $("#no").val();

    $.ajax({
        url : "/seller/regist/project4" + (no === null ?  '' : `?no=${no}`),
        type : "post",
        contentType : "application/json; charset=utf-8;",
        data : JSON.stringify({no:projectNo, content}),
        success : function (success){
            if(temporary){
                alert("임시저장이 완료 됐습니다.")
            }else {
                let no = new URLSearchParams(location.search).get('no')
                location.href='/seller/regist/project5' + (no === null ?  '' : `?no=${no}`);
            }
        },
        error : function (error){
            console.log(error)
        }
    })
}

function registProject5(temporary) {
    let no = new URLSearchParams(location.search).get('no')
    let faqListLength =$("#faqList").children("div").length;
    let dataList = [];

    for(let i = 1 ; i < faqListLength+1; i++){
        const title = $("#title"+i).val()
        const content = $("#content"+i).val()
        const registDate = new Date();
        const no = $("#faqNo"+i).val();

        if(title === '' || content === '' ) break;

        dataList = [...dataList, {no, title, content, registDate}]
    }

    $.ajax({
        url : '/seller/regist/project5' + (no === null ?  '' : `?no=${no}`),
        type : 'post',
        contentType: "application/json; charset=utf-8",
        data : JSON.stringify({old : registFaqList, new : dataList}),
        success : function (success) {
            if(temporary){
                alert("임시저장이 완료 됐습니다.")
            }else {
                location.href='/seller/regist/project6' + (no === null ?  '' : `?no=${no}`);
            }
        },
        error: function (error){
            console.log(error)
        }
    })

}
function registProject6(){
    let no = new URLSearchParams(location.search).get('no')
    let title = $("#title").val();
    let content = $($("#suneditor_content").children("div")
                                            .children("div")[3])
                                            .children("div")
                                            .html();
    $.ajax({
        url : "/seller/regist/project6" + (no === null ?  '' : `?no=${no}`),
        type : "post",
        contentType : "application/json; charset=utf-8;",
        data : JSON.stringify({title, content}),
        success : function (success){
            location.reload()
        },
        error : function (error){
            console.log(error)
        }
    })
}
function nextProject7() {
    let no = new URLSearchParams(location.search).get('no')
    location.href = '/seller/regist/project7' + (no === null ?  '' : `?no=${no}`)
}

function registProjectComplete(){
    let no = new URLSearchParams(location.search).get('no')

    $.ajax({
        url : `/seller/regist/projectRegist` + (no === null ?  '' : `?no=${no}`),
        type : 'get',
        success : function (success) {
            alert("성공적으로 프로젝트가 등록되었습니다.\n심사 승인 후 프로젝트가 시작될 수 있습니다.")
            location.href = `/seller/projectList`;
        },
        error : function (error){
            console.log(error)
        }
    })

}

