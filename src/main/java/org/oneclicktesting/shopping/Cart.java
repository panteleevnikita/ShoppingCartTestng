package org.oneclicktesting.shopping;

public interface Cart {
    int getCartProductQuantity(int id);
    float getCartProductPrice(int id);
    float getCartTotalPrice();
    void removeProductFromCard(int id);
    int getProductsCount();
}
