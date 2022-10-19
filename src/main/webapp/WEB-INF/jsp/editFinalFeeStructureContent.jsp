

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page import="java.util.List" %>
<%@ page import="com.data.dashboard.model.FinalFeeStructure" %>
  
  <div class="container-xxl flex-grow-1 container-p-y">

              <!-- Basic Layout & Basic with Icons -->
              <div class="row">
                <!-- Basic Layout -->
                <div class="col-xxl">
                  <div class="card mb-4">
                    <div class="card-header d-flex align-items-center justify-content-between">
                      <h5 class="mb-0"></h5>
                      <small class="text-muted float-end"></small>
                    </div>
                    <div class="card-body">
                    <%
                    FinalFeeStructure finalFeeStructure = (FinalFeeStructure) request.getAttribute("finalFeeStructure");
                    if(finalFeeStructure!=null ){
                    %>
                      <form action="updatefinalFeeStructureData" method="post">
                       <input
                              type="hidden"
                              value="<%=finalFeeStructure.getId() %>"
                              name="id"
                            >
                             <input
                              type="hidden"
                              value="${authToken}"
                              name="hamaraKendra"
                            >  
                        <div class="row mb-3">
                          <label class="col-sm-2 col-form-label" for="basic-default-name">Gov Fee</label>
                          <div class="col-sm-10">
                            <input type="text" class="form-control" id="basic-default-name"  name="govFee" value="<%=finalFeeStructure.getGovFee()%>" placeholder="" />
                          </div>
                        </div>
                        <div class="row mb-3">
                          <label class="col-sm-2 col-form-label" for="basic-default-company">Service Charges</label>
                          <div class="col-sm-10">
                            <input
                              type="text"
                              class="form-control"
                              id="basic-default-company"
                              placeholder=""
                              value="<%=finalFeeStructure.getServiceCharges() %>"
                              name="serviceCharge"
                            />
                          </div>
                        </div>
                        <div class="row mb-3">
                          <label class="col-sm-2 col-form-label" for="basic-default-email">Total</label>
                          <div class="col-sm-10">
                            <div class="input-group input-group-merge">
                              <input
                                type="text"
                                id="basic-default-email"
                                class="form-control"
                                placeholder=""
                                aria-label=""
                                aria-describedby="basic-default-email2"
                                value="<%=finalFeeStructure.getTotal() %>"
                                name="totalFee"
                              />
                             
                            </div>
                            
                          </div>
                        </div>
                        
                         
                        
                         
                        
                        
                        
                          
                   
                        
                       
                        
                        <div class="row justify-content-end">
                          <div class="col-sm-10">
                            <button type="submit" class="btn btn-primary">Submit</button>
                          </div>
                        </div>
                          <div class="row justify-content-end">
                          <div class="col-sm-10">
                          	<% 
                          		String msg = (String) request.getAttribute("message");
                    		   if(msg!=null){
                          	%>
                          </div>
                         	<center> <h5 ><%=msg %></h5> </center>
                         
                          <%} %>
                        </div>
                      </form>
                      <%} %>
                    </div>
                  </div>
                </div>
                </div>