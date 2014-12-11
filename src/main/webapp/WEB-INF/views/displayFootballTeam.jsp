<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
    <head>
        <title>My Football Team</title>

        <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
        <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.css" rel="stylesheet">

    </head>

    <body style="background: ${clubDetails.clubColour}">

        <div class="container" style="background: white">

            <div class="row">

                <div class="col-md-1"></div>

                <div class="col-md-10">
                    <!-- Register User Modal -->
                    <button type="button" class="btn btn-primary" data-toggle="modal"
                            data-target=".bs-example-modal-lg">Register User</button>
                </div>

                <div class="col-md-1"></div>

            </div>

            <jsp:include page="club/teamDetails.jsp"/>

            <jsp:include page="club/nextFixture.jsp"/>

            <jsp:include page="club/teamForm.jsp"/>

            <jsp:include page="club/leagueStanding.jsp"/>

        </div>

        <div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <jsp:include page="registerUser.jsp"/>
                </div>
            </div>
        </div>

    </body>

</html>