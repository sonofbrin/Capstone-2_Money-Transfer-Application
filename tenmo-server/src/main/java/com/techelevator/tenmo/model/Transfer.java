package com.techelevator.tenmo.model;

import javax.validation.constraints.NotNull;

public class Transfer {

    private Long transferId;
    @NotNull
    private int transferTypeId;
    @NotNull
    private int transferStatusId;
    @NotNull
    private Long accountFrom;
    @NotNull
    private Long accountTo;
    @NotNull
    private int transferAmount;

    public Transfer() {}

    public Long getTransferId() {
        return transferId;
    }

    public void setTransferId(Long transferId) {
        this.transferId = transferId;
    }

    public int getTransferTypeId() {
        return transferTypeId;
    }

    public void setTransferTypeId(int transferTypeId) {
        this.transferTypeId = transferTypeId;
    }

    public int getTransferStatusId() {
        return transferStatusId;
    }

    public void setTransferStatusId(int transferStatusId) {
        this.transferStatusId = transferStatusId;
    }

    public Long getAccountFromId() {
        return accountFrom;
    }

    public void setAccountFromId(Long accountFromId) {
        this.accountFrom = accountFromId;
    }

    public Long getAccountToId() {
        return accountTo;
    }

    public void setAccountToId(Long accountToId) {
        this.accountTo = accountToId;
    }

    public int getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(int transferAmount) {
        this.transferAmount = transferAmount;
    }
}

