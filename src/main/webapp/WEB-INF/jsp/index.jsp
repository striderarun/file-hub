<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="POST" enctype="multipart/form-data"
		action="${pageContext.request.contextPath}/file/upload">
		File to upload: <input type="file" name="file"><br /> 
		author: <input type="text" name="author"><br /> <br /> 
		domain: <input type="text" name="domain"><br /> <br /> 
		technology: <input type="text" name="technology"><br /> <br /> 
		projectName: <input type="text" name="projectName"><br /> <br /> 
		description: <input type="text" name="description"><br /> <br /> 
		
		<input type="submit" value="Upload"> Press here to upload the file!
	</form>
</body>
</html>
