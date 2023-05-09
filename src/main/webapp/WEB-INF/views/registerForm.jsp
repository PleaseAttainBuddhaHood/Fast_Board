<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="java.net.URLDecoder" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>회원가입 양식</title>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css">

    <style>
        *
        {
            box-sizing: border-box;
        }

        form
        {
            width: 400px;
            height: 600px;
            display: flex;
            flex-direction: column;
            align-items: center;
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            border: 1px solid rgb(89, 117, 196);
            border-radius: 10px;
        }

        .input-field
        {
            width: 300px;
            height: 40px;
            border: 1px solid rgb(89, 117, 196);
            border-radius: 5px;
            padding: 0 10px;
            margin-bottom: 10px;
        }

        label
        {
            width: 300px;
            height: 30px;
            margin-top: 4px;
        }

        button
        {
            background-color: rgb(89, 117, 196);
            color: white;
            width: 300px;
            height: 50px;
            font-size: 17px;
            border: none;
            border-radius: 5px;
            margin: 20px 0 30px 0;
        }

        .title
        {
            font-size: 50px;
            margin: 40px 0 30px 0;
        }

        .msg
        {
            height: 30px;
            text-align: center;
            font-size: 16px;
            color: red;
            margin-bottom: 20px;
        }

        .sns-chk
        {
            margin-top: 5px;
        }
    </style>

</head>
<body>

<form:form modelAttribute="user" action="/register/add" method="post">
<!-- this는 form 태그 자기 자신을 의미함 -->
<!--<form action="<c:url value="/register/save" />" method="post" onsubmit="return formCheck(this)"> -->
    <div class="title">회원가입</div>

    <div id="msg" class="msg"><form:errors path="id" /></div>
    <label>아이디</label>
    <input type="text" class="input-field" name="id" placeholder="4~12자리의 영어 대소문자와 숫자 조합" autofocus>

    <label>비밀번호</label>
    <input type="password" class="input-field" name="pwd" placeholder="8~12자리의 영어 대소문자와 숫자 조합">

    <label>이름</label>
    <input type="text" class="input-field" name="name" placeholder="홍길동">

    <label>이메일</label>
    <input type="text" class="input-field" name="email" placeholder="example@naver.com">

    <label>생일</label>
    <input type="text" class="input-field" name="birth" placeholder="2023/02/04">

    <label>취미</label>
    <input type="text" class="input-field" name="hobby" placeholder="노래 부르기">

    <div class="sns-chk">
        <label><input type="checkbox" name="sns" value="facebook">페이스북</label>
        <label><input type="checkbox" name="sns" value="kakaotalk">카카오톡</label>
        <label><input type="checkbox" name="sns" value="instagram">인스타그램</label>
    </div>

    <button type="submit">회원 가입</button>

</form:form>


<script>
    function formCheck(frm)
    {
        let msg = '';

        if(frm.id.value.length < 3)
        {
            setMessage('id의 길이는 3글자 이상이여야 합니다.', frm.id);

            return false;
        }

        if(frm.pwd.value.length < 3)
        {
            setMessage('pwd의 길이는 3글자 이상이여야 합니다.', frm.pwd);

            return false;
        }

        return true;
    }


    function setMessage(msg, element)
    {
        document.querySelector("#msg").innerHTML = `<i class="fa fa-exclamation-circle">${'${msg}'}</i>`;

        if(element)
        {
            element.select(); // 값을 잘못 입력했을 때,그 잘못 입력된 값을 선택되게 함
        }
    }
</script>
</body>
</html>