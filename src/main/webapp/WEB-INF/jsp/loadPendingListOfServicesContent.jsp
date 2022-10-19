<%@ page import="java.util.List" %>
<%@ page import="com.data.dashboard.model.BookedServices" %>
  
  <div class="content-wrapper">
            <!-- Content -->

            <div class="container-xxl flex-grow-1 container-p-y">
  
   <div class="card">
                <h5 class="card-header">Request For Services</h5>
                <div class="table-responsive text-nowrap">
                  <table class="table">
                    <thead class="table-light">
                      <tr>
                        <th>Sr No.</th>
                        <th>Name</th>
                        <th>Requested Services</th>
                        <th>Email Id</th>
                        <th>Mobile No</th>
                        <th>Address</th>
                        <th>Date</th>
                          <th>Time</th>
                        <th>Status</th>
                        <th>Actions</th>
                      </tr>
                    </thead>
                    <tbody class="table-border-bottom-0">
                    <%
                    List<BookedServices> bookedServices= ( List<BookedServices>)request.getAttribute("listOfPendingBookedServices");
                   	if(bookedServices!=null && !bookedServices.isEmpty()){
                   		for(BookedServices bookedService:bookedServices){
                    %>
                    
                      <tr>
                        <td><i class="fab fa-angular fa-lg text-danger me-3"></i> <strong><%=bookedService.getId() %></strong></td>
                        <td><%=bookedService.getName() %></td>
                       <td><%=bookedService.getSelectedService() %></td>
                       <td><%=bookedService.getEmailId() %></td>
                       <td><%=bookedService.getMobileNo() %></td>
                       <td><%=bookedService.getAddress1() %></td>
                       <td><%=bookedService.getDate() %></td>
                        <td><%=bookedService.getTimeSlot() %></td>
                        <td><%=bookedService.getStatus() %></td>
                        <td>
                          <div class="dropdown">
                            <button type="button" class="btn p-0 dropdown-toggle hide-arrow" data-bs-toggle="dropdown">
                              <i class="bx bx-dots-vertical-rounded"></i>
                            </button>
                            <div class="dropdown-menu">
                              <a class="dropdown-item" href="editServiceStatus?id=<%=bookedService.getId() %>&hamaraKendra=${authToken}"
                                ><i class="bx bx-edit-alt me-1"></i> Edit</a
                              >
                               <a class="dropdown-item" href="viewServiceStatus?id=<%=bookedService.getId() %>&hamaraKendra=${authToken}"
                                ><i class="bx bx-edit-alt me-1"></i> View</a
                              >
                            </div>
                            
                           
                          </div>
                        </td>
                      </tr>
                      
                      <%} 
                      }%>
                     
                      
                      
                    </tbody>
                  </table>
                  <br>
                  <br>
                  <br>
                  <br>
                  <br>
                  <br>
                </div>
              </div>
             </div>
             </div>