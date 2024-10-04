package com.ar.gfstabile.orders.util;

import java.util.List;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;

import com.ar.gfstabile.orders.model.Person;
import com.ar.gfstabile.orders.model.Order;
import com.ar.gfstabile.orders.model.Product;

@Component
public class PrintInvoice {

    private static final String INVOICE_TEMPLATE = """
            Factura %s     %s
            ---------------
            Datos del cliente
            Nombre: %s
            Apellido: %s
            Documento: %s %s
            ---------------
            Productos
            Nombre | Precio | Cantidad | Subtotal
            %s\
            ---------------
            Total a pagar: $%.2f\
            """;
    private static final String PRODUCT_LINE_TEMPLATE = """
            %s | $%.2f | %d | $%.2f
            """;
    private static final String DATE_FORMAT = "dd/MM/yyyy HH:mm";
    private static final SimpleDateFormat DATE_FORMAT_FORMATTER = new SimpleDateFormat(DATE_FORMAT);

    public String print(Order order) {
        Person client = order.getClient();
        return String.format(INVOICE_TEMPLATE, order.getId(), DATE_FORMAT_FORMATTER.format(order.getCreationDate()),
                client.name(),
                client.surname(),
                client.document().type().name(), client.document().number(),
                this.buildProductLines(order.getProducts()), order.getTotalPrice());
    }

    private String buildProductLines(List<Product> products) {
        return products.stream()
                .map(product -> String.format(PRODUCT_LINE_TEMPLATE, product.getCode(),
                        product.getUnitPrice(), product.getQuantity(), product.getTotalPrice()))
                .reduce((a, b) -> a.concat(b)).orElse("no posee productos");
    }
}
