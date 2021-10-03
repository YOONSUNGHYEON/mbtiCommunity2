window.onload = function() {
	getBoardById(); //해당 게시물 내용 가져오기
	getCommentListByBoardId();
	getRecommentByUserIdAndBoardId();
}
function deleteConfirm() {
	if (!confirm("정말 취소 하시겠습니까?")) {

	} else {

	}
}
function getParam(sMethod) {
	let url = location.pathname;
	let params = new URLSearchParams(location.search);
	const urlSplit = url.split("/");
	if (sMethod == 'boardOptionId') {
		return urlSplit[urlSplit.length - 2];
	} else if (sMethod == 'boardId') {
		return urlSplit[urlSplit.length - 1];
	} else if(sMethod=='page') {
		return params.get('page');
	}
}

function goLastPage() {
	let page = getParam("page");
	location.href = '/boards/' + getParam("boardOptionId") + '?page=' + page;

}
//해당 게시물 내용 가져오기
function getBoardById() {
	const boardId = getParam("boardId");
	$.getJSON('/api/board/' + boardId, function(board) {
		$("#writer").html(board["memberDTO"]["id"]);
		$("#title").html(board["title"]);
		$("#content").html(board["content"]);
		let memberId = null;
		$.getJSON('/api/member', function(member) {
			if (member != null) {
				memberId = member['id'];
				if (board["memberDTO"]["id"] == memberId) {
					$("#edit").show();
					$("#delete").show();
				} else {
					$("#edit").hide();
					$("#delete").hide();
				}
			}
		})
	})

}



//게시물 삭제하기
function deleteBoard() {
	const url = location.pathname;
	$.ajax({
		type: 'DELETE',
		url: "/api" + url,
		success: function(deleteResult) {
			if (deleteResult == true) {
				alert("삭제 성공했습니다.");
			}
			else {
				alert(deleteResult);
			}
			goLastPage();
		}
	});
}

//수정하기
function editBoard() {
	const boardId = getParam('boardId');
	const boardOptionId = getParam('boardOptionId');
	location.href = "/boards/" + boardOptionId + "/post/" + boardId;
}


////////////////////////////////////
////////////Comment////////////////
//////////////////////////////////

//댓글 달기
function enrollComment() {
	const boardId = getParam('boardId');
	let commentForm = {
		content: $("#comment").val(),
	};
	$.ajax({
		url: "/api/comment/" + boardId,
		type: "POST",
		data: commentForm, // data에 바로 serialze한 데이터를 넣는다.
		success: function(result) {
			if (result['code'] == '400') {
				alert(result['message']);

			} else {
				alert(result['message']);
				$('#comment').val('');
				getCommentListByBoardId();
			}
		}
	})
}
//댓글 삭제
function deleteComment(commentId) {
	$.ajax({
		type: 'DELETE',
		url: "/api/comment/" + commentId,
		success: function() {
			getCommentListByBoardId();
		}
	});
}

//댓글 목록 가져오기
function getCommentListByBoardId() {
	const boardId = getParam('boardId');
	let memberId = null;
	$.getJSON('/api/member', function(member) {
		if (member != null) {
			memberId = member['id'];
		}
		$.getJSON('/api/comments/' + boardId, function(commentList) {
			let commentTable = "";
			$.each(commentList, function(index, comment) {
				commentTable += '<ul>';
				if (comment['memberDTO']['id'] == memberId) {
					commentTable += "<button  onclick='deleteComment(" + comment['seq'] + ");' class='btn-submit comment-delete'>삭제</button>";
				}
				commentTable += '<li class="comment-writer">' + comment['memberDTO']['id'] + '</li>';
				commentTable += '<li class="comment-content">' + comment['content'] + '</li>';
				commentTable += '<li class="comment-date">' + comment['createDate'] + '</li>';
				commentTable += '</ul><hr>';
			})
			$("#comment-list").html(commentTable);
		})
	})

}


////////////////////////////////////
////////////like////////////////
//////////////////////////////////

//좋아요 값 가져오기
function getRecommentByUserIdAndBoardId() {
	const boardId = getParam("boardId");
	$.ajax({
		type: 'GET',
		url: "/api/recommend/" + boardId,
		dataType: "json",
		success: function(recommend) {
			console.log(recommend);
			if (recommend == true) {
				$(".btn-like").attr("src", "/image/fullHeart.png");
			}
			else {
				$(".btn-like").attr("src", "/image/heart.png");
			}
		}
	});
}


//좋아요 누르기
function clickLike() {
	const boardId = getParam("boardId");
	$.ajax({
		type: 'POST',
		url: "/api/recommend/" + boardId,
		dataType: "text",
		success: function(recommend) {
			if (recommend == true) {
				$(".btn-like").attr("src", "/image/fullHeart.png");
			}
			else {
				$(".btn-like").attr("src", "/image/heart.png");
			}
		}
	});

}

























