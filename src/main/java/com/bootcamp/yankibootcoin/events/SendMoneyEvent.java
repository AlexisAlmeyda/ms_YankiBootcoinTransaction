package com.bootcamp.yankibootcoin.events;

import java.util.Date;
import lombok.Data;

@Data
public abstract class SendMoneyEvent<T> {

  private String id;
  private Date date;
  private PaymentMethod paymentMethod;
  private T data;
}
