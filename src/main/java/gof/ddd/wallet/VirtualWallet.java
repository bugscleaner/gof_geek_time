package gof.ddd.wallet;

import java.math.BigDecimal;

/**
 * 
 * @since : 2022/3/10
 **/
public class VirtualWallet {
    private Long id;

    private Long createTime = System.currentTimeMillis();

    private BigDecimal balance = BigDecimal.ZERO;

    private boolean isAllowedOverdraft = true;

    private BigDecimal overdraftAmount = BigDecimal.ZERO;

    private BigDecimal frozenAmount = BigDecimal.ZERO;

    public void setAllowedOverdraft(boolean allowedOverdraft) {
        isAllowedOverdraft = allowedOverdraft;
    }

    public void setOverdraftAmount(BigDecimal overdraftAmount) {
        this.overdraftAmount = overdraftAmount;
    }

    public void setFrozenAmount(BigDecimal frozenAmount) {
        this.frozenAmount = frozenAmount;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public VirtualWallet(Long preAllocatedId) {
        this.id = preAllocatedId;
    }

    public BigDecimal balance() {
        return this.balance;
    }

    public BigDecimal getAvaliableBalance() {
        BigDecimal totalAvaliableBalance = this.balance.subtract(this.frozenAmount);
        if (isAllowedOverdraft){
            totalAvaliableBalance.add(this.overdraftAmount);
        }

        return totalAvaliableBalance;
    }

    public void debit(BigDecimal amount) {
        BigDecimal totalAvaliableBalance = this.getAvaliableBalance();
        if (totalAvaliableBalance.compareTo(amount) < 0){
            throw new InsufficientBalanceException();
        }

        this.balance.subtract(amount);
    }

    public void credit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0){
            throw new InvalidAmountException();
        }

        this.balance.add(amount);
    }

    public void freeze(BigDecimal amount) {

    }

    public void unfreeze(BigDecimal amount) {

    }

    public void increaseOverdraftAmount(BigDecimal amount) {

    }

    public void decreaseOverdraftAmount(BigDecimal amount) {

    }

    public void closeOverdraft() {


    }

    public void openOverdraft() {

    }
}
