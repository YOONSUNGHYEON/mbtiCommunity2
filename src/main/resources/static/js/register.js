$(window).load(function() {
	getMbtiList();
});
function register() {
	let registerForm = {
		id: $("#id").val(),
		password: $("#password").val(),
		password2: $("#password2").val()
	};
	let mbtiSeq = $("#mbtiOptionSelect option:selected").val();

	$.ajax({
		url: "/api/register/" + mbtiSeq,
		type: "POST",
		data: registerForm, // data에 바로 serialze한 데이터를 넣는다
		success: function(result) {
			alert(result['message']);
			
		}, error: function(error) {
			console.log(error);
		}
	})
}
function getMbtiList() {
	$.getJSON('/api/mbtiOptions', function(mbtiOptionList) {
		let mbtiOptionHtml = "";
		$.each(mbtiOptionList, function(index, mbtiOption) {
			mbtiOptionHtml += '<option id="' + mbtiOption["seq"] + '"';
			mbtiOptionHtml += ' name="' + mbtiOption["seq"] + '"';
			mbtiOptionHtml += ' value = "' + mbtiOption["seq"] + '">';
			mbtiOptionHtml += mbtiOption["name"] + '</option>';
			$("#mbtiOptionSelect").html(mbtiOptionHtml);
		})
	})
}
