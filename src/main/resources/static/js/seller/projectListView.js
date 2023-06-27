function projectListViewDummyData(){
    let data = [
        {img: "/images/seller/project/item1.png" ,money:"123,123,123", date:12,title:"타이틀1",user:1001},
        {img: "/images/seller/project/item2.jpg" ,money:"1,123,123", date:14,title:"타이틀1",user:101},
        {img: "/images/seller/project/item3.png" ,money:"12,123,123", date:17,title:"타이틀123",user:201},
        {img: "/images/seller/project/item4.jpg" ,money:"123,123,123", date:20,title:"타이틀234234231",user:1},
        {img: "/images/seller/project/item5.jpg" ,money:"123,123,123", date:60,title:"긴글 테스트 니아리냐어리냐어리ㅓ냐이러ㅑ너ㅣㅑ타이ㄴㄹㅇㄴㄹㄴㄹㄴㅇㄹ틀1",user:121},
        {img: "/images/seller/project/item5.jpg" ,money:"123,123,123", date:90,title:"긴글 테스트 니아리냐어리냐어리ㅓ냐이러ㅑ너ㅣㅑ타이ㄴㄹㅇㄴㄹㄴㄹㄴㅇㄹ틀1",user:121},
    ]
    projectListView(data)
}

function projectListView(data){
    $("#project-view-list").html("")
    let color = "";
    data.forEach(item=>{
        if(item.date >60){
            color = "days-default";
        }else if(item.date >30){
            color = "days-green";
        }else if(item.date > 15){
            color = "days-blue";
        }else{
            color = "days-red";
        }

        let $div = $("<div>").addClass("project-item");
        let $thumbnailDiv = $("<div>");
        const $thumbnailImg = $("<img>").addClass("project-item-thumbnail").attr("src",item.img);
        let $contentDiv = $("<div>").addClass("project-row");
        const $moneyDiv = $("<div>").text(item.money);
        let $labelDiv  = $("<div>");
        const $labelSpan = $("<span>").addClass(color).text(`${item.date}일남음`);
        const $titleDiv = $("<div>").text(item.title);
        let $userDiv = $("<div>");
        const $userCountSpan = $("<span>").text(item.user);
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


function categorySelect(){
    let selectCategoryList = []
    $(".project-list-tag-selection").click(function () {
        const $thisTagId = $(this).attr("id").toString().split('-')[1]


        if(selectCategoryList.indexOf($thisTagId)>=0){
            selectCategoryList = selectCategoryList.filter(e=> e !== $thisTagId);
            $(this).removeClass('project-list-tag-selection-active')
            salesListAjax(selectCategoryList);
        }else {
            $(this).addClass('project-list-tag-selection-active')
            selectCategoryList = [...selectCategoryList,$thisTagId]
            salesListAjax(selectCategoryList);
        }
    })
}

function salesListAjax(filter) {
    $.ajax({
        url: `/getSaleList?categoryCode=${filter.toString()}`,
        type : "get",
        success : function (success) {
            projectListView(success)
        },
        error : function (error){
            console.log(error)
        }
    })


}