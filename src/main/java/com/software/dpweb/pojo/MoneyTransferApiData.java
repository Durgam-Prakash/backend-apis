package com.software.dpweb.pojo;

import lombok.Data;

@Data
public class MoneyTransferApiData {
	
	private String fromAccount;
	private String toAccount;
	private Double amount;

}
