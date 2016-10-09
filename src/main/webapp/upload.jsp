<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
<title>Upload Multiple File Request Page</title>
</head>
<body>

	<form method="POST" action="upload" enctype="multipart/form-data">
		File1 to upload: <input type="file" name="file"><br />
		File2 to upload: <input type="file" name="file"><br />
		File3 to upload: <input type="file" name="file"><br />
		File4 to upload: <input type="file" name="file"><br />
		File5 to upload: <input type="file" name="file"><br />
		<input type="submit" value="Upload"> Press here to upload the file!
	</form>
	
</body>
</html>