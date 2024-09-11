package com.mercadoPAgo.controllerPay;

import com.mercadoPAgo.Entity.UserBuyer;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController

public class MercadoPagoController {

    @Value("${codigo.mercadoLibre}")
    private String mercadoLibreToken;

    @RequestMapping(value = "/api/mp", method = RequestMethod.POST)
    public String getList(@RequestBody UserBuyer userBuyer) {
        if (userBuyer == null){return "error jsons";}
        String title= userBuyer.getTitle();
        int quantity= userBuyer.getQuantity();
        int price= userBuyer.getUnit_price();

        try{
            MercadoPagoConfig.setAccessToken(mercadoLibreToken);
            //1-preferencia de venta
            PreferenceItemRequest itemRequest= PreferenceItemRequest.builder()
                    .title(title)
                    .quantity(quantity)
                    .unitPrice(new BigDecimal(price))
                    .currencyId("ARS")
                    .build();
            List<PreferenceItemRequest> items= new ArrayList<>();
            items.add(itemRequest);
            //2-preferencia de control de sucesos
            PreferenceBackUrlsRequest backUrls= PreferenceBackUrlsRequest.builder()
                    .success("http://youtube.com")//no se puede poner local host porque no funciona según la documentación
                    .pending("http://youtube.com")
                    .failure("http://youtube.com")
                    .build();

            //3-ensamble de preferencias (se crea una preferencia que contiene las otras preferencias creadas)
            PreferenceRequest preferenceRequest= PreferenceRequest.builder()
                    .items(items)
                    .backUrls(backUrls)
                    .build();

            // creo un objeto de tipo cliente para comunicarme con mp
            PreferenceClient client= new PreferenceClient();
            //creo una preferencia que será igual la respuesta que nuestro cliente creara a partir de la información que le enviamos
            Preference preference= client.create(preferenceRequest);

            //retornamos la preferencia a nuestro fronted
            return preference.getId();
        }catch (MPException | MPApiException e){return e.toString();}

    }

}
