            <!-- Footer -->
            <footer class="content-footer footer bg-footer-theme">
              <div class="container-xxl d-flex flex-wrap justify-content-between py-2 flex-md-row flex-column">
                <div class="mb-2 mb-md-0">
            
                 
                  <a href="" target="_blank" class="footer-link fw-bolder"></a>
                </div>
             
              </div>
            </footer>
            <!-- / Footer -->

            <div class="content-backdrop fade"></div>
          </div>
          <!-- Content wrapper -->
        </div>
        <!-- / Layout page -->
      </div>

      <!-- Overlay -->
      <div class="layout-overlay layout-menu-toggle"></div>
    </div>
    <!-- / Layout wrapper -->

    
    <!-- Core JS -->
    <!-- build:js assets/vendor/js/core.js -->
    <script src="../assets/vendor/libs/jquery/jquery.js"></script>
    <script src="../assets/vendor/libs/popper/popper.js"></script>
    <script src="../assets/vendor/js/bootstrap.js"></script>
    <script src="../assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.js"></script>

    <script src="../assets/vendor/js/menu.js"></script>
    <!-- endbuild -->

    <!-- Vendors JS -->
    <script src="../assets/vendor/libs/apex-charts/apexcharts.js"></script>

    <!-- Main JS -->
    <script src="../assets/js/main.js"></script>

    <!-- Page JS -->
    <script src="../assets/js/dashboards-analytics.js"></script>

    <!-- Place this tag in your head or just before your close body tag. -->
    <script async defer src="https://buttons.github.io/buttons.js"></script>
    
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
    
   <script type="text/javascript">
   	function someFunction(token){
   		var authToken = "Bearer "+token;
   	  	var data;
   		$.ajax({
   			async: false,
   		    url: "listOfServices",
   		 	contentType : 'application/json',
   			dataType : 'json',
   		 	type: "GET",
   		 	beforeSend: function(xhr) {
   		    xhr.setRequestHeader("Authorization", authToken);
   		  	},
   			success: function(resp) { 
   			  data = resp; 
   			 callback.call(data);
   			}
   		});
   		if(data.msg ==='Success'){  	
   			window.location.href = 'loadListOfServices';
      	}
   		
   	}
   </script>
   <script type="text/javascript">
   	function redirectToListOfServices(){
   	 let element = document.getElementById(id);
   	 window.location.href = "loadListOfServices";
   	}
   </script>
   <script type="text/javascript">
   function mapEmployee(id,empName){
	   //alert(id + empName.value);
		var data;
   		$.ajax({
   			async: false,
   		    url: "mapEmployee",
   		 	contentType : 'application/json',
   			dataType : 'json',
   		 	type: "GET",
   		 	beforeSend: function(xhr) {
   		    xhr.setRequestHeader("id", id);
   		 	xhr.setRequestHeader("empName", empName.value);
   		  	},
   			success: function(resp) { 
   			  data = resp; 
   			 callback.call(data);
   			}
   		});
   		if(data.msg ==='Success'){  	
   			alert('Employee Successfully Assigned')
      	}
	   
   }
   
   
</script>
  </body>
</html>
