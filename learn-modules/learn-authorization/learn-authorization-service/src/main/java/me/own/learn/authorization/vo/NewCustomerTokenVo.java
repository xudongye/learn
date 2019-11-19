package me.own.learn.authorization.vo;

public class NewCustomerTokenVo extends CustomerTokenVo {
    private boolean newCustomer;

    public boolean isNewCustomer() {
        return newCustomer;
    }

    public void setNewCustomer(boolean newCustomer) {
        this.newCustomer = newCustomer;
    }
}
