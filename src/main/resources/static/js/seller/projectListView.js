let selectCategoryList = []

let pageNo = 1;
let endList = false;
let categoryNo;

function projectListView(data){

    let color = "";
    data.forEach(item=>{
        if(item.date >60){
            color = "days-default";
        }else if(item.date > 30){
            color = "days-green";
        }else if(item.date > 15){
            color = "days-blue";
        }else{
            color = "days-red";
        }
        let $div = $("<div>").addClass("project-item").attr('onclick',`moveSale(${item.no})`)
        let $thumbnailDiv = $("<div>");
        const $thumbnailImg = $("<img>").addClass("project-item-thumbnail")
        item?.img === undefined ? $thumbnailImg.attr('src','./files/images/common/notImg.jpg') : $thumbnailImg.attr("src",item.img);
        let $contentDiv = $("<div>").addClass("project-row");
        const $moneyDiv = $("<div>").text(item.currentAmount);
        let $labelDiv  = $("<div>");
        const $labelSpan = $("<span>").addClass(color).text(`${item.date}일남음`);
        const $titleDiv = $("<div>").text(item.title);
        let $userDiv = $("<div>");
        const $userCountSpan = $("<span>").text(item.views);
        $userDiv.append("참여자수 : ").append($userCountSpan).append("명");

        $thumbnailDiv.append($thumbnailImg);
        $labelDiv.append($labelSpan);
        $contentDiv.append($moneyDiv)
                    .append($labelDiv);
        $div.append($thumbnailDiv)
            .append($contentDiv)
            .append($titleDiv)
            .append($userDiv);

        $("#project-view-list").append($div);
    })
}

function moveSale(no){
    location.href = `/seller/sales/detail?no=${no}`
}

function createCategoryEle(id, text){
    let $label = $('<label>').addClass('project-list-tag-selection').attr('id',id).attr('onclick','categorySelect(this)')
    let $span = $('<span>').text(text);
    $label.append($span);
    return $label;
}
function subCategoryList(mainNo, data){
    let $subCategoryEle = $('#subCategoryList').html("")
    $subCategoryEle.append(createCategoryEle(`maincategory-${mainNo}`,'전체'));

    data.forEach(item=>{
        $subCategoryEle.append(createCategoryEle(`category-${item.no}`,item.name));
    })
}

function mainCategorySelect(){
    $(".main-category").click(function () {
        let categoryNo = this?.id?.toString().split('-')[1]
        subCategoryGetdata(categoryNo)
    })
}

function subCategoryGetdata(no){
    if(no === undefined) return;
    $.ajax(({
        url: `/getSubCategory?no=${no}`,
        type: 'get',
        success : function (success){
            subCategoryList(no, success)
        },
        error : function (error){
            console.log(error)
        }
    }))
}



function categorySelect(ele){
    pageNo = 1;
    endList = false;
    categoryNo = ele?.id?.toString().split('-')[1];
    let elist = $(ele).siblings()
    $(ele).addClass('project-list-tag-selection-active')

    if(ele?.id?.toString().split('-')[0] === 'maincategory') {

        salesListAjax(pageNo, categoryNo);
        let siblings = [$(ele).siblings()]
        siblings.forEach(e=>{
            $(e).removeClass('project-list-tag-selection-active')
        })
        selectCategoryList = [];
        return;
    }

    if (categoryNo === undefined) return;

    $(elist[0]).removeClass('project-list-tag-selection-active')

    if(selectCategoryList.indexOf(categoryNo)>=0){
        selectCategoryList = selectCategoryList.filter(e=> e !== categoryNo);
        $(ele).removeClass('project-list-tag-selection-active')
        salesListAjax(pageNo, selectCategoryList);
        categoryNo = undefined;
    }else {
        $(ele).addClass('project-list-tag-selection-active')
        selectCategoryList = [...selectCategoryList, categoryNo]
        salesListAjax(pageNo, selectCategoryList);
        categoryNo = undefined;
    }
}

function salesListAjax(pageNo, filter) {

    if(pageNo === undefined) pageNo = 1;

    if(pageNo === 1) {
        // saleItemList = [];
        $("#project-view-list").html("")
    }
    if(endList) return;

    $.ajax({
        url: `/getSaleList` + `?startNo=${pageNo}` + (filter === undefined ? '' : `&categoryCode=${filter.toString()}`) ,
        type : "get",
        success : function (success) {
            console.log(success)
            if(success.length === 0 ) endList = true;
            projectListView(success)
        },
        error : function (error){
            console.log(error)
        }
    })
}

function initCategory(){
    $('#subCategoryList').html("")
    selectCategoryList = [];
    endList = false;
    pageNo = 1;
    salesListAjax()
}

function moreItem(){
    pageNo += 10;
    salesListAjax(pageNo, categoryNo === undefined ? selectCategoryList : categoryNo);
}