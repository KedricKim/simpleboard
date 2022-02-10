<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<script th:inline="javascript">
    window.onload = function(){
        var msg = '${msg}';
        alert(msg);
        location.href = '/logout';
    };
</script>
</body>
</html>