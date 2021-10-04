window.onload = function() {
	getMbtiList();
}


function getMbtiList() {
	$.getJSON('/api/boardOptions', function(boardOptionList) {
		let option = "";
		
		for (let i = 0; i < boardOptionList.length-2; i += 4) {
			option += ' <div class="d-table gap-3">';
			for (let j = i; j < i + 4; j++) {
				option += '<a class="btn btn-lg btn-primary mbti-btn" type="button" href="/boards/'+boardOptionList[j]["seq"]+'?page=1">';
				option += boardOptionList[j]["name"];
				option += '</a>';
			}
			option += '</div>';
		}
		$("#content-wrapper").html(option);
	});
}