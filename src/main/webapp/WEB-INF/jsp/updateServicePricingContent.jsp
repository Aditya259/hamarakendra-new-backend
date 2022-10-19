<%@ page import="java.util.List" %>
<%@ page import="com.data.dashboard.model.FinalFeeStructure" %>
  
  <div class="content-wrapper">
            <!-- Content -->

            <div class="container-xxl flex-grow-1 container-p-y">
  
   <div class="card">
                <h5 class="card-header">Fees Structure </h5>
                <div class="table-responsive text-nowrap">
                  <table class="table">
                    <thead class="table-light">
                      <tr>
                        <th>Sr No.</th>
                        <th>Gov Fee</th>
                        <th>Service Charges</th>
                        <th>Total</th>
                        <th>Service Name</th>
                        <th>URL Name</th>
                        <th>Actions</th>
                      </tr>
                    </thead>
                    <tbody class="table-border-bottom-0">
                    <%
                    List<FinalFeeStructure> finalFeeStructure= ( List<FinalFeeStructure>)request.getAttribute("finalFeeStructure");
                   	if(finalFeeStructure!=null && !finalFeeStructure.isEmpty()){
                   		for(FinalFeeStructure bookedService:finalFeeStructure){
                    %>
                    
                      <tr>
                        <td><i class="fab fa-angular fa-lg text-danger me-3"></i> <strong><%=bookedService.getId() %></strong></td>
                       <td><%=bookedService.getGovFee() %></td>
                       <td><%=bookedService.getServiceCharges() %></td>
                       <td><%=bookedService.getTotal() %></td>
                       <td><%=bookedService.getSeriveName() %></td>
                       <td><%=bookedService.getUrlName() %></td>
                        <td>
                          <div class="dropdown">
                            <button type="button" class="btn p-0 dropdown-toggle hide-arrow" data-bs-toggle="dropdown">
                              <i class="bx bx-dots-vertical-rounded"></i>
                            </button>
                            <div class="dropdown-menu">
                              <a class="dropdown-item" href="editFeeStructure?id=<%=bookedService.getId() %>&hamaraKendra=${authToken}"
                                ><i class="bx bx-edit-alt me-1"></i> Edit</a
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