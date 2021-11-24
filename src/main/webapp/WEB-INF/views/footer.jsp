<%@ page isELIgnored="false" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

			    </div>

					<ul>
						<li font color="#C0C0C0"><a href="team?add=true">Add new team</a></li>
						<li font color="#C0C0C0"><a href="player?add=true">Add new player</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>

</body>
 
<script type="text/javascript">
	function updateTeamInfo(teamId, comId, monId) {
		var commission = document.getElementById(comId).value;
		var money = document.getElementById(monId).value;
		$.ajax({
	 		url: 'team',
	  		method: 'put',
	  		data: "id=" + teamId + "&com=" + +commission + "&mon=" + +money,
	  		success: function(data){
	  					alert("Info updated successfully");
	  					window.location = '/team?id=' + teamId;
	  				}
		})
	}
	
	function updatePlayerInfo(playerId, lnId, fnId, dateId, ageId) {
		var lastname = document.getElementById(lnId).value;
		var firstname = document.getElementById(fnId).value;
		var careerdate = document.getElementById(dateId).value;
		var age = document.getElementById(ageId).value;
		$.ajax({
	 		url: 'player',
	  		method: 'put',
	  		data: "id="+playerId+"&lastName="+lastname+"&firstName="+firstname+"&careerStartDate="+careerdate+"&age="+ +age,
	  		success: function(data){
	  					alert("Info updated successfully");
	  					window.location = '/player?id=' + playerId;
	  				}
		})
	}
	
	function deleteTeamById(teamId) {
		$.ajax({
	 		url: 'team',
	  		method: 'delete',
	  		data: "id=" + teamId,
	  		success: function(data){
	  					alert("Team deleted successfully");
	  					window.location = '/team';
	  				}
		})
	}
	
	function deletePlayerById(playerId) {
		$.ajax({
	 		url: 'player',
	  		method: 'delete',
	  		data: "id=" + playerId,
	  		success: function(data){
	  					alert("Player deleted successfully");
	  					window.location = '/player';
	  				}
		})
	}
	
	$.ajaxSetup({
		error: function (jqXHR, exception) {
			if (jqXHR.status === 0) {
				alert('Not connect. Verify Network.');
			} else if (jqXHR.status == 404) {
				alert('Requested page not found.');
			} else if (jqXHR.status == 500) {
				alert("Something went wrong. ");
			} else if (exception === 'parsererror') {
				alert('Requested JSON parse failed.');
			} else if (exception === 'timeout') {
				alert('Time out error.');
			} else if (exception === 'abort') {
				alert('Ajax request aborted.');
			} else {
				alert('Uncaught Error. ' + jqXHR.responseText);
			}
		}
	});
	
</script>

</html>
