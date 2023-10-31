package it.cgmconsulting.rentalfilms.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class CustomerStoreResponse {
    private String storeName;
    private long totalCustomers;
}
