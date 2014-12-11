<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="row">

    <div class="col-md-1"></div>

    <div class="col-md-10">
        <p class="text-center">
            <img src="<c:out value="${team.crestUrl}"/>"
                 alt="..." class="img-thumbnail" style="height: 200px; width: 200px">
        </p>
        <p>
        <h2><b><c:out value="${team.name}"/></b></h2>
        <h4>Club Information</h4>
        <h5>
            <b>Nickname:</b>
            <c:out value="${clubDetails.clubNickname}"/><br/>
            <b>Founded: </b>
            <c:out value="${clubDetails.clubFounded}"/><br/>
            <b>Stadium: </b>
            <c:out value="${clubDetails.clubStadium}"/><br/>
            <b>Capacity: </b>
            <c:out value="${clubDetails.clubCapacity}"/><br/>
            <b>Website: </b>
            <a href="<c:out value="${clubDetails.clubWebsite}"/>" target="_blank">Official Club Website</a><br/>
            <b>Facebook: </b>
            <c:out value="${clubDetails.clubFacebook}"/><br/>
        </h5>
        </p>
    </div>

    <div class="col-md-1"></div>

</div>