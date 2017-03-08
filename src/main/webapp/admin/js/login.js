/**
 * Created by Administrator on 2017/3/7.
 */
$(function(){
	var name ="";
	var password ="";
	var projectName="/go";
	$("#login").click(function(){
		name = $("#name").val();
		password = $("#password").val();
		console.log("name="+name+",password="+hex_sha1(password));
		var _data ={"name":name,"password":hex_sha1(password)};
		$.ajax({
			type:"post",
			url:projectName+"/admin/login/login",
			data : _data,
			async: false,
			dataType : 'json',
			error : function(){
				alert('登陆异常，请重试');
			},
			success : function(data) {
				if(data.errcode==0){
					
				}else{
					alert(data.errmsg);
				}
			}
		});
	});
});	