/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var dialogBox = null;
function submitFormTest(id,url){
    
    var formData = $("#"+id).serialize();
    
//alert(formData);
console.log("Data: " + formData);
  // alert(formData);
    $.ajax({
       type : 'POST',
       url : url,
       data : formData,
        beforeSend: function() {
           $('#ajaxResponse').fadeIn(1500, function(){
               var urlLink = "../assets/images/loading_.gif ";
              
              // var html = urlLink;
              $("#formdata").html(formData);
              $("#ajaxResponse").html("<span style='margin-left:20% !important;'><img src='"+urlLink+"' /><span> ").animate("slow");
           });
        },
       success: function(response){
           console.log("Successful: " + JSON.stringify(response));
           //var resp = JSON.parse(response);
           //$("#ajaxResponse").html(response).animate(450,"easing");
           $('#ajaxResponse').fadeIn(1500, function(){
              $("#ajaxResponse").html(response).animate("slow");
           });
               //updateSubmitError(response);
       },
       error: function(xHr, status, error){
           console.log("NOT Successful: " + xHr.responseText);
           //processSubmitError(xHr.responseText);
       }
    });
}
function submitFormSales(id,url){
      var formData = $("#"+id).serialize();
    
//alert(formData);
console.log("Data: " + formData);
  // alert(formData);
    $.ajax({
       type : 'POST',
       url : url,
       data : formData,
        beforeSend: function() {
           $('#ajaxResponse').fadeIn(1500, function(){
               var urlLink = "../assets/images/loading_.gif ";
               document.getElementById("tableResponse").innerHTML = "";
              // var html = urlLink;
              $("#ajaxResponse").html("<span style='margin-left:20% !important;'><img src='"+urlLink+"' /><span> ").animate("slow");
              
           });
           startLoading();
        },
       success: function(response){
           console.log("Successful: " + JSON.stringify(response));
           //
            var outputArray = jQuery.parseJSON(response);
            var returnMessage = outputArray.returnMessage;
            if(returnMessage!=""){
                $('#ajaxResponse').fadeIn(1500, function(){
              $("#ajaxResponse").html(returnMessage).animate("slow");
           });
            }else {
            var dataObject = outputArray.data;
            var DataArray = [];
             document.getElementById("tableResponse").innerHTML = "";
            var r=0;
            jQuery.each( dataObject, function( key, value ) {
               // alert(key+" "+value);
                var tempArray = new Object;
            
              
                  
                   var queryResult = value.queryResult;
                   jQuery.each( queryResult, function( keyr, valuer ) {
                   if(keyr==0){
                      tempArray.prodImagePath = valuer;
                  }
                  if(keyr==1){
                    tempArray.prodName = valuer;
                  }
                  if(keyr==2){
                      tempArray.prodMeasurement = valuer;
                  }
                 
                  if(keyr==4){
                      tempArray.salesAmount = valuer; 
                  }
                  if(keyr==5){
                      tempArray.salesCount = valuer;
                  }
                  if(keyr==6){
                       tempArray.salesQty = valuer;
                  }
                  
//                  if(keyr==4){
//                      tempArray.prodMeasurement = valuer;
//                  }
//                  
                 
              });
              
               tempArray.startDate = value.startDate;
               tempArray.endDate = value.endDate;
         
          
          //alert(tempArray.startDate+" "+tempArray.endDate+" "+tempArray.salesAmount+" "+tempArray.salesCount+" "+tempArray.salesQty+" "+tempArray.prodName+" "+tempArray.prodMeasurement+" "+tempArray.prodImagePath)
               
                DataArray.push(tempArray);
                
//              var queryResult = jQuery.parseJSON(value.queryResult);
              
          // r++;
             });
           //alert(DataArray);
           
             var responseTable ='<div class="col-xs-12">\n\
                                  <h3 class="header smaller lighter blue">Average Sales Table</h3>\n\			\n\
                                  <div class="clearfix">				\n\
                                  <div class="pull-right tableTools-container"></div>		\n\
                                  </div>				\n\
                                  <div class="table-header">	Results for "Average Sales"\n\
                                  </div><div> \n\
                                  <table id="dynamic-table" class="table table-striped table-bordered table-hover"> \n\
                                  <thead>      \n\
                                  <tr>          \n\
                                  <th>Product Image</th> \n\
                                  <th>Product Name</th>\n\
                                  <th>Sales Amount</th>\n\
                                  <th>Sales Frequency</th>\n\
                                  <th>Sales Quantity</th>\n\
                                  <th><i class="ace-icon fa fa-clock-o bigger-110 hidden-480"></i> Start Date</th>\n\
                                  <th><i class="ace-icon fa fa-clock-o bigger-110 hidden-480"></i> End Date</th> \n\
                                  </tr>     \n\
                                  </thead> \n\
                                  <tbody>';
                                                                                           




                                
            jQuery.each(DataArray, function (key, value) {
             //alert(key+" "+value);
                    //var dataObj = DataArray[i];
//                     var dataObj = jQuery.parseJSON(ke);
                 responseTable += '<tr>';
                 var prodMeasurement = "";
                    jQuery.each( value, function( keys, data ) {
                       // alert(keys+" "+data);
                        if(data==null){
                            data = 0;
                        }
                        if(keys=="prodImagePath"){
                            responseTable +='<td><figure id="wrapper"><img src="../images/uploads/products/'+data+'" /></figure></td>'; 
                        }else if(keys=="prodMeasurement"){
                            prodMeasurement = data;
                        }
                        else if(keys=="salesQty"){
                            responseTable +='<td>'+data+" "+prodMeasurement+'</td>'; 
                        }
                        else{
                             responseTable +='<td>'+data+'</td>'; 
                         }
                        
                        
                    });
                    responseTable += '</tr>';
                })
                responseTable  += '</tbody></table></div></div>';
                
                document.getElementById("tableResponse").innerHTML = "";
                document.getElementById("tableResponse").innerHTML = responseTable;
                
               
			  var myTable = $('#dynamic-table').DataTable( {
					bAutoWidth: false,
					"aoColumns": [{ "bSortable": false },null,null, null,null, null, null
					  
					],
					"aaSorting": [],
					
				
			
			
					select: {
						style: 'multi'
					}
			    } );
                            
                            $.fn.dataTable.Buttons.defaults.dom.container.className = 'dt-buttons btn-overlap btn-group btn-overlap';
				
				new $.fn.dataTable.Buttons( myTable, {
					buttons: [
					  {
						"extend": "colvis",
						"text": "<i class='fa fa-search bigger-110 blue'></i> <span class='hidden'>Show/hide columns</span>",
						"className": "btn btn-white btn-primary btn-bold",
						columns: ':not(:first):not(:last)'
					  },
					  {
						"extend": "copy",
						"text": "<i class='fa fa-copy bigger-110 pink'></i> <span class='hidden'>Copy to clipboard</span>",
						"className": "btn btn-white btn-primary btn-bold"
					  },
					  {
						"extend": "csv",
						"text": "<i class='fa fa-database bigger-110 orange'></i> <span class='hidden'>Export to CSV</span>",
						"className": "btn btn-white btn-primary btn-bold"
					  },
					  {
						"extend": "excel",
						"text": "<i class='fa fa-file-excel-o bigger-110 green'></i> <span class='hidden'>Export to Excel</span>",
						"className": "btn btn-white btn-primary btn-bold"
					  },
					  {
						"extend": "pdf",
						"text": "<i class='fa fa-file-pdf-o bigger-110 red'></i> <span class='hidden'>Export to PDF</span>",
						"className": "btn btn-white btn-primary btn-bold"
					  },
					  {
						"extend": "print",
						"text": "<i class='fa fa-print bigger-110 grey'></i> <span class='hidden'>Print</span>",
						"className": "btn btn-white btn-primary btn-bold",
						autoPrint: false,
						message: 'This print was produced using the Print button for DataTables'
					  }		  
					]
				} );
				myTable.buttons().container().appendTo( $('.tableTools-container') );
				
				//style the message box
				var defaultCopyAction = myTable.button(1).action();
				myTable.button(1).action(function (e, dt, button, config) {
					defaultCopyAction(e, dt, button, config);
					$('.dt-button-info').addClass('gritter-item-wrapper gritter-info gritter-center white');
				});
				
				
				var defaultColvisAction = myTable.button(0).action();
				myTable.button(0).action(function (e, dt, button, config) {
					
					defaultColvisAction(e, dt, button, config);
					
					
					if($('.dt-button-collection > .dropdown-menu').length == 0) {
						$('.dt-button-collection')
						.wrapInner('<ul class="dropdown-menu dropdown-light dropdown-caret dropdown-caret" />')
						.find('a').attr('href', '#').wrap("<li />")
					}
					$('.dt-button-collection').appendTo('.tableTools-container .dt-buttons')
				});
			
				////
			
				setTimeout(function() {
					$($('.tableTools-container')).find('a.dt-button').each(function() {
						var div = $(this).find(' > div').first();
						if(div.length == 1) div.tooltip({container: 'body', title: div.parent().text()});
						else $(this).tooltip({container: 'body', title: $(this).text()});
					});
				}, 500);
				
				
				
				
				
				myTable.on( 'select', function ( e, dt, type, index ) {
					if ( type === 'row' ) {
						$( myTable.row( index ).node() ).find('input:checkbox').prop('checked', true);
					}
				} );
				myTable.on( 'deselect', function ( e, dt, type, index ) {
					if ( type === 'row' ) {
						$( myTable.row( index ).node() ).find('input:checkbox').prop('checked', false);
					}
				} );
			
			
			
			
				/////////////////////////////////
				//table checkboxes
				$('th input[type=checkbox], td input[type=checkbox]').prop('checked', false);
				
				//select/deselect all rows according to table header checkbox
				$('#dynamic-table > thead > tr > th input[type=checkbox], #dynamic-table_wrapper input[type=checkbox]').eq(0).on('click', function(){
					var th_checked = this.checked;//checkbox inside "TH" table header
					
					$('#dynamic-table').find('tbody > tr').each(function(){
						var row = this;
						if(th_checked) myTable.row(row).select();
						else  myTable.row(row).deselect();
					});
				});
				
				//select/deselect a row when the checkbox is checked/unchecked
				$('#dynamic-table').on('click', 'td input[type=checkbox]' , function(){
					var row = $(this).closest('tr').get(0);
					if(this.checked) myTable.row(row).deselect();
					else myTable.row(row).select();
				});
			
			
			
				$(document).on('click', '#dynamic-table .dropdown-toggle', function(e) {
					e.stopImmediatePropagation();
					e.stopPropagation();
					e.preventDefault();
				});
			
		
		$('#ajaxResponse').fadeIn(1500, function(){
              $("#ajaxResponse").html("<div class='alert alert-success alert-dismissable' style='width:100%; margin: 0 auto;'><i class='fa fa-check'></i>"
                    +"<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;</button><b>Alert!</b> Data Fetch  successfully...</div>").animate("slow");
           });
           stopLoading();
       }
               // alert(responseTable);
//           $('#ajaxResponse').fadeIn(1500, function(){
//              $("#ajaxResponse").html(response).animate("slow");
//           });
               //updateSubmitError(response);
       },
       error: function(xHr, status, error){
           $('#ajaxResponse').fadeIn(1500, function(){
              $("#ajaxResponse").html("<div class='alert alert-danger alert-dismissable' style='width:100%; margin: 0 auto;'><i class='fa fa-ban'></i>"
                    +"<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;</button><b>Alert!</b> Unable to fetch data. Error encountered...</div>").animate("slow");
           });
           console.log("NOT Successful: " + xHr.responseText);
           //processSubmitError(xHr.responseText);
       }
    });
    
}

