package org.oneclicktesting.shopping;

public interface Cart {
    int getCartProductQuantity(int id);
    int getCartProductPrice(int id);
    int getCartTotalPrice();
    void removeProductFromCard(int id);
    int getProductsCount();
}
