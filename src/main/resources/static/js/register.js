$(window).load(function() {
	getMbtiList();
});
function register() {
	let registerForm = {
		id: $("#id").val(),
		password: $("#password").val(),
		password2: $("#password2").val(),
		mbtiOption: $("#mbtiOptionSelect option:selected").val()
	};
	$.ajax({
		url: "api/register",
		type: "POST",
		dataType: "json",
		data: JSON.stringify(registerForm),
		contentType: "application/json",
		async: true,
		success: function(result) {
			if (result['code'] == '400') {
				alert(result['message']);
				location.href = '/register';
			}
			else {
				alert(result['message']);
				location.href = '/login';
			}
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
