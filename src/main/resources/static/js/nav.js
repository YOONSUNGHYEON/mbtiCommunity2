$(document).ready(function() {
	checkSession();
});

function checkSession() {
	$.getJSON('/api/session', function(sessionResult) {
		let a = "";
		if (sessionResult == true) {
			a += '<a href="/logout">Log out</a>';
		}
		else {
			a += '<a href="/login">Log In  |  </a>';
			a += '<a href="/register">Sign Up</a>';
		}
		$("#login-logout-register").append(a);

	})
}



