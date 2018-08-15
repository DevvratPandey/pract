<html>
<head>
<script type="text/javascript">
	function validate() {
		var s = document.getElementById("1").value;
	
		
		var r =/^[A-Z a-z]{4,15}$/;
		var p = r.test(s);
		
		if(p==true){
			alert("Success");
			return true
		}else{
			alert("fail");
			return false;
		}
	}
	function check(){
		var a = validate();
		if(a==false){
			alert("enter valid name")
		}
		
	}
</script>
</head>
<body>
	<h2>Hello World!</h2>
	<form action="login" method="post">

		Username:<input type="text" id="1" onblur="validate()"  name="username"><br>
		Password:<input type="text" id="2" name="password"><br>

		<button submit="button" name="login" onClick="check()">Login</button>


	</form>



</body>
</html>
