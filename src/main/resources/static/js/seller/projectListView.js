let SELECT_CATEGORY_LIST = []
let PAGE_NUMBER = 1;
let END_LIST = false;
let CATEGORY_CODE;

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

        let $divContainer = $('<div>').addClass('thumbnailWarp')
        const $favoriteBtn = $("<input>").attr('id','favorite-btn').attr('type','button').addClass('favorite-button')
        let $div = $("<div>").addClass("project-item").attr('onclick',`moveSale(${item.no})`)
        let $thumbnailDiv = $("<div>");
        const $thumbnailImg = $("<img>").addClass("project-item-thumbnail")
            .attr("src",item?.img === undefined ? '' : item?.img)
            .attr('onerror',"this.src='/images/common/notImg.jpg'")
        let $contentDiv = $("<div>").addClass("project-row");
        const $moneyDiv = $("<div>");
        let $labelDiv  = $("<div>");
        const $titleDiv = $("<div>").text(item.title);
        const $labelSpan = $("<span>")
        const $alertButton = $('<input>')
        const $userCountSpan = $("<span>")
        let $userDiv = $("<div>");

        if(item?.openExpect === 'true'){
            $labelSpan.addClass('days-cyan').text(' 오픈예정');
            $moneyDiv.text(`${item.startDate}일 남음`).addClass('font-cyan')
            $titleDiv.addClass('font-cyan')
        }else {
            $labelSpan.addClass(color).text(`${item.date}일남음`);
            $moneyDiv.text(item.currentAmount);
        }

        if(item?.favorite === 'true'){
            $favoriteBtn.addClass('activate-favorite').attr('onclick',`favoriteToggle(this,${item.no},false)`)
        }else {
            $favoriteBtn.addClass('deactivate-favorite').attr('onclick',`favoriteToggle(this,${item.no},true)`)
        }

        if(item?.today === 'true') {
            const $todayLabel = $('<input>').addClass('today-label')
            $divContainer.append($todayLabel);
        }

        $thumbnailDiv.append($thumbnailImg);

        $labelDiv.append($labelSpan);
        $contentDiv.append($moneyDiv)
                    .append($labelDiv);

        $div.append($thumbnailDiv)
            .append($contentDiv)
            .append($titleDiv);

        if(item?.openExpect === 'true'){
            $alertButton.attr('type','button').addClass('alert-btn').val("오픈 알림")
            $div.append($alertButton)
        }else{
            $userCountSpan.text(item.views);
            $userDiv.append("참여자수 : ").append($userCountSpan).append("명");
            $div.append($userDiv);
        }

        $divContainer.append($favoriteBtn);
        $divContainer.append($div)

        $("#project-view-list").append($divContainer);
    })
}

function favoriteToggle(e, no, isActive){

    $.ajax({
        url : `/projectFavorite?no=${no}&isActive=${isActive}`,
        type : 'get',
        success : function (success) {
            if(success === 'noAccount') location.href='/member/login/login'
            if(success === 'insertSuccess') $(e).removeClass('deactivate-favorite').addClass('activate-favorite').attr('onclick',`favoriteToggle(this,${no},false)`)
            if(success === 'deleteSuccess') $(e).removeClass('activate-favorite').addClass('deactivate-favorite').attr('onclick',`favoriteToggle(this,${no},true)`)
        },
        error : function (e){
        }
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
        let CATEGORY_CODE = this?.id?.toString().split('-')[1]
        subCategoryGetdata(CATEGORY_CODE)
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
        }
    }))
}



function categorySelect(ele){
    PAGE_NUMBER = 1;
    END_LIST = false;
    CATEGORY_CODE = ele?.id?.toString().split('-')[1];
    let elist = $(ele).siblings()
    $(ele).addClass('project-list-tag-selection-active')

    if(ele?.id?.toString().split('-')[0] === 'maincategory') {

        salesListAjax(PAGE_NUMBER, CATEGORY_CODE);
        let siblings = [$(ele).siblings()]
        siblings.forEach(e=>{
            $(e).removeClass('project-list-tag-selection-active')
        })
        SELECT_CATEGORY_LIST = [];
        return;
    }

    if (CATEGORY_CODE === undefined) return;

    $(elist[0]).removeClass('project-list-tag-selection-active')

    if(SELECT_CATEGORY_LIST.indexOf(CATEGORY_CODE)>=0){
        SELECT_CATEGORY_LIST = SELECT_CATEGORY_LIST.filter(e=> e !== CATEGORY_CODE);
        $(ele).removeClass('project-list-tag-selection-active')
        salesListAjax(PAGE_NUMBER, SELECT_CATEGORY_LIST);
        CATEGORY_CODE = undefined;
    }else {
        $(ele).addClass('project-list-tag-selection-active')
        SELECT_CATEGORY_LIST = [...SELECT_CATEGORY_LIST, CATEGORY_CODE]
        salesListAjax(PAGE_NUMBER, SELECT_CATEGORY_LIST);
        CATEGORY_CODE = undefined;
    }
}

function salesListAjax(pageNo, filter) {

    if(pageNo === undefined) pageNo = 1;

    if(pageNo === 1) {
        $("#project-view-list").html("")
    }
    if(END_LIST) return;

    const today = new URLSearchParams(location.search).get('today')
    const openExpect = new URLSearchParams(location.search).get('openExpect')

    $.ajax({
        url: `/getSaleList`
            + `?startNo=${pageNo}`
            + (filter === undefined ? '' : `&categoryCode=${filter.toString()}`)
            + (today === null ? '' : `&today=${true}`)
            + (openExpect === null ? '' : `&openExpect=${true}`),
        type : "get",
        success : function (success) {
            if(success.length === 0 ) END_LIST = true;
            projectListView(success)
        },
        error : function (error){
        }
    })
}

function initCategory(){
    $('#subCategoryList').html("")
    SELECT_CATEGORY_LIST = [];
    END_LIST = false;
    PAGE_NUMBER = 1;
    salesListAjax()
}

function moreItem(){
    PAGE_NUMBER += 13;
    salesListAjax(PAGE_NUMBER, CATEGORY_CODE === undefined ? SELECT_CATEGORY_LIST : CATEGORY_CODE);
}