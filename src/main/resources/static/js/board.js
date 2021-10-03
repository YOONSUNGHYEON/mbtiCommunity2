window.onload = function() {
	//새글 버튼 보여주기 여부 검사
	getOptionNameByOptionId();
	getListByOptionId(getParam('page'));
}

function getParam(sMethod) {
	let url = location.pathname;
	let params = new URLSearchParams(location.search);
	const urlSplit = url.split("/");
	
	if(sMethod=='page') {
		return params.get('page');
	}
	else if(sMethod=='optionId') {
		return urlSplit[urlSplit.length-1];
	}
}

function clickCreateBtn() {
	let nBoardOptionId = getParam('optionId');
	location.href = "/boards/" + nBoardOptionId+"/post";
}
function getOptionNameByOptionId() {
	let nBoardOptionId = getParam('optionId');

	$.getJSON('/api/boardOption/' + nBoardOptionId, function(boardOption) {
		//console.log(boardOptionName);
		$("#boardTitle").text(boardOption["name"]);
	})
	
}
//게시물 삭제하기
function deleteBoard(nBoardId, page) {

	$.ajax({
		type: 'DELETE',
		url: "BoardController.php?method=delete&id=" + nBoardId,
		success: function(board) {
			getListByOptionId(page);
		}
	});
}

//게시판 목록 가져오기
function getListByOptionId(page) {
	let boardOptionId = getParam('optionId');
	console.log(page);
	$.getJSON('/api/boards/' + boardOptionId+'?page='+page, function(response) {
		//console.log(response);
		
		let pagingHtml="";
			pagingHtml+="<li class='page-item'><a class='page-link' href='javascript:getListByOptionId(" + response["pagination"]["startPage"]+")'>&laquo;</a></li>";
			for (var i = response["pagination"]["startPage"]; i <= response["pagination"]["endPage"]; i++) {
				if (i == response["pagination"]["currentPage"]) {
					pagingHtml += "<li class='page-item active'>";
				} else {
					pagingHtml += "<li class=page-item>";
				}
				pagingHtml += "<a class=page-link href='javascript:getListByOptionId("+i+")'>" + i;
				pagingHtml += "</a></li>";
			}
			pagingHtml+="<li class='page-item'><a class='page-link' href='javascript:getListByOptionId(" + response["pagination"]["endPage"]+")'>&raquo;</a></li>";
			$('#pagination').empty();
			$('#pagination').html(pagingHtml);
		
		
		let boardTable = "";
		$.each(response["boardList"], function(index, item) {
				boardTable += '<tr style="cursor:pointer;">';
				boardTable += '<th class="content-th" scope="row"><div><a class="board-a" href="/board/';
				boardTable += boardOptionId +"/"+item["seq"]+"?page="+page;
				boardTable +='">' ;
				boardTable += item["title"] + '</a></div>';
				boardTable += '<td class="content-th">' + item["memberDTO"]["id"] + '</td>';
				boardTable += '<td class="content-th">' + 0 + '</td>';
				boardTable += '<td class="content-th">' + 0 + '</td>';
				boardTable += '<td class="content-th">' + item["createDate"] + '</td>';
				boardTable += '</tr>';
		});
		$("#boardTable").html(boardTable);
		
	})
}




