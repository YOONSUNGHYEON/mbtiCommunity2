

function login(){
	 $.ajax({
            url: "UserController.php?method=login",
            type: "POST",
            data: {
					username:$('#username').val(),
					password:$('#password').val()
			}, 
            success: function(result){
				if(result == true) {
					alert('로그인 성공!');
					history.back();
				}
				else {
					console.log(result);
					alert(result);
				}
            },
            error: function (request, status, error){        
                
            }
        }) 

}

