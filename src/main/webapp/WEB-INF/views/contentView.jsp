<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>		<!-- 대입문, 제어문 -->
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>		<!-- 서식 -->
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 	<!-- 함수 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="js/setting.js"></script>
<title>게시글 보기</title>
</head>
<body>
	<form action="update" method="post">
	  <table width="600" align="center" border="1" cellpadding="3", cellspacing="0">
		  <tr>
		    <th colspan="4">
	    	  게시글 보기
		    </th>
		  </tr>
		  <tr>
		    <td width="80" align="center">글번호</td>
		    <td width="320" align="center">이름</td>
		    <td width="120" align="center">작성일</td>
			<td width="80" align="center">조회수</td>
		  </tr>
		  <tr>
			<td align="center">
				${mvcboardVO.idx }
			</td>
			<td align="center">
				<c:set var="name" value="${fn:replace(mvcboardVO.name, '<', '&lt;')} "/>
	 			<c:set var="name" value="${fn:replace(name, '>', '&gt;')} "/>
				${name}
			</td>
			<td align="center">
				<!-- 오늘 입력된 글은 시간만 어제 이전에 입력된 글은 날짜만 출력 -->
	 			<c:if test="${date.year == mvcboardVO.writeDate.year && date.month == mvcboardVO.writeDate.month && date.date == mvcboardVO.writeDate.date }">
		 			<fmt:formatDate value="${mvcboardVO.writeDate}" pattern="a h:mm"/>
	 			</c:if>
	 			<c:if test="${date.year != mvcboardVO.writeDate.year || date.month != mvcboardVO.writeDate.month || date.date != mvcboardVO.writeDate.date }">
		 			<fmt:formatDate value="${mvcboardVO.writeDate}" pattern="yyyy.MM.dd(E)"/>
	 			</c:if>
			</td>
			<td align="center">
				${mvcboardVO.hit }
			</td>
		  </tr>
		  <tr>
			<td align="center">
				제목
			</td>
			<td colspan="3" align="center">
			  <input type="text" value="${mvcboardVO.subject}" name="subject" style="width: 96.5%">
			</td>
		  </tr>	
		  <tr>
			<td align="center">내용</td>
			<td colspan="3">
			  <textarea name="content" rows="10" style="width:98%; resize:none; ">${mvcboardVO.content }</textarea>
			</td>
		  </tr>	
		  <tr>
			<td colspan="4" align="center">
			  <!-- submit 버튼 클릭시에도 데이터를 넘겨주기 위해서ㅏ hidden type input테그 삽입!  -->
			  <input type="hidden" name="idx" value="${mvcboardVO.idx }">
			  <input type="hidden" name="currentPage" value="${currentPage}">
			  <input type="submit" value="수정하기" />
			  <input type="button" value="삭제하기" onclick="location.href='delete?idx=${mvcboardVO.idx}&currentPage=${currentPage}'"/>
			  <input type="button" value="답변하기" onclick="location.href='reply?idx=${mvcboardVO.idx}&currentPage=${currentPage}'"/>
			  <!--onclick="history.back() or .go(-1)" 는 데이터 안넘어가서 location.href 로 해야돼  -->
			  <input type="button" value="돌아가기" onclick="location.href='list?currentPage=${currentPage}'">
			</td>
		  </tr>
	  </table>
	</form>
</body> 
</html>















