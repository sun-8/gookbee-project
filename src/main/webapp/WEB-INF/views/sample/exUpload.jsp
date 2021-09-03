<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 업로드 처리 (여러개의 파일을 한꺼번에 업로드)</title>
</head>
<body>
	<form action="/sample/exUploadPost" method="post" enctype="multipart/form-data">
	<%-- enctype속성은 폼 데이터(form data)가 서버로 제출될 때 해당 데이터가 인코딩되는 방법을 명시한다. method=post 일 때만 사용 가능하다. --%>
	<%-- multipart/form-data는 모든 문자를 인코딩하지 않음을 명시한다. 이 방식은 <form> 요소가 파일이나 이미지를 서버로 전송할 때 주로 사용한다. --%>
		<div>
			<input type="file" name="file">
			<%-- name을 file로 했더니 log가 출력되지 않았다. files로 해야한다.
					파일이 여러개라서 files인 줄 알았는데 하나만 업로드 하려해도 files라고 해야한다. --%>
		</div>
		<div>
			<input type="file" name="files">
		</div>
		<div>
			<input type="file" name="files">
		</div>
		<div>
			<input type="file" name="files">
		</div>
		<div>
			<input type="file" name="files">
		</div>
		<div>
			<input type="submit">
		</div>
	</form>
</body>
</html>