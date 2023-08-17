package eComApiPojo;

import java.util.List;

public class CreateOrders {

    private List<CreateOrderDetails> orders;

    public List<CreateOrderDetails> getOrders() {
        return orders;
    }

    public void setOrders(List<CreateOrderDetails> orders) {
        this.orders = orders;
    }
}
