package org.oneclicktesting.shopping;

public interface CartSummary {
    int getCartProductQuantity(int id);
    void removeProductFromCard(int id);
    int getCartProductPrice(int id);
    int getCartTotalPrice();
    int getProductsCount();
    void incrementProduct();
    void decrementProduct();
}
