window.onload = function() {
	//새글 버튼 보여주기 여부 검사
	getOptionNameByOptionId();
	getListByOptionId(getParam('page'));
}

function getParam(sMethod) {
	let url = location.pathname;
	const urlSplit = url.split("/");
	if(sMethod=='page') {
		
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
	let nBoardOptionId = getParam('optionId');
	
	$.getJSON('/api/boards/' + nBoardOptionId, function(boardList) {
		
		let boardTable = "";
		$.each(boardList, function(index, item) {
				boardTable += '<tr style="cursor:pointer;">';
				boardTable += '<th class="content-th" scope="row"><div><a class="board-a" href="/board/';
				boardTable += item["seq"];
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
	/*$.ajax({
		type: 'GET',
		url: "BoardController.php?method=board&id=" + nBoardOptionId + "&page=" + page,
		dataType: "json",
		success: function(data) {
			let boardTable = "";
			for (let i = 1; i <= data['nCurrentCount']; i++) {
				boardTable += '<tr style="cursor:pointer;">';
				boardTable += '<th class="content-th" scope="row"><div><a class="board-a" href="view.php?optionId=' + nBoardOptionId + '&id=' + data[i]['nBoardSeq'] +  '&page=' +data["pageData"]["nCurrentPage"] + '">' + data[i]['sTitle'] + '</a></div>';
				boardTable += '<td class="content-th">' + data[i]['sID'] + '</td>';
				boardTable += '<td class="content-th">' + data[i]['nHit'] + '</td>';
				boardTable += '<td class="content-th">' + data[i]['nCommentCount'] + '</td>';
				boardTable += '<td class="content-th">' + data[i]['dtCreateDate'] + '</td>';
				if(data["nCheckAdmin"]==true) {
					boardTable += "<td class='content-th'><button id='delete' onclick='deleteBoard(" + data[i]['nBoardSeq']+ "," + page + ");' class='btn-submit'>삭제</button></td>";
				}
				boardTable += '</tr>';
			}
			$("#boardTable").html(boardTable);
			
			let pagingHtml="";
			pagingHtml+="<li class='page-item'><a class='page-link' href='javascript:getListByOptionId(" + data["pageData"]["nStartPage"]+")'>&laquo;</a></li>";
			for (var i = data["pageData"]['nStartPage']; i <= data["pageData"]["nEndPage"]; i++) {
				if (i == data["pageData"]["nCurrentPage"]) {
					pagingHtml += "<li class='page-item active'>";
				} else {
					pagingHtml += "<li class=page-item>";
				}
				pagingHtml += "<a class=page-link href='javascript:getListByOptionId("+i+")'>" + i;
				pagingHtml += "</a></li>";
			}
			pagingHtml+="<li class='page-item'><a class='page-link' href='javascript:getListByOptionId(" + data["pageData"]["nEndPage"]+")'>&raquo;</a></li>";
			$('#pagination').empty();
			$('#pagination').html(pagingHtml);


		}
	});*/
}




