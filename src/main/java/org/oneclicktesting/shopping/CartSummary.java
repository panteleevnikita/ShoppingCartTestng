package org.oneclicktesting.shopping;

public interface CartSummary {
    int getCartProductQuantity(int id);
    void removeProductFromCard(int id, int productCount);
    float getCartProductPrice(int id);
    float getCartTotalPrice();
    int getProductsCount();
    void incrementProduct(int id);
    void decrementProduct(int id);
}
