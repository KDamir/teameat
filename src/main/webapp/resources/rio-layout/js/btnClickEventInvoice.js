window.addEventListener("keypress", function(e){
                if(e.keyCode === 32 && document.activeElement !== 'text') {
                    e.preventDefault();
                    //alert('Prevent page from going back');
                    document.getElementById("invoiceProdForm:sumInput").focus();
                   
                    
                   
                }
            });
            
window.addEventListener("keypress", function(e){
            if( (e.keyCode === 115 || e.keyCode === 83) && document.activeElement !== 'text') {
                e.preventDefault();
                document.getElementById("invoiceProdForm:saveBtn").click();
                
                
                
            }
        });