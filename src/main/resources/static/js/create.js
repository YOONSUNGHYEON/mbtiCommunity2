window.onload = function() {
	document.boardForm.action = location.pathname;
}
//board/14/post/2 수정 페이지
function getParam(sMethod) {
	let url = location.pathname;
	const urlSplit = url.split("/");
	if (sMethod == 'boardOptionId') {
		return urlSplit[urlSplit.length - 2];
	}
	else if (sMethod == 'boardId') {
		return urlSplit[urlSplit.length - 1];
	}
}


function getnBoardOptionIdParam() {
	const params = new URLSearchParams(location.search);
	const nOptionId = params.get('id');
	return nOptionId;
}

function goLastPage() {
	const nOptionId =getnBoardOptionIdParam();
	location.href = './board.php?id=' + nOptionId;
}