function submitFormSalesFrequency(id,url){
      var formData = $("#"+id).serialize();
    
//alert(formData);
console.log("Data: " + formData);
  // alert(formData);
    $.ajax({
       type : 'POST',
       url : url,
       data : formData,
        beforeSend: function() {
           $('#ajaxResponse').fadeIn(1500, function(){
               var urlLink = "../assets/images/loading_.gif ";
               document.getElementById("tableResponse").innerHTML = "";
              // var html = urlLink;
              $("#ajaxResponse").html("<span style='margin-left:20% !important;'><img src='"+urlLink+"' /><span> ").animate("slow");
              
           });
          // startLoading();
        },
       success: function(response){
           console.log("Successful: " + JSON.stringify(response));
           //
            var outputArray = jQuery.parseJSON(response);
            var returnMessage = outputArray.returnMessage;
            if(returnMessage!=""){
                $('#ajaxResponse').fadeIn(1500, function(){
              $("#ajaxResponse").html(returnMessage).animate("slow");
           });
            }else {
            var dataObject = outputArray.data;
            var DataArray = [];
             document.getElementById("tableResponse").innerHTML = "";
            var r=0;
            jQuery.each( dataObject, function( key, value ) {
               // alert(key+" "+value);
               
            
              
                  
                   var queryResult = value.queryResult;
                   jQuery.each( queryResult, function( keyr, valuer ) {
                        var tempArray = new Object;
                       jQuery.each( valuer, function( k, v ){
                  if(k==9){
                      tempArray.prodImagePath = v;
                  }
                  if(k==1){
                    tempArray.prodName = v;
                  }
                                 
                  if(k==13){
                      if(v==null || v== ""){
                          v = 0;
                      }
                      tempArray.salesCount = v;
                  }
                       })
                   
                 
//                  if(keyr==4){
//                      tempArray.prodMeasurement = valuer;
//                  }
//                  
                 tempArray.startDate = value.startDate;
                 tempArray.endDate = value.endDate;
                 DataArray.push(tempArray);
              });
              
               
         
          
          //alert(tempArray.startDate+" "+tempArray.endDate+" "+tempArray.salesAmount+" "+tempArray.salesCount+" "+tempArray.salesQty+" "+tempArray.prodName+" "+tempArray.prodMeasurement+" "+tempArray.prodImagePath)
               
                
                
//              var queryResult = jQuery.parseJSON(value.queryResult);
              
          // r++;
             });
           //alert(DataArray);
           
             var responseTable ='<div class="col-xs-12">\n\
                                  <h3 class="header smaller lighter blue">Average Sales Table</h3>\n\			\n\
                                  <div class="clearfix">				\n\
                                  <div class="pull-right tableTools-container"></div>		\n\
                                  </div>				\n\
                                  <div class="table-header">	Results for "Average Sales"\n\
                                  </div><div> \n\
                                  <table id="dynamic-table" class="table table-striped table-bordered table-hover"> \n\
                                  <thead>      \n\
                                  <tr>          \n\
                                  <th>Product Image</th> \n\
                                  <th>Product Name</th>\n\
                                  <th>Sales Frequency</th>\n\
                                  <th><i class="ace-icon fa fa-clock-o bigger-110 hidden-480"></i> Start Date</th>\n\
                                  <th><i class="ace-icon fa fa-clock-o bigger-110 hidden-480"></i> End Date</th> \n\
                                  </tr>     \n\
                                  </thead> \n\
                                  <tbody>';
                                                                                           




                                
            jQuery.each(DataArray, function (key, value) {
             //alert(key+" "+value);
                    //var dataObj = DataArray[i];
//                     var dataObj = jQuery.parseJSON(ke);
                 responseTable += '<tr>';
                 
                    
                       // alert(keys+" "+data);
                       
                       
                            responseTable +='<td><figure id="wrapper"><img src="../images/uploads/products/'+value.prodImagePath+'" /></figure></td>'; 
                      
                         
                             responseTable +='<td>'+value.prodName+'</td>'; 
                             responseTable +='<td>'+value.salesCount+'</td>';
                             responseTable +='<td>'+value.startDate+'</td>';
                             responseTable +='<td>'+value.endDate+'</td>';
                         
                        
                        
                 
                    responseTable += '</tr>';
                })
                responseTable  += '</tbody></table></div></div>';
                
                document.getElementById("tableResponse").innerHTML = "";
                document.getElementById("tableResponse").innerHTML = responseTable;
                
               
			  var myTable = $('#dynamic-table').DataTable( {
					bAutoWidth: false,
					"aoColumns": [{ "bSortable": false },null,null, null,null
					  
					],
                                        "order":[[2,"desc"]],
					"aaSorting": [],
					
				
			
			
					select: {
						style: 'multi'
					}
			    } );
                            
                            $.fn.dataTable.Buttons.defaults.dom.container.className = 'dt-buttons btn-overlap btn-group btn-overlap';
				
				new $.fn.dataTable.Buttons( myTable, {
					buttons: [
					  {
						"extend": "colvis",
						"text": "<i class='fa fa-search bigger-110 blue'></i> <span class='hidden'>Show/hide columns</span>",
						"className": "btn btn-white btn-primary btn-bold",
						columns: ':not(:first):not(:last)'
					  },
					  {
						"extend": "copy",
						"text": "<i class='fa fa-copy bigger-110 pink'></i> <span class='hidden'>Copy to clipboard</span>",
						"className": "btn btn-white btn-primary btn-bold"
					  },
					  {
						"extend": "csv",
						"text": "<i class='fa fa-database bigger-110 orange'></i> <span class='hidden'>Export to CSV</span>",
						"className": "btn btn-white btn-primary btn-bold"
					  },
					  {
						"extend": "excel",
						"text": "<i class='fa fa-file-excel-o bigger-110 green'></i> <span class='hidden'>Export to Excel</span>",
						"className": "btn btn-white btn-primary btn-bold"
					  },
					  {
						"extend": "pdf",
						"text": "<i class='fa fa-file-pdf-o bigger-110 red'></i> <span class='hidden'>Export to PDF</span>",
						"className": "btn btn-white btn-primary btn-bold"
					  },
					  {
						"extend": "print",
						"text": "<i class='fa fa-print bigger-110 grey'></i> <span class='hidden'>Print</span>",
						"className": "btn btn-white btn-primary btn-bold",
						autoPrint: false,
						message: 'This print was produced using the Print button for DataTables'
					  }		  
					]
				} );
				myTable.buttons().container().appendTo( $('.tableTools-container') );
				
				//style the message box
				var defaultCopyAction = myTable.button(1).action();
				myTable.button(1).action(function (e, dt, button, config) {
					defaultCopyAction(e, dt, button, config);
					$('.dt-button-info').addClass('gritter-item-wrapper gritter-info gritter-center white');
				});
				
				
				var defaultColvisAction = myTable.button(0).action();
				myTable.button(0).action(function (e, dt, button, config) {
					
					defaultColvisAction(e, dt, button, config);
					
					
					if($('.dt-button-collection > .dropdown-menu').length == 0) {
						$('.dt-button-collection')
						.wrapInner('<ul class="dropdown-menu dropdown-light dropdown-caret dropdown-caret" />')
						.find('a').attr('href', '#').wrap("<li />")
					}
					$('.dt-button-collection').appendTo('.tableTools-container .dt-buttons')
				});
			
				////
			
				setTimeout(function() {
					$($('.tableTools-container')).find('a.dt-button').each(function() {
						var div = $(this).find(' > div').first();
						if(div.length == 1) div.tooltip({container: 'body', title: div.parent().text()});
						else $(this).tooltip({container: 'body', title: $(this).text()});
					});
				}, 500);
				
				
				
				
				
				myTable.on( 'select', function ( e, dt, type, index ) {
					if ( type === 'row' ) {
						$( myTable.row( index ).node() ).find('input:checkbox').prop('checked', true);
					}
				} );
				myTable.on( 'deselect', function ( e, dt, type, index ) {
					if ( type === 'row' ) {
						$( myTable.row( index ).node() ).find('input:checkbox').prop('checked', false);
					}
				} );
			
			
			
			
				/////////////////////////////////
				//table checkboxes
				$('th input[type=checkbox], td input[type=checkbox]').prop('checked', false);
				
				//select/deselect all rows according to table header checkbox
				$('#dynamic-table > thead > tr > th input[type=checkbox], #dynamic-table_wrapper input[type=checkbox]').eq(0).on('click', function(){
					var th_checked = this.checked;//checkbox inside "TH" table header
					
					$('#dynamic-table').find('tbody > tr').each(function(){
						var row = this;
						if(th_checked) myTable.row(row).select();
						else  myTable.row(row).deselect();
					});
				});
				
				//select/deselect a row when the checkbox is checked/unchecked
				$('#dynamic-table').on('click', 'td input[type=checkbox]' , function(){
					var row = $(this).closest('tr').get(0);
					if(this.checked) myTable.row(row).deselect();
					else myTable.row(row).select();
				});
			
			
			
				$(document).on('click', '#dynamic-table .dropdown-toggle', function(e) {
					e.stopImmediatePropagation();
					e.stopPropagation();
					e.preventDefault();
				});
			
		
		$('#ajaxResponse').fadeIn(1500, function(){
              $("#ajaxResponse").html("<div class='alert alert-success alert-dismissable' style='width:100%; margin: 0 auto;'><i class='fa fa-check'></i>"
                    +"<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;</button><b>Alert!</b> Data Fetch  successfully...</div>").animate("slow");
           });
           //stopLoading();
       }
               // alert(responseTable);
//           $('#ajaxResponse').fadeIn(1500, function(){
//              $("#ajaxResponse").html(response).animate("slow");
//           });
               //updateSubmitError(response);
       },
       error: function(xHr, status, error){
           $('#ajaxResponse').fadeIn(1500, function(){
              $("#ajaxResponse").html("<div class='alert alert-danger alert-dismissable' style='width:100%; margin: 0 auto;'><i class='fa fa-ban'></i>"
                    +"<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;</button><b>Alert!</b> Unable to fetch data. Error encountered...</div>").animate("slow");
           });
           console.log("NOT Successful: " + xHr.responseText);
           //processSubmitError(xHr.responseText);
       }
    });
    
}


