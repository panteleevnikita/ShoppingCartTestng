package org.oneclicktesting.shopping;

public interface CartSummary {
    public int getCartProductQuantity(int id);
    public void removeProductFromCard(int id);
}
