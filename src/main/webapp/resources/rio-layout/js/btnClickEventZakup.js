window.addEventListener("keypress", function(e){
                if(e.keyCode === 32 && document.activeElement !== 'text') {
                    e.preventDefault();
                    //alert('Prevent page from going back');
                    
                    document.getElementById("purchaseProdForm:sumInputZak").focus();
                    
                   
                }
            });
            
window.addEventListener("keypress", function(e){
            if(e.keyCode === 115 && document.activeElement !== 'text') {
                e.preventDefault();
                
                document.getElementById("purchaseProdForm:saveBtnZak").click();
                
                
            }
        });