function submitForm(id, url){
  
    var formData = $("#"+id).serialize();
    
//alert(formData);
console.log("Data: " + formData);
  // alert(formData);
    $.ajax({
       type : 'POST',
       url : url,
       data : formData,
        beforeSend: function() {
           $('#ajaxResponse').fadeIn(1500, function(){
               var urlLink = "../assets/images/loading_.gif ";
              
              // var html = urlLink;
              $("#ajaxResponse").html("<span style='margin-left:20% !important;'><img src='"+urlLink+"' /><span> ").animate("slow");
           });
        },
       success: function(response){
           console.log("Successful: " + JSON.stringify(response));
           //var resp = JSON.parse(response);
           //$("#ajaxResponse").html(response).animate(450,"easing");
           $('#ajaxResponse').fadeIn(1500, function(){
              $("#ajaxResponse").html(response).animate("slow");
           });
               //updateSubmitError(response);
       },
       error: function(xHr, status, error){
           console.log("NOT Successful: " + xHr.responseText);
           //processSubmitError(xHr.responseText);
       }
    });
}


function showDeleteModal(url, id){    
    var args = "deleteEntity('" + url + "','" + id + "')";
    $("#deleteModal #ok").attr("onclick", args);
    $('#deleteModal').modal();
}
function loadProductData(url,id){
    console.log("URL: "+ url+"id "+id);
    
      $.ajax({
       type : 'POST',
       url : url,
       data : {id:id,action:'productfetch'},
        beforeSend: function() {
           $('#ajaxResponse').fadeIn(1500, function(){ });
         
    $('#loadModal').modal();
        },
       success: function(response){
           console.log("Successful: " + JSON.stringify(response));
           //var resp = JSON.parse(response);
           //$("#ajaxResponse").html(response).animate(450,"easing");
            //$("#loadModal").modal("hide");
           var response = response;
           var productData = jQuery.parseJSON(response);
//           alert(response);
//           alert(productData);
             $("#selling_price").val(productData.prodSellingPrice);
             $("#discount").val(productData.discount);
             
             $("#loadModal").modal('hide');
               //updateSubmitError(response);
               
               $('span[id="prodMeasurement"]').html(productData.prodMeasurement);
               calculateTotalCost();
       },
       error: function(xHr, status, error){
           console.log("NOT Successful: " + xHr.responseText);
          $("#loadModal").modal('hide');
           //processSubmitError(xHr.responseText);
       }
    });
}

