window.onload = function() {
	findByBoardId();
}


function getParam(sMethod) {
	let url = location.pathname;
	let urlSplit = url.split("/");
	if (sMethod == 'boardOptionId') {
		return urlSplit[urlSplit.length - 3];
	}
	else if (sMethod == 'boardId') {
		return urlSplit[urlSplit.length - 1];
	}
}

//해당 게시물 내용 가져오기
function findByBoardId() {
	let boardId = getParam("boardId");
	$.getJSON('/api/board/' + boardId, function(board) {
		$('#title').val(board["title"]);
		$('#content').val(board["content"]);

	})
}
function goLastPage() {
	let boardId = getParam('boardId');
	let boardOptionId = getParam('boardOptionId');
	location.href = '/board/' + boardOptionId + '/' + boardId;
}

function clickEditBtn() {
	let boardId = getParam('boardId');
	let boardOptionId = getParam('boardOptionId');

	let boardForm = {
		title: $("#title").val(),
		content: $("#content").val(),
	};

	$.ajax({
		url: "/api/boards/" + boardOptionId + "/post/" + boardId,
		type: "POST",
		data: boardForm,
		success: function(updateResult) {
			if (updateResult['code'] == '400') {
				alert(updateResult['message']);
			}
			else if (updateResult['code'] == '200') {
				alert(updateResult['message']);
			}
			location.href = '/board/' + boardOptionId + '/' + boardId;
		},
	})
}