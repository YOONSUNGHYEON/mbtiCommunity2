window.onload = function() {
	document.boardForm.action = location.pathname;
	checkWritePermission();
}
function getnBoardOptionIdParam() {
	const params = new URLSearchParams(location.search);
	const nOptionId = params.get('id');
	return nOptionId;
}
//create 페이지 들어가자마자 글쓰기 권한이 있는지 확인
function checkWritePermission(){
	const nBoardOptionId = getnBoardOptionIdParam();
	$.ajax({
		type: 'GET',
		url: "BoardController.php?method=checkWritePermission&id=" + nBoardOptionId,
		dataType: "text",
		success: function(sResult) {
			if(sResult!="") {			
				location.href = "./board.php?id=" + nBoardOptionId;
				alert(sResult);
			}
			
		}
	});
}

function goLastPage() {
	const nOptionId =getnBoardOptionIdParam();
	location.href = './board.php?id=' + nOptionId;
}