function calculateTotalCost(){
//    alert("hello");
//    alert($("#productList").val());
    if($("#productList").val()=== "" || $("#productList").val() === null){
        $('#ajaxResponse').fadeIn(1500, function(){
              $("#ajaxResponse").html("<div class='alert alert-danger alert-dismissable' style='width:100%; margin: 0 auto;'><i class='fa fa-ban'></i>"
                    +"<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;</button><b>Alert!</b> Please select a valid product</div>").animate("slow");
           });
    }else{
         $('#ajaxResponse').fadeIn(1500, function(){
              $("#ajaxResponse").html("")});
    var sellingCost = $("#selling_price").val();
    var discount = $("#discount").val();
    var quantity = $("#qty_sold").val();
    var totalCost = (sellingCost - discount)* quantity;
    $("#total_amount").val(totalCost);
    }
}

function calculateWeightedAverageValue(){
    var prod_qty = $("#prod_qty").val();
    var total_cost = $("#total_cost").val();
     if($("#productList").val()=== "" || $("#productList").val() === null){
        $('#ajaxResponse').fadeIn(1500, function(){
              $("#ajaxResponse").html("<div class='alert alert-danger alert-dismissable' style='width:100%; margin: 0 auto;'><i class='fa fa-ban'></i>"
                    +"<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;</button><b>Alert!</b> Please select a valid product</div>").animate("slow");
           });
    }else{
    //check if the total cost is empty
    if(total_cost!=0 && total_cost!=""){
    $("#cost_price").val(total_cost/prod_qty);
     }
 }
     
     
}
function deleteEntity(url, id){
  
    console.log("URL: " + url+" id "+id);
    
    $.ajax({
       type : 'POST',
       url : url,
       data : {id:id, action:'delete'},
       success: function(response){
           console.log("Delete successful");
          
           $('#deleteModal').modal('hide');
           $('#row'+id + ',#row'+id + ' td').addClass("deleting");

           $('#row'+id).fadeOut(1500, function(){
               $('#row'+id).remove();
           });
            $('#ajaxResponse').fadeIn(1500, function(){
              $("#ajaxResponse").html(response).animate("slow");
           });

       },
       error: function(){
           console.log("Delete NOT Successful");
       }
    });
}



	
//$('#datetimepicker').datetimepicker({
//dayOfWeekStart : 1,
//lang:'en',
//format:'j M Y H:i',
//disabledDates:['1986/01/08','1986/01/09','1986/01/10'],
//startDate:''	
//});
//$('#datetimepicker').datetimepicker({value:'',step:10});
	
   function startLoading()
{
     console.log("starting dialog box loader");
  dialogBox = $("#loader").dialog({
      modal : true,
      dialogClass: "no-close",
      resizable: false,
      draggable: false,
      height : 60,
      width : 150
  });
}

function stopLoading()
{
 console.log("closing dialog box loader");
 dialogBox.dialog('close');
 console.log("closed dialog box loader");
}

