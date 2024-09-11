const mp= new MercadoPago('APP_USR-3bb555ee-4d85-4ae8-85e3-e058b14e30c6', {
    locale: "es-AR"
});

const MP= async () =>{
    try{
        miArticulo = {}
        miArticulo.title= "manzana";
        miArticulo.quantity= 1;
        miArticulo.unit_price= 100;

        const response = await fetch ('api/mp',{
            method: 'POST',
            headers:{
                'Accept': 'Application/json',
                'Content-type': 'Application/json'	
            },
            body: JSON.stringify(miArticulo)
        })

        const preference= await response.text()
        createCheckoutButton(preference)
        

    }catch(e){alert("Error: "+ e)}
}
const createCheckoutButton = (preferenceId_OK)=>{
    const bricksBuilder = mp.bricks();
    const generateButton = async () =>{
        if (window.checkoutButton) window.checkoutButton.unmount();
        bricksBuilder.create("wallet", "wallet_container", {
            initialization:{
                preferenceId: preferenceId_OK,
            },
        });
    }
    generateButton()
